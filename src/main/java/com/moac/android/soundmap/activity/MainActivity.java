package com.moac.android.soundmap.activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.SearchView;

import com.moac.android.soundmap.R;

import com.moac.android.soundmap.fragment.TrackMapFragment;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();
    TrackMapFragment mMapFragment;
    SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate()- start");
        setContentView(R.layout.activity_main);

        // Get reference to MapFragment
        mMapFragment = (TrackMapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);

        // We are using single top mode, so this will not contain
        // search intents as the SearchView is operating on its host
        // Activity - instead see onNewIntent()
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent _intent) {
        Log.i(TAG, "onNewIntent - received intent");
        //  Removed this as causes the fragment to reperform search on rotation.
        // setIntent(_intent);
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
        mSearchView = (SearchView) menu.findItem(R.id.menu_item_search).getActionView();
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setIconifiedByDefault(true);
        mSearchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(v == mSearchView && !hasFocus) mSearchView.setIconified(true);
            }
        });
        // Note: I don't register callbacks to invoke the search query - use the Intents instead.

        return true;
    }

    private void doSearch(String _query) {
        Log.i(TAG, "doSearch() - query: " + _query);
        mMapFragment.doSearch(_query);
    }
}
