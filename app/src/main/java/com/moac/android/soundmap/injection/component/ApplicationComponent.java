package com.moac.android.soundmap.injection.component;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.moac.android.soundmap.api.FreeSoundApi;
import com.moac.android.soundmap.injection.module.ApiModule;
import com.moac.android.soundmap.injection.module.ApplicationModule;
import com.moac.android.soundmap.injection.module.DataModule;
import com.moac.android.soundmap.injection.module.ForApplication;
import com.moac.android.soundmap.injection.module.ImagesModule;
import com.moac.android.soundmap.provider.SearchProvider;
import com.moac.android.soundmap.ui.fragment.MarkerImageController;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ApplicationModule.class, ApiModule.class, ImagesModule.class, DataModule.class})
@Singleton
public interface ApplicationComponent {
    Application getApplication();

    @ForApplication Context getApplicationContext();

    FreeSoundApi getApi();

    Picasso getPicasso();

    SearchProvider getSearchProvider();

    LruCache<String, Bitmap> getBitmapCache();

    MarkerImageController getMarkerImageController();

    void inject(Application application);
}
