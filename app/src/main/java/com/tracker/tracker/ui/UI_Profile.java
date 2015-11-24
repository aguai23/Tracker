package com.tracker.tracker.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tracker.tracker.R;
import com.tracker.tracker.model.PersonalInfo;
import com.tracker.tracker.model.User;

public class UI_Profile extends Activity {
    private TextView username;
    private TextView name;
    private TextView phone;
    private TextView email;
    private Button delete;
    private User user;
    private PersonalInfo contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username=(TextView)findViewById(R.id.user_name);
        name=(TextView)findViewById(R.id.name);
        phone=(TextView)findViewById(R.id.phone);
        email=(TextView)findViewById(R.id.phone);
        delete=(Button)findViewById(R.id.delete);

        username.setText(contact.getUsername());
        name.setText(contact.getName());
        phone.setText(contact.getPhone());
        email.setText(contact.getEmail());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call methods to delete this contact
            }
        });




    }

}
