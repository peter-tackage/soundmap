package com.moac.android.soundmap.model;

public class GeoLocation {

    public GeoLocation(double lat, double lon) {
        mLat = lat;
        mLong = lon;
    }

    protected double mLat;
    protected double mLong;

    public double getLatitude() { return mLat; }

    public double getLongitude() { return mLong; }

    /**
     * Populate from the tags: "/tracks?tags=geo:"
     */
}
