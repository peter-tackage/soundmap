package com.moac.android.soundmap.injection.module;

import android.app.Activity;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides @PerActivity
    @ForActivity Context provideActivityContext() {
        return activity;
    }

    @Provides @PerActivity
    Activity provideActivity() {
        return activity;
    }

}
