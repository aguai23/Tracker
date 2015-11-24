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

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(loginButtonListener);

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(registerButtonListener);

        username=(EditText)findViewById(R.id.user_name);
        password=(EditText)findViewById(R.id.password);

        error=(TextView)findViewById(R.id.error);

        thisUser=new User();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener loginButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //check the validity of password and user name here
            String user=username.getText().toString();
            String pass=password.getText().toString();

            boolean valid=thisUser.checkLogin(user,pass);
            try {
                if (user.length() == 0 || pass.length() == 0) {
                    throw new NoInputException();
                }

                if (valid) {
                    Intent intent = new Intent(UI_Login.this, UI_PersonalPage.class);
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
