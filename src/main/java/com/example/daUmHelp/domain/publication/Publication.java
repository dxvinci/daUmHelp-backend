package com.example.daUmHelp.domain.publication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "publications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Publication {

    @Id
    private String id;
    private String title;
    private List<String> paragraphs;

    public Publication(PublicationDTO publicatonDTO) {
        this.title = publicatonDTO.title();
        this.paragraphs = publicatonDTO.paragraphs();
    }
}
