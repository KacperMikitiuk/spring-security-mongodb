package com.example.springsecurity.repository;

import com.example.springsecurity.Model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;


@Repository
public interface AppUserRepo extends MongoRepository<AppUser, Long> {

    AppUser findAllByUsername(String username);

}
