package com.doctor.anywhere.task.service.impl;

import com.doctor.anywhere.task.dto.request.LoginRequestDTO;
import com.doctor.anywhere.task.dto.response.StatusResponseDTO;
import com.doctor.anywhere.task.exception.CustomException;
import com.doctor.anywhere.task.model.AppUser;
import com.doctor.anywhere.task.repository.UserRepository;
import com.doctor.anywhere.task.service.AuthService;
import com.doctor.anywhere.task.utils.JwtUtility;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailServiceImpl userDetailService;

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final JwtUtility jwtUtility;

    public AuthServiceImpl(UserDetailServiceImpl userDetailService, AuthenticationManager authenticationManager, UserRepository userRepository, JwtUtility jwtUtility) {
        this.userDetailService = userDetailService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtility = jwtUtility;
    }

    @Override
    public Object login(LoginRequestDTO loginInfo) {
        if (!StringUtils.hasText(loginInfo.getUsername()) || !StringUtils.hasText(loginInfo.getPassword()))
            throw new CustomException(StatusResponseDTO.INFORMATION_IS_MISSING);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginInfo.getUsername(),
                            loginInfo.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final UserDetails userDetails
                    = userDetailService.loadUserByUsername(loginInfo.getUsername());

            AppUser loggedUser  = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new CustomException(StatusResponseDTO.USER_NOT_FOUND));;

            final String tokenDto = jwtUtility.createToken(loggedUser);


            JSONObject json = new JSONObject();

            json.put("jwtToken" ,tokenDto);
            json.put("expiresIn", System.currentTimeMillis() + JwtUtility.EXPIRE_DURATION);
            json.put("roles", loggedUser.getRoles() );

            return json.toMap();
        } catch (BadCredentialsException e) {
            throw new CustomException(StatusResponseDTO.FAIL_AUTHENTICATION);
        }
    }
}
