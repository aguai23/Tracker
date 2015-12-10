package com.tracker.tracker.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.tracker.tracker.R;
import com.tracker.tracker.model.PersonalInfo;
import com.tracker.tracker.model.User;

import java.util.ArrayList;

public class UI_Notification extends Activity {

    private Button back;
    private ListView list;
    private User thisUser;
    private RequestAdapter myAdapter;
    private ArrayList<String>request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_page);
        Intent intent=getIntent();
        thisUser=(User)intent.getSerializableExtra("user");
        request=new ArrayList<>();
        request=thisUser.getPendings();
        list=(ListView)findViewById(R.id.list);
        back=(Button)findViewById(R.id.back);
        myAdapter=new RequestAdapter(this,request);

        list.setAdapter(myAdapter);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UI_Notification.this, UI_TrackeePage.class);
                intent.putExtra("user",thisUser);
                startActivity(intent);
            }
        });

    }

    private class RequestAdapter extends ArrayAdapter<String>{

        public RequestAdapter(Context context,ArrayList<String>requests) {
            super(context,R.layout.request_item,requests);
        }

        public View getView(final int position, View convertView, ViewGroup parent){
            if(convertView==null){
                convertView=getLayoutInflater().inflate(R.layout.request_item,null);
            }

            TextView contact=(TextView)convertView.findViewById(R.id.contact);
            Button accept=(Button)convertView.findViewById(R.id.accept);
            Button reject=(Button)convertView.findViewById(R.id.reject);
            String current=getItem(position);
            contact.setText(current);

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    thisUser.addFollowers(getItem(position));

                    request.remove(position);

                    myAdapter.notifyDataSetChanged();

                }
            });

            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    thisUser.rejectRequest(position);


                    request.remove(position);
                    myAdapter.notifyDataSetChanged();
                }
            });

            return convertView;
        }
    }

}
