package com.tracker.tracker.dbLayout;

import android.location.Location;
import android.util.Pair;

import com.tracker.tracker.model.PersonalInfo;
import com.tracker.tracker.model.User;

import java.security.Timestamp;
import java.util.ArrayList;

/**
 * abstract class defines dbfuntion
 */
public abstract class DbFunction {
    public abstract boolean create_user(PersonalInfo userInfo, String password);

    public abstract boolean update_use(PersonalInfo userInfo);

    public abstract PersonalInfo login_user(String uname, String pwd);

    public abstract ArrayList<Pair<Location, Timestamp>> get_userLocation(PersonalInfo user);
}
