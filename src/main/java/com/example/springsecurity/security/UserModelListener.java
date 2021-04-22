package com.example.springsecurity.security;

import com.example.springsecurity.Model.AppUser;
import com.example.springsecurity.SequenceGeneratorService;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class UserModelListener extends AbstractMongoEventListener<AppUser> {

    private SequenceGeneratorService sequenceGenerator;
    private MongoOperations mongoOperations;


    public void onBeforeConvert(BeforeConvertEvent<AppUser> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(AppUser.SEQUENCE_NAME, mongoOperations));
        }
    }
}
