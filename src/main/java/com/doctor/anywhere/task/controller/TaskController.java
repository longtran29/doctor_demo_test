package com.doctor.anywhere.task.controller;

import com.doctor.anywhere.task.dto.request.LoginRequestDTO;
import com.doctor.anywhere.task.dto.request.TaskRequestDTO;
import com.doctor.anywhere.task.dto.request.TaskUpdateDTO;
import com.doctor.anywhere.task.dto.response.TaskResponseDTO;
import com.doctor.anywhere.task.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/tasks")
@SecurityRequirement(name = "bearerAuth")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Create a new task ")
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_NURSE')")
    public ResponseEntity<?> createNewTask(@RequestBody @Valid TaskRequestDTO task) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(task));
    }

    @Operation(summary = "Get a list of all tasks")
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_NURSE')")
    public ResponseEntity<?> getListTask() {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTask());
    }

    @Operation(summary = "Get a single task by ID ")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_NURSE')")
    public ResponseEntity<?> getSingleTask(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getSingleTask(id));
    }

    @Operation(summary = "Update a task by ID ")
    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR')")
    public ResponseEntity<?> updateTaskById(@PathVariable Long id, @RequestBody TaskUpdateDTO taskRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTaskById(id, taskRequest));
    }

    @Operation(summary = "Delete a task by ID ")
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteTaskById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.deleteTaskById(id));
    }

}
