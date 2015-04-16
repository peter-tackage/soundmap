package com.moac.android.soundmap.injection.module;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ConfigModule {

    @Provides @Singleton @Named(ApiModule.URL_CONFIG)
    public String provideApiModuleUrlConfig() {
        return "https://www.freesound.org/";
    }

    @Provides @Singleton @Named(ApiModule.API_TOKEN_CONFIG)
    public String provideApiModuleApiTokenConfig() {
        throw new IllegalStateException("No Api Token defined");
    }

    @Provides @Singleton @Named(ApiModule.SEARCH_CACHE_SIZE_CONFIG)
    public int provideApiModuleSearchCacheSizeConfig() {
        return 50;
    }

    @Provides @Singleton @Named(ApiModule.BITMAP_CACHE_SIZE_CONFIG)
    public int provideApiModuleBitmapCacheSizeConfig() {
        return 50;
    }

}
