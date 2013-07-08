package com.moac.android.soundmap;

import android.app.Application;
import android.util.Log;
import com.android.volley.RequestQueue;
import com.moac.android.soundmap.api.ApiClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SoundMapApplication extends Application {

    private static final String TAG = SoundMapApplication.class.getSimpleName();

    private static SimpleVolley sVolley;
    private static ApiClient sApiClient;

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate() - start");
        super.onCreate();
        sVolley = initVolley();
        sApiClient = initApiClient(sVolley.getRequestQueue());
    }

    private SimpleVolley initVolley() {
        SimpleVolley volley = new SimpleVolley(getApplicationContext());
        return volley;
    }

    private ApiClient initApiClient(RequestQueue _requestQueue) {
        Log.i(TAG, "initApiWrapper() - start");

        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("soundcloud.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            String apiScheme = properties.getProperty("host.scheme");
            String apiDomain = properties.getProperty("host.domain");
            String clientId = properties.getProperty("client.id");
            String clientSecret = properties.getProperty("client.secret");
            Log.i(TAG, "initApiWrapper() - creating with clientId: " + clientId + " clientSecret: " + clientSecret);

            return new ApiClient(_requestQueue, apiScheme, apiDomain, clientId);
        } catch(IOException e) {
            Log.e(TAG, "Failed to initialise API Client", e);
            throw new RuntimeException("Unable to initialise API Client");
        } finally {
            safeClose(inputStream);
        }
    }

    public static ApiClient getApiClient() { return sApiClient; }
    public static SimpleVolley getVolley() { return sVolley; }

    private void safeClose(InputStream _stream) {
        if(_stream != null) {
            try {
                _stream.close();
            } catch(IOException e) { }
        }
    }
}
