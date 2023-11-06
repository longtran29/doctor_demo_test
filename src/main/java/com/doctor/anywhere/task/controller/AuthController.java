package com.doctor.anywhere.task.controller;

import com.doctor.anywhere.task.dto.request.LoginRequestDTO;
import com.doctor.anywhere.task.dto.request.UserRequestDTO;
import com.doctor.anywhere.task.service.AppUserService;
import com.doctor.anywhere.task.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController
{

    private final AuthService authService;
    public AuthController(AuthService userService) {
        authService = userService;
    }

    @Operation(summary = "Authenticate with credentials ")
    @PostMapping(value = "/login")
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginRequestDTO loginInfo) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginInfo));
    }
}
