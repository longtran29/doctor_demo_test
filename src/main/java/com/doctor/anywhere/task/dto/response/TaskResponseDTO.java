package com.doctor.anywhere.task.dto.response;


import lombok.Data;

@Data
public class TaskResponseDTO {

    private Long id;
    private String title;

    private String description;

    private boolean completed;
}
