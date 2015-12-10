package com.tracker.tracker.dbLayout;

import android.location.Location;
import android.util.Pair;

import com.tracker.tracker.model.PersonalInfo;
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

    public ArrayList<Pair<Location, Timestamp>> get_userLocation(PersonalInfo user){
        ArrayList<Pair<Location, Timestamp>> locations = null;

        return locations;
    }
}
