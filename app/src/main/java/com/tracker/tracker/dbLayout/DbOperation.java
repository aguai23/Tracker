package com.tracker.tracker.dbLayout;

import android.location.Location;
import android.util.Pair;

import com.tracker.tracker.model.PersonalInfo;
import com.tracker.tracker.services.remote.webRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Map;

/**
 * Database layer between Model and data source
 */
public class DbOperation extends DbFunction{

    public static final String username = "username";
    public static final String phone = "phone_number";
    public static final String email = "email";
    public static final String name = "name";
    public static final String password = "password";
    public static final String tracking = "tracking";
    public static final String latitude = "latitude";
    public static final String longitude = "longitude";
    public static final String timestamp = "timestamp";


    webRequest remote;

    public DbOperation() {
        remote = new webRequest();
    }

    public boolean create_user(PersonalInfo userInfo, String password){
        try {
            JSONObject obj = new JSONObject();
            obj.put(this.username, userInfo.getUsername());
            obj.put(this.phone, userInfo.getPhone());
            obj.put(this.email, userInfo.getEmail());
            obj.put(this.name, userInfo.getName());
            obj.put(this.password, password);

            return remote.register_user(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update_use(PersonalInfo userInfo){

        return false;
    }

    public PersonalInfo login_user(String uname, String pwd){
        PersonalInfo user = null;
        //user = remote.login(uname, pwd);

        return user;
    }

    public boolean approve_follower(PersonalInfo info){

        return false;
    }

    public boolean remove_follower(PersonalInfo info){

        return false;
    }

    public boolean send_request(PersonalInfo info){

        return false;
    }

    @Override
    public Map<java.sql.Timestamp,Pair<Double,Double>> get_location(String uname) {
        return remote.get_location(uname);
    }

    @Override
    public PersonalInfo search_user(String phone) {
        return remote.search_user(phone);
    }

    @Override
    public PersonalInfo get_user(String uname) {
        return remote.get_user(uname);
    }

    @Override
    public void delete_user(String uname) {
        JSONObject obj = new JSONObject();
        try {
            obj.put(this.username, uname);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        remote.delete_user(obj);
    }

    @Override
    public ArrayList<String> getFollowers(String uname) {
        return remote.get_followers(uname);
    }

    @Override
    public ArrayList<String> getFollowing(String uname) {
        return remote.get_following(uname);
    }

    @Override
    public ArrayList<String> getPending(String uname) {
        return remote.get_pending(uname);
    }

    @Override
    public boolean add_relation(String from, String to) {
        return remote.add_relation(from, to);
    }

    @Override
    public void add_request(String from, String to) {
        remote.add_request(from, to);
    }

    @Override
    public void delete_relation(String from, String to) {
        remote.delete_relation(from, to);
    }

    @Override
    public void delete_request(String from, String to) {
        remote.delete_request(from, to);
    }
}
