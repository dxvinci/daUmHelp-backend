package com.example.daUmHelp.service;

import com.example.daUmHelp.domain.user.User;
import com.example.daUmHelp.domain.user.UserDTO;
import com.example.daUmHelp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(UserDTO userDTO) {
        User user = new User(userDTO);
        return userRepository.save(user);
    }
}
