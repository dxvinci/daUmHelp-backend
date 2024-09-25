package com.example.daUmHelp.controller;

import com.example.daUmHelp.domain.task.TaskDTO;
import com.example.daUmHelp.domain.task.TaskIdsRequest;
import com.example.daUmHelp.domain.task.TaskRequest;
import com.example.daUmHelp.domain.theme.Theme;
import com.example.daUmHelp.domain.theme.ThemeDTO;
import com.example.daUmHelp.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/themes")
public class ThemeController {

    @Autowired
    ThemeService themeService;

    @GetMapping
    public ResponseEntity<List<Theme>> getAllThemes() {
        List<Theme> themes = themeService.getAllThemes();
        return ResponseEntity.ok().body(themes);
    }

    @PostMapping
    public ResponseEntity<Theme> createTheme(@RequestBody ThemeDTO themeDTO) {
        Theme createdTheme = themeService.createTheme(themeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTheme);
    }

    @PutMapping("/{themeId}/tasks")
    public ResponseEntity<Theme> addTaskToTheme(@PathVariable String themeId, @RequestBody TaskRequest taskRequest) {
        Theme updatedTheme = themeService.addTaskToTheme(themeId, taskRequest.taskId());
        return ResponseEntity.ok(updatedTheme);
    }

    @PutMapping("/{themeId}/tasks/batch")
    public ResponseEntity<Theme> addTasksToTheme(@PathVariable String themeId, @RequestBody TaskIdsRequest taskIdsRequest) {
        Theme updatedTheme = themeService.addTasksToTheme(themeId, taskIdsRequest);
        return ResponseEntity.ok(updatedTheme);
    }

}
