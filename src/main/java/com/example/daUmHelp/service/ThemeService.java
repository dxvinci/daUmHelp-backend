package com.example.daUmHelp.service;

import com.example.daUmHelp.domain.task.Task;
import com.example.daUmHelp.domain.task.TaskDTO;
import com.example.daUmHelp.domain.task.TaskIdsRequest;
import com.example.daUmHelp.domain.theme.Theme;
import com.example.daUmHelp.domain.theme.ThemeDTO;
import com.example.daUmHelp.repository.TaskRepository;
import com.example.daUmHelp.repository.ThemeRepository;
import com.example.daUmHelp.shared.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ThemeService {
    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private TaskRepository taskRepository;

    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    public Theme createTheme(ThemeDTO themeDTO) {
        Theme theme = new Theme(themeDTO);
        theme.setTasks(new ArrayList<>());

        return themeRepository.save(theme);
    }

    public Theme addTasksToTheme(String themeId, TaskIdsRequest taskIdsRequest) {
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new NoSuchElementException(Messages.RECORD_NOT_FOUND + themeId));

        List<Task> tasks = taskRepository.findAllById(taskIdsRequest.taskIds());

        for (Task task : tasks) {
            if (!theme.getTasks().contains(task)) {
                theme.getTasks().add(task);
            }
        }

        return themeRepository.save(theme);
    }

    public Theme addTaskToTheme(String themeId, String taskId) {
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new NoSuchElementException(Messages.RECORD_NOT_FOUND + themeId));
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException(Messages.RECORD_NOT_FOUND + taskId));

        if (!theme.getTasks().contains(task)) {
            theme.getTasks().add(task);
        }

        return themeRepository.save(theme);
    }

}
