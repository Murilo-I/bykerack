package br.gov.sp.cptm.bykerack.app.usecase;

import br.gov.sp.cptm.bykerack.data.model.Access;
import br.gov.sp.cptm.bykerack.data.model.Address;
import br.gov.sp.cptm.bykerack.data.model.User;
import br.gov.sp.cptm.bykerack.data.respository.AccessRepository;
import br.gov.sp.cptm.bykerack.data.respository.AddressRepository;
import br.gov.sp.cptm.bykerack.data.respository.DocumentTypeRepository;
import br.gov.sp.cptm.bykerack.data.respository.UserRepository;
import br.gov.sp.cptm.bykerack.util.exception.DocTypeNotFoundException;
import br.gov.sp.cptm.bykerack.util.exception.EMailAlreadyRegisteredException;
import br.gov.sp.cptm.bykerack.util.exception.UserNotFoundException;
import br.gov.sp.cptm.bykerack.util.mapper.UserMapper;
import br.gov.sp.cptm.bykerack.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserUseCase {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccessRepository accessRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DocumentTypeRepository docTypeRepository;
    @Autowired
    UserMapper mapper;

    @Transactional
    public UserDTO save(UserDTO request) {
        if (userRepository.findByAccess_Email(request.getEmail()).isPresent())
            throw new EMailAlreadyRegisteredException();

        User user = mapper.toEntity(request);
        user.setAccess(accessRepository.save(mapper.mapAccess(request)));
        user.setAddress(addressRepository.save(mapper.mapAddress(request)));
        user.setDocumentType(docTypeRepository.findById(request.getDocType())
                .orElseThrow(DocTypeNotFoundException::new));

        userRepository.save(user);
        return mapper.toDTO(user);
    }

    public UserDTO findById(Long userId) {
        return mapper.toDTO(userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new));
    }

    @Transactional
    public UserDTO update(UserDTO request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Access mappedAccess = mapper.mapAccess(request);
        Access access = user.getAccess();
        access.setEmail(mappedAccess.getEmail());
        access.setPassword(mappedAccess.getPassword());
        user.setAddress(updateAddress(user, request));
        return mapper.toDTO(user);
    }

    @Transactional
    private Address updateAddress(User user, UserDTO request) {
        Address mappedAddress = mapper.mapAddress(request);
        Address address = user.getAddress();
        address.setAddress(mappedAddress.getAddress());
        address.setNumber(mappedAddress.getNumber());
        address.setNeighborhood(mappedAddress.getNeighborhood());
        address.setCity(mappedAddress.getCity());
        address.setState(mappedAddress.getState());
        address.setPostalCode(mappedAddress.getPostalCode());
        address.setComplement(mappedAddress.getComplement());
        return address;
    }
}
