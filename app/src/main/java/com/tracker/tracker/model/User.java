package com.tracker.tracker.model;

import android.location.Location;
import android.util.Pair;

import com.tracker.tracker.dbLayout.DbFunction;
import com.tracker.tracker.dbLayout.DbOperation;
import com.tracker.tracker.services.local.locationService;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class represents all the information
 * about the logged in user
 */
public class User implements User_connect,Serializable {
    private PersonalInfo info;
    private DbOperation db;

    public User(String username, String pwd){
        this.info = null;
        db = new DbOperation();

        this.info = db.login_user(username, pwd);
        if(info!=null) locationService.setuser(username);
    }

    public User() {
        this.info=null;
        db = new DbOperation();
    }

    public String getUsername(){
        if(this.info == null){
            return null;
        }

        return info.getUsername();
    }

    public String getName(){
        if(this.info == null) {
            return null;
        }

        return info.getName();
    }

    public String getPhone(){
        return info.getPhone();
    }

    public String getEmail(){
        if(this.info == null){
            return null;
        }

        return info.getEmail();
    }

    public void setUsername(String username){
        info.setUsername(username);
    }

    public void setName(String name){
        info.setName(name);
    }

    public void setPhone(String phone){
        info.setPhone(phone);
    }

    public void setEmail(String email){
        info.setEmail(email);
    }

    @Override
    public ArrayList<String> getFollowers() {
        return db.getFollowers(this.getUsername());
    }

    @Override
    public ArrayList<String> getFollowings() {
        return db.getFollowing(this.getUsername());
    }

    @Override
    public ArrayList<String> getPendings() {
        return db.getPending(this.getUsername());
    }

    public void addFollowing(String username){
        db.add_request(this.getUsername(), username);
    }

    public void addFollowers(String username){
         db.add_relation(username, this.getUsername());
    }

    //Authenticate user logging in
    public boolean checkLogin(){
        return this.info != null;
    }

    public PersonalInfo search_user(String phone){
        return db.search_user(phone);
    }

    public PersonalInfo get_user(String uname){
        return db.get_user(uname);
    }

    public Map<Timestamp,Pair<Double,Double>> get_location(String uname){
        return db.get_location(uname);
    }

    public void updateInfo(String name,String phone,String email){
        PersonalInfo old = db.get_user(this.getUsername());
    }

    public void deleteFollowing(String name){
        db.delete_relation(this.getUsername(), name);
    }

    public void deleteFollowers(String name){
        db.delete_relation(name, this.getUsername());
    }

    public void rejectRequest(String username){
        db.delete_request(username, this.getUsername());
    }

    public boolean register(String username,String password,String name,String phone,String email){
        if(info == null)info = new PersonalInfo(username, name, phone, email);
        Boolean ret = db.create_user(info, password);

        if(ret) locationService.setuser(username);
        return ret;
    }

}
