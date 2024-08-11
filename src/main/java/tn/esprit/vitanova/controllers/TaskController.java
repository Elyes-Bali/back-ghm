package tn.esprit.vitanova.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.vitanova.entities.Task;
import tn.esprit.vitanova.entities.TaskState;
import tn.esprit.vitanova.services.TaskService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/task")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;


    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        // Print the received task object to check its contents
        System.out.println("Received task: " + task);
        // Ensure task object fields are printed separately

        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/updateState/{taskId}")
    public ResponseEntity<Task> updateTaskState(@PathVariable Long taskId, @RequestParam TaskState state) {
        Task updatedTask = taskService.updateTaskState(taskId, state);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @PutMapping("/assign/{taskId}/{userId}")
    public ResponseEntity<Task> assignTaskToUser(@PathVariable Long taskId, @PathVariable Long userId) {
        Task assignedTask = taskService.assignTaskToUser(taskId, userId);
        return new ResponseEntity<>(assignedTask, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        Task task = taskService.getTaskById(taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }
}
