package br.gov.sp.cptm.bykerack.data.respository;

import br.gov.sp.cptm.bykerack.data.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
