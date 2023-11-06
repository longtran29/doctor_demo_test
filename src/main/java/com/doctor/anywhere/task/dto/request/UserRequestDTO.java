package com.doctor.anywhere.task.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserRequestDTO {

    @JsonProperty("username")
    private String userName;

    @JsonProperty("password")
    private String passWord;

    private String email;

    private List<RoleRequestDTO> roles;

}
