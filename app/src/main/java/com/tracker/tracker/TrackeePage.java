package com.tracker.tracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TrackeePage extends AppCompatActivity {

    private Button notificationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_being_tracked);

        notificationButton = (Button) findViewById(R.id.notificationButton);
        notificationButton.setOnClickListener(notificationButtonListener);
    }

    private View.OnClickListener notificationButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(TrackeePage.this, Notification.class);
            startActivity(intent);
        }
    };

}
