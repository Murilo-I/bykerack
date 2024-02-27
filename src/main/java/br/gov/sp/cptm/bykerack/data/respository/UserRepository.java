package br.gov.sp.cptm.bykerack.data.respository;

import br.gov.sp.cptm.bykerack.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAccess_Email(String email);
}
