package com.adventistas.apimipsmais.config;

import com.adventistas.apimipsmais.config.JwtFilter;
import com.adventistas.apimipsmais.service.CustomUserDetailsService;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(JwtFilter jwtFilter, CustomUserDetailsService userDetailsService) {
        this.jwtFilter = jwtFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                        // 🔑 ROTAS DE AUTENTICAÇÃO LIBERADAS
                        .requestMatchers("/auth/**").permitAll()

                        // 🔓 ROTAS PÚBLICAS - APENAS GET PERMITIDO
                        .requestMatchers(HttpMethod.GET, "/api/publico/**").permitAll()

                        // 🔒 BLOQUEIA POST/PUT/DELETE mesmo em /api/publico/**
                        .requestMatchers(HttpMethod.POST, "/api/publico/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/publico/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/publico/**").authenticated()

                        // 🔒 TODAS AS ROTAS PRIVATE PRECISAM DE AUTENTICAÇÃO
                        .requestMatchers("/api/private/**").authenticated()

                        // 🔒 TODO O RESTANTE TAMBÉM É PROTEGIDO
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


/*
| Caminho           | Método           | Acesso           |
| ----------------- | ---------------- | ---------------- |
| `/auth/login`     | POST             | **Público**      |
| `/api/publico   | GET / GET/{id}   | **Público**      |
| /api/publico/ | POST/PUT/DELETE  | **Protegido** |
 | `/api/private/**` | Todos os métodos | **Protegido** |
*/


//package com.adventistas.apimipsmais.config;
//
//import com.adventistas.apimipsmais.config.JwtFilter;
//import com.adventistas.apimipsmais.service.CustomUserDetailsService;
//import org.springframework.context.annotation.*;
//import org.springframework.security.authentication.*;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.*;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.*;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final JwtFilter jwtFilter;
//    private final CustomUserDetailsService userDetailsService;
//
//    public SecurityConfig(JwtFilter jwtFilter, CustomUserDetailsService userDetailsService) {
//        this.jwtFilter = jwtFilter;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/auth/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
//            throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
