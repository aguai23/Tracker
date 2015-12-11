package com.tracker.tracker.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tracker.tracker.R;
import com.tracker.tracker.model.PersonalInfo;
import com.tracker.tracker.model.User;

public class UI_Search_contact extends Activity {

    private TextView result;
    private Button search;
    private EditText phone;
    private User thisUser;
    private Button back;
    private PersonalInfo searchInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);
        Intent intent=getIntent();
        thisUser=(User)intent.getSerializableExtra("user");
        phone=(EditText)findViewById(R.id.phone);

        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UI_Search_contact.this, UI_TrackingPage.class);
                intent.putExtra("user",thisUser);
                startActivity(intent);
            }
        });
        result=(TextView)findViewById(R.id.result);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchInfo!=null) {
                    Intent intent = new Intent(UI_Search_contact.this, UI_addcontact.class);
                    intent.putExtra("user", thisUser);
                    intent.putExtra("search", searchInfo);
                    startActivity(intent);
                }


            }
        });

        search=(Button)findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneString=phone.getText().toString();
                searchInfo=thisUser.search_user(phoneString);
                if(searchInfo==null)
                    result.setText("no result");
                else
                    result.setText(searchInfo.getUsername());
            }
        });



    }

}
