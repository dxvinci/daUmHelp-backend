package com.example.daUmHelp.service;

import com.example.daUmHelp.domain.task.Task;
import com.example.daUmHelp.domain.task.TaskDTO;
import com.example.daUmHelp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(TaskDTO taskDTO) {
        Task task = new Task(taskDTO);
        return taskRepository.save(task);
    }

}
