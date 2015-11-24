package com.tracker.tracker.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tracker.tracker.R;
import com.tracker.tracker.model.PersonalInfo;
import com.tracker.tracker.model.User;

import java.util.ArrayList;

public class UI_TrackingPage extends Activity {

    private Button listButton;

    private GoogleMap mMap;
    private User user;
    private Spinner list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_page);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        listButton = (Button) findViewById(R.id.listButton);
        listButton.setOnClickListener(listButtonListener);

        list=(Spinner)findViewById(R.id.list);


        //display user's location of all followings on the map
    }





    private View.OnClickListener listButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String contact=list.getSelectedItem().toString();
            Intent intent = new Intent(UI_TrackingPage.this, UI_SpecificLocation.class);
            startActivity(intent);
        }
    };
}
