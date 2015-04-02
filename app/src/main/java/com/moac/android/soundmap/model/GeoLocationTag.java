package com.moac.android.soundmap.model;

public class GeoLocationTag {

    private double lat;
    private double lon;

    public GeoLocationTag(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLatitude() {
        return lat;
    }

    public double getLongitude() {
        return lon;
    }

    @Override
    public String toString() {
        return lat + "," + lon;
    }
}
