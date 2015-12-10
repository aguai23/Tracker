package com.tracker.tracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tracker.tracker.R;
import com.tracker.tracker.model.User;

public class Add_contact extends AppCompatActivity {

    private TextView username;
    private TextView name;
    private TextView phone;
    private TextView email;
    private Button add;
    private Button back;
    private User thisUser;
    private User searchInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        username=(TextView)findViewById(R.id.user_name);
        name=(TextView)findViewById(R.id.name);
        phone=(TextView)findViewById(R.id.phone);
        email=(TextView)findViewById(R.id.email);
        add=(Button)findViewById(R.id.add);

        Intent intent=getIntent();
        thisUser=(User)intent.getSerializableExtra("user");
        searchInfo=(User)intent.getSerializableExtra("search");

        username.setText(searchInfo.getUsername());
        name.setText(searchInfo.getName());
        phone.setText(searchInfo.getPhone());
        email.setText(searchInfo.getEmail());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                thisUser.addFollowing(searchInfo.getName());
                Intent intent=new Intent(Add_contact.this,UI_Search_contact.class);
                intent.putExtra("user",thisUser);
                startActivity(intent);
            }
        });

        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_contact.this, UI_Search_contact.class);
                intent.putExtra("user", thisUser);
                startActivity(intent);
            }
        });

    }

}
