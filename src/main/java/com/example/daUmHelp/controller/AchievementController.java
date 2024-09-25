package com.example.daUmHelp.controller;

import com.example.daUmHelp.domain.achievement.Achievement;
import com.example.daUmHelp.domain.achievement.AchievementDTO;
import com.example.daUmHelp.domain.publication.Publication;
import com.example.daUmHelp.domain.theme.Theme;
import com.example.daUmHelp.domain.theme.ThemeDTO;
import com.example.daUmHelp.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/achievements")
public class AchievementController {

    @Autowired
    private AchievementService achievementService;

    @PostMapping
    public ResponseEntity<Achievement> createAchievement(@RequestBody AchievementDTO achievementDTO) {
        Achievement createdAchievement = achievementService.createAchievement(achievementDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAchievement);
    }

    @GetMapping
    public ResponseEntity<List<Achievement>> getAllAchievements() {
        List<Achievement> achievements = achievementService.getAllAchievements();
        return ResponseEntity.ok().body(achievements);
    }

}

