package fact.it.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
        return serverHttpSecurity
                // CSRF uitschakelen als je geen CSRF nodig hebt
                .csrf(ServerHttpSecurity.CsrfSpec::disable)

                // Configureer de toegang tot routes
                .authorizeExchange(exchange -> exchange
                        // Maak de "GET /api/cars/all" route openbaar
                        .pathMatchers("/api/cars/all").permitAll()
                        // Maak de routes voor CarService, CustomerService en CarHireService beveiligd (vereist authenticatie)
                        .pathMatchers("/api/cars/**", "/api/customers/**", "/api/carhires/**").authenticated()
                        // Alle andere routes moeten ook geauthenticeerd zijn
                        .anyExchange().authenticated()
                )
                // Configureer de JWT-validatie voor de OAuth2-resource-server
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults()))  // Gebruik de standaardinstellingen voor JWT-validatie
                .build();
    }
}