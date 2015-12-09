package com.tracker.tracker.dbLayout;

import android.location.Location;
import android.util.Pair;

import com.tracker.tracker.model.PersonalInfo;
import com.tracker.tracker.model.User;
import com.tracker.tracker.services.remote.webRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.Timestamp;
import java.util.ArrayList;

/**
 * Database layer between Model and data source
 */
public class DbOperation extends DbFunction{

    public static final String username = "username";
    public static final String phone = "phone_number";
    public static final String email = "email";
    public static final String name = "name";
    public static final String password = "password";
    webRequest remote;

    public DbOperation() {
        remote = new webRequest();
    }

    public boolean create_user(PersonalInfo userInfo, String password){
        try {
            JSONObject obj = new JSONObject();
            obj.put(username, userInfo.getUsername());
            obj.put(phone, userInfo.getPhone());
            obj.put(email, userInfo.getEmail());
            obj.put(name, userInfo.getName());
            obj.put(password, password);

            //remote.send_request(obj, webRequest.REQUEST_TYPE.ADD_USER);

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

    public ArrayList<Pair<Location, Timestamp>> get_userLocation(PersonalInfo user){
        ArrayList<Pair<Location, Timestamp>> locations = null;

        return locations;
    }
}
