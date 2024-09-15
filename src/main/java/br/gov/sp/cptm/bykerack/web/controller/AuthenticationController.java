package br.gov.sp.cptm.bykerack.web.controller;

import br.gov.sp.cptm.bykerack.app.usecase.AuthenticationUseCase;
import br.gov.sp.cptm.bykerack.web.dto.AccessDTO;
import br.gov.sp.cptm.bykerack.web.dto.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    AuthenticationUseCase authUseCase;

    @Autowired
    public AuthenticationController(AuthenticationUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping
    ResponseEntity<TokenDTO> authenticate(@RequestBody AccessDTO access) {
        return ResponseEntity.ok().body(authUseCase.authenticate(access));
    }
}
