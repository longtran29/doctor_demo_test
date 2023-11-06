package com.doctor.anywhere.task.exception;

import com.doctor.anywhere.task.dto.response.StatusResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomResponseException extends RuntimeException {
    public CustomResponseException(String message) {
        super(message);
    }

    


    //
//    public CustomResponseException(HttpStatus status, String reason) {
//        super(status, reason);
//    }
//
//    public CustomResponseException(StatusResponseDTO response) {
//        super(HttpStatus.valueOf(Integer.parseInt(response.getCode())), response.getMessage());
//    }
}
