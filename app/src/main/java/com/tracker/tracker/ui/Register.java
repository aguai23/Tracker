package com.tracker.tracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tracker.tracker.R;

public class Register extends AppCompatActivity {

    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(submitButtonListener);
    }

    private View.OnClickListener submitButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Register.this, PersonalPage.class);
            startActivity(intent);
        }
    };

}
