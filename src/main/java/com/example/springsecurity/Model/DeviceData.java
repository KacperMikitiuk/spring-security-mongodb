package com.example.springsecurity.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@Document(collection = "devices")
public class DeviceData {

    @Transient
    public static final String SEQUENCE_NAME = "device_sequence";

    @Id
    private long id;

    private String name;

    private String[] usersPermitted;

    private org.bson.Document data;

    private UsernamePasswordAuthenticationToken token;

    public DeviceData() {
    }

    public UsernamePasswordAuthenticationToken getToken() {
        return token;
    }

    public void setToken(UsernamePasswordAuthenticationToken token) {
        this.token = token;
    }

    public static String getSequenceName() {
        return SEQUENCE_NAME;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String[] getUsersPermitted() {
        return usersPermitted;
    }

    public void setUsersPermitted(String[] usersPermitted) {
        this.usersPermitted = usersPermitted;
    }

    public org.bson.Document getData() {
        return data;
    }

    public void setData(org.bson.Document data) {
        this.data = data;
    }

    public void addData(double x, double y) {
        data.put(String.valueOf(x), y);
    }
}
