package com.tracker.tracker.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tracker.tracker.R;
import com.tracker.tracker.exception.InvalidAccountException;
import com.tracker.tracker.exception.NoInputException;
import com.tracker.tracker.exception.UserExistException;
import com.tracker.tracker.model.User;
import com.tracker.tracker.services.local.locationService;

public class UI_Register extends Activity {

    private Button submitButton;
    private EditText username;
    private EditText password;
    private EditText name;
    private EditText phone;
    private EditText email;
    private User thisUser;
    private Button back;
    private TextView error;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        thisUser=new User();

        submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(submitButtonListener);

        username=(EditText)findViewById(R.id.user_name);
        password=(EditText)findViewById(R.id.password);
        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        email=(EditText)findViewById(R.id.email);

        error=(TextView)findViewById(R.id.error);

        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UI_Register.this,UI_Login.class);
                startActivity(intent);
            }
        });
    }

    private View.OnClickListener submitButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String user=username.getText().toString();
            String pass=password.getText().toString();
            String nameString=name.getText().toString();
            String phoneString=phone.getText().toString();
            String emailString=email.getText().toString();

            boolean inputValid;
            boolean serverValid;
            try{
                thisUser = new User();
                inputValid=checkValid(user,pass, nameString, phoneString, emailString);

                if(!inputValid){
                    throw new NoInputException();
                }

                serverValid = thisUser.register(user, pass, nameString, phoneString, emailString);
                if(!serverValid){
                    throw new UserExistException();
                }

                Intent intentservice = new Intent(getApplicationContext(), locationService.class);
                startService(intentservice);

                Intent intent = new Intent(UI_Register.this, UI_PersonalPage.class);
                intent.putExtra("user",thisUser);
                startActivity(intent);
            }
            catch (Exception e){
                error.setText(e.getMessage());

            }


        }
    };

    private boolean checkValid(String username,String password,String name,String phone,String email){
        if(username.length()==0 || password.length()==0 || name.length()==0 || phone.length()==0
                || email.length()==0)
        return false;

        return true;
    }

}
