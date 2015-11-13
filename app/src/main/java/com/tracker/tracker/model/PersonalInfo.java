package com.tracker.tracker.model;

/**
 * Class contains profile information of user
 */
public class PersonalInfo {
    private String userName;
    private String name;
    private String phNumber;
    private String email;

    public PersonalInfo() {
    }

    public PersonalInfo(String userName, String name, String phNumber, String email) {
        this.userName = userName;
        this.name = name;
        this.phNumber = phNumber;

        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {

        return userName;
    }

    public String getName() {
        return name;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public String getEmail() {
        return email;
    }
}
