package com.doctor.anywhere.task.service;

import com.doctor.anywhere.task.dto.request.LoginRequestDTO;
import com.doctor.anywhere.task.dto.request.UserRequestDTO;

public interface AppUserService {


    Object addNewUser(UserRequestDTO createUser);

}
