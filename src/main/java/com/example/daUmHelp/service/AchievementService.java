package com.example.daUmHelp.service;

import com.example.daUmHelp.domain.achievement.Achievement;
import com.example.daUmHelp.domain.achievement.AchievementDTO;
import com.example.daUmHelp.repository.AchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementService {

    @Autowired
    private AchievementRepository achievementRepository;

    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    public Achievement createAchievement(AchievementDTO achievementDTO) {
        Achievement achievement = new Achievement(achievementDTO);
        return achievementRepository.save(achievement);
    }

}
