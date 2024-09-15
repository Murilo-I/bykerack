package br.gov.sp.cptm.bykerack.web.controller;

import br.gov.sp.cptm.bykerack.data.model.DocumentType;
import br.gov.sp.cptm.bykerack.data.respository.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doc-type")
public class DocTypeController {

    DocumentTypeRepository repository;

    @Autowired
    public DocTypeController(DocumentTypeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    ResponseEntity<List<DocumentType>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }
}
