package com.tracker.tracker.model;

import java.sql.Timestamp;

/**
 * Device Location information
 */
public class DeviceLocation {
    private Double lattitude;
    private Double longitude;

    public DeviceLocation(Timestamp timestamp, Double longitude, Double lattitude) {
        this.longitude = longitude;
        this.lattitude = lattitude;
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
