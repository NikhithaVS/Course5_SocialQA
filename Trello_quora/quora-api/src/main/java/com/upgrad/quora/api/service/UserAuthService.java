package com.upgrad.quora.api.service;

import com.upgrad.quora.api.model.UserAuth;
import com.upgrad.quora.api.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {

    @Autowired
    private UserAuthRepository repo;

    public void save(UserAuth userAuth) {
        repo.save(userAuth);
    }
}
