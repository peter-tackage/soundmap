package com.moac.android.soundmap.adapter;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
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
    private final Map<Marker, MarkerViewModel> markerMap;

    public InfoAdapter(LayoutInflater inflater, Map<Marker, MarkerViewModel> markerMap) {
        this.inflater = inflater;
        this.markerMap = markerMap;
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

        MarkerViewModel markerViewModel = markerMap.get(marker);
        Bitmap bmp = markerViewModel.getAvatarBitmap();
        if (bmp != null) {
            imageView.setImageBitmap(bmp);
        } else {
            // Avatar image not available (temporarily or permanently)
            Drawable myIcon = inflater.getContext().getResources().getDrawable(R.drawable.ic_soundcloud);
            imageView.setImageDrawable(myIcon);
        }

        // Note: It is impossible to asynchronously load the image into the
        // the ImageView here. The contents of the returned view is rendered as an
        // image once it is returned from this call.

        // Cache hits are actually successfully rendered here as the async/network
        // call is never actually made, hence the image contents are ready.

        return popup;
    }
}
