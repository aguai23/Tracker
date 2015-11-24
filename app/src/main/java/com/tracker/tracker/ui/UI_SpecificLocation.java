package com.tracker.tracker.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.tracker.tracker.R;
import com.tracker.tracker.model.User;

import java.sql.Timestamp;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class UI_SpecificLocation extends Activity {

    private Spinner from;
    private Spinner to;
    private GoogleMap map;
    private ArrayList<Pair<Location,Timestamp>>locations;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_location);

        Button back=(Button)findViewById(R.id.back);
        back.setOnClickListener(backButtonListener);


        from=(Spinner)findViewById(R.id.from);

        to=(Spinner)findViewById(R.id.to);

        Button ok=(Button)findViewById(R.id.ok);
        ok.setOnClickListener(okButtonListener);

        MapFragment mapFragment=(MapFragment)getFragmentManager().findFragmentById(R.id.map);
        map=mapFragment.getMap();




    }

    private View.OnClickListener backButtonListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(UI_SpecificLocation.this, UI_PersonalPage.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener okButtonListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String fromTime=from.getSelectedItem().toString();
            String toTime=to.getSelectedItem().toString();
            Timestamp fromStamp=parseTimeString(fromTime);
            Timestamp toStamp=parseTimeString(toTime);
            locations=user.get_location(fromStamp,toStamp);
            //put these locations onto the map
        }
    };

    public Timestamp parseTimeString(String time){
        Timestamp result=new Timestamp(100);

        return result;
    }

}
