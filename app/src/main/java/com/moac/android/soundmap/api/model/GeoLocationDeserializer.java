package com.moac.android.soundmap.api.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.moac.android.soundmap.model.GeoLocation;

import java.lang.reflect.Type;

public final class GeoLocationDeserializer implements JsonDeserializer<GeoLocation> {

    private static final String TAG = GeoLocationDeserializer.class.getSimpleName();

    @Override
    public GeoLocation deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String text = json.getAsString().trim();
        // Format example: "41.0082325664 28.9731252193"
        String[] latLong = text.split(" ", 2);
        if (latLong.length != 2) {
            throw new JsonParseException(String.format("Unable to deserialize latitude/long values from: %s", text));
        }
        return new GeoLocation(Double.valueOf((latLong[0])), Double.valueOf((latLong[1])));
    }

}