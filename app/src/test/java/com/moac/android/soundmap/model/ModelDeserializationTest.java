package com.moac.android.soundmap.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.moac.android.soundmap.api.model.GeoLocation;
import com.moac.android.soundmap.api.model.GeoLocationDeserializer;
import com.moac.android.soundmap.api.model.SoundJsonModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class ModelDeserializationTest {

    // FIXME Inject this
    Gson gson;

    @Before
    public void setup() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(GeoLocation.class, new GeoLocationDeserializer());
        gson = gsonBuilder.create();
    }

    @After
    public void tearDown() {
        gson = null;
    }

    @Test
    public void testTracksJsonDeserialisation() throws IOException {
        String json = readTestDataFile("tracks.json");
        fail("No yet implemented");
    }

    @Test
    public void testTrackSingleJsonDeserialisation() throws IOException {
        String json = readTestDataFile("track_single.json");
        fail("No yet implemented");
    }

    @Test
    public void testTrackSingleGeoJsonDeserialisation() throws IOException {
        String json = readTestDataFile("track_single_geo.json");
        fail("No yet implemented");
    }

    @Test
    public void testTrackSingleGeoQuotedJsonDeserialisation() throws IOException {
        String json = readTestDataFile("track_single_geo_quoted.json");
        fail("No yet implemented");
    }

    @Test
    public void testTrackSingleGeoNegativeJsonDeserialisation() throws IOException {
        String json = readTestDataFile("track_single_geo_negative.json");
        fail("No yet implemented");
    }

    @Test
    public void testTrackSingleGeoPositiveJsonDeserialisation() throws IOException {
        String json = readTestDataFile("track_single_geo_positive.json");
        fail("No yet implemented");
    }

    @Test
    public void testTrackSingleGeoReversedOrderJsonDeserialisation() throws IOException {
        String json = readTestDataFile("track_single_geo_reversed_order.json");
        fail("No yet implemented");
    }

    /**
     * FIXME Test the GeoLocation deserialization more directly.
     * Use only the required data rather than a whole track.
     * Using a full JSON Track makes it difficult to see what
     * the variance in the input data is and forces me to get an
     * entire Track dump just to test a single component.
     */

    private static String readTestDataFile(String _filename) {
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(_filename);

        if (inputStream == null)
            throw new IllegalArgumentException("Test data file not found on classpath: " + _filename);

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            return sb.toString();
        } catch (IOException ex) {
            throw new RuntimeException("Test data file could not be read: " + _filename, ex);
        }
    }
}
