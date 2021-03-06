package com.upgrad.quora.api.service;

import com.upgrad.quora.api.model.User;
import com.upgrad.quora.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    public User getUserByUserName(String username) {
        return userRepo.getUserByUsername();
    }
}
