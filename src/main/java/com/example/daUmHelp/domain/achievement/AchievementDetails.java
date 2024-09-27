package com.example.daUmHelp.domain.achievement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AchievementDetails {
    private String imageUrl;
    private AchievementTypeEnum type;
    private Integer threshold;

}
