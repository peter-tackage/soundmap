package com.moac.android.soundmap.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.moac.android.soundmap.R;

public class MapFragment extends Fragment {

    private static final String SEARCH_TERM_TAG_EXTRA = "searchTerm";

    @Override
    public android.view.View onCreateView(LayoutInflater inflater,
                                          ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }
}
