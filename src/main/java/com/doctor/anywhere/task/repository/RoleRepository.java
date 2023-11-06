package com.doctor.anywhere.task.repository;

import com.doctor.anywhere.task.enums.ERole;
import com.doctor.anywhere.task.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(ERole name);


}
