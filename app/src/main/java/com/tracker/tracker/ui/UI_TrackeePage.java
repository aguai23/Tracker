package com.tracker.tracker.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tracker.tracker.R;
import com.tracker.tracker.model.PersonalInfo;
import com.tracker.tracker.model.User;

import java.util.ArrayList;

public class UI_TrackeePage extends Activity {

    private Button notificationButton;
    private Button back;
    private User thisUser;
    private ArrayList<String>contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_being_tracked);


        notificationButton = (Button) findViewById(R.id.notificationButton);
        notificationButton.setOnClickListener(notificationButtonListener);


        Intent intent = getIntent();
        thisUser = (User) intent.getSerializableExtra("user");

        contact=thisUser.getFollowers();


        ListView contactList = (ListView) findViewById(R.id.contactlist);
        Adapter myAdapter=new ContactAdapter(this,contact);
        contactList.setAdapter((ListAdapter) myAdapter);

        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(UI_TrackeePage.this,UI_PersonalPage.class);
                intent.putExtra("user",thisUser);
                startActivity(intent);
            }
        });

        if(thisUser.getPendings().size()>0)
            notificationButton.setBackgroundResource(R.drawable.notification);


    }

    private View.OnClickListener notificationButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(UI_TrackeePage.this, UI_Notification.class);
            intent.putExtra("user",thisUser);
            startActivity(intent);
        }
    };

    private class ContactAdapter extends ArrayAdapter<String> {

        public ContactAdapter(Context context, ArrayList<String> requests) {
            super(context, R.layout.contact_item, requests);
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.contact_item, null);
            }

            TextView name = (TextView) convertView.findViewById(R.id.name);
            Button check = (Button) convertView.findViewById(R.id.profile);
            final String thisName=getItem(position);
            name.setText(thisName);


            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //get User object by user name
                    Intent intent=new Intent(UI_TrackeePage.this,UI_Profile.class);
                    intent.putExtra("user",thisUser);
                    intent.putExtra("from","follower");
                    intent.putExtra("contact",getItem(position));
                    startActivity(intent);
                }
            });
            return convertView;
        }

    }
}
