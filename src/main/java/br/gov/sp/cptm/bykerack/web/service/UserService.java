package br.gov.sp.cptm.bykerack.web.service;

import br.gov.sp.cptm.bykerack.app.usecase.AddressUseCase;
import br.gov.sp.cptm.bykerack.app.usecase.UserUseCase;
import br.gov.sp.cptm.bykerack.data.model.Access;
import br.gov.sp.cptm.bykerack.data.model.User;
import br.gov.sp.cptm.bykerack.data.respository.AccessRepository;
import br.gov.sp.cptm.bykerack.data.respository.DocumentTypeRepository;
import br.gov.sp.cptm.bykerack.data.respository.UserRepository;
import br.gov.sp.cptm.bykerack.util.exception.DocTypeNotFoundException;
import br.gov.sp.cptm.bykerack.util.exception.EMailAlreadyRegisteredException;
import br.gov.sp.cptm.bykerack.util.exception.UserNotFoundException;
import br.gov.sp.cptm.bykerack.util.mapper.Mapper;
import br.gov.sp.cptm.bykerack.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserUseCase {

    UserRepository userRepository;
    AccessRepository accessRepository;
    AddressUseCase addressUseCase;
    DocumentTypeRepository docTypeRepository;
    Mapper mapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       AccessRepository accessRepository,
                       AddressUseCase addressUseCase,
                       DocumentTypeRepository docTypeRepository,
                       Mapper mapper) {
        this.userRepository = userRepository;
        this.accessRepository = accessRepository;
        this.addressUseCase = addressUseCase;
        this.docTypeRepository = docTypeRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public UserDTO save(UserDTO request) {
        if (userRepository.findByAccess_Email(request.getEmail()).isPresent())
            throw new EMailAlreadyRegisteredException();

        User user = mapper.toEntity(request);
        user.setAccess(accessRepository.save(mapper.mapAccess(request)));
        user.setAddress(addressUseCase.save(mapper.mapAddress(request)));
        user.setDocumentType(docTypeRepository.findById(request.getDocType())
                .orElseThrow(DocTypeNotFoundException::new));

        userRepository.save(user);
        return mapper.toDTO(user);
    }

    @Override
    public UserDTO findById(Long userId) {
        return mapper.toDTO(userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new));
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Access mappedAccess = mapper.mapAccess(request);
        Access access = user.getAccess();
        access.setEmail(mappedAccess.getEmail());
        access.setPassword(mappedAccess.getPassword());
        user.setAddress(addressUseCase.updateAddress(user, request));
        return mapper.toDTO(user);
    }
}
