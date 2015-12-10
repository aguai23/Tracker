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

    public User(String uname, String pwd){
        this.info = null;
        db = new DbOperation();

        this.info = db.login_user(uname, pwd);
        if(info!=null) locationService.setuser(uname);
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
        return null;
    }

    @Override
    public ArrayList<String> getFollowings() {
        return null;
    }

    @Override
    public ArrayList<String> getPendings() {
        return null;
    }

    public void addFollowing(String username){
        //PersonalInfo contact=new PersonalInfo(username,name,phone,email);
        //this.following.add(contact);
    }

    public void addFollowers(String username){
        //PersonalInfo contact=new PersonalInfo(username,name,phone,email);
        //this.followers.add(contact);
    }

    public void addPending(String username){
        //PersonalInfo contact=new PersonalInfo(username,name,phone,email);
        //this.pendingRequests.add(contact);
    }

    @Override
    public void send_Request(String username) {

    }

    //Authenticate user logging in
    public boolean checkLogin(){
        return this.info != null;
    }

    public PersonalInfo search_user(String phone){
        return null;
    }

    public PersonalInfo get_user(String uname){

        return null;
    }

    public Map<Timestamp,Pair<Double,Double>> get_location(String uname){
        Map<Timestamp,Pair<Double,Double>> locations = new HashMap<Timestamp,Pair<Double,Double>>();

        return locations;
    }

    public void updateInfo(String name,String phone,String email){
    }

    public void deleteFollowing(String name){
    }

    public void deleteFollowers(String name){
    }

    public void acceptRequest(int position){
        //this.pendingRequests.remove(position);
    }

    public void rejectRequest(int position){
        //this.pendingRequests.remove(position);
    }

    public boolean register(String username,String password,String name,String phone,String email){
        if(info == null)info = new PersonalInfo(username, name, phone, email);
        /* this.info.setUsername(username);
         this.info.setEmail(email);
         this.info.setName(name);
         this.info.setPhone(phone);
         */
        Boolean ret = db.create_user(info, password);
        if(ret) locationService.setuser(username);
        return ret;
    }

    public void addContact(String name){

    }
}
