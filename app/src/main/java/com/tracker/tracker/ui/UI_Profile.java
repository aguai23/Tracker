package com.tracker.tracker.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
    private PersonalInfo contact;
    private LinearLayout buttonLayout;
    private String from;
    private String contactName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username=(TextView)findViewById(R.id.user_name);
        name=(TextView)findViewById(R.id.name);
        phone=(TextView)findViewById(R.id.phone);
        email=(TextView)findViewById(R.id.email);
        delete=(Button)findViewById(R.id.delete);
        buttonLayout=(LinearLayout)findViewById(R.id.buttonLayout);


        Intent intent=getIntent();
        thisUser=(User)intent.getSerializableExtra("user");
        from=(String)intent.getStringExtra("from");
        if(from.equals("notification")){
            buttonLayout.setVisibility(View.GONE);
        }
        contactName=(String)intent.getStringExtra("contact");
        contact=thisUser.get_user(contactName);
        username.setText(contact.getUsername());
        name.setText(contact.getName());
        phone.setText(contact.getPhone());
        email.setText(contact.getEmail());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call methods to delete this contact
                AlertDialog.Builder builder = new AlertDialog.Builder(UI_Profile.this);
                builder.setMessage("Are you sure you want to delete this contact");
                builder.setTitle("Delete Alert");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (from.equals("follower")) {
                                    Intent intent = new Intent(UI_Profile.this, UI_TrackeePage.class);
                                    thisUser.deleteFollowers(contactName);
                                    intent.putExtra("user", thisUser);
                                    startActivity(intent);
                                }
                                if (from.equals("following")) {
                                    Intent intent = new Intent(UI_Profile.this, UI_TrackingPage.class);
                                    thisUser.deleteFollowing(contactName);
                                    intent.putExtra("user", thisUser);
                                    startActivity(intent);
                                }
                                if (from.equals("notification")) {

                                }
                            }
                        }


                );
                builder.setNegativeButton("no", new DialogInterface.OnClickListener()

                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }

                );
                builder.show();

            }
        });

        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UI_Profile.this,UI_TrackeePage.class);
                intent.putExtra("user", thisUser);
                startActivity(intent);
            }
        });




    }

}
