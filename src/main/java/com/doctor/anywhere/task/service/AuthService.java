package com.doctor.anywhere.task.service;

import com.doctor.anywhere.task.dto.request.LoginRequestDTO;

public interface AuthService {


    Object login(LoginRequestDTO loginInfo);
}
