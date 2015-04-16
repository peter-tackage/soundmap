package com.moac.android.soundmap.api;

import com.moac.android.soundmap.api.model.SoundListJsonModel;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface FreeSoundApi {

    @GET("/apiv2/search/text/")
    public Observable<SoundListJsonModel> search(@Query("query") String query,
                                                 @Query("filter") String filter,
                                                 @Query("fields") String fields);
}
