package com.doctor.anywhere.task.dto.request;

import lombok.Data;

@Data
public class TaskUpdateDTO {


    private String title;

    private String description;

    private boolean completed;
}
