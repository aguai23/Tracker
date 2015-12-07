package com.tracker.tracker.model;

import java.io.Serializable;

/**
 * Class contains profile information of user
 */
public class PersonalInfo implements Serializable{
    private String username;
    private String name;
    private String phone;
    private String email;

    public PersonalInfo() {
    }

    public PersonalInfo(String username, String name, String phone, String email) {
        this.username = username;
        this.name = name;
        this.phone = phone;

        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
