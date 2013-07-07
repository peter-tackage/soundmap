package com.moac.android.soundmap;

import android.app.Application;
import android.util.Log;
import com.soundcloud.api.ApiWrapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SoundMapApplication extends Application {

    private static final String TAG = SoundMapApplication.class.getSimpleName();

    private static ApiWrapper sApiWrapper;

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate() - start");
        super.onCreate();
        sApiWrapper = initApiWrapper();
    }

    private ApiWrapper initApiWrapper() {
        Log.i(TAG, "initApiWrapper() - start");

        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("soundcloud.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            String clientId = properties.getProperty("client.id");
            String clientSecret = properties.getProperty("client.secret");
            Log.i(TAG, "initApiWrapper() - creating with clientId: " + clientId + " clientSecret: " + clientSecret);

            return new ApiWrapper(clientId, clientSecret, null, null);
        } catch(IOException e) {
            Log.e(TAG, "Failed to initialise SoundCloud API Wrapper", e);
            throw new RuntimeException("Unable to initialise SoundCloud API Wrapper");
        } finally {
            safeClose(inputStream);
        }

    }

    public static ApiWrapper getApiWrapper() {
       return sApiWrapper;
    }

    private void safeClose(InputStream _stream) {
        if(_stream != null) {
            try {
                _stream.close();
            } catch(IOException e) { }
        }
    }


}
