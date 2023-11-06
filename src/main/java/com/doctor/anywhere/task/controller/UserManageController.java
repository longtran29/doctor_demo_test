package com.doctor.anywhere.task.controller;


import com.doctor.anywhere.task.dto.request.UserRequestDTO;
import com.doctor.anywhere.task.service.AppUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/account")
public class AuthController {

    private final AppUserService appUserService;
    public AuthController(AppUserService userService) {
        appUserService = userService;
    }

    @Operation(summary = "Register new account ")
    @PostMapping(value = "/add_account")
    public ResponseEntity<?> addAccount(@RequestBody @Valid UserRequestDTO createUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appUserService.addNewUser(createUser));
    }
}
