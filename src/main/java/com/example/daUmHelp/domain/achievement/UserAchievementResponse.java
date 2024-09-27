package com.example.daUmHelp.domain.achievement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAchievementResponse {
    String achievementId;
    AchievementTypeEnum type;

}
