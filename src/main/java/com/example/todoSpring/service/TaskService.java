package com.example.todoSpring.service;


import com.example.todoSpring.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> getTasksByEmail(String email);

    Task getTaskById(Long id);

    void deleteTask(Long id);

    Task createTaskByEmail(String email, Task task);

    void archiveTask(Long id);

    void unArchiveTask(Long id);

    List<Task> getArchiveTasks(String email);

    Task updateTask(Long id, Task task);




}
