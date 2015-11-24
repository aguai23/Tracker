package com.tracker.tracker.model;

import android.location.Location;
import android.util.Pair;

import java.security.Timestamp;
import java.util.ArrayList;

/**
 * Class represents all the information
 * about the logged in user
 */
public class User implements User_interface {
    private PersonalInfo info;
    private ArrayList<PersonalInfo> following; // users you are tracking
    private ArrayList<PersonalInfo> followers; // users tracking you
    private ArrayList<PersonalInfo> pendingRequests; // request waiting for your approval

    public User() {}

    public PersonalInfo getInfo() {
        return info;
    }

    public void setInfo(PersonalInfo info) {
        this.info = info;
    }

    public ArrayList<PersonalInfo> getFollowing() {
        return following;
    }

    public void setFollowing(ArrayList<PersonalInfo> following) {
        this.following = following;
    }

    public ArrayList<PersonalInfo> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<PersonalInfo> followers) {
        this.followers = followers;
    }

    public ArrayList<PersonalInfo> getPendingRequests() {
        return pendingRequests;
    }

    public void setPendingRequests(ArrayList<PersonalInfo> pendingRequests) {
        this.pendingRequests = pendingRequests;
    }

    public void send_Request(PersonalInfo user){

    }

    public boolean checkLogin(String username,String password){
        return true;
    }

    public boolean checkRegister(String username,String password,String name,String phone,String email){
        return true;
    }

    public PersonalInfo search_user(String phNumber){
        PersonalInfo info = new PersonalInfo();

        return info;
    }

    public ArrayList<Pair<Location, Timestamp>> get_location(Timestamp from, Timestamp to){
        ArrayList<Pair<Location, Timestamp>> locations = null;

        return locations;
    }
}
