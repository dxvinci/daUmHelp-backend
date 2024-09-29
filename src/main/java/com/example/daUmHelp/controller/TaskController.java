package com.example.daUmHelp.controller;

import com.example.daUmHelp.domain.publication.Publication;
import com.example.daUmHelp.domain.task.*;
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

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok().body(tasks);
    }

    @GetMapping("/special")
    public ResponseEntity<SpecialTaskResponse> getSpecialTask() {
        SpecialTaskResponse specialTask = taskService.getSpecialTask();
        return ResponseEntity.ok().body(specialTask);
    }

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
        TaskCompletionResponse response = taskService.completeTask(taskCompletionRequest.userId(), taskCompletionRequest.taskId(), false);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/complete-special")
    public ResponseEntity<TaskCompletionResponse> completeSpecialTask(@RequestBody TaskCompletionRequest taskCompletionRequest) {
        TaskCompletionResponse response = taskService.completeTask(taskCompletionRequest.userId(), taskCompletionRequest.taskId(), true);
        return ResponseEntity.ok().body(response);
    }

}
