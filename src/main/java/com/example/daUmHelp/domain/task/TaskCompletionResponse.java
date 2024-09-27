package com.example.daUmHelp.domain.task;

import com.example.daUmHelp.domain.achievement.AchievementResponse;

public record TaskCompletionResponse(Integer experiencePoints, boolean leveledUp, AchievementResponse achievementResponse) {
}
