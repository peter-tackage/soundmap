package com.moac.android.soundmap.activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.moac.android.soundmap.R;
import com.moac.android.soundmap.SoundMapApplication;
import com.moac.android.soundmap.adapter.InfoAdapter;
import com.moac.android.soundmap.api.ApiRequest;
import com.moac.android.soundmap.api.TracksEndPoint;
import com.moac.android.soundmap.model.GeoLocation;
import com.moac.android.soundmap.model.Track;

import java.util.Collection;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private GoogleMap mMap;
    private InfoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate()- start");
        setContentView(R.layout.activity_main);

        // Get reference to MapFragment
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.setRetainInstance(true);
        mMap = mapFragment.getMap();
        mAdapter = new InfoAdapter(getLayoutInflater(), SoundMapApplication.getVolley().getImageLoader());
        mMap.setInfoWindowAdapter(mAdapter);

        // We are using single top mode, so this will not contain
        // search intents as the SearchView is operating on its host
        // Activity - instead see onNewIntent()
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent _intent) {
        Log.i(TAG, "onNewIntent - received intent");
        setIntent(_intent);
        handleIntent(_intent);
    }

    private void handleIntent(Intent _intent) {
        if(Intent.ACTION_SEARCH.equals(_intent.getAction())) {
            String query = _intent.getStringExtra(SearchManager.QUERY);
            doSearch(query);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle _outstate) {
        super.onSaveInstanceState(_outstate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_item_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
        // Note: I don't register callbacks to invoke the search query - instead use the Intents.

        return true;
    }

    private void doSearch(String _query) {
        Log.i(TAG, "doSearch() - query: " + _query);

        // Take query string
        // Run query on SoundCloud API
        // Build Tracks list
        // Take Geo from Tracks list
        // Feed to Maps Fragment
        ApiRequest<Collection<Track>> request = TracksEndPoint.getGeoTracks(_query);
        SoundMapApplication.getApiClient().execute(request, new TracksResponseListener(), new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.w(TAG, "onErrorResponse() - " + volleyError.getMessage());
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
                    Marker marker = mMap.addMarker(new MarkerOptions()
                      .position(new LatLng(loc.getLatitude(), loc.getLongitude())).snippet(track.getUser().getUsername())
                      .title(track.getTitle()));
                }
            }
        }
    }

    private void clear() {
        mMap.clear();
    }
}
