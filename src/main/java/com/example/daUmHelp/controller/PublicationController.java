package com.example.daUmHelp.controller;

import com.example.daUmHelp.domain.publication.Publication;
import com.example.daUmHelp.domain.publication.PublicationDTO;
import com.example.daUmHelp.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publications")
public class PublicationController {

    @Autowired
    PublicationService publicationService;

    @PostMapping
    public ResponseEntity<Publication> create(@RequestBody PublicationDTO publicationDTO) {
        System.out.println("Received PublicationDTO: " + publicationDTO);
        Publication publication = publicationService.createPublication(publicationDTO);
        return ResponseEntity.ok().body(publication);
    }

    @GetMapping
    public ResponseEntity<List<Publication>> getAllPublications() {
        List<Publication> publications = publicationService.getAllPublications();
        return ResponseEntity.ok().body(publications);
    }

}
