package br.gov.sp.cptm.bykerack.web.service;

import br.gov.sp.cptm.bykerack.app.usecase.BikeRackUseCase;
import br.gov.sp.cptm.bykerack.data.model.*;
import br.gov.sp.cptm.bykerack.data.respository.BikeRackRepository;
import br.gov.sp.cptm.bykerack.data.respository.UserRepository;
import br.gov.sp.cptm.bykerack.data.respository.VacancyRepository;
import br.gov.sp.cptm.bykerack.util.exception.BikeRackNotFoundException;
import br.gov.sp.cptm.bykerack.util.exception.NoVacanciesAvailableException;
import br.gov.sp.cptm.bykerack.util.exception.UserNotFoundException;
import br.gov.sp.cptm.bykerack.web.dto.BikeRackRequest;
import br.gov.sp.cptm.bykerack.web.dto.BikeRackResponse;
import br.gov.sp.cptm.bykerack.web.dto.VacancyInfo;
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
    private static final User SELF_ATTENDANCE_QR_READER = null;

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
    public VacancyResponse saveVacancy(BikeRackRequest request) {
        var bikeRack = bikeRackRepository.findById(request.getBikeRackId())
                .orElseThrow(BikeRackNotFoundException::new);
        var availableVacancies = bikeRack.getAvailableVacancies();

        if (availableVacancies.equals(0))
            throw new NoVacanciesAvailableException();

        var employee = Objects.isNull(request.getEmployeeDocument())
                ? SELF_ATTENDANCE_QR_READER
                : userRepository.findByDocumentAndRole(request.getEmployeeDocument(),
                        Role.EMPLOYEE)
                .orElseThrow(() -> new UserNotFoundException("Employee Not Found"));
        var user = userRepository.findByDocumentAndRole(request.getUserDocument(),
                        Role.USER)
                .orElseThrow(UserNotFoundException::new);

        var vacancies = vacancyRepository.findByVacancyIdBikeRackAndVacancyIdUser(bikeRack, user);
        setIsRetrieval(false);

        vacancies.flatMap(vacancyList -> vacancyList.stream()
                .filter(vacancy -> Objects.isNull(vacancy.getExitDate()))
                .findAny()).ifPresent(retrieval -> {
            retrieval.setExitDate(LocalDateTime.now());
            retrieval.setExitReason(ExitReason.BIKE_RETRIEVAL);
            bikeRack.setAvailableVacancies(availableVacancies + 1);
            setIsRetrieval(true);
        });

        var message = RETRIEVED;

        if (vacancies.isEmpty() || Boolean.FALSE.equals(isRetrieval)) {
            var vacancyId = new Vacancy.VacancyId(LocalDateTime.now(), user, bikeRack);
            var newVacancy = new Vacancy(vacancyId, employee);
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

    @Override
    public VacancyInfo getVacancyInfo(Long userId) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        var vacancy = vacancyRepository.findByVacancyIdUserAndExitDateIsNull(user);

        if (vacancy.isPresent()) {
            var get = vacancy.get();
            var bikeRack = get.getVacancyId().getBikeRack();
            return VacancyInfo.builder()
                    .entryDate(get.getVacancyId().getEntryDate())
                    .attendant(get.getEmployee().getName())
                    .bikeRack(new BikeRackResponse(bikeRack.getName(),
                            bikeRack.getRailwayLine().getName()))
                    .build();
        }

        return VacancyInfo.builder().build();
    }
}
