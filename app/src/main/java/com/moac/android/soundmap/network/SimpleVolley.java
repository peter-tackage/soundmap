package com.moac.android.soundmap.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.moac.android.soundmap.util.BitmapLruCache;

public class SimpleVolley {

    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    public SimpleVolley(Context context) {
        // Single request queue.
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        imageLoader = new ImageLoader(requestQueue, new BitmapLruCache());
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
