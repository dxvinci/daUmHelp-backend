package com.example.daUmHelp.domain.achievement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String imageUrl;
    private AchievementTypeEnum type;
    private Integer threshold;

}
