package com.moac.android.soundmap.api;

import com.moac.android.soundmap.model.Track;

public class TracksEndPoint {

    public static final String GENRE_FILTER = "genres";

    public static ApiRequest<Track> getTracks(String _genre) {
        ApiRequest<Track> request = new ApiRequest<Track>(Track.class, EndPoints.TRACKS);
        request.withQuery(GENRE_FILTER, _genre);
        return request;
    }
}
