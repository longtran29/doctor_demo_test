package com.doctor.anywhere.task.service.impl;

import com.doctor.anywhere.task.dto.request.UserRequestDTO;
import com.doctor.anywhere.task.dto.response.StatusResponseDTO;
import com.doctor.anywhere.task.enums.ERole;
import com.doctor.anywhere.task.exception.CustomException;
import com.doctor.anywhere.task.model.AppUser;
import com.doctor.anywhere.task.model.Role;
import com.doctor.anywhere.task.repository.RoleRepository;
import com.doctor.anywhere.task.repository.UserRepository;
import com.doctor.anywhere.task.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public AppUserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Object addNewUser(UserRequestDTO createUser) {

        if(userRepository.findByUsername(createUser.getUserName()).isPresent())
            throw new CustomException(StatusResponseDTO.USERNAME_BEEN_TAKEN);

        AppUser creatingUser = new AppUser();

        try {
            creatingUser.setUsername(createUser.getUserName());
            creatingUser.setPassword(passwordEncoder.encode(createUser.getPassWord()));
            creatingUser.setEmail(createUser.getEmail());

            Set<Role> roles = createUser.getRoles().stream().map(role -> {

                Role existingRole = roleRepository.findByName(ERole.valueOf(role.getRoleName())).orElseThrow(() -> new CustomException(StatusResponseDTO.ROLE_NOT_EXIST));
                return existingRole;
            }).collect(Collectors.toSet());


            creatingUser.setRoles(roles);

            return userRepository.save(creatingUser);
        } catch (Exception ex) {
            throw ex;
        }


    }
}
