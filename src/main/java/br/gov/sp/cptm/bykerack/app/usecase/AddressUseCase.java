package br.gov.sp.cptm.bykerack.app.usecase;

import br.gov.sp.cptm.bykerack.data.model.Address;
import br.gov.sp.cptm.bykerack.data.model.User;
import br.gov.sp.cptm.bykerack.web.dto.UserDTO;

public interface AddressUseCase {

    Address save(Address address);

    Address updateAddress(User user, UserDTO request);
}
