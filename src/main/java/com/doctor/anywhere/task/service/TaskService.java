package com.doctor.anywhere.task.service;

import com.doctor.anywhere.task.dto.request.TaskRequestDTO;
import com.doctor.anywhere.task.dto.request.TaskUpdateDTO;
import com.doctor.anywhere.task.dto.response.TaskResponseDTO;

import java.util.List;

public interface TaskService {


    TaskResponseDTO createTask(TaskRequestDTO task);


    List<TaskResponseDTO> getAllTask();

    TaskResponseDTO getSingleTask(Long taskId);

    TaskResponseDTO updateTaskById(Long id, TaskUpdateDTO taskRequest);

    Object deleteTaskById(Long id);
}
