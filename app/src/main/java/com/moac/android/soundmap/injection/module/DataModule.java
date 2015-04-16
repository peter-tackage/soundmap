package com.moac.android.soundmap.injection.module;

import android.util.LruCache;

import com.moac.android.soundmap.api.FreeSoundApi;
import com.moac.android.soundmap.api.model.SoundListJsonModel;
import com.moac.android.soundmap.provider.SearchProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Peter Tackage
 * @since 16/04/15
 */
@Module
public class DataModule {
    @Provides @Singleton SearchProvider provideSearchProvider(FreeSoundApi freeSoundApi, LruCache<String, SoundListJsonModel> cache) {
        return new SearchProvider(freeSoundApi, cache);
    }
}
