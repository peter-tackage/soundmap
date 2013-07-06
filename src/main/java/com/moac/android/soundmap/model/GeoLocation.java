package com.moac.android.soundmap.model;

public class GeoLocation {

    public GeoLocation(double lat, double lon) {
        mGeoLat = lat;
        mGeoLong = lon;
    }

    protected double mGeoLat;
    protected double mGeoLong;

    public double getGeoLat() { return mGeoLat; }
    public double getGeoLong() { return mGeoLong; }

    /**
     * Populate from the tags: "/tracks?tags=geo:"
     */
}
