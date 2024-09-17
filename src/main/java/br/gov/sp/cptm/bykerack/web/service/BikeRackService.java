package br.gov.sp.cptm.bykerack.web.service;

import br.gov.sp.cptm.bykerack.app.usecase.BikeRackUseCase;
import br.gov.sp.cptm.bykerack.data.model.BikeRack;
import br.gov.sp.cptm.bykerack.data.model.ExitReason;
import br.gov.sp.cptm.bykerack.data.model.Vacancy;
import br.gov.sp.cptm.bykerack.data.respository.BikeRackRepository;
import br.gov.sp.cptm.bykerack.data.respository.UserRepository;
import br.gov.sp.cptm.bykerack.data.respository.VacancyRepository;
import br.gov.sp.cptm.bykerack.util.exception.BikeRackNotFoundException;
import br.gov.sp.cptm.bykerack.util.exception.NoVacanciesAvailableException;
import br.gov.sp.cptm.bykerack.util.exception.UserNotFoundException;
import br.gov.sp.cptm.bykerack.web.dto.BikeRackDTO;
import br.gov.sp.cptm.bykerack.web.dto.VacancyResponse;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class BikeRackService implements BikeRackUseCase {

    private static final String NEW_VACANCY = "New Vacancy Registered Successfully";
    private static final String RETRIEVED = "Bike Retrieved Successfully";

    BikeRackRepository bikeRackRepository;
    VacancyRepository vacancyRepository;
    UserRepository userRepository;

    @Setter
    Boolean isRetrieval;

    @Autowired
    public BikeRackService(BikeRackRepository bikeRackRepository,
                           VacancyRepository vacancyRepository,
                           UserRepository userRepository) {
        this.bikeRackRepository = bikeRackRepository;
        this.vacancyRepository = vacancyRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public VacancyResponse saveVacancy(BikeRackDTO request) {
        var bikeRack = bikeRackRepository.findById(request.getBikeRackId())
                .orElseThrow(BikeRackNotFoundException::new);
        var availableVacancies = bikeRack.getAvailableVacancies();

        if (availableVacancies.equals(0))
            throw new NoVacanciesAvailableException();

        var employee = userRepository.findByDocument(request.getEmployeeDocument())
                .orElseThrow(() -> new UserNotFoundException("Employee Not Found"));
        var user = userRepository.findByDocument(request.getUserDocument())
                .orElseThrow(UserNotFoundException::new);

        var vacancies = vacancyRepository.findByVacancyIdBikeRackAndVacancyIdUser(bikeRack, user);
        setIsRetrieval(false);

        vacancies.flatMap(vacancyList -> vacancyList.stream()
                .filter(vacancy -> Objects.isNull(vacancy.getDateExit()))
                .findAny()).ifPresent(retrieval -> {
            retrieval.setDateExit(LocalDateTime.now());
            retrieval.setExitReason(ExitReason.BIKE_RETRIEVAL);
            bikeRack.setAvailableVacancies(availableVacancies + 1);
            setIsRetrieval(true);
        });

        var message = RETRIEVED;

        if (vacancies.isEmpty() || !isRetrieval) {
            var vacancyId = new Vacancy.VacancyId(user, bikeRack);
            var newVacancy = new Vacancy(vacancyId, employee, LocalDateTime.now());
            bikeRack.setAvailableVacancies(availableVacancies - 1);
            vacancyRepository.save(newVacancy);
            message = NEW_VACANCY;
        }

        return new VacancyResponse(message, isRetrieval);
    }

    @Override
    public List<BikeRack> findAll() {
        return bikeRackRepository.findAll();
    }
}
