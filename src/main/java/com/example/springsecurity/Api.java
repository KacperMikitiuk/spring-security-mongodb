package com.example.springsecurity;

import com.example.springsecurity.model.AppUser;
import com.example.springsecurity.service.AppUserService;
import com.example.springsecurity.service.DeviceDataService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.*;

@RestController
public class Api {

    private DeviceDataService deviceDataService;

    private AppUserService appUserService;

    private MongoOperations mongoOperations;

    @Autowired
    public Api(DeviceDataService deviceDataService, AppUserService appUserService, MongoOperations mongoOperations) {
        this.deviceDataService = deviceDataService;
        this.appUserService = appUserService;
        this.mongoOperations = mongoOperations;
    }

    @GetMapping("/forAll")
    public String forAll() {
        return "You have been logout";
    }

    @GetMapping("/forUser")
    public String forUser(Principal principal) {
        return "Hello user:" + principal.getName();
    }

    @GetMapping("/forAdmin")
    public String forAdmin(Principal principal) {
        return "Hello admin:" + principal.getName();
    }

    @GetMapping("/getAllUsers")
    public List<AppUser> getAllUsers() {
        return appUserService.findAll();
    }

    @GetMapping("/getAllDevices")
    public List<DeviceData> getAllDevices() {
        return deviceDataService.findAll();
    }


    @GetMapping("/addData/{message}")
    public String addData(@PathVariable("message") String message) {

        //odczyt wiadomości
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
                    i++;
                }
                i++;
                while (stringBuilderMessage.charAt(i) != ',' && stringBuilderMessage.charAt(i) != ')') {
                    stringBuilderValue.append(stringBuilderMessage.charAt(i));
                    i++;
                }
                values.put(stringBuilderValueName.toString(), stringBuilderValue.toString());
                stringBuilderValueName = new StringBuilder();
                stringBuilderValue = new StringBuilder();
            }
        } else message = "invalid format message";

        stringBuilderMessage = new StringBuilder();
        for (Map.Entry<String, String> e : values.entrySet()) {
            stringBuilderMessage.append("/" + e.getKey() + " = " + e.getValue());
            System.out.println('x');
        }
        message = stringBuilderMessage.toString();
        //zapis wiadomości do danego urządzenia
//        DeviceData deviceData =  deviceDataService.findByName(deviceName);
//        for (Map.Entry<String, String> e: values.entrySet()) {
//            deviceData.getData().put(e.getKey(),e.getValue());
//        }

        return "Device name is " + deviceName + " and values are " + message;

    }

    @GetMapping("addDevice/{name}")
    public String addDevice(@PathVariable("name") String name) {
        DeviceData device = new DeviceData();
        device.setId(new SequenceGeneratorService().generateSequence(DeviceData.SEQUENCE_NAME, mongoOperations));
        device.setName(name);
        device.setUsersPermitted(new String[]{"Romek", "Atomek"});
        device.setData(new Document("x", 12));
        deviceDataService.save(device);
        return "NARESZCIE";
    }

//    @GetMapping("/show")
//    public String showDevice() {
//        String name = "termometr";
//        List<DeviceData> deviceData    = mongoOperations.find(
//                Query.query(Criteria.where("name:")), DeviceData.class);
//        if(deviceData==null){
//            return "brak urzadzenia";
//        }
//        for (DeviceData dev : deviceData) {
//            System.out.println(dev.getId());
//            System.out.println();
//        }
//        deviceData.get(0).setName("Jim");
//        deviceDataService.save(deviceData.get(0));
//        return "zmieniono nazwe urzadzenia";
//    }

}
