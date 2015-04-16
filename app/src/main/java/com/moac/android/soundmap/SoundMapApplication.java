package com.moac.android.soundmap;

import android.app.Application;

import com.moac.android.soundmap.injection.component.ApplicationComponent;
import com.moac.android.soundmap.injection.component.DaggerApplicationComponent;
import com.moac.android.soundmap.injection.module.ApiModule;
import com.moac.android.soundmap.injection.module.ApplicationModule;
import com.moac.android.soundmap.injection.module.ConfigModule;
import com.moac.android.soundmap.injection.module.ImagesModule;

public class SoundMapApplication extends Application {

    private static final String TAG = SoundMapApplication.class.getSimpleName();

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModule(new ApiModule())
                .imagesModule(new ImagesModule())
                .configModule(new ConfigModule())
                .build();
        component().inject(this);
    }

    public ApplicationComponent component() {
        return applicationComponent;
    }
}
