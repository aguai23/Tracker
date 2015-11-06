package com.tracker.tracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class PersonalPage extends AppCompatActivity {

    private Button profileButton;
    private Button logoutButton;
    private Button trackingButton;
    private Button trackeeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_page);

        profileButton = (Button) findViewById(R.id.personal_info);
        profileButton.setOnClickListener(profileButtonListener);

        logoutButton = (Button) findViewById(R.id.logout);
        logoutButton.setOnClickListener(logoutButtonListener);

        trackingButton = (Button) findViewById(R.id.tracking_button);
        trackingButton.setOnClickListener(trackingButtonListener);

    }

    private View.OnClickListener profileButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PersonalPage.this, Profile.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener logoutButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PersonalPage.this, Login.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener trackingButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PersonalPage.this, TrackingPage.class);
            startActivity(intent);
        }
    };

}
