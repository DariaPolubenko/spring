package ru.daria.security_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.daria.filters.JwtFilter;

// настраивает springSecurity
@Configuration
@EnableWebSecurity
public class Config {
    private JwtFilter jwtFilter;

    public Config(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/security/").permitAll();
                    auth.requestMatchers("/person/**").permitAll();
                    auth.requestMatchers("/**").authenticated();
                })
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(element -> {
                    element.requestMatchers("/api/security/**").permitAll();
                    element.requestMatchers("/person/**").permitAll();
                    element.requestMatchers("/**").authenticated();
                }).addFilterBefore(jwtFilter, JwtFilter.class)
                .build();
     }

        // permitAll - запрос от пользователя доступен всем
        // все запросы будут обрабатыаться securityFilterChain
        // ** - означает "любой запрос"
        // csrf - зашита от межсайтовой подделки запросов через postman,
        // CORS - список доменов, которым сервер разрешает обращаться к себе

        // прочитать про cors и csfr!!
     */

    // пароли в БД должны храниться в зашифрованном виде!
    @Bean
    public PasswordEncoder passwordEncoder() { //базовая шифровка паролей, которая позволяет шифровать пароль по определенному алгоритму BCryptPasswordEncoder
        return new BCryptPasswordEncoder();
    }
}
