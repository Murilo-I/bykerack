package br.gov.sp.cptm.bykerack.web.service;

import br.gov.sp.cptm.bykerack.app.usecase.UserUseCase;
import br.gov.sp.cptm.bykerack.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserUseCase useCase;

    public UserDTO save(UserDTO request) {
        return useCase.save(request);
    }

    public UserDTO findById(Long userId) {
        return useCase.findById(userId);
    }

    public UserDTO update(UserDTO request, Long userId) {
        return useCase.update(request, userId);
    }
}
