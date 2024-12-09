package br.gov.sp.cptm.bykerack.data.respository;

import br.gov.sp.cptm.bykerack.data.model.BikeRack;
import br.gov.sp.cptm.bykerack.data.model.User;
import br.gov.sp.cptm.bykerack.data.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VacancyRepository extends JpaRepository<Vacancy, Vacancy.VacancyId> {

    Optional<List<Vacancy>> findByVacancyIdBikeRackAndVacancyIdUser(BikeRack bikeRack, User user);

    Optional<Vacancy> findByVacancyIdUserAndExitDateIsNull(User user);
}
