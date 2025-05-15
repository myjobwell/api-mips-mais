package com.adventistas.apimipsmais.controller;

import com.adventistas.apimipsmais.config.JwtUtil;
import com.adventistas.apimipsmais.dto.AuthRequest;
import com.adventistas.apimipsmais.dto.AuthResponse;
import com.adventistas.apimipsmais.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            System.out.println("➡️ Tentando autenticar: " + request.getUsername());

            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            System.out.println("✅ Usuário e senha VÁLIDOS para: " + request.getUsername());

            final UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
            final String token = jwtUtil.generateToken(user.getUsername());

            return ResponseEntity.ok(new AuthResponse(token));

        } catch (BadCredentialsException ex) {
            System.out.println("❌ Usuário ou senha INVÁLIDOS para: " + request.getUsername());
            return ResponseEntity.status(401).body("Usuário ou senha inválidos");

        } catch (Exception ex) {
            System.out.println("❌ Erro inesperado ao autenticar: " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(500).body("Erro interno");
        }
    }

}

