package com.example.todoSpring.service;

import com.example.todoSpring.entity.Task;
import com.example.todoSpring.entity.User;
import com.example.todoSpring.model.TaskStatus;
import com.example.todoSpring.repository.TaskRepository;
import com.example.todoSpring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;


    @Override
    public List<Task> getTasksByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user.getTasks();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteTask(Long id) {
       taskRepository.deleteById(id);
    }

    @Override
    public Task createTaskByEmail(String email, Task task) {
        User user = userRepository.findByEmail(email);
        task.setUser(user);
        return taskRepository.save(task);
    }


    @Override
    public List<Task> getArchiveTasks(String email) {
        User user = userRepository.findByEmail(email);
        return user.getTasks().stream()
                .filter(this::isTaskStatusArchived)
                .collect(Collectors.toList());
    }

    @Override
    public Task updateTask(Long id, Task task) {

        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new RuntimeException());

        existingTask.setTaskName(task.getTaskName());
        existingTask.setTaskStatus(task.getTaskStatus());
        existingTask.setTaskDeadlineDate(task.getTaskDeadlineDate());
        existingTask.setDescription(task.getDescription());
        existingTask.setTaskSortType(task.getTaskSortType());

        return taskRepository.save(existingTask);

    }

    @Override
    public void archiveTask(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        Task task = optionalTask.orElseThrow(() -> new RuntimeException());
        if ((TaskStatus.ARCHIVED).equals(task.getTaskStatus())) {
            throw new RuntimeException();
        }
        task.setTaskStatus(TaskStatus.ARCHIVED);
        taskRepository.save(task);
    }

    @Override
    public void unArchiveTask(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        Task task = optionalTask.orElseThrow(() -> new RuntimeException());
        if ((TaskStatus.ACTIVE).equals(task.getTaskStatus())) {
            throw new RuntimeException();
        }
        task.setTaskStatus(TaskStatus.ACTIVE);
        taskRepository.save(task);
    }

    private boolean isTaskStatusArchived(Task task) {
        if (Objects.isNull(task)) return false;
        return TaskStatus.ARCHIVED.equals(task.getTaskStatus());
    }
}
