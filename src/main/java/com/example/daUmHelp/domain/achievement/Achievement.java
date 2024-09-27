package com.example.daUmHelp.domain.achievement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "achievements")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Achievement {
    @Id
    private String id;
    private String name;
    private String description;
    private List<AchievementDetails> type;

    public Achievement(AchievementDTO achievementDTO) {
        this.name = achievementDTO.name();
        this.description = achievementDTO.description();

        this.type = new ArrayList<>();
        this.type.addAll(achievementDTO.details());

    }
}
