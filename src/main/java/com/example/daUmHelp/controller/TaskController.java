package com.example.daUmHelp.controller;

import com.example.daUmHelp.domain.task.Task;
import com.example.daUmHelp.domain.task.TaskCompletionRequest;
import com.example.daUmHelp.domain.task.TaskCompletionResponse;
import com.example.daUmHelp.domain.task.TaskDTO;
import com.example.daUmHelp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody TaskDTO taskDTO) {
        Task task = taskService.createTask(taskDTO);
        return ResponseEntity.ok().body(task);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Task>> createBatch(@RequestBody List<TaskDTO> taskDTOs) {
        List<Task> tasks = taskService.createTasks(taskDTOs);
        return ResponseEntity.ok().body(tasks);
    }

    @PostMapping("/complete")
    public ResponseEntity<TaskCompletionResponse> completeTask(@RequestBody TaskCompletionRequest taskCompletionRequest) {
        TaskCompletionResponse response = taskService.completeTask(taskCompletionRequest.userId(), taskCompletionRequest.taskId());
        return ResponseEntity.ok().body(response);
    }

}
