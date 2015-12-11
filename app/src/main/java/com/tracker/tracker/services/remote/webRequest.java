package com.tracker.tracker.services.remote;

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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to send request, and receive response
 * from the remote service
 */
public class webRequest implements Serializable{

    public static String url = "http://128.237.163.155:8000";
    public HttpURLConnection connection;

    public webRequest() {
    }

    public enum REQUEST {
        ADD_USER,
        SEND_LOCATION,
        GET_USER,
        GET_LOCATIONS,
        GET_FOLLOWING,
        GET_FOLLOWERS,
        ADD_RELATION,
        DELETE_RELATION,
        PENDING_REQUEST,
        ADD_REQUEST
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
                    request = new String("/tracker/users?username=[" + (String) obj.get(DbOperation.username) + "]");
                    break;
                case GET_LOCATIONS:
                    request = new String("/tracker/locations?username=[" + (String) obj.get(DbOperation.username) + "]");
                    break;
                case SEND_LOCATION:
                    request = new String("/tracker/location");
                    break;
                case GET_FOLLOWING:
                    request = new String("/tracker/get_tracking_users?username=[" + (String) obj.get(DbOperation.username) + "]");
                    break;
                case GET_FOLLOWERS:
                    request = new String("/tracker/get_trackee_users?username=[" + (String) obj.get(DbOperation.username) + "]");
                    break;
                case ADD_RELATION:
                    request = new String("/tracker/add_relation");
                    break;
                case DELETE_RELATION:
                    request = new String("/tracker/get_trackee_users?username=[" + (String) obj.get(DbOperation.username)
                            + "]"+"&tracking=["+(String) obj.get(DbOperation.tracking)+"]");
                    break;
                case PENDING_REQUEST:
                    request = new String("/tracker/requests?username=[" + (String) obj.get(DbOperation.username) + "]");
                    break;
                case ADD_REQUEST:
                    request = new String("/tracker/add_request");
                    break;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return request;
    }

    public JSONObject request;

    public REQUEST req;

    public REQUEST_TYPE type;

    public StringBuilder sb = new StringBuilder();

    public String send_request(JSONObject request_new, REQUEST req_new, REQUEST_TYPE type_new) {
        request = request_new;
        req = req_new;
        type = type_new;

        try {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String header = createHeader(request, req); // <<- HEADER -- unused

                        String newurl = new String(url.concat(header));

                        URL uri = new URL(newurl); // TODO: or url.concat(header)

                        HttpURLConnection con = (HttpURLConnection) uri.openConnection();

                        if (type == REQUEST_TYPE.POST) {
                            con.setRequestMethod("POST");
                            con.setDoOutput(true);
                            con.setDoInput(true);
                        } else {
                            con.setRequestMethod("GET");
                            con.setDoOutput(true);
                            con.setDoInput(true);
                        }

                        String jsonstr = new String(request.toString());
                        System.out.println(jsonstr);

                        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(), "utf-8");
                        BufferedWriter br = new BufferedWriter(wr);

                        br.write(new String("data="+request.toString())); // <<- BODY
                        br.flush();

                        int HttpResult = con.getResponseCode();
                        if (HttpResult == HttpURLConnection.HTTP_OK) {
                            BufferedReader br1 = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                            String line = null;

                            while ((line = br1.readLine()) != null) {
                                sb.append(line + "\n");
                            }
                            br1.close();

                            System.out.println("" + sb.toString());

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
        return send_request(obj, webRequest.REQUEST.ADD_USER, webRequest.REQUEST_TYPE.POST) == null?false:true;
    }

    public void SendLocation(DeviceLocation loc){
        JSONObject obj = new JSONObject();
        try {
            obj.put(DbOperation.latitude, Double.toString(loc.getLattitude()));
            obj.put(DbOperation.longitude, Double.toString(loc.getLongitude()));
            obj.put(DbOperation.timestamp, loc.getTimestamp());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        send_request(obj, REQUEST.SEND_LOCATION, REQUEST_TYPE.POST);
    }

    public ArrayList<DeviceLocation> get_location(String user){
        ArrayList<DeviceLocation> loc = new ArrayList<DeviceLocation>();
        JSONObject obj_req = new JSONObject();
        try {
            obj_req.put(DbOperation.username, user);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String response = this.send_request(obj_req, webRequest.REQUEST.GET_USER, webRequest.REQUEST_TYPE.GET);

        return loc;
    }

}
