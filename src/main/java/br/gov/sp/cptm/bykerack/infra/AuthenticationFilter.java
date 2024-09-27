package br.gov.sp.cptm.bykerack.infra;

import br.gov.sp.cptm.bykerack.data.respository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static br.gov.sp.cptm.bykerack.infra.TokenService.BEARER;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";

    TokenService tokenService;
    UserRepository repository;

    @Autowired
    public AuthenticationFilter(TokenService tokenService, UserRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = getToken(request);

        if (tokenService.isValidToken(token)) authenticate(request, token);

        filterChain.doFilter(request, response);
    }

    private void authenticate(HttpServletRequest request, String token) {
        Long userIdOnToken = tokenService.getSubject(token);
        Long userIdOnPath = extractUserIdFromPath(request.getRequestURI());

        if (userIdOnPath == null || userIdOnPath.equals(userIdOnToken)) {
            repository.findById(userIdOnToken).ifPresent(user -> {
                var authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            });
        }
    }

    private Long extractUserIdFromPath(String path) {
        try {
            String[] paths = path.split("/");
            return Long.parseLong(paths[paths.length - 1]);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION);

        if (token == null || !token.startsWith(BEARER))
            return null;

        return token.substring(7);
    }
}
