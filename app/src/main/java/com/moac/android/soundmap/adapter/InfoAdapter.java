package com.moac.android.soundmap.adapter;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.moac.android.soundmap.R;
import com.moac.android.soundmap.ui.map.CachedMarkerTarget;
import com.moac.android.soundmap.viewmodel.MarkerViewModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.Map;

public class InfoAdapter implements GoogleMap.InfoWindowAdapter {

    private static final String TAG = InfoAdapter.class.getSimpleName();

    private final LayoutInflater inflater;
    private final Map<String, MarkerViewModel> markerMap;
    private final Picasso picasso;
    private Target target;

    public InfoAdapter(LayoutInflater inflater, Map<String, MarkerViewModel> markerMap, Picasso picasso) {
        this.inflater = inflater;
        this.markerMap = markerMap;
        this.picasso = picasso;
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

        // The the Sound's displayed image URL
        String imageUrl = markerViewModel.getImageUrl();

        // Check if the bitmap is still immediately available
        // Sound image not available (temporarily or permanently) - set placeholder
        Drawable placeholderDrawable = inflater.getContext().getResources().getDrawable(R.drawable.ic_soundcloud);
        if (target != null) {
            picasso.cancelRequest(target);
        }
        target = new CachedMarkerTarget(marker, imageView);
        picasso.load(imageUrl)
                .error(placeholderDrawable)
                .placeholder(placeholderDrawable)
                .into(target);

        return popup;
    }

}
