package com.example.daUmHelp.controller;

import com.example.daUmHelp.domain.achievement.AchievementResponse;
import com.example.daUmHelp.domain.achievement.UserAchievementResponse;
import com.example.daUmHelp.domain.user.User;
import com.example.daUmHelp.domain.user.UserDTO;
import com.example.daUmHelp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> create(@RequestBody UserDTO userDTO) {
        User user = userService.createUser(userDTO);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/{userId}/achievements")
    public ResponseEntity<List<UserAchievementResponse>> getUserAchievements(@PathVariable String userId) {
        List<UserAchievementResponse> userAchievements = userService.getUserAchievements(userId);
        return ResponseEntity.ok(userAchievements);
    }

}
