package ma.fstf.ServeurGestionRessourcesMaterielles.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    String urlPattern = "/api/auth/**";
    RequestMatcher requestMatcher = new AntPathRequestMatcher(urlPattern);
    http
            .cors()
            .and()
            .csrf()
            .disable()
            .authorizeHttpRequests()
            .requestMatchers(requestMatcher)
            .permitAll()
            .anyRequest()
<<<<<<< HEAD
            .authenticated()
            //.permitAll()
=======
            //.authenticated()
            .permitAll()
>>>>>>> ba5a08b4626b2bd0f571799bf0501ea11f5e5891
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .logout()
<<<<<<< HEAD
            .logoutUrl("/api/auth/logout1")
=======
            .logoutUrl("/api/auth/logout")
>>>>>>> ba5a08b4626b2bd0f571799bf0501ea11f5e5891
            .addLogoutHandler(logoutHandler)
            .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
    ;
    return http.build();
  }
<<<<<<< HEAD
}
=======
}
>>>>>>> ba5a08b4626b2bd0f571799bf0501ea11f5e5891
