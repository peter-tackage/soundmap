package com.moac.android.soundmap.injection.module;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moac.android.soundmap.api.FreeSoundApi;
import com.moac.android.soundmap.api.FreeSoundApiInterceptor;
import com.moac.android.soundmap.api.model.GeoLocationDeserializer;
import com.moac.android.soundmap.api.model.SoundListJsonModel;
import com.moac.android.soundmap.model.GeoLocation;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

@Module(includes = ConfigModule.class)
public class ApiModule {

    public static final String URL_CONFIG = "ApiModule.URL_CONFIG";
    public static final String API_TOKEN_CONFIG = "ApiModule.API_TOKEN_CONFIG";
    public static final String SEARCH_CACHE_SIZE_CONFIG = "ApiModule.SEARCH_CACHE_SIZE_CONFIG";
    public static final String BITMAP_CACHE_SIZE_CONFIG = "ApiModule.BITMAP_CACHE_SIZE_CONFIG";

    @Provides @Singleton FreeSoundApi provideFreeSoundApi(Endpoint endpoint,
                                                          //     Client client,
                                                          Converter converter,
                                                          RequestInterceptor requestInterceptor) {
        return new RestAdapter.Builder()
                .setEndpoint(endpoint)
                        //.setClient(client)
                .setConverter(converter)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
                .create(FreeSoundApi.class);
    }

    // Private //

    @Provides @Singleton Endpoint provideEndpoint(@Named(URL_CONFIG) String url) {
        return Endpoints.newFixedEndpoint(url);
    }

//    @Provides @Singleton Client provideClient(OkHttpClient okHttpClient) {
//        return new OkClient(okHttpClient);
//    }

    @Provides @Singleton Converter provideConverter(Gson gson) {
        return new GsonConverter(gson);
    }

    @Provides @Singleton Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(GeoLocation.class, new GeoLocationDeserializer())
                .create();
    }

    @Provides @Singleton RequestInterceptor provideRequestInterceptor(@Named(API_TOKEN_CONFIG) String apiToken) {
        return new FreeSoundApiInterceptor(apiToken);
    }

    @Provides @Singleton LruCache<String, SoundListJsonModel> provideSearchCache(@Named(SEARCH_CACHE_SIZE_CONFIG) int cacheSize) {
        return new LruCache<>(cacheSize);
    }

    @Provides @Singleton LruCache<String, Bitmap> provideBitmapCache(@Named(BITMAP_CACHE_SIZE_CONFIG) int cacheSize) {
        return new LruCache<>(cacheSize);
    }

}
