package com.tracker.tracker.model;

import android.location.Location;
import android.util.Pair;

import com.tracker.tracker.dbLayout.DbFunction;
import com.tracker.tracker.dbLayout.DbOperation;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Class represents all the information
 * about the logged in user
 */
public class User implements User_interface,Serializable {
    private PersonalInfo info;
    private ArrayList<PersonalInfo> following; // users you are tracking
    private ArrayList<PersonalInfo> followers; // users tracking you
    private ArrayList<PersonalInfo> pendingRequests; // request waiting for your approval
    private DbOperation db;

    public User(String uname, String pwd){
        this.info=new PersonalInfo();
        following=new ArrayList<>();
        followers=new ArrayList<>();
        pendingRequests=new ArrayList<>();
        db = new DbOperation();

        this.info = db.login_user(uname, pwd);
    }

    public User(String username,String name,String phone,String email) {
        this.info=new PersonalInfo(username,name,phone,email);
        following=new ArrayList<>();
        followers=new ArrayList<>();
        pendingRequests=new ArrayList<>();
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

    public void send_Request(PersonalInfo user){
    }

    //Authenticate user logging in
    public boolean checkLogin(){
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

    public ArrayList<Pair<Location, Timestamp>> get_location(String uname){
        ArrayList<Pair<Location, Timestamp>> locations = null;

        return locations;
    }

    public void updateInfo(String name,String phone,String email){
    }

    public void deleteFollowing(String name){
    }

    public void deleteFollowers(String name){
    }

    public void acceptRequest(int position){
        this.pendingRequests.remove(position);
    }

    public void rejectRequest(int position){
        this.pendingRequests.remove(position);
    }

    public void register(String username,String password,String name,String phone,String email){
    }

    public void addContact(String name){

    }
}
