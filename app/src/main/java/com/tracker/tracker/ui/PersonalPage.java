package com.tracker.tracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tracker.tracker.R;

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

        trackeeButton = (Button) findViewById(R.id.trackee_button);
        trackeeButton.setOnClickListener(trackeeButtonListener);
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

    private View.OnClickListener trackeeButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PersonalPage.this, TrackeePage.class);
            startActivity(intent);
        }
    };

}
