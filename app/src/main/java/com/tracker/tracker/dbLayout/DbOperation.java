package com.tracker.tracker.dbLayout;

import android.location.Location;
import android.util.Pair;

import com.tracker.tracker.model.PersonalInfo;
import com.tracker.tracker.model.User;

import java.security.Timestamp;
import java.util.ArrayList;

/**
 * Database layer between Model and data source
 */
public class DbOperation extends DbFunction{
    public boolean create_user(PersonalInfo userInfo){

        return false;
    }

    public boolean update_use(PersonalInfo userInfo){

        return false;
    }

    public User login_user(String uname, String pwd){
        User user = null;

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
