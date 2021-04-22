//package com.example.springsecurity;
//
//import com.example.springsecurity.repository.AppUserRepo;
//import org.bson.Document;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoActionOperation;
//import org.springframework.data.mongodb.core.MongoOperations;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//@Component
//public class Start {
//
//    private PasswordEncoder passwordEncoder;
//
//    private DeviceDataRepo deviceDataRepo;
//
//    private MongoOperations mongoOperations;
//
//    @Autowired
//    public Start(PasswordEncoder passwordEncoder, DeviceDataRepo deviceDataRepo, MongoOperations mongoOperations) {
//        this.mongoOperations = mongoOperations;
//        this.passwordEncoder = passwordEncoder;
//        this.deviceDataRepo = deviceDataRepo;
//        DeviceData user = new DeviceData();
//        user.setId(new SequenceGeneratorService().generateSequence(DeviceData.SEQUENCE_NAME, mongoOperations));
//        user.setUsersPermitted(new String[]{"Jan"});
//        user.setName("silomierz");
//        user.setData(new Document("x",12));
//        deviceDataRepo.save(user);
//
////        AppUser appUser = new AppUser();
////        appUser.setId((long) 1245233);
////        appUser.setUsername("Przemek");
////        appUser.setPassword(passwordEncoder.encode("Przemek123"));
////        appUserRepo.save(appUser);
//    }
//}
//
