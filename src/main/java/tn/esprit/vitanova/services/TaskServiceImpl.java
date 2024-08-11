package tn.esprit.vitanova.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.Task;
import tn.esprit.vitanova.entities.TaskState;
import tn.esprit.vitanova.entities.User;
import tn.esprit.vitanova.repository.TaskRepo;
import tn.esprit.vitanova.repository.UserRepo;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{
    private TaskRepo taskRepo;
    private UserRepo userRepo;
    private ProjectService projectRepo;

    @Override
    public Task createTask(Task task) {
        return taskRepo.save(task);
    }

    @Override
    public Task updateTaskState(Long taskId, TaskState state) {
        Task task = taskRepo.findById(taskId).orElseThrow(() -> new RuntimeException("Task with id " + taskId + " not found"));
        task.setState(state);
        return taskRepo.save(task);
    }

    @Override
    public Task assignTaskToUser(Long taskId, Long userId) {
        Task task = taskRepo.findById(taskId).orElseThrow(() -> new RuntimeException("Task with id " + taskId + " not found"));
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User with id " + userId + " not found"));

        return taskRepo.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @Override
    public Task getTaskById(Long taskId) {
        return taskRepo.findById(taskId).orElseThrow(() -> new RuntimeException("Task with id " + taskId + " not found"));
    }
}
