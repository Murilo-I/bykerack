package br.gov.sp.cptm.bykerack.data.respository;

import br.gov.sp.cptm.bykerack.data.model.RailwayLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RailwayLineRepository extends JpaRepository<RailwayLine, Integer> {
}
