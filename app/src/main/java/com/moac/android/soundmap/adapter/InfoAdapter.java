package com.moac.android.soundmap.adapter;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.moac.android.soundmap.R;
import com.moac.android.soundmap.viewmodel.MarkerViewModel;

import java.util.Map;

public class InfoAdapter implements GoogleMap.InfoWindowAdapter {

    private static final String TAG = InfoAdapter.class.getSimpleName();

    private final LayoutInflater inflater;
    private final Map<String, MarkerViewModel> markerMap;
    private final LruCache<String, Bitmap> bitmapCache;

    public InfoAdapter(LayoutInflater inflater, Map<String, MarkerViewModel> markerMap, LruCache<String, Bitmap> bitmapCache) {
        this.inflater = inflater;
        this.markerMap = markerMap;
        this.bitmapCache = bitmapCache;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null; // default Window
    }

    @Override
    public View getInfoContents(final Marker marker) {
        Log.i(TAG, "getInfoContents() started: " + marker.getId());
        @SuppressLint("InflateParams") // Ok for popup view
        final View popup = inflater.inflate(R.layout.popup_view, null);

        ImageView imageView = (ImageView) popup.findViewById(R.id.track_imageview);
        TextView titleTextView = (TextView) popup.findViewById(R.id.track_title_textview);
        TextView userTextView = (TextView) popup.findViewById(R.id.username_textview);

        titleTextView.setText(marker.getTitle());
        userTextView.setText(marker.getSnippet());

        // Find the model associated with this Marker
        MarkerViewModel markerViewModel = markerMap.get(marker.getId());
        // Use the Sound's displayed image URL to key the cache
        String cacheKey = markerViewModel.getImageUrl();

        // Check if the bitmap is still immediately available
        Bitmap bitmap = bitmapCache.get(cacheKey);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            // Sound image not available (temporarily or permanently) - set placeholder
            Drawable placeholderDrawable = inflater.getContext().getResources().getDrawable(R.drawable.ic_soundcloud);
            imageView.setImageDrawable(placeholderDrawable);
        }

        // Note: It is impossible to asynchronously load the image into the
        // the ImageView here. The contents of the returned view is rendered as an
        // image once it is returned from this call.

        // Picasso cache hits via Picasso's get() would actually successfully rendered here as the
        // async/network call is never actually made, hence the image contents are ready.
        // However, we can't rely on this as if the image is not cached then a potentially long
        // blocking operation will occur.

        return popup;
    }
}
