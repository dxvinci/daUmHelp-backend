package com.example.daUmHelp.service;

import com.example.daUmHelp.domain.publication.Publication;
import com.example.daUmHelp.domain.user.User;
import com.example.daUmHelp.domain.user.UserDTO;
import com.example.daUmHelp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(UserDTO userDTO) {
        User user = new Publication(userDTO);
        return userRepository.save(user);
    }
}
