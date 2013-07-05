package com.moac.android.soundmap.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class ModelDeserializationTest {

    @Test
    public void testTrackJsonDeserialisation() throws IOException {
    }

    @Test
    public void testUserJsonDeserialisation() throws IOException {
    }

    private static String readTestDataFile(String _filename) {
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(_filename);

        if(inputStream == null)
            throw new IllegalArgumentException("Test data file not found on classpath: " + _filename);

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            return sb.toString();
        } catch(IOException ex) {
            throw new RuntimeException("Test data file could not be read: " + _filename, ex);
        }
    }
}
