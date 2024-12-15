package br.gov.sp.cptm.bykerack.web.controller;

import br.gov.sp.cptm.bykerack.app.usecase.BikeRackUseCase;
import br.gov.sp.cptm.bykerack.data.model.BikeRack;
import br.gov.sp.cptm.bykerack.web.dto.BikeRackRequest;
import br.gov.sp.cptm.bykerack.web.dto.VacancyInfo;
import br.gov.sp.cptm.bykerack.web.dto.VacancyResponse;
import br.gov.sp.cptm.bykerack.web.service.BikeRackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacancy")
public class BikeRackController {

    BikeRackUseCase useCase;

    @Autowired
    public BikeRackController(BikeRackService useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'QR_READER')")
    ResponseEntity<VacancyResponse> saveVacancy(@RequestBody BikeRackRequest request) {
        return ResponseEntity.ok(useCase.saveVacancy(request));
    }

    @GetMapping
    ResponseEntity<List<BikeRack>> findAll() {
        return ResponseEntity.ok(useCase.findAll());
    }

    @GetMapping("{userId}")
    ResponseEntity<VacancyInfo> getVacancyInfo(@PathVariable Long userId) {
        return ResponseEntity.ok(useCase.getVacancyInfo(userId));
    }
}
