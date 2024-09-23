package com.example.daUmHelp.repository;

import com.example.daUmHelp.domain.theme.Theme;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends MongoRepository<Theme, String> {

}
