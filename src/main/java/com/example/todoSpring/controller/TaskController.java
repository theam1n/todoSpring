package com.example.todoSpring.controller;


import com.example.todoSpring.entity.Task;
import com.example.todoSpring.service.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskServiceImpl taskService;


    @GetMapping("/allTasks/{email}")
    public ResponseEntity<List<Task>> getTasksByEmail(@PathVariable String email) {

        List<Task> tasks = taskService.getTasksByEmail(email);
        return ResponseEntity.ok(tasks);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @PostMapping("/addTask/{email}")
    public ResponseEntity<Task> createTaskByEmail(@PathVariable String email, @RequestBody Task task) {
        taskService.createTaskByEmail(email, task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PostMapping("/archiveTask/{id}")
    public void archiveTask(@PathVariable Long id){
        taskService.archiveTask(id);
    }

    @PostMapping("/unarchiveTask/{id}")
    public void unArchiveTask(@PathVariable Long id){
        taskService.unArchiveTask(id);
    }

    @GetMapping("/allArchiveTasks/{email}")
    public ResponseEntity<List<Task>> getArchiveTasks(@PathVariable String email) {
        List<Task> tasks = taskService.getArchiveTasks(email);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/updateTask/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        taskService.updateTask(id, task);
        return ResponseEntity.ok(task);
    }


}
