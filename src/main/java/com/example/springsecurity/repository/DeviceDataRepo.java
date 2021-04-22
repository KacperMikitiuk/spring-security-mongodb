package com.example.springsecurity.repository;

import com.example.springsecurity.Model.DeviceData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableMongoRepositories
public interface DeviceDataRepo extends MongoRepository<DeviceData, Long> {

}