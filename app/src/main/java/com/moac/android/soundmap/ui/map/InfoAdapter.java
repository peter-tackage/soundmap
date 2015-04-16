package com.moac.android.soundmap.ui.map;

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
        final View popup = inflater.inflate(R.layout.view_popup, null);

        ImageView waveformImageView = (ImageView) popup.findViewById(R.id.imageView_waveform);
        TextView titleTextView = (TextView) popup.findViewById(R.id.textView_title);
        TextView userTextView = (TextView) popup.findViewById(R.id.textView_username);
        TextView descriptionTextView = (TextView) popup.findViewById(R.id.textView_description);

        // Find the model associated with this Marker
        MarkerViewModel markerViewModel = markerMap.get(marker.getId());

        // Populate InfoWindow fields
        titleTextView.setText(marker.getTitle());
        userTextView.setText(marker.getSnippet());
        descriptionTextView.setText(markerViewModel.getSound().getDescription());

        // The the Sound's displayed image URL
        String imageUrl = markerViewModel.getImageUrl();
        loadWaveformInto(marker, waveformImageView, imageUrl);

        return popup;
    }

    private void loadWaveformInto(final Marker marker, final ImageView waveformImageView, final String imageUrl) {
        Drawable placeholderDrawable = inflater.getContext().getResources().getDrawable(R.drawable.ic_soundcloud);
        if (target != null) {
            picasso.cancelRequest(target);
        }
        target = new CachedMarkerTarget(marker, waveformImageView);
        picasso.load(imageUrl)
                .error(placeholderDrawable)
                .placeholder(placeholderDrawable)
                .into(target);
    }

}
