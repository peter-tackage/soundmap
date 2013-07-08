package com.moac.android.soundmap.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.moac.android.soundmap.R;


public class InfoAdapter implements GoogleMap.InfoWindowAdapter {

    private static final String TAG = InfoAdapter.class.getSimpleName();

    private final LayoutInflater mInflator;

    public InfoAdapter(LayoutInflater _inflator, ImageLoader _imageLoader) {
      mInflator = _inflator;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null; // default Window
    }


    @Override
    public View getInfoContents(final Marker marker) {
        final View popup = mInflator.inflate(R.layout.popup_view, null);
        TextView titleTextView = (TextView)popup.findViewById(R.id.track_title_textview);
        TextView userTextView = (TextView)popup.findViewById(R.id.username_textview);

        titleTextView.setText(marker.getTitle());
        userTextView.setText(marker.getSnippet());

        // Note: It is impossible to asynchronously load the image into the
        // the ImageView. The contents of the returned view is rendered as an
        // image once it is returned from this call.

        // Cache hits are actually successfully rendered here as the async/network
        // call is never actually made, hence the image contents are ready.

        return popup;
    }

}
