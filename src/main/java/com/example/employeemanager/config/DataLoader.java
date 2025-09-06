package com.example.employeemanager.config;

import com.example.employeemanager.model.Role;
import com.example.employeemanager.model.User;
import com.example.employeemanager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            if (userRepository.count() == 0) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPasswordHash(encoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);

                User manager = new User();
                manager.setUsername("manager");
                manager.setPasswordHash(encoder.encode("manager123"));
                manager.setRole(Role.MANAGER);
                userRepository.save(manager);
            }
        };
    }
}
