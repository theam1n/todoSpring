package com.example.todoSpring.repository;

import com.example.todoSpring.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
