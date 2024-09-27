package com.example.daUmHelp.service;

import com.example.daUmHelp.domain.achievement.UserAchievementResponse;
import com.example.daUmHelp.domain.user.User;
import com.example.daUmHelp.domain.user.UserDTO;
import com.example.daUmHelp.repository.AchievementRepository;
import com.example.daUmHelp.repository.UserRepository;
import com.example.daUmHelp.shared.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AchievementRepository achievementRepository;

    public List<UserAchievementResponse> getUserAchievements(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException(Messages.RECORD_NOT_FOUND + userId));

        return new ArrayList<>(user.getUserAchievements().stream()
                .map(userAchievement -> new UserAchievementResponse(
                        userAchievement.getAchievementId(),
                        userAchievement.getAchievementTypeEnum()
                ))
                .toList());
    }

    public User createUser(UserDTO userDTO) {
        User user = new User(userDTO);
        return userRepository.save(user);
    }

}
