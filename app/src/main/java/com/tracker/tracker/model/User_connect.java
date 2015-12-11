package com.tracker.tracker.model;

import android.location.Location;
import android.util.Pair;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

/**
 * API for user class
 */
public interface User_connect {

    public ArrayList<String>getFollowers();

    public ArrayList<String>getFollowings();

    public ArrayList<String>getPendings();

    public void addFollowing(String username);

    public void addFollowers(String username);

    PersonalInfo search_user(String phNumber);

     Map<Timestamp,Pair<Double,Double>> get_location(String username);
}
