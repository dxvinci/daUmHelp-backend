package com.example.daUmHelp.service;

import com.example.daUmHelp.domain.task.Task;
import com.example.daUmHelp.domain.task.TaskDTO;
import com.example.daUmHelp.domain.theme.Theme;
import com.example.daUmHelp.domain.theme.ThemeDTO;
import com.example.daUmHelp.repository.TaskRepository;
import com.example.daUmHelp.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public Theme addTasksToTheme(String themeId, List<String> taskIds) {
        Theme theme = themeRepository.findById(themeId).get();

        List<Task> tasks = taskRepository.findAllById(taskIds);
        for (Task task : tasks) {
            if (!theme.getTasks().contains(task)) {
                theme.getTasks().add(task);
            }
        }

        return themeRepository.save(theme);
    }

    public Theme addTaskToTheme(String themeId, String taskId) {
        Theme theme = themeRepository.findById(themeId).get();
        Task task = taskRepository.findById(taskId).get();

        if (!theme.getTasks().contains(task)) {
            theme.getTasks().add(task);
        }

        return themeRepository.save(theme);
    }

}
