package com.example.daUmHelp.domain.user;

import com.example.daUmHelp.domain.achievement.AchievementTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAchievement {
    private String achievementId;
    private AchievementTypeEnum achievementTypeEnum;
}
