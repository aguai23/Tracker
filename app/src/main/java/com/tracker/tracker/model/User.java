package com.tracker.tracker.model;

import android.location.Location;
import android.util.Pair;

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

    public User(){
        this.info=new PersonalInfo();
        following=new ArrayList<>();
        followers=new ArrayList<>();
        pendingRequests=new ArrayList<>();

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

    public int getFollowingSize(){
        return this.following.size();
    }
    public String getFollowingUsername(int i){
        PersonalInfo contact=following.get(i);
        return contact.getUsername();
    }

    public String getFollowingName(int i){
        PersonalInfo contact=following.get(i);
        return contact.getName();
    }

    public String getFollowingPhone(int i){
        PersonalInfo contact=following.get(i);
        return contact.getPhone();
    }

    public String getFollowingEmail(int i){
        PersonalInfo contact=following.get(i);
        return contact.getEmail();
    }
    public int getFollowerSize(){
        return this.followers.size();
    }
    public String getFollowerUsername(int i){
        PersonalInfo contact=followers.get(i);
        return contact.getUsername();
    }

    public String getFollowergName(int i){
        PersonalInfo contact=followers.get(i);
        return contact.getName();
    }

    public String getFollowerPhone(int i){
        PersonalInfo contact=followers.get(i);
        return contact.getPhone();
    }

    public String getFollowerEmail(int i){
        PersonalInfo contact=followers.get(i);
        return contact.getEmail();
    }

    public int getPendingSize(){
        return this.pendingRequests.size();
    }
    public String getPendingUsername(int i){
        PersonalInfo contact=pendingRequests.get(i);
        return contact.getUsername();
    }

    public String getPendingName(int i){
        PersonalInfo contact=pendingRequests.get(i);
        return contact.getName();
    }

    public String getPendingPhone(int i){
        PersonalInfo contact=pendingRequests.get(i);
        return contact.getPhone();
    }

    public String getPendingEmail(int i){
        PersonalInfo contact=pendingRequests.get(i);
        return contact.getEmail();
    }

    public void addFollowing(String username,String name,String phone,String email){
        PersonalInfo contact=new PersonalInfo(username,name,phone,email);
        this.following.add(contact);
    }

    public void addFollowers(String username,String name,String phone,String email){
        PersonalInfo contact=new PersonalInfo(username,name,phone,email);
        this.followers.add(contact);
    }

    public void addPending(String username,String name,String phone,String email){
        PersonalInfo contact=new PersonalInfo(username,name,phone,email);
        this.pendingRequests.add(contact);
    }



    public void send_Request(PersonalInfo user){

    }

    public boolean checkLogin(String username,String password){
        return true;
    }

    public boolean checkRegister(String username,String password,String name,String phone,String email){
        return true;
    }

    public User search_user(String phone){
        return null;
    }

    public ArrayList<Pair<Location, Timestamp>> get_location(Timestamp from, Timestamp to){
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

    public Pair<Location,Timestamp> getLocation(PersonalInfo contact){
        Location location=null;
        Timestamp time=null;
        Pair<Location,Timestamp>pair=new Pair<Location,Timestamp>(location,time);
        return pair;
    }

    public void register(String username,String password,String name,String phone,String email){

    }

    public void addContact(String name){

    }

}
