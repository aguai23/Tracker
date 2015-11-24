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

import com.tracker.tracker.services.remote.webRequest;

import java.util.jar.Manifest;

public class locationService extends Service {
    private boolean STOP_MYSERVICE = false;

    private long TIMEOUT = 10000;

    private String TAG ="LOCATION_SERVICE";

    private webRequest webservice;

    public locationService() {
        webservice = new webRequest();
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
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        synchronized (this) {
            while (!STOP_MYSERVICE) {
                try {
                    wait(TIMEOUT);
                    if (checkPermission(android.Manifest.permission.ACCESS_FINE_LOCATION, android.os.Process.myPid(), android.os.Process.myUid())
                            != PackageManager.PERMISSION_GRANTED){
                        Log.e(TAG, "No permissions fpr GPS");
                    }
                    Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    send_location(locationGPS.getLatitude(), locationGPS.getLongitude());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return START_STICKY;
    }

    private void send_location(double latitude, double longitude) {

    }
}
