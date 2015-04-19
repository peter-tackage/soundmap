package com.moac.android.soundmap.api.model;

public class GeoLocation {

    private final double latitude;
    private final double longitude;

    public GeoLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return latitude + "," + longitude;
    }
}
