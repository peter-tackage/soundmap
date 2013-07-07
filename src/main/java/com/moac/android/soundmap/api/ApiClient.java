package com.moac.android.soundmap.api;

import android.content.Context;
import android.util.Log;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moac.android.soundmap.model.GeoLocation;
import com.moac.android.soundmap.model.GeoLocationDeserializer;

public class ApiClient {

    private static final String TAG = ApiClient.class.getSimpleName();

    private final String HOST_DOMAIN;
    private final String HOST_SCHEME;
    private final String CLIENT_ID;

    private Gson mGson;
    private RequestQueue mQueue;

    public ApiClient(Context _context, String _hostScheme, String _hostDomain, String _clientId) {
        HOST_SCHEME = _hostScheme;
        HOST_DOMAIN = _hostDomain;
        CLIENT_ID = _clientId;
        mGson = new GsonBuilder().registerTypeAdapter(GeoLocation.class, new GeoLocationDeserializer()).create();
        mQueue = Volley.newRequestQueue(_context);
    }

    public <T> void execute(ApiRequest<T> _request, Response.Listener<T> _okListener, Response.ErrorListener _errorListener) {
        if(!_request.getQueries().containsKey(EndPoints.CLIENT_ID_PARAM)) {
            _request.withQuery(EndPoints.CLIENT_ID_PARAM, CLIENT_ID);
        }

        final String url = HOST_SCHEME + HOST_DOMAIN + _request.toUrl();
        Log.i(TAG, "execute() - url: " + url);
        GsonRequest request = new GsonRequest<T>(mGson, url, _request.getTargetClass(), _okListener, _errorListener);
        mQueue.add(request);
    }
}
