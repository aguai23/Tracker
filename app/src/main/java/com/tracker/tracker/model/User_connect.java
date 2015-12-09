package com.tracker.tracker.model;

import android.location.Location;
import android.util.Pair;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

/**
 * API for user class
 */
public interface User_connect {

    public ArrayList<String>getFollowers(String username);

    public ArrayList<String>getFollowings(String username);

    public ArrayList<String>getPendings(String username);

    public void addFollowing(String username);

    public void addFollowers(String username);

    public void addPending(String username);

    public void send_Request(String username);

    PersonalInfo search_user(String phNumber);

     Map<Timestamp,Pair<Double,Double>> get_location(String username);
}
