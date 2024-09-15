package br.gov.sp.cptm.bykerack.app.usecase;

import br.gov.sp.cptm.bykerack.web.dto.UserDTO;

public interface UserUseCase {

    UserDTO save(UserDTO request);

    UserDTO findById(Long userId);

    UserDTO update(UserDTO request, Long userId);
}
