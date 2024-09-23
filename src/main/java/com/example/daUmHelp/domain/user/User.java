package com.example.daUmHelp.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String name;
    private Integer level;
    private Integer experiencePoints;
    private List<UserTask> userTasks;

    public User(UserDTO userDTO) {
        this.name = userDTO.name();
        this.level = 1;
        this.experiencePoints = 0;
        this.userTasks = new ArrayList<>();
    }

}
