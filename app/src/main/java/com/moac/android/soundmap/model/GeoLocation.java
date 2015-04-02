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

    @Override
    public String toString() {
        return mLat + "," + mLong;
    }
}
