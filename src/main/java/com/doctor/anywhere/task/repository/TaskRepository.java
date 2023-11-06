package com.doctor.anywhere.task.repository;

import com.doctor.anywhere.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {




}
