package com.tracker.tracker.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tracker.tracker.R;
import com.tracker.tracker.model.PersonalInfo;
import com.tracker.tracker.model.User;

public class UI_UserProfile extends Activity {

    private User thisUser;
    private TextView username;
    private EditText name;
    private EditText phone;
    private EditText email;
    private Button save;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui__user_profile);
        Intent intent=getIntent();
        thisUser=(User)intent.getSerializableExtra("user");
        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        email=(EditText)findViewById(R.id.email);
        username=(TextView)findViewById(R.id.username);



        //populate the view with data in the user object
        name.setText(thisUser.getName());
        phone.setText(thisUser.getPhone());
        email.setText(thisUser.getEmail());
        username.setText(thisUser.getUsername());


        save=(Button)findViewById(R.id.save);
        save.setOnClickListener(saveListener);

    }

    private View.OnClickListener saveListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String nameString=name.getText().toString();
            String phoneString=phone.getText().toString();
            String emailString=email.getText().toString();
            thisUser.updateInfo(nameString, phoneString, emailString);

            Intent intent=new Intent(UI_UserProfile.this,UI_PersonalPage.class);
            intent.putExtra("user",thisUser);
            startActivity(intent);
        }
    };

}
