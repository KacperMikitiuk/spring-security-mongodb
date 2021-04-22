package com.example.springsecurity.service;

import com.example.springsecurity.Model.AppUser;
import com.example.springsecurity.repository.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    @Autowired
    AppUserRepo appUserRepo;

    public void save(AppUser user) {
        appUserRepo.save(user);
    }
}
