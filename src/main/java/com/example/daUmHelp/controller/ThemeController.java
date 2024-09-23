package com.example.daUmHelp.controller;

import com.example.daUmHelp.domain.task.TaskDTO;
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

    public ResponseEntity<Theme> addTaskToTheme(@PathVariable String themeId, @RequestBody String taskId) {
        Theme updatedTheme = themeService.addTaskToTheme(themeId, taskId);
        return ResponseEntity.ok(updatedTheme);
    }

    @PutMapping("/{themeId}/tasks/batch")
    public ResponseEntity<Theme> addTasksToTheme(@PathVariable String themeId, @RequestBody List<String> taskIds) {
        Theme updatedTheme = themeService.addTasksToTheme(themeId, taskIds);
        return ResponseEntity.ok(updatedTheme);
    }

}
