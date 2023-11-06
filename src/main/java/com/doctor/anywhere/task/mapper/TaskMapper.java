package com.doctor.anywhere.task.mapper;

import com.doctor.anywhere.task.dto.request.TaskRequestDTO;
import com.doctor.anywhere.task.dto.response.TaskResponseDTO;
import com.doctor.anywhere.task.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskResponseDTO taskToDTO(Task review);
}
