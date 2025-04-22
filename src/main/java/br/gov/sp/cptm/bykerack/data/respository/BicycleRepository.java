package br.gov.sp.cptm.bykerack.data.respository;

import br.gov.sp.cptm.bykerack.data.model.Bicycle;
import br.gov.sp.cptm.bykerack.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BicycleRepository extends JpaRepository<Bicycle, Long> {

    List<Bicycle> findByUser(User user);
}
