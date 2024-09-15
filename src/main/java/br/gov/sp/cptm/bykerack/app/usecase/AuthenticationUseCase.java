package br.gov.sp.cptm.bykerack.app.usecase;

import br.gov.sp.cptm.bykerack.infra.TokenService;
import br.gov.sp.cptm.bykerack.web.dto.AccessDTO;
import br.gov.sp.cptm.bykerack.web.dto.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUseCase {

    AuthenticationManager authManager;
    TokenService tokenService;

    @Autowired
    public AuthenticationUseCase(AuthenticationManager authManager, TokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    public TokenDTO authenticate(AccessDTO access) {
        var usernamePasswordToken =
                new UsernamePasswordAuthenticationToken(access.getEmail(), access.getPassword());

        return tokenService.generateToken(authManager.authenticate(usernamePasswordToken));
    }
}
