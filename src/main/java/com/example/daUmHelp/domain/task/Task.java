package com.example.daUmHelp.domain.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    private String id;
    private String description;
    private Integer experiencePoints;
    private String relatedAchievementId;

    public Task(TaskDTO taskDTO) {
        this.description = taskDTO.description();
        this.experiencePoints = taskDTO.experiencePoints();
        this.relatedAchievementId = taskDTO.relatedAchievementId();
    }

}
