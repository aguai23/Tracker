package com.tracker.tracker.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tracker.tracker.R;

public class UI_TrackeePage extends Activity {

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
            Intent intent = new Intent(UI_TrackeePage.this, UI_Notification.class);
            startActivity(intent);
        }
    };

}
