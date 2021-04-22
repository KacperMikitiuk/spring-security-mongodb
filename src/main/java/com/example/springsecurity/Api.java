package com.example.springsecurity;

import com.example.springsecurity.Model.DeviceData;
import com.example.springsecurity.repository.AppUserRepo;
import com.example.springsecurity.repository.DeviceDataRepo;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.*;

@RestController
public class Api {

    public DeviceDataRepo deviceDataRepo;

    public AppUserRepo appUserRepo;

    private MongoOperations mongoOperations1;

    @GetMapping("/forAll")
    public String forAll(){
        return "You have been logout";
    }

    @GetMapping("/forUser")
    public String forUser(Principal principal){
        return "Hello user:"+principal.getName();
    }

    @GetMapping("/forAdmin")
    public String forAdmin(Principal principal){
        return "Hello admin:"+principal.getName();
    }

    @GetMapping("/add/{message}")
    public String addData(@PathVariable("message") String message) {
        StringBuilder stringBuilderMessage = new StringBuilder(message);
        StringBuilder stringBuilder = new StringBuilder();
        String deviceName = "none";
        Map<String, String> values = new LinkedHashMap<>();

            int indexOfValuesStart = stringBuilderMessage.indexOf("(");
            int indexOfValuesEnd = stringBuilderMessage.indexOf(")");
            if (indexOfValuesEnd != -1 && indexOfValuesStart != -1) {
                for (int i = 0; stringBuilderMessage.charAt(i) != '='; i++) {
                    stringBuilder.append(stringBuilderMessage.charAt(i));
                }
                deviceName = stringBuilder.toString();
                StringBuilder stringBuilderValueName = new StringBuilder();
                StringBuilder stringBuilderValue = new StringBuilder();
                int i = indexOfValuesStart;
                while (i != indexOfValuesEnd) {
                    i++;
                    while (stringBuilderMessage.charAt(i) != '=' && stringBuilderMessage.charAt(i) != ',') {
                        stringBuilderValueName.append(stringBuilderMessage.charAt(i));
                    }
                    i++;
                    while (stringBuilderMessage.charAt(i) != ',' && stringBuilderMessage.charAt(i) != ')') {
                        stringBuilderValue.append(stringBuilderMessage.charAt(i));
                    }
                    values.put(stringBuilderValueName.toString(), stringBuilderValue.toString());
                    stringBuilderValueName = new StringBuilder();
                    stringBuilderValue = new StringBuilder();
                }
            } else message = "invalid format message";

        stringBuilderMessage = new StringBuilder();
        for (Map.Entry<String, String> e: values.entrySet()) {
            stringBuilderMessage.append("/" + e.getKey() + " = " + e.getValue());
            System.out.println('x');
        }
        message = stringBuilderMessage.toString();
        return "Device name is " + deviceName + " and values are " + message;

    }

    @GetMapping("addDevice/{name}")
    public String addDevice(@PathVariable("name") String name, DeviceDataRepo deviceDataRepo,
                            MongoOperations mongoOperations) {
        this.deviceDataRepo = deviceDataRepo;
        this.mongoOperations1 = mongoOperations;
        DeviceData device = new DeviceData();
        device.setId(new SequenceGeneratorService().generateSequence(DeviceData.SEQUENCE_NAME, mongoOperations));
        device.setName(name);
        device.setUsersPermitted(new String[]{"Tytus"});
        device.setData(new Document("x",12));
        deviceDataRepo.save(device);
        return "NARESZCIE";
    }

}
