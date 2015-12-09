package com.tracker.tracker.model;

import android.location.Location;
import android.util.Pair;

import com.tracker.tracker.dbLayout.DbOperation;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

/**
 * Class represents all the information
 * about the logged in user
 */
public class User implements User_connect,Serializable {
    private PersonalInfo info;
    private DbOperation db;

    public User(String uname, String pwd){
        this.info=new PersonalInfo();
        db = new DbOperation();

        this.info = db.login_user(uname, pwd);
    }

    public User(String username,String name,String phone,String email) {
        this.info=new PersonalInfo(username,name,phone,email);

    }

    public String getUsername(){
        return info.getUsername();
    }

    public String getName(){
        return info.getName();

    }

    public String getPhone(){
        return info.getPhone();
    }

    public String getEmail(){
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

    public ArrayList<String>getFollowers(String username){
        return new ArrayList<>();
    }

    public ArrayList<String>getFollowings(String username){
        return new ArrayList<>();
    }
    public ArrayList<String>getPendings(String username){
        return new ArrayList<>();
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

    public void send_Request(String username){
        //the user want to add contact with this username
    }

    //Authenticate user logging in
    public boolean checkLogin(String username,String password){
        return this.info != null;
    }

    public boolean checkRegister(String username,String password,String name,String phone,String email){
        return true;
    }

    public PersonalInfo search_user(String phone){
        return null;
    }

    public PersonalInfo get_user(String uname){

        return null;
    }

    public Map<Timestamp,Pair<Double,Double>> get_location(String uname){
        return null;
    }

    public void updateInfo(String name,String phone,String email){
    }

    public void deleteFollowing(String name){
    }

    public void deleteFollowers(String name){
    }

    public void acceptRequest(int position){

    }

    public void rejectRequest(int position){

    }

    public void register(String username,String password,String name,String phone,String email){
    }

    public void addContact(String name){

    }
}
