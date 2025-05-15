package com.adventistas.apimipsmais.config;

import com.adventistas.apimipsmais.entity.User;
import com.adventistas.apimipsmais.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Profile("dev") // Só roda quando o perfil 'dev' estiver ativo
public class DevDataInitializer {

    @Bean
    public CommandLineRunner loadInitialUser(UserRepository userRepository) {
        return args -> {
            String defaultUsername = "pro-admin";
            String defaultPassword = "vivaviva1020";

            boolean userExists = userRepository.findByUsername(defaultUsername).isPresent();

            if (!userExists) {
                User user = new User();
                user.setUsername(defaultUsername);

                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                user.setPassword(encoder.encode(defaultPassword));

                userRepository.save(user);

                System.out.println("✅ Usuário padrão criado: " + defaultUsername);
            } else {
                System.out.println("ℹ️ Usuário já existe: " + defaultUsername);
            }
        };
    }
}

