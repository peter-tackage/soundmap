package com.moac.android.soundmap.provider;

import android.util.LruCache;

import com.moac.android.soundmap.api.ApiConst;
import com.moac.android.soundmap.api.FreeSoundApi;
import com.moac.android.soundmap.api.model.SoundListJsonModel;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

/**
 * @author Peter Tackage
 * @since 14/04/15
 */
public class SearchProvider {

    private static final String SOUND_RESPONSE_OBJECT_FIELDS = "id,url,name,tags,description,geotag,username,images";
    private final FreeSoundApi freeSoundApi;
    private final LruCache<String, SoundListJsonModel> cache;

    @Inject
    public SearchProvider(FreeSoundApi freeSoundApi, LruCache<String, SoundListJsonModel> cache) {
        this.freeSoundApi = freeSoundApi;
        this.cache = cache;
    }

    public Observable<SoundListJsonModel> searchForGeoTagged(final String query) {
        return freeSoundApi.search(query, buildGeoSearchFilter(), buildSearchFields())
                .doOnNext(new Action1<SoundListJsonModel>() {
                    @Override public void call(SoundListJsonModel soundListJsonModel) {
                        cache.put(query, soundListJsonModel);
                    }
                })
                .startWith(getCached(query))
                .onErrorResumeNext(getCached(query));

        // TODO Some sort of distinct operator?
    }

    private static String buildSearchFields() {
        return SOUND_RESPONSE_OBJECT_FIELDS;
    }

    private static String buildGeoSearchFilter() {
        return String.format(ApiConst.IS_GEO_TAGGED_FILTER_QUERY_PARAM + ":%s", true);
    }

    private Observable<SoundListJsonModel> getCached(String key) {
        SoundListJsonModel result = cache.get(key);
        return result == null ? Observable.<SoundListJsonModel>empty() : Observable.just(result);
    }
}
