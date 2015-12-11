package com.tracker.tracker.dbLayout;

import android.location.Location;
import android.util.Pair;

import com.tracker.tracker.model.PersonalInfo;
import com.tracker.tracker.model.User;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Map;

/**
 * abstract class defines dbfuntion
 */
public abstract class DbFunction {
    public abstract boolean create_user(PersonalInfo userInfo, String password);

    public abstract boolean update_use(PersonalInfo userInfo);

    public abstract PersonalInfo login_user(String uname, String pwd);

    public abstract Map<java.sql.Timestamp,Pair<Double,Double>> get_location(String uname);

    public abstract PersonalInfo search_user(String phone);

    public abstract PersonalInfo get_user(String uname);

    public abstract void delete_user(String uname);

    public abstract ArrayList<String> getFollowers(String uname);

    public abstract ArrayList<String> getFollowing(String uname);

    public abstract ArrayList<String> getPending(String uname);

    public abstract boolean add_relation(String from, String to);

    public abstract void add_request(String from, String to);

    public abstract void delete_relation(String from, String to);

    public abstract void delete_request(String from, String to);

    }
