package com.example.daUmHelp.service;

import com.example.daUmHelp.domain.publication.Publication;
import com.example.daUmHelp.domain.publication.PublicationDTO;
import com.example.daUmHelp.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationService {
    @Autowired
    private PublicationRepository publicationRepository;

    public List<Publication> getAllPublications() {
        return publicationRepository.findAll();
    }

    public Publication createPublication(PublicationDTO publicationDTO) {
        Publication publication = new Publication(publicationDTO);
        return publicationRepository.save(publication);
    }

}
