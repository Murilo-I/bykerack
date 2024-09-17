package br.gov.sp.cptm.bykerack.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] IGNORING_MATCHERS = {"/**.html", "/v2/api-docs", "/webjars/**",
            "/configuration/**", "/swagger-resources/**", "/h2", "/h2/**"};

    @Value("${cptm.cors.allowed-origin}")
    String frontendOrigin;
    AuthenticationFilter authenticationFilter;

    @Autowired
    public SecurityConfig(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(customizer ->
                customizer.requestMatchers(HttpMethod.POST, "/user").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                        .requestMatchers(HttpMethod.GET, "/doc-type").permitAll()
                        .requestMatchers(HttpMethod.GET, "/bikerack").permitAll()
                        .requestMatchers(HttpMethod.GET, "/railway-line").permitAll()
                        .anyRequest().authenticated());

        http.sessionManagement(customizer ->
                customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.csrf(AbstractHttpConfigurer::disable); // TODO -> implements csrf
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.cors(customizer -> {
            CorsConfigurationSource configurationSource = request -> {
                var corsConfiguration = new CorsConfiguration();
                corsConfiguration.addAllowedOrigin(frontendOrigin);
                corsConfiguration.addAllowedMethod(HttpMethod.GET);
                corsConfiguration.addAllowedMethod(HttpMethod.POST);
                corsConfiguration.addAllowedMethod(HttpMethod.PATCH);
                corsConfiguration.addAllowedHeader("Content-Type");
                corsConfiguration.addAllowedHeader("Authorization");
                return corsConfiguration;
            };
            customizer.configurationSource(configurationSource);
        });

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(IGNORING_MATCHERS);
    }
}
