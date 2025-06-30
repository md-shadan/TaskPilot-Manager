package com.example.TaskManager.web;

import com.example.TaskManager.Model.Task;
import com.example.TaskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskViewController {

    @Autowired
    private TaskRepository taskRepository;

    // Show task page (UI only – JS will fetch user tasks with token)
    @GetMapping("/task-ui")
    public String showTasks() {
        return "tasks"; // Loads templates/tasks.html
    }

    // Handle task form submission (used in UI, not API)
    @PostMapping("/tasks/add")
    public String addTask(@RequestParam String title,
                          @RequestParam String description,
                          @RequestParam String priority) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setPriority(priority);
        task.setStatus("Pending"); // default
        taskRepository.save(task);
        return "redirect:/task-ui";
    }

    // Edit task form UI
    @GetMapping("/tasks/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Task task = taskRepository.findById(id).orElse(null);
        model.addAttribute("task", task);
        return "edit-task"; // templates/edit-task.html
    }

    // Update task from form
    @PostMapping("/tasks/update")
    public String updateTask(@ModelAttribute Task updatedTask) {
        Task task = taskRepository.findById(updatedTask.getId()).orElse(null);
        if (task != null) {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setPriority(updatedTask.getPriority());
            taskRepository.save(task);
        }
        return "redirect:/task-ui";
    }

    // Delete task by ID (only used in UI flow)
    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "redirect:/task-ui";
    }

    // Toggle status (Pending <=> Completed)
    @GetMapping("/tasks/toggle/{id}")
    public String toggleStatus(@PathVariable Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            if ("Completed".equalsIgnoreCase(task.getStatus())) {
                task.setStatus("Pending");
            } else {
                task.setStatus("Completed");
            }
            taskRepository.save(task);
        }
        return "redirect:/task-ui";
    }

    // Load login UI page
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // templates/login.html
    }

    // Load registration UI page
    @GetMapping("/register")
    public String registerPage() {
        return "register"; // templates/register.html
    }
}
