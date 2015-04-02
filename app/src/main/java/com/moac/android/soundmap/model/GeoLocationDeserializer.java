package com.moac.android.soundmap.model;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Scanner;

public class GeoLocationDeserializer implements JsonDeserializer<GeoLocationTag> {

    private static final String TAG = GeoLocationDeserializer.class.getSimpleName();
    private static final double UNSET = -1d;

    // Format is: "someword "two words" geo:lat=52.527544 geo:lon=-22.527432 "more words"
    public GeoLocationTag deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        // FIXME Shouldn't have to use two scanners.
        // The issue with using one is that calls to findInLine move the
        // start of the next findInLine call to the end of the last find.
        // This causes it to break when the lat/lon appear in different
        // orders in the String.
        Log.i(TAG, "JSon: " + json.getAsString());
        Scanner scannerLat = new Scanner(json.getAsString());
        Scanner scannerLon = new Scanner(json.getAsString());
        try {
            double lat = parseGeoTag(scannerLat.findInLine("geo:lat=[+\\-]?(\\d+)\\.(\\d+)"));
            if (lat == UNSET) return null;
            double lon = parseGeoTag(scannerLon.findInLine("geo:lon=[+\\-]?(\\d+)\\.(\\d+)"));
            if (lon == UNSET) return null;
            return new GeoLocationTag(lat, lon);
        } finally {
            scannerLat.close();
            scannerLon.close();
        }
    }

    // Extract the number from String like "geo:lat=52.527544"
    private static double parseGeoTag(String rawTag) {
        if (rawTag == null)
            return UNSET;

        String[] tokens = rawTag.split("=", 2);
        if (tokens.length != 2)
            return UNSET;

        try {
            return Double.valueOf(tokens[1]);
        } catch (NumberFormatException nfe) {
            return UNSET;
        }
    }
}