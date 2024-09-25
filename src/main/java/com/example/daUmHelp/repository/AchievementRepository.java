package com.example.daUmHelp.repository;

import com.example.daUmHelp.domain.achievement.Achievement;
import com.example.daUmHelp.domain.publication.Publication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepository extends MongoRepository<Achievement, String> {

}
