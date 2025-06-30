package com.example.TaskManager.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String status; // Example: Pending, Completed
    private String priority; // e.g., High, Medium, Low

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Link to the logged-in user
}
