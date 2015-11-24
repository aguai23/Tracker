package com.tracker.tracker.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tracker.tracker.R;

public class UI_TrackingPage extends Activity {

    private Button listButton;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_page);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        listButton = (Button) findViewById(R.id.listButton);
        listButton.setOnClickListener(listButtonListener);
    }





    private View.OnClickListener listButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(UI_TrackingPage.this, UI_SpecificLocation.class);
            startActivity(intent);
        }
    };
}
