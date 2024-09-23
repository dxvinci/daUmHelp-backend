package com.example.daUmHelp.domain.theme;

import com.example.daUmHelp.domain.task.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "themes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Theme {

    @Id
    private String id;
    private Integer key;
    private String imageUrl;
    private String primaryColor;
    private String secondaryColor;
    @DBRef
    private List<Task> tasks;

    public Theme(ThemeDTO themeDTO) {
        this.key = themeDTO.key();
        this.imageUrl = themeDTO.imageUrl();
        this.primaryColor = themeDTO.primaryColor();
        this.secondaryColor = themeDTO.secondaryColor();
    }
}
