package by.it_academy.user.spring_config;

import by.it_academy.user.controllers.filters.JwtFilter;
import by.it_academy.user.services.JpaUserDetailsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtFilter filter;

    private final JpaUserDetailsManager manager;

    private final DaoAuthenticationProvider provider;

    //number2
    public SecurityConfig(JwtFilter filter, JpaUserDetailsManager manager, DaoAuthenticationProvider provider) {
        this.filter = filter;
        this.manager = manager;
        this.provider = provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }

    //number3
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF
        http = http.cors().and().csrf().disable();

        // Set session management to stateless
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set unauthorized requests exception handler
        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                )
                .and();

        // Set permissions on endpoints
        http.authorizeRequests()
                // Our public endpoints
                .antMatchers("/api/v1/users/registration").anonymous()
                .antMatchers("/api/v1/users/login").anonymous()
                // Our private endpoints
                .anyRequest().hasAnyAuthority("USER");

        // Add JWT token filter
        http.addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter.class
        );
    }
}