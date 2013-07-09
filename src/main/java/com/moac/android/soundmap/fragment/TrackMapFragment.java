package com.moac.android.soundmap.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.moac.android.soundmap.SoundMapApplication;
import com.moac.android.soundmap.adapter.InfoAdapter;
import com.moac.android.soundmap.api.ApiRequest;
import com.moac.android.soundmap.api.TracksEndPoint;
import com.moac.android.soundmap.model.GeoLocation;
import com.moac.android.soundmap.model.Track;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TrackMapFragment extends MapFragment implements GoogleMap.OnMarkerClickListener {

    private static final String TAG = TrackMapFragment.class.getSimpleName();

    private Map<Marker, Track> mMarkerMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate() - start");
        super.onCreate(savedInstanceState);
        mMarkerMap = new HashMap<Marker, Track>();
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater _inflator, ViewGroup parent, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView() - start");
        View view = super.onCreateView(_inflator, parent, savedInstanceState);
        getMap().setOnMarkerClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.i(TAG, "onViewCreated() - start");
        getMap().setInfoWindowAdapter(new InfoAdapter(getActivity().getLayoutInflater(), mMarkerMap));
        super.onViewCreated(view, savedInstanceState);
    }

    private boolean loadImage(final Marker marker) {
        Log.i(TAG, "loadImage() started: " + marker.getId());

        final Track track = mMarkerMap.get(marker);
        if(track == null)
            return false;

        String url = track.getArtworkUrl();
        if(url == null)
            return false;

        SoundMapApplication.getVolley().getImageLoader().get(url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                Log.i(TAG, "onResponse() image loaded");
                track.setAvatar(imageContainer.getBitmap());
                if(marker.isInfoWindowShown())
                    marker.showInfoWindow();
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i(TAG, "onResponse() image failed to load");
                // FIXME Show placeholder?
            }
        });
        return true;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        // I can't believe I have to do all this myself now...
        getMap().animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
        loadImage(marker);
        marker.showInfoWindow();
        return true;
    }

    public void doSearch(String _query) {
        clear();
        ApiRequest<Collection<Track>> request = TracksEndPoint.getGeoTracks(_query);
        SoundMapApplication.getApiClient().execute(request, new TracksResponseListener(), new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.w(TAG, "onErrorResponse() - " + volleyError.getMessage());
                Toast.makeText(getActivity(),"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Generates Map Marker model objects from API derived Track objects.
     */
    private class TracksResponseListener implements Response.Listener<Collection<Track>> {
        @Override
        public void onResponse(Collection<Track> tracks) {
            Log.i(TAG, "onResponse(): got tracks: " + tracks.size());
            clear();
            for(Track track : tracks) {
                Log.v(TAG, "onResponse() - GEO IS: " + track.getGeoLocation());
                GeoLocation loc = track.getGeoLocation();
                if(loc != null) {
                    Marker marker = getMap().addMarker(new MarkerOptions()
                      .position(new LatLng(loc.getLatitude(), loc.getLongitude())).snippet(track.getUser().getUsername())
                      .title(track.getTitle()));
                    mMarkerMap.put(marker, track);
                }
            }
            String msg = String.format("Found %d sounds", mMarkerMap.size());
            Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
        }
    }

    private void clear() {
        getMap().clear();
        mMarkerMap.clear();
    }
}
