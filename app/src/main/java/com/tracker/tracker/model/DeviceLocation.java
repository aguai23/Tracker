package com.tracker.tracker.model;

/**
 * Device Location information
 */
public class DeviceLocation {
    private String timestamp;
    private Double lattitude;
    private Double longitude;

    public DeviceLocation(String timestamp, Double longitude, Double lattitude) {
        this.timestamp = timestamp;
        this.longitude = longitude;
        this.lattitude = lattitude;
    }

    public String getTimestamp() {

        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLattitude() {
        return lattitude;
    }

    public void setLattitude(Double lattitude) {
        this.lattitude = lattitude;
    }
}
