package com.moac.android.soundmap.api;

import com.google.gson.reflect.TypeToken;
import com.moac.android.soundmap.model.Track;

import java.util.Collection;

/**
 * Refer - http://developers.soundcloud.com/docs/api/reference#tracks
 */
public class TracksEndPoint {

    private static final String GENRE_FILTER = "genres";

    private static final String QUERY_FILTER_PARAM = "q";

    private static final String TAGS_FILTER_PARAM = "tags";
    private static final String GEO_TAG = "geo";
    private static final String GEO_LAT_TAG = "geo:lat=";

    /**
     * SEEMS TO BE A PROBLEM USING THE TAGS FILTER WITH GEO_TAG STRING - GET HTTP 509 RESPONSE
     * <p/>
     * UNTIL I WORK IT OUT - USING A QUERY THAT GENERATES SOME GEO HITS - NO GENRE INFO.
     */

    public static ApiRequest<Collection<Track>> getGeoTracks(String genre) {
        ApiRequest<Collection<Track>> request = new ApiRequest<>(new TypeToken<Collection<Track>>() {
        }, EndPoints.TRACKS);
        // See note above.
        //    request.withQuery(GENRE_FILTER, genre);
        //    request.withQuery(TAGS_FILTER_PARAM, GEO_TAG);
        // request.withQuery(QUERY_FILTER_PARAM, genre);
        request.withQuery(QUERY_FILTER_PARAM, GEO_LAT_TAG);  // query is very broad, get many non geo: hits too.
        return request;
    }
}
