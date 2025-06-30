package com.example.TaskManager.service;

import com.example.TaskManager.Model.Task;
import com.example.TaskManager.Model.User;
import com.example.TaskManager.repository.TaskRepository;
import com.example.TaskManager.repository.UserRepository;
import com.example.TaskManager.security.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpServletRequest request;

    private User getCurrentUser() {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String email = jwtService.extractUsername(token);
        return userRepository.findByEmail(email).orElseThrow();
    }

    public Task createTask(Task task) {
        task.setUser(getCurrentUser());
        task.setStatus("Pending"); // Default
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findByUser(getCurrentUser());
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id).orElseThrow();
        if (!task.getUser().getId().equals(getCurrentUser().getId()))
            throw new RuntimeException("Unauthorized");
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setPriority(updatedTask.getPriority());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        if (!task.getUser().getId().equals(getCurrentUser().getId()))
            throw new RuntimeException("Unauthorized");
        taskRepository.delete(task);
    }

    public Task markAsCompleted(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        if (!task.getUser().getId().equals(getCurrentUser().getId()))
            throw new RuntimeException("Unauthorized");

        if ("Completed".equalsIgnoreCase(task.getStatus())) {
            task.setStatus("Pending");
        } else {
            task.setStatus("Completed");
        }

        return taskRepository.save(task);
    }
}
