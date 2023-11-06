package com.doctor.anywhere.task.dto.response;

public enum StatusResponseDTO {

    USERNAME_BEEN_TAKEN("400", "Username already taken , choose another one !"),
    ROLE_NOT_EXIST("400", "Role not valid, check again !"),

    INFORMATION_IS_MISSING("400", "User information is missing !"),


    FAIL_AUTHENTICATION("400", "Fail authentication, check your credentials"),


    USER_NOT_FOUND("404", "User not found !"),

    TASK_NOT_EXISTS("404", "Task not exist, check again !")




    ;



    private final String code;
    private final String message;

    StatusResponseDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() { return this.code; }
    public String getMessage() { return this.message;}
    }
