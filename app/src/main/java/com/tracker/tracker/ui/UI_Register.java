package com.tracker.tracker.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tracker.tracker.R;
import com.tracker.tracker.model.User;

public class UI_Register extends Activity {

    private Button submitButton;
    private EditText username;
    private EditText password;
    private EditText name;
    private EditText phone;
    private EditText email;
    private User thisUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(submitButtonListener);

        username=(EditText)findViewById(R.id.user_name);
        password=(EditText)findViewById(R.id.password);
        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        email=(EditText)findViewById(R.id.email);
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
                serverValid=thisUser.checkRegister(user,pass,nameString,phoneString,emailString);
                inputValid=checkValid(user,pass,nameString,phoneString,emailString);
                if(serverValid==false){

                }
                if(inputValid==false){

                }
                if(serverValid==true && inputValid==true){
                    thisUser.register(user,pass,nameString,phoneString,emailString);
                    Intent intent = new Intent(UI_Register.this, UI_PersonalPage.class);
                    startActivity(intent);
                }
            }
            catch (Exception e){

            }


        }
    };

    private boolean checkValid(String username,String password,String name,String phone,String email){
        return true;
    }

}
