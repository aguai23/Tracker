package com.tracker.tracker.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
import com.tracker.tracker.model.PersonalInfo;
import com.tracker.tracker.model.User;

import java.util.ArrayList;

public class UI_TrackingPage extends Activity implements OnMapReadyCallback {

    private Button listButton;

    private GoogleMap map;
    private User thisUser;
    private Spinner list;
    private Button satellite;
    private ArrayList<String>contact;
    private Button normal;
    private Button search;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_page);
        Intent intent=getIntent();
        contact=new ArrayList<>();
        thisUser=(User)intent.getSerializableExtra("user");
        for (int i=0;i<thisUser.getFollowingSize();i++){
            contact.add(thisUser.getFollowingName(i));
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        ArrayAdapter<String>contactAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,contact);
        listButton = (Button) findViewById(R.id.listButton);
        listButton.setOnClickListener(listButtonListener);

        list=(Spinner)findViewById(R.id.list);
        list.setAdapter(contactAdapter);
        list.setOnItemSelectedListener(new CustomOnItemSelectedListener());


        //display user's location of all followings on the map
        MapFragment mapFragment=(MapFragment)getFragmentManager().findFragmentById(R.id.map);
        map=mapFragment.getMap();
        mapFragment.getMapAsync(this);

        satellite=(Button)findViewById(R.id.satellite);
        satellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });

        normal=(Button)findViewById(R.id.normal);
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });

        search=(Button)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UI_TrackingPage.this,UI_Search_contact.class);
                intent.putExtra("user",thisUser);
                startActivity(intent);
            }
        });


        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UI_TrackingPage.this,UI_PersonalPage.class);
                intent.putExtra("user",thisUser);
                startActivity(intent);
            }
        });

    }





    private View.OnClickListener listButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String contact=list.getSelectedItem().toString();

            Intent intent = new Intent(UI_TrackingPage.this, UI_SpecificLocation.class);
            intent.putExtra("user",thisUser);
            intent.putExtra("contact",contact);
            startActivity(intent);
        }
    };

    @Override
    public void onMapReady(GoogleMap googleMap) {
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
