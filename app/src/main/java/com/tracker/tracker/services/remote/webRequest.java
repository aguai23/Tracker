package com.tracker.tracker.services.remote;

/**
 * Class to send request, and receive response
 * from the remote service
 */
public class webRequest {

    public enum REQUEST_TYPE{
        SEND_LOCATION
    }

    private String createRequest(String data, REQUEST_TYPE type){
        String request = null;

        switch (type){
            case SEND_LOCATION:
                break;
        }

        return request;
    }

    public String send_request(String payload){
        return null;
    }

    public void SendLocation(double lattitude, double longitude){
        String request = createRequest(Double.toString(lattitude)+","+Double.toString(longitude), REQUEST_TYPE.SEND_LOCATION);

        // send request to web service
    }
}
