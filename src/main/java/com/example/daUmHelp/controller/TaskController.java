package com.example.daUmHelp.controller;

import com.example.daUmHelp.domain.task.Task;
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

    @GetMapping
    public ResponseEntity<List<Task>> getAllPublications() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok().body(tasks);
    }



}
