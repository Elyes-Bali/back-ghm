package tn.esprit.vitanova.services;

import tn.esprit.vitanova.entities.Task;
import tn.esprit.vitanova.entities.TaskState;

import java.util.List;

public interface TaskService {
    Task createTask(Task task);

    Task updateTaskState(Long taskId, TaskState state);

    Task assignTaskToUser(Long taskId, Long userId);

    List<Task> getAllTasks();

    Task getTaskById(Long taskId);
}
