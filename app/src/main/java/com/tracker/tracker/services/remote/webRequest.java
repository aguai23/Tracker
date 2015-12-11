package com.tracker.tracker.services.remote;

import android.util.Pair;

import com.tracker.tracker.dbLayout.DbOperation;
import com.tracker.tracker.model.DeviceLocation;
import com.tracker.tracker.model.PersonalInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to send request, and receive response
 * from the remote service
 */
public class webRequest implements Serializable{

    public static String url = "http://128.237.180.213:8000";

    public String request;

    public REQUEST req;

    public REQUEST_TYPE type;

    public StringBuilder sb = new StringBuilder();

    public webRequest() {
    }

    public enum REQUEST {
        ADD_USER,
        DELETE_USER,
        SEND_LOCATION,
        GET_USER,
        GET_USER_PHONE,
        GET_LOCATIONS,
        GET_FOLLOWING,
        GET_FOLLOWERS,
        ADD_RELATION,
        DELETE_RELATION,
        PENDING_REQUEST,
        ADD_REQUEST,
        DELETE_REQUEST
    }

    public enum REQUEST_TYPE {
        GET,
        POST
    }

    private String createHeader(JSONObject obj, REQUEST req) {
        String request = null;
        try {
            switch (req) {
                case ADD_USER:
                    request =  new String("/tracker/add_user");
                    break;
                case GET_USER:
                    request = new String("/tracker/users?username=" + (String) obj.get(DbOperation.username));
                    break;
                case GET_LOCATIONS:
                    request = new String("/tracker/locations?username=" + (String) obj.get(DbOperation.username));
                    break;
                case SEND_LOCATION:
                    request = new String("/tracker/location");
                    break;
                case GET_FOLLOWING:
                    request = new String("/tracker/get_tracking_users?username=" + (String) obj.get(DbOperation.username));
                    break;
                case GET_FOLLOWERS:
                    request = new String("/tracker/get_trackee_users?username=" + (String) obj.get(DbOperation.username));
                    break;
                case ADD_RELATION:
                    request = new String("/tracker/add_relation");
                    break;
                case DELETE_RELATION:
                    request = new String("/tracker/get_trackee_users?username=" + (String) obj.get(DbOperation.username)
                            +"&tracking="+(String) obj.get(DbOperation.tracking));
                    break;
                case PENDING_REQUEST:
                    request = new String("/tracker/requests?username=" + (String) obj.get(DbOperation.username));
                    break;
                case ADD_REQUEST:
                    request = new String("/tracker/add_request");
                    break;
                case DELETE_USER:
                    request = new String("/tracker/delete_user?username="+ (String) obj.get(DbOperation.username));
                    break;
                case DELETE_REQUEST:
                    request = new String("/tracker/delete_request");
                    break;
                case GET_USER_PHONE:
                    request = new String("/tracker/users?phone="+(String) obj.get(DbOperation.phone));
                    break;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return request;
    }

    public void delete_request(String from, String to){
        JSONObject obj = new JSONObject();
        try {
            obj.put(DbOperation.username, from);
            obj.put(DbOperation.tracking, to);
            send_request(obj, REQUEST.DELETE_REQUEST, REQUEST_TYPE.POST);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void add_request(String from, String to){
        JSONObject obj = new JSONObject();
        try {
            obj.put(DbOperation.username, from);
            obj.put(DbOperation.tracking, to);
            send_request(obj, REQUEST.ADD_REQUEST, REQUEST_TYPE.POST);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> get_following(String username){
        ArrayList<String> following = new ArrayList<String>();
        JSONObject obj = new JSONObject();
        try {
            obj.put(DbOperation.username, username);
            String response = send_request(obj, REQUEST.GET_FOLLOWERS, REQUEST_TYPE.GET);
            JSONArray following_list = new JSONArray(response);
            for(int idx = 0; idx < following_list.length(); idx++){
                following.add(following_list.getString(idx));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return following;
    }

    public ArrayList<String> get_pending(String username){
        ArrayList<String> pending = new ArrayList<String>();
        JSONObject obj = new JSONObject();
        try {
            obj.put(DbOperation.username, username);
            String response = send_request(obj, REQUEST.GET_FOLLOWERS, REQUEST_TYPE.GET);
            JSONArray pending_list = new JSONArray(response);
            for(int idx = 0; idx < pending_list.length(); idx++){
                pending.add(pending_list.getString(idx));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pending;
    }

    public boolean add_relation(String from, String to){
        JSONObject obj = new JSONObject();
        try {
            obj.put(DbOperation.username, from);
            obj.put(DbOperation.tracking, to);

            String response = send_request(obj, REQUEST.ADD_RELATION, REQUEST_TYPE.GET);

            JSONObject obj_res = new JSONObject(response);
            if(!((String)obj_res.get("error")).equalsIgnoreCase("none")){
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return true;
    }

    public void delete_relation(String from, String to) {
        JSONObject obj = new JSONObject();
        try {
            obj.put(DbOperation.username, from);
            obj.put(DbOperation.tracking, to);

            String response = send_request(obj, REQUEST.DELETE_RELATION, REQUEST_TYPE.GET);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<String> get_followers(String username){
        ArrayList<String> followers = new ArrayList<String>();
        JSONObject obj = new JSONObject();

        try {
            obj.put(DbOperation.username, username);
            String response = send_request(obj, REQUEST.GET_FOLLOWERS, REQUEST_TYPE.GET);
            JSONArray follower_list = new JSONArray(response);
            for(int idx = 0; idx < follower_list.length(); idx++){
                followers.add(follower_list.getString(idx));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return followers;
    }

    public String send_request(JSONObject request_new, REQUEST req_new, REQUEST_TYPE type_new) {
        request = request_new.toString();
        req = req_new;
        type = type_new;

        try {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject jsonrequest = new JSONObject(request);

                        String header = createHeader(jsonrequest, req); // <<- HEADER

                        String newurl = new String(url.concat(header));

                        URL uri = new URL(newurl);

                        HttpURLConnection con = (HttpURLConnection) uri.openConnection();

                        if (type == REQUEST_TYPE.POST) {
                            con.setRequestMethod("POST");
                            con.setDoOutput(true);
                            con.setDoInput(true);
                            String jsonstr = new String(jsonrequest.toString());
                            System.out.println(jsonstr);

                            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(), "utf-8");
                            BufferedWriter br = new BufferedWriter(wr);

                            br.write(new String("data=" + jsonrequest.toString())); // <<- BODY
                            br.flush();
                        } else {
                            con.setRequestMethod("GET");
                            con.setDoOutput(false);
                            con.setDoInput(true);
                        }

                        int HttpResult = con.getResponseCode();
                        if (HttpResult == HttpURLConnection.HTTP_OK) {
                            BufferedReader br1 = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                            String line = null;

                            while ((line = br1.readLine()) != null) {
                                sb.append(line + "\n");
                            }
                            br1.close();

                            System.out.println("Response::" + sb.toString());
                        }

                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    } catch (ProtocolException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();
            thread.join();
        } catch (Exception e){
            e.printStackTrace();
        }

        return sb.toString();
    }

    public PersonalInfo login(String username, String pwd){
        PersonalInfo info = null;

        JSONObject obj_req = new JSONObject();
        try {
            obj_req.put(DbOperation.username, username);
            String response = this.send_request(obj_req, webRequest.REQUEST.GET_USER, webRequest.REQUEST_TYPE.GET);
            if(response.isEmpty()){
                return null;
            }

            JSONObject obj =  new JSONObject(response);

            if(!((String)obj.get("error")).equalsIgnoreCase("none")){
                return null;
            }

            if(pwd.equalsIgnoreCase((String)obj.get((String)DbOperation.password))){
                info = new PersonalInfo((String)obj.get(DbOperation.username), (String)obj.get(DbOperation.name),
                        (String)obj.get((String)DbOperation.phone), (String)obj.get((String)DbOperation.email));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return info;
    }

    public boolean register_user(JSONObject obj){
        boolean success = true;
        String response =  send_request(obj, webRequest.REQUEST.ADD_USER, webRequest.REQUEST_TYPE.POST);
        JSONObject obj_res = null;
        try {
            obj_res = new JSONObject(response);
            String re = new String((String) obj_res.get("error"));

            System.out.println(re);

            if(!((String)obj_res.get("error")).equalsIgnoreCase("None")){
                success = false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return success;
    }

    public void send_location(DeviceLocation loc){
        JSONObject obj = new JSONObject();
        try {
            obj.put(DbOperation.latitude, Double.toString(loc.getLattitude()));
            obj.put(DbOperation.longitude, Double.toString(loc.getLongitude()));

            String ts_str = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date());

            obj.put(DbOperation.timestamp, ts_str);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        send_request(obj, REQUEST.SEND_LOCATION, REQUEST_TYPE.POST);
    }

    public Map<Timestamp,Pair<Double,Double>> get_location(String user){
        Map<Timestamp,Pair<Double,Double>> locationMap = new HashMap<Timestamp,Pair<Double,Double>>();

        JSONObject obj_req = new JSONObject();
        try {
            obj_req.put(DbOperation.username, user);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String response = this.send_request(obj_req, REQUEST.GET_LOCATIONS, webRequest.REQUEST_TYPE.GET);
        try {
            JSONArray location_list = new JSONArray(response);

            for(int idx = 0; idx < location_list.length(); idx++){
                JSONObject obj = location_list.getJSONObject(idx);
                String timestr = (String)obj.get(DbOperation.timestamp);
                String latstr = (String)(String)obj.get(DbOperation.latitude);
                String lonstr = (String)(String)obj.get(DbOperation.longitude);

                if(!timestr.isEmpty() && !latstr.isEmpty() && !lonstr.isEmpty()){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                    Date parsedDate = null;
                    parsedDate = dateFormat.parse(timestr);
                    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                    locationMap.put(timestamp, new Pair<Double, Double>(Double.parseDouble(latstr), Double.parseDouble(lonstr)));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        }

        return locationMap;
    }

    public PersonalInfo search_user(String phone){
        PersonalInfo info = null;
        JSONObject obj_req = new JSONObject();
        try {
            obj_req.put(DbOperation.phone, phone);
            String response = this.send_request(obj_req, REQUEST.GET_USER_PHONE, webRequest.REQUEST_TYPE.GET);
            if(response.isEmpty()){
                return null;
            }

            JSONObject obj =  new JSONObject(response);

            if(!((String)obj.get("error")).equalsIgnoreCase("none")){
                return null;
            }

            info = new PersonalInfo((String)obj.get(DbOperation.username), (String)obj.get(DbOperation.name),
                    (String)obj.get((String)DbOperation.phone), (String)obj.get((String)DbOperation.email));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return info;
    }

    public PersonalInfo get_user(String username){
        PersonalInfo info = null;

        JSONObject obj_req = new JSONObject();
        try {
            obj_req.put(DbOperation.username, username);
            String response = this.send_request(obj_req, webRequest.REQUEST.GET_USER, webRequest.REQUEST_TYPE.GET);
            if(response.isEmpty()){
                return null;
            }

            JSONObject obj =  new JSONObject(response);

            if(!((String)obj.get("error")).equalsIgnoreCase("none")){
                return null;
        }

        info = new PersonalInfo((String)obj.get(DbOperation.username), (String)obj.get(DbOperation.name),
                (String)obj.get((String)DbOperation.phone), (String)obj.get((String)DbOperation.email));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return info;
    }

    public void delete_user(JSONObject obj){
        send_request(obj, REQUEST.DELETE_USER, REQUEST_TYPE.GET);
    }
}
