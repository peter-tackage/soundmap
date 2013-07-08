package com.moac.android.soundmap.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.moac.android.soundmap.R;
import com.moac.android.soundmap.model.Track;

import java.util.HashMap;
import java.util.Map;

public class InfoAdapter implements GoogleMap.InfoWindowAdapter {

    private final LayoutInflater mInflator;
    private final ImageLoader mImageLoader;
    private Map<Marker, Track> mItems = new HashMap<Marker, Track>();

    public InfoAdapter(LayoutInflater _inflator, ImageLoader _imageLoader) {
      mInflator = _inflator;
      mImageLoader = _imageLoader;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null; // default Window
    }

    public void addMarker(Marker _marker, Track _track) {
        mItems.put(_marker, _track);
    }

    @Override
    public View getInfoContents(Marker marker) {
        Track track = mItems.get(marker);
        if(track == null)
            return null;

        View popup = mInflator.inflate(R.layout.popup_view, null);
        TextView titleTextView = (TextView)popup.findViewById(R.id.track_title_textview);
        NetworkImageView trackImageView = (NetworkImageView)popup.findViewById(R.id.track_imageview);

        titleTextView.setText(track.getTitle());
        if (track.getWaveformUrl() != null) {
            trackImageView.setImageUrl(track.getWaveformUrl(), mImageLoader);
        } else {
            //trackImageView.setImageResource(null);
        }
        return popup;
    }

    public void clear() {
        mItems.clear();
    }
}
