package com.hudsonpanning.planner.controller;

import com.hudsonpanning.planner.PlannerApplication;
import com.hudsonpanning.planner.entity.Task;
import com.hudsonpanning.planner.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final Logger logger = LoggerFactory.getLogger(PlannerApplication.class);

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTasks()
    {
        return this.taskService.getTasks();
    }

    @PostMapping(path = "/add")
    public void addTask(@RequestBody Task task)
    {
        this.logger.info("TaskController.addTask - Received task: {}", task);

        this.taskService.addTask(task);

        this.logger.info("TaskController.addTask - Added task: {}", task);
    }

    @DeleteMapping
    public void deleteTask(@RequestParam long id)
    {
        this.logger.info("TaskController.deleteTask - Deleting task with ID: {}", id);

        this.taskService.deleteTask(id);

        this.logger.info("TaskController.deleteTask - Deleted task");
    }

    @PutMapping(path = "/update-status")
    public void updateStatus(@RequestParam("id") long id, @RequestParam("status") String status)
    {
        this.logger.info("TaskController.updateStatus - Setting status of task: {} to {}", id, status);

        this.taskService.updateTask(id, status);

        this.logger.info("TaskController.updateStatus - Status updated.");
    }
}
