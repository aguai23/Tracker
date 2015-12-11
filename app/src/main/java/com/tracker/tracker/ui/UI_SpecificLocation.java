package com.tracker.tracker.ui;

import android.app.Activity;
import android.app.AlertDialog;
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
import com.tracker.tracker.exception.NoLocationsException;
import com.tracker.tracker.exception.TimeConflictException;
import com.tracker.tracker.model.User;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
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
    private String contact;
    private ArrayList<LatLng>points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_location);
        Intent intent=getIntent();
        thisUser=(User)intent.getSerializableExtra("user");
        contact=(String)intent.getStringExtra("contact");
        points=new ArrayList<>();

        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(backButtonListener);


        from=(Spinner)findViewById(R.id.from);

        to=(Spinner)findViewById(R.id.to);


        Timestamp now=new Timestamp(new Date().getTime());

        Calendar c=Calendar.getInstance();
        c.setTimeInMillis(now.getTime());

        choice=new ArrayList<>();
        ArrayList<String>time=new ArrayList<>();
        choice.add(new Timestamp(c.getTimeInMillis()));

        for(int i=0;i<24;i++){

            c.add(Calendar.HOUR,-1);

            choice.add(new Timestamp(c.getTimeInMillis()));

        }


        for(int i=0;i<choice.size();i++){
            time.add(choice.get(i).toString());

        }

        ArrayAdapter<String>timeAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,time);
        from.setAdapter(timeAdapter);
        to.setAdapter(timeAdapter);
        from.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        to.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        from.setVisibility(View.GONE);
        to.setVisibility(View.GONE);


        ok=(Button)findViewById(R.id.ok);
        ok.setOnClickListener(okButtonListener);
        ok.setVisibility(View.GONE);
        MapFragment mapFragment=(MapFragment)getFragmentManager().findFragmentById(R.id.map);
        map=mapFragment.getMap();
        mapFragment.getMapAsync(this);

        locations=thisUser.get_location(contact);
        if(locations.size()==0){
            ok.setClickable(false);
        }
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
            String fromTime = from.getSelectedItem().toString();
            String toTime = to.getSelectedItem().toString();
            Timestamp fromStamp = Timestamp.valueOf(fromTime);
            Timestamp toStamp = Timestamp.valueOf(toTime);

            try {
                if (!fromStamp.before(toStamp)) {
                    throw new TimeConflictException();
                }
                points.clear();
                for(int i=0;i<locations.size();i++){
                    ArrayList<Timestamp> times=new ArrayList<>(locations.keySet());
                    if(times.get(i).after(fromStamp) && times.get(i).before(toStamp)){
                        Pair<Double,Double>location=locations.get(times.get(i));
                        LatLng point=new LatLng(location.first,location.second);
                        points.add(point);
                        Date time = new Date(times.get(i).getTime());
                        String date = new SimpleDateFormat("MM-dd HH:mm").format(time);
                        map.addMarker(new MarkerOptions()
                                .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("marker", 30, 30)))

                                .title(contact)
                                //.snippet(times.get(i).toString())
                                .snippet(date)
                                .position(point)).showInfoWindow();

                    }


                }
            }
            catch (Exception e){
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(UI_SpecificLocation.this);

                // set dialog title & message, and provide Button to dismiss
                builder.setTitle("Time conflict");
                builder.setMessage("from time must be ealier");
                builder.setPositiveButton("OK", null);
                builder.show(); // display the Dialog
            }
            System.out.println(fromStamp.toString());




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
        try {
            if(locations.size()==0){
                throw new NoLocationsException();
            }

            PolylineOptions line=new PolylineOptions().geodesic(true).color(Color.GREEN);

            ArrayList<Timestamp> times=new ArrayList<>(locations.keySet());
            Collections.sort(times);
            for(int i=0;i<locations.size();i++){

                    Pair<Double,Double>location=locations.get(times.get(i));
                    LatLng point=new LatLng(location.first,location.second);
                    line.add(point);
                    points.add(point);
                    Date time = new Date(times.get(i).getTime());
                    String date = new SimpleDateFormat("MMMM dd HH:mm", Locale.ENGLISH).format(time);
                    map.addMarker(new MarkerOptions()
                            .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("marker", 30, 30)))

                            .title(contact)
                            .snippet(date)
                            .position(point)).showInfoWindow();

                }

            map.addPolyline(line);




            /*Pair<String, Pair<Double, Double>> location = getLatest(locations);

            LatLng thisLocation = new LatLng(location.second.first, location.second.second);

            map.setMyLocationEnabled(true);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(thisLocation, 13));



            map.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("marker", 30, 30)))

                    .title(contact)
                    .snippet(location.first)
                    .position(thisLocation)).showInfoWindow();*/


        }
        catch (Exception e){
            Toast.makeText(UI_SpecificLocation.this, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

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


    public Pair<String,Pair<Double,Double>> getLatest(Map<Timestamp,Pair<Double,Double>>locations){
        ArrayList<Timestamp>times=new ArrayList<>(locations.keySet());

        Collections.sort(times);
        Timestamp time = times.get(times.size() - 1);
        Pair location=new Pair(time.toString(), locations.get(time));
        return location;

    }





}
