package br.gov.sp.cptm.bykerack.data.respository;

import br.gov.sp.cptm.bykerack.data.model.UserAccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccessRepository extends JpaRepository<UserAccess, Long> {
}
