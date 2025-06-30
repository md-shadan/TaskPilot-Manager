package com.example.TaskManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.TaskManager.Model.Task;
import com.example.TaskManager.service.TaskService;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody Task task, @RequestHeader("Authorization") String token) {
        return taskService.createTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks(@RequestHeader("Authorization") String token) {
        return taskService.getAllTasks();
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask, @RequestHeader("Authorization") String token) {
        return taskService.updateTask(id, updatedTask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        taskService.deleteTask(id);
    }

    @PatchMapping("/{id}/complete")
    public Task markAsCompleted(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        return taskService.markAsCompleted(id);
    }
}
