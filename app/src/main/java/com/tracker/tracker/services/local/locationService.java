package com.tracker.tracker.services.local;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.tracker.tracker.model.DeviceLocation;
import com.tracker.tracker.services.remote.webRequest;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.jar.Manifest;

public class locationService extends Service implements Serializable   {
    private boolean STOP_MYSERVICE = false;

    private static double last_latitude = 0;
    private static double last_longitude = 0;

    private long TIMEOUT = 10000;

    private String TAG ="LOCATION_SERVICE";

    private static boolean isrunning = false;

    private webRequest webservice;

    private static String username = null;

    public static void setuser(String name){
        username = name;
        last_latitude = 0;
        last_longitude = 0;
    }

    public locationService() {
        webservice = new webRequest();
    }

    public static boolean getIsrunning(){
        return isrunning;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isrunning = true;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                LocationManager locationManager = (LocationManager)
                        getSystemService(Context.LOCATION_SERVICE);
                synchronized (this) {
                    while (!STOP_MYSERVICE) {
                        try {
                            wait(TIMEOUT);
                            if (username != null && checkPermission(android.Manifest.permission.ACCESS_FINE_LOCATION, android.os.Process.myPid(), android.os.Process.myUid())
                                    == PackageManager.PERMISSION_GRANTED) {
                                Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                send_location(locationGPS.getLatitude(), locationGPS.getLongitude());
                            } else {
                                if (username != null) {
                                    Log.e(TAG, "No permissions for GPS");
                                } else {
                                    Log.e(TAG, "NULL USER");
                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        thread.start();

        return START_STICKY;
    }

    private void send_location(double latitude, double longitude) {

        if(last_latitude != latitude && last_longitude != longitude) {
            java.util.Date today = new java.util.Date();
            Timestamp tsTemp = new java.sql.Timestamp(today.getTime());
            DeviceLocation loc = new DeviceLocation(tsTemp, longitude, latitude);
            webservice.send_location(loc, username);
            last_latitude = latitude;
            last_longitude = longitude;
        } else {
            System.out.println("Same location Detected: NO UPDATE");
        }
    }
}
