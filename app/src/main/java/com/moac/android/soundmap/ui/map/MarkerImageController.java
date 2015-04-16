package com.moac.android.soundmap.ui.map;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.LruCache;

import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import javax.inject.Inject;

/**
 * @author Peter Tackage
 * @since 14/04/15
 */
@Deprecated
public class MarkerImageController {

    private static final String TAG = MarkerImageController.class.getSimpleName();
    private final Picasso picasso;
    private final LruCache<String, Bitmap> bitmapCache;

    // Strong reference required to prevent GC
    @SuppressWarnings("FieldCanBeLocal")
    private Target inFlightBitmapRequest;

    @Inject
    public MarkerImageController(Picasso picasso, LruCache<String, Bitmap> bitmapCache) {
        this.picasso = picasso;
        this.bitmapCache = bitmapCache;
    }

    public void loadImageInto(final String url, final Marker marker) {
        Log.i(TAG, "loadImageInto() started: " + marker.getId());

        if (url == null) {
            return;
        }

        // TODO Make cancelled
        inFlightBitmapRequest = null;
        inFlightBitmapRequest = new MarkerTarget(marker, url);
        picasso.load(url).into(inFlightBitmapRequest);

    }

    private class MarkerTarget implements Target {

        private final Marker marker;
        private final String cacheKey;

        private MarkerTarget(Marker marker, String cacheKey) {
            this.marker = marker;
            this.cacheKey = cacheKey;
        }

        @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            Log.i(TAG, "onBitmapLoaded() bitmap loaded");
            bitmapCache.put(cacheKey, bitmap);
            if (marker.isInfoWindowShown()) {
                marker.showInfoWindow(); // update rendered marker
            }
        }

        @Override public void onBitmapFailed(Drawable errorDrawable) {
            Log.i(TAG, "onBitmapFailed() bitmap failed to load");
        }

        @Override public void onPrepareLoad(Drawable placeHolderDrawable) {
        }
    }
}
