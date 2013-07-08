package com.moac.android.soundmap.adapter;

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
import com.moac.android.soundmap.model.Track;

import java.util.Map;

public class InfoAdapter implements GoogleMap.InfoWindowAdapter {

    private static final String TAG = InfoAdapter.class.getSimpleName();

    private final LayoutInflater mInflator;
    private Map<Marker, Track> mMarkerMap;
    public InfoAdapter(LayoutInflater _inflator, Map<Marker, Track> markerMap) {
      mInflator = _inflator;
      mMarkerMap = markerMap;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null; // default Window
    }


    @Override
    public View getInfoContents(final Marker marker) {
        Log.i(TAG, "getInfoContents() started: " + marker.getId());
        final View popup = mInflator.inflate(R.layout.popup_view, null);

        ImageView imageView = (ImageView)popup.findViewById(R.id.track_imageview);
        TextView titleTextView = (TextView)popup.findViewById(R.id.track_title_textview);
        TextView userTextView = (TextView)popup.findViewById(R.id.username_textview);

        titleTextView.setText(marker.getTitle());
        userTextView.setText(marker.getSnippet());
        Track track = mMarkerMap.get(marker);
        Bitmap bmp = track.getAvatar();
        if(bmp != null) {
            imageView.setImageBitmap(bmp);
        } else {
            Drawable myIcon = mInflator.getContext().getResources().getDrawable( R.drawable.ic_soundcloud );
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
