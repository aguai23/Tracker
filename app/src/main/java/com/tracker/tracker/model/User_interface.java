package com.tracker.tracker.model;

import android.location.Location;
import android.util.Pair;

import java.security.Timestamp;
import java.util.ArrayList;

/**
 * API for user class
 */
public interface User_interface {

    PersonalInfo search_user(String phNumber);

    ArrayList<Pair<Location, Timestamp>> get_location(Timestamp from, Timestamp to);
}
