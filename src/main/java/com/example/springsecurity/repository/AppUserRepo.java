package com.example.springsecurity.repository;

import com.example.springsecurity.model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppUserRepo extends MongoRepository<AppUser, Long> {

    AppUser findAllByUsername(String username);
    List<AppUser> findAll();

}
