package com.main.online_clothing_store.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.main.online_clothing_store.services.UserSecurityService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    AuthenticationManager authenticationManager;

    @Autowired
    private UserSecurityService userSecurityService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                    .requestMatchers("/admin/**").hasAuthority("ADMIN")
                    .requestMatchers("/user/**").hasAuthority("USER")
                    .requestMatchers("/**").permitAll()
            )
            .userDetailsService(userSecurityService)
            .authenticationProvider(authenticationProvider())
            .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/")
                .permitAll()
            .and()
            .logout()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll()
            .and()
            .rememberMe()
                .key("UniqueAndSecret")
                .tokenValiditySeconds(86400)
            .and()
            .csrf()
                .disable();
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userSecurityService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
