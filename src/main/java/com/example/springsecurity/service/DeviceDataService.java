package com.example.springsecurity.service;

import com.example.springsecurity.Model.DeviceData;
import com.example.springsecurity.repository.DeviceDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceDataService {

    @Autowired
    DeviceDataRepo deviceDataRepo;

    public void save(DeviceData device) {
        deviceDataRepo.save(device);
    }
}
