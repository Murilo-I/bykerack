package br.gov.sp.cptm.bykerack.web.controller;

import br.gov.sp.cptm.bykerack.app.usecase.BicycleUseCase;
import br.gov.sp.cptm.bykerack.web.dto.BicycleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/{userId}/bicycle")
public class BicycleController {

    BicycleUseCase useCase;

    @Autowired
    public BicycleController(BicycleUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    ResponseEntity<List<BicycleDTO>> findAll(@PathVariable Long userId) {
        return ResponseEntity.ok(useCase.findAll(userId));
    }

    @PostMapping
    ResponseEntity<Void> save(@PathVariable Long userId, @RequestBody BicycleDTO request) {
        useCase.save(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("{bikeId}")
    ResponseEntity<Void> update(@PathVariable Long userId, @PathVariable Long bikeId, @RequestBody BicycleDTO request) {
        useCase.update(request, userId, bikeId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{bikeId}")
    ResponseEntity<Void> delete(@PathVariable Long userId, @PathVariable Long bikeId) {
        useCase.delete(userId, bikeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
