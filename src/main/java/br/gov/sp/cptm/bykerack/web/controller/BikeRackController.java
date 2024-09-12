package br.gov.sp.cptm.bykerack.web.controller;

import br.gov.sp.cptm.bykerack.data.model.BikeRack;
import br.gov.sp.cptm.bykerack.data.respository.BikeRackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bikerack")
public class BikeRackController {

    @Autowired
    BikeRackRepository repository;

    @GetMapping
    ResponseEntity<List<BikeRack>> findAll() { return ResponseEntity.ok(repository.findAll());}
}
