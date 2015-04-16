package com.moac.android.soundmap.ui.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.moac.android.soundmap.R;
import com.moac.android.soundmap.SoundMapApplication;
import com.moac.android.soundmap.injection.module.ActivityModule;
import com.moac.android.soundmap.ui.fragment.SoundMapFragment;

public final class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MainComponent component;

    private SoundMapFragment mapFragment;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component().inject(this);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbar);

        // Get reference to MapFragment
        mapFragment = (SoundMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);

        // We are using single top mode, so this will not contain
        // search intents as the SearchView is operating on its host
        // Activity - instead see onNewIntent()
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(TAG, "onNewIntent - received intent");
        setIntent(intent);
        handleIntent(intent);
    }

    public MainComponent component() {
        if (component == null) {
            component = DaggerMainComponent.builder()
                    .applicationComponent(((SoundMapApplication) getApplication()).component())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return component;
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doSearch(query);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v == searchView && !hasFocus) searchView.setIconified(true);
            }
        });
        // Note: I don't register callbacks to invoke the search query - use the Intents instead.
        return true;
    }

    private void doSearch(String query) {
        Log.i(TAG, "doSearch() - query: " + query);
        mapFragment.doSearch(query);
    }
}
