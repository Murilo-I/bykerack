package br.gov.sp.cptm.bykerack.web.service;

import br.gov.sp.cptm.bykerack.data.model.User;
import br.gov.sp.cptm.bykerack.data.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    UserRepository repository;

    @Autowired
    public AuthenticationService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByAccess_Email(username);

        if (user.isPresent()) return user.get();

        throw new UsernameNotFoundException("Usuário ou senha inválidos");
    }
}
