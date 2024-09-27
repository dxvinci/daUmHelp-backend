package com.example.daUmHelp.domain.achievement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AchievementResponse {
    String achievementId;
    String name;
    String description;
    AchievementTypeEnum type;
    String imageUrl;

    public AchievementTypeEnum getAchievementTypeEnum() {
        return this.type;
    }

    public AchievementResponse(String achievementId, AchievementTypeEnum type) {
        this.achievementId = achievementId;
        this.type = type;
    }
}
