package com.doctor.anywhere.task.service.impl;


import com.doctor.anywhere.task.enums.ERole;
import com.doctor.anywhere.task.model.AppUser;
import com.doctor.anywhere.task.model.Role;
import com.doctor.anywhere.task.repository.RoleRepository;
import com.doctor.anywhere.task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ApplicationRunnermpl {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Component
    @Order(1)
    class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            if(!roleRepository.findByName(ERole.ROLE_ADMIN).isPresent())
            {
                Role role = new Role();
                role.setName(ERole.valueOf("ROLE_ADMIN"));
                roleRepository.save(role);
            }
        }
    }


    @Component
    @Order(2)
    class CommandLineRunner1 implements org.springframework.boot.CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            if(!roleRepository.findByName(ERole.ROLE_NURSE).isPresent())
            {
                Role role = new Role();
                role.setName(ERole.valueOf("ROLE_NURSE"));
                roleRepository.save(role);
            }
        }
    }

    @Component
    @Order(3)
    class CommandLineRunner2 implements org.springframework.boot.CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            if(!roleRepository.findByName(ERole.ROLE_DOCTOR).isPresent())
            {
                Role role = new Role();
                role.setName(ERole.valueOf("ROLE_DOCTOR"));
                roleRepository.save(role);
            }
        }
    }
    @Component
    @Order(4)
    class CommandLineRunner3 implements org.springframework.boot.CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            if(!userRepository.findByUsername("admin").isPresent())
            {
                AppUser appClient = new AppUser();
                Set<Role> listRoles = new HashSet<>();
                Role role = roleRepository.findByName(ERole.ROLE_ADMIN).get();
                listRoles.add(role);
                appClient.setRoles(listRoles);
                appClient.setUsername("admin");
                appClient.setEmail("admin2001@gmail.com");
                appClient.setPassword(passwordEncoder.encode("123456"));
                userRepository.save(appClient);
            }
        }
    }



}
