package com.moac.android.soundmap.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides @Singleton @ForApplication
    public Context provideApplicationContext() {
        return application.getApplicationContext();
    }

    @Provides @Singleton
    public Application provideApplication() {
        return application;
    }

}
