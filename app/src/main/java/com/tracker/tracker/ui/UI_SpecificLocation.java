package com.tracker.tracker.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.tracker.tracker.R;
import com.tracker.tracker.model.User;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;

public class UI_SpecificLocation extends Activity implements OnMapReadyCallback {

    private Spinner from;
    private Spinner to;
    private GoogleMap map;
    private Map<Timestamp,Pair<Double,Double>> locations;
    private User thisUser;
    private Button back;
    private Button ok;
    private ArrayList<Timestamp>choice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_location);
        Intent intent=getIntent();
        thisUser=(User)intent.getSerializableExtra("user");
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(backButtonListener);


        from=(Spinner)findViewById(R.id.from);

        to=(Spinner)findViewById(R.id.to);


        Timestamp now=new Timestamp(new Date().getTime());

        Calendar c=Calendar.getInstance();
        c.setTimeInMillis(now.getTime());

        choice=new ArrayList<>();
        for(int i=1;i<25;i++){
            int year= c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH);
            int day=c.get(Calendar.DATE);
            int hours=c.get(Calendar.HOUR_OF_DAY);
            hours=hours-i;
            if(hours<0){
                hours+=24;
                day=day-1;
            }
            choice.add(new Timestamp(year,month,day,hours,0,0,0));
        }

        ArrayList<String>time=new ArrayList<>();

        for(int i=0;i<choice.size();i++){
            System.out.println(choice.get(i).toString());
            Calendar c1=Calendar.getInstance();
            c1.setTimeInMillis(choice.get(i).getTime());
            int year= c1.get(Calendar.YEAR)-1900;
            int month=c1.get(Calendar.MONTH);
            int day=c1.get(Calendar.DATE);
            int hours=c1.get(Calendar.HOUR_OF_DAY);
            time.add(year+"-"+month+"-"+day+"-"+hours);

        }

        ArrayAdapter<String>timeAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,time);
        from.setAdapter(timeAdapter);
        to.setAdapter(timeAdapter);
        from.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        to.setOnItemSelectedListener(new CustomOnItemSelectedListener());


        ok=(Button)findViewById(R.id.ok);
        ok.setOnClickListener(okButtonListener);
        ok.setText(now.toString());
        MapFragment mapFragment=(MapFragment)getFragmentManager().findFragmentById(R.id.map);
        map=mapFragment.getMap();
        mapFragment.getMapAsync(this);
    }

    private View.OnClickListener backButtonListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(UI_SpecificLocation.this, UI_TrackingPage.class);
            intent.putExtra("user",thisUser);
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
            System.out.println(fromStamp.toString());

            locations=thisUser.get_location(thisUser.getUsername());

            //put these locations onto the map
        }
    };

    public Timestamp parseTimeString(String time){
        String [] date=time.split("-");
        int year=Integer.parseInt(date[0])-1900;
        int month=Integer.parseInt(date[1]);
        int day=Integer.parseInt(date[2]);
        int hour=Integer.parseInt(date[3]);
        Timestamp result=new Timestamp(year,month,day,hour,0,0,0);

        return result;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng sydney = new LatLng(-33.867, 151.206);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("marker", 30, 30)))

                .title("User1")
                .snippet("The time stamp.")
                .position(sydney)).showInfoWindow();

        map.addPolyline(new PolylineOptions().geodesic(true)
                        .add(new LatLng(-33.866, 151.195))  // Sydney
                        .add(new LatLng(-18.142, 178.431))  // Fiji
                        .add(new LatLng(21.291, -157.821))  // Hawaii
                        .add(new LatLng(37.423, -122.091))// Mountain View
                        .color(Color.GREEN)
        );

    }

    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
            Toast.makeText(parent.getContext(),
                    "You have select : " + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

    }

    public Bitmap resizeMapIcons(String iconName,int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }





}
