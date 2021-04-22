package com.example.springsecurity.service;

import com.example.springsecurity.model.AppUser;
import com.example.springsecurity.repository.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

    @Autowired
    AppUserRepo appUserRepo;

    public void save(AppUser user) {
        appUserRepo.save(user);
    }

    public List<AppUser> findAll() {
        return appUserRepo.findAll();
    }
}
