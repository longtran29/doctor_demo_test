package com.doctor.anywhere.task.service.impl;

import com.doctor.anywhere.task.dto.request.TaskRequestDTO;
import com.doctor.anywhere.task.dto.request.TaskUpdateDTO;
import com.doctor.anywhere.task.dto.response.StatusResponseDTO;
import com.doctor.anywhere.task.dto.response.SuccessMessage;
import com.doctor.anywhere.task.dto.response.TaskResponseDTO;
import com.doctor.anywhere.task.exception.CustomException;
import com.doctor.anywhere.task.mapper.TaskMapper;
import com.doctor.anywhere.task.model.Task;
import com.doctor.anywhere.task.repository.TaskRepository;
import com.doctor.anywhere.task.service.TaskService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }


    @Override
    public TaskResponseDTO createTask(TaskRequestDTO task) {

        Task newTask = new Task();
        newTask.setTitle(task.getTitle());
        newTask.setDescription(task.getDescription());

        return taskMapper.taskToDTO(taskRepository.save(newTask));
    }

    @Override
    public List<TaskResponseDTO> getAllTask() {

        return taskRepository.findAll().stream().map(taskMapper::taskToDTO).collect(Collectors.toList());
    }

    @Override
    public TaskResponseDTO getSingleTask(Long taskId) {
        Task existingTask = taskRepository.findById(taskId).orElseThrow(() -> new CustomException(StatusResponseDTO.TASK_NOT_EXISTS));
        return taskMapper.taskToDTO(existingTask);
    }

    @Override
    public TaskResponseDTO updateTaskById(Long taskId, TaskUpdateDTO taskRequest) {

        Task existingTask = taskRepository.findById(taskId).orElseThrow(() -> new CustomException(StatusResponseDTO.TASK_NOT_EXISTS));

        existingTask.setTitle(taskRequest.getTitle());
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setCompleted(taskRequest.isCompleted());

        return taskMapper.taskToDTO(taskRepository.save(existingTask));

    }

    @Override
    public Object deleteTaskById(Long taskId) {

        taskRepository.findById(taskId).orElseThrow(() -> new CustomException(StatusResponseDTO.TASK_NOT_EXISTS));

        try {
            taskRepository.deleteById(taskId);
        } catch (DataIntegrityViolationException ex) {
            throw ex;
        }

        return new SuccessMessage("Delete task successfully !");
    }


}
