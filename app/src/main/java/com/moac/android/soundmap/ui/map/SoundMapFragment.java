package com.moac.android.soundmap.ui.map;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.moac.android.soundmap.api.model.SoundImageFormat;
import com.moac.android.soundmap.api.model.SoundJsonModel;
import com.moac.android.soundmap.api.model.SoundListJsonModel;
import com.moac.android.soundmap.api.model.GeoLocation;
import com.moac.android.soundmap.provider.SearchProvider;
import com.moac.android.soundmap.viewmodel.MarkerViewModel;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

public class SoundMapFragment extends SupportMapFragment implements GoogleMap.OnMarkerClickListener {

    private static final String TAG = SoundMapFragment.class.getSimpleName();

    MainComponent component;

    @Inject
    SearchProvider searchProvider;

    @Inject
    Picasso picasso;

    // TODO Size based on default fetch size
    // Maps Marker Id to ViewModel
    private Map<String, MarkerViewModel> markerMap = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView() - start");
        View view = super.onCreateView(inflater, parent, savedInstanceState);
        component = ((MainActivity) getActivity()).component();
        component.inject(this);
        initMap();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.i(TAG, "onViewCreated() - start");
        super.onViewCreated(view, savedInstanceState);
        getMap().setInfoWindowAdapter(new InfoAdapter(getActivity().getLayoutInflater(), markerMap, picasso));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        getMap().animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
        marker.showInfoWindow();
        return true;
    }

    public void doSearch(String query) {
        clearMap();
        searchProvider.searchForGeoTagged(query)
                .filter(new Func1<SoundListJsonModel, Boolean>() {
                    @Override public Boolean call(SoundListJsonModel soundListJsonModel) {
                        return soundListJsonModel != null;
                    }
                })
                .map(new Func1<SoundListJsonModel, List<SoundJsonModel>>() {
                    @Override
                    public List<SoundJsonModel> call(SoundListJsonModel soundListJsonModel) {
                        return soundListJsonModel.getResults();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SearchObserver());
    }

    /**
     * Generates Map Marker model objects from API derived Track objects.
     */
    private class SearchObserver implements Observer<List<SoundJsonModel>> {

        @Override public void onCompleted() {
        }

        @Override public void onError(Throwable e) {
            Log.w(TAG, "onErrorResponse() - " + e.getMessage(), e);
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }

        @Override public void onNext(List<SoundJsonModel> sounds) {
            Log.i(TAG, "onResponse(): got tracks: " + sounds.size());
            clearMap();
            for (SoundJsonModel sound : sounds) {
                GeoLocation location = sound.getGeotag();
                Log.v(TAG, "onResponse() - sound: " + sound.toString());
                if (location != null) {
                    Marker marker = getMap().addMarker(
                            new MarkerOptions()
                                    .position(new LatLng(location.getLatitude(), location.getLongitude()))
                                    .snippet(sound.getUsername())
                                    .title(sound.getName()));

                    // Define model, include which image format to display
                    MarkerViewModel markerViewModel = new MarkerViewModel(sound, SoundImageFormat.waveform_m);

                    // Map between Marker identifier and model.
                    markerMap.put(marker.getId(), markerViewModel);
                }
            }
            String msg = String.format("Found %d sounds", sounds.size());
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    private void initMap() {
        getMap().getUiSettings().setZoomControlsEnabled(true);
        getMap().setOnMarkerClickListener(this);
    }

    private void clearMap() {
        getMap().clear();
        markerMap.clear();
    }

}
