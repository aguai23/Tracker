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
    private Button back;
    private User thisUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username=(TextView)findViewById(R.id.user_name);
        name=(TextView)findViewById(R.id.name);
        phone=(TextView)findViewById(R.id.phone);
        email=(TextView)findViewById(R.id.email);
        delete=(Button)findViewById(R.id.delete);

        Intent intent=getIntent();
        thisUser=(User)intent.getSerializableExtra("user");
        username.setText(thisUser.getUsername());
        name.setText(thisUser.getName());
        phone.setText(thisUser.getPhone());
        email.setText(thisUser.getEmail());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call methods to delete this contact
                Intent intent = new Intent(UI_Profile.this, UI_PersonalPage.class);
                intent.putExtra("user", thisUser);
                startActivity(intent);
            }
        });

        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UI_Profile.this,UI_TrackeePage.class);
                intent.putExtra("user",thisUser);
                startActivity(intent);
            }
        });




    }

}
