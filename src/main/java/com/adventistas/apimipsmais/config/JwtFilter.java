package com.adventistas.apimipsmais.config;

import com.adventistas.apimipsmais.service.CustomUserDetailsService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();

        // 🟡 Log: mostra qual caminho a requisição está acessando
        System.out.println("➡️ JwtFilter interceptando: " + path);

        // ✅ Ignora endpoints públicos como /auth/login, /auth/register
        if (path.startsWith("/auth")) {
            System.out.println("🚫 Ignorando JwtFilter para caminho público: " + path);
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        // 🔍 Log: verifica se o cabeçalho Authorization contém um Bearer Token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtUtil.extractUsername(token);

            // 🟢 Log: token extraído com sucesso
            System.out.println("✅ Token JWT recebido: " + token);
            System.out.println("🔐 Username extraído do token: " + username);
        } else {
            // 🚨 Log: Authorization ausente ou malformado
            System.out.println("❌ Cabeçalho Authorization ausente ou inválido");
        }

        // ⚠️ Se o usuário ainda não está autenticado
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(token, userDetails)) {
                System.out.println("🔐 Token JWT válido para o usuário: " + username);

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);

                // 🟢 Log: autenticação injetada no contexto de segurança
                System.out.println("✅ Usuário autenticado via JWT: " + username);
            } else {
                System.out.println("❌ Token JWT inválido para o usuário: " + username);
            }
        }

        // 🟢 Continua a cadeia de filtros
        filterChain.doFilter(request, response);
    }
}
