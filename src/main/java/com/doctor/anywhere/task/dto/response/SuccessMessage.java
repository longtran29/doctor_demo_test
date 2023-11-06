package com.doctor.anywhere.task.dto.response;


import lombok.Data;

@Data
public class SuccessMessage {

    private String message;

    public SuccessMessage(String message) {
        this.message = message;
    }
}
