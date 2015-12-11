package com.tracker.tracker.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tracker.tracker.R;
import com.tracker.tracker.model.User;
import com.tracker.tracker.services.local.locationService;

public class UI_PersonalPage extends Activity {

    private Button profileButton;
    private Button logoutButton;
    private Button trackingButton;
    private Button trackeeButton;
    private User thisUser;

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

        Intent intent=getIntent();
        thisUser=(User)intent.getSerializableExtra("user");

    }

    private View.OnClickListener profileButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(UI_PersonalPage.this, UI_UserProfile.class);
            intent.putExtra("user",thisUser);
            startActivity(intent);
        }
    };

    private View.OnClickListener logoutButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            locationService.setuser("");
            Intent intent = new Intent(UI_PersonalPage.this, UI_Login.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener trackingButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(UI_PersonalPage.this, UI_TrackingPage.class);
            intent.putExtra("user",thisUser);
            startActivity(intent);
        }
    };

    private View.OnClickListener trackeeButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(UI_PersonalPage.this, UI_TrackeePage.class);
            intent.putExtra("user",thisUser);
            startActivity(intent);
        }
    };

}
