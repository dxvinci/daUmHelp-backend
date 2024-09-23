package com.example.daUmHelp.service;

import com.example.daUmHelp.domain.task.Task;
import com.example.daUmHelp.domain.task.TaskDTO;

public class TaskMapper {

    public Task toEntity(TaskDTO taskDTO) {
        Task task = new Task();
        task.setDescription(taskDTO.description());
        task.setExperiencePoints(taskDTO.experiencePoints());
        return task;
    }
}
