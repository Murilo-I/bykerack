package br.gov.sp.cptm.bykerack.web.controller;

import br.gov.sp.cptm.bykerack.data.model.BikeRack;
import br.gov.sp.cptm.bykerack.data.model.RailwayLine;
import br.gov.sp.cptm.bykerack.data.respository.BikeRackRepository;
import br.gov.sp.cptm.bykerack.data.respository.RailwayLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/railway-line")
public class RailwayLineController {

    @Autowired
    RailwayLineRepository repository;

    @GetMapping
    ResponseEntity<List<RailwayLine>> findAll() { return ResponseEntity.ok(repository.findAll());}
}
