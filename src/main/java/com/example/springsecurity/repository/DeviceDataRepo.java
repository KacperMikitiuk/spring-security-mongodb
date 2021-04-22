package com.example.springsecurity.repository;

import com.example.springsecurity.DeviceData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceDataRepo extends MongoRepository<DeviceData, Long> {

    List<DeviceData> findAll();
}