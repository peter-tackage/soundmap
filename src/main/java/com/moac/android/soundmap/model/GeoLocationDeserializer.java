package com.moac.android.soundmap.model;

import android.util.Log;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Scanner;

public class GeoLocationDeserializer implements JsonDeserializer<GeoLocation> {

    private static final String TAG = GeoLocationDeserializer.class.getSimpleName();
    private static final double UNSET = -1d;

    // Format is: "someword "two words" geo:lat=52.527544 geo:lon=-22.527432 "more words"
    public GeoLocation deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {

    //    System.out.println("Input: " + json.getAsString());
        Scanner scanner = new Scanner(json.getAsString());
        double lat = parseGeoTag(scanner.findInLine("geo:lat=[+\\-]?(\\d+)\\.(\\d+)"));
        double lon = parseGeoTag(scanner.findInLine("geo:lon=[+\\-]?(\\d+)\\.(\\d+)"));

     //   System.out.println("Got: " + lat + "," + lon);
        if(lat == UNSET || lon == UNSET)
            return null;

        return new GeoLocation(lat, lon);
    }

    // Extract the number from String like "geo:lat=52.527544"
    private static double parseGeoTag(String rawTag) {
        System.out.println(" raw: " + rawTag);
        if(rawTag == null)
            return UNSET;

        String[] tokens = rawTag.split("=", 2);
        if(tokens.length != 2)
            return UNSET;

        try {
            return Double.valueOf(tokens[1]);
        } catch(NumberFormatException nfe) {
            return UNSET;
        }
    }
}