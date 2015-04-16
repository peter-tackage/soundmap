package com.moac.android.soundmap.api;

import retrofit.RequestInterceptor;

public final class FreeSoundApiInterceptor implements RequestInterceptor {

    private final String apiToken;

    public FreeSoundApiInterceptor(String apiToken) {
        this.apiToken = apiToken;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addQueryParam(ApiConst.TOKEN_QUERY_PARAM, apiToken);
    }
}
