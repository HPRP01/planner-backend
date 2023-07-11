package com.hudsonpanning.planner.service;

import com.hudsonpanning.planner.entity.Task;
import com.hudsonpanning.planner.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks()
    {
        return this.taskRepository.findAll();
    }

    public void addTask(Task task)
    {
        if(task == null)
        {
            throw new RuntimeException("Task cannot be null");
        }

        this.taskRepository.save(task);
    }

    public void deleteTask(long id)
    {
        this.taskRepository.deleteById(id);
    }

    public void updateTask(long id, String status) {
        Optional<Task> task = this.taskRepository.findById(id);

        task.get().setStatus(status);

        this.taskRepository.save(task.get());
    }
}
