package com.tracker.tracker.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tracker.tracker.R;
import com.tracker.tracker.exception.InvalidAccountException;
import com.tracker.tracker.exception.NoInputException;
import com.tracker.tracker.exception.NoInputException;
import com.tracker.tracker.model.User;
import com.tracker.tracker.services.local.locationService;

public class UI_Login extends Activity {

    private Button loginButton;
    private Button registerButton;
    private EditText username;
    private EditText password;
    private TextView error;
    private User thisUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = new Intent(this, locationService.class);
        startService(intent);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(loginButtonListener);

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(registerButtonListener);

        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);

        error=(TextView)findViewById(R.id.error);

    }

    private View.OnClickListener loginButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //check the validity of password and user name here
            String user=username.getText().toString();
            String pass=password.getText().toString();

            try {
                    if (user.length() == 0 || pass.length() == 0) {
                        throw new NoInputException();
                }

                thisUser=new User(user, pass);
                boolean valid=thisUser.checkLogin();

                if (valid) {

                    Intent intent = new Intent(UI_Login.this, UI_PersonalPage.class);
                    intent.putExtra("user",thisUser);
                    startActivity(intent);
                } else {
                    throw new InvalidAccountException();

                }
            }
            catch (Exception e){
                error.setText(e.getMessage());
            }
        }
    };

    private View.OnClickListener registerButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //just navigate to register page,no more actions needed
            Intent intent = new Intent(UI_Login.this, UI_Register.class);
            startActivity(intent);
        }
    };


}
