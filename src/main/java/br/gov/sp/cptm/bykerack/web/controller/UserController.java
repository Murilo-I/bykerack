package br.gov.sp.cptm.bykerack.web.controller;

import br.gov.sp.cptm.bykerack.web.dto.UserDTO;
import br.gov.sp.cptm.bykerack.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping
    ResponseEntity<UserDTO> save(@RequestBody UserDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @GetMapping("{userId}")
    ResponseEntity<UserDTO> findById(@PathVariable Long userId) {
        return ResponseEntity.ok(service.findById(userId));
    }

    @PatchMapping("{userId}")
    ResponseEntity<UserDTO> update(@RequestBody UserDTO request, @PathVariable Long userId) {
        return ResponseEntity.ok(service.update(request, userId));
    }
}
