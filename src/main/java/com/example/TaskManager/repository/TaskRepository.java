package com.example.TaskManager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TaskManager.Model.Task;
import com.example.TaskManager.Model.User;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}
