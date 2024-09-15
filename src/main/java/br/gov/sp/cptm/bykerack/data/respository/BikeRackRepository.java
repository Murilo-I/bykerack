package br.gov.sp.cptm.bykerack.data.respository;

import br.gov.sp.cptm.bykerack.data.model.BikeRack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRackRepository extends JpaRepository<BikeRack, Integer> {
}