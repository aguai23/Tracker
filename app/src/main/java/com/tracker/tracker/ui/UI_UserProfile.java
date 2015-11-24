package com.tracker.tracker.ui;

import android.app.Activity;
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

    private User user;
    private TextView username;
    private EditText name;
    private EditText phone;
    private EditText email;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui__user_profile);

        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        username=(TextView)findViewById(R.id.username);

        PersonalInfo info=user.getInfo();

        //populate the view with data in the user object
        name.setText(info.getName());
        phone.setText(info.getPhone());
        email.setText(info.getEmail());
        username.setText(info.getUsername());


    }

    private View.OnClickListener saveListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String nameString=name.getText().toString();
            String phoneString=phone.getText().toString();
            String emailString=email.getText().toString();
            user.updateInfo(nameString,phoneString,emailString);

        }
    };

}
