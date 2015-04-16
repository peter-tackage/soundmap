package com.moac.android.soundmap.injection.module;

import android.content.Context;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ConfigModule.class)
public class ImagesModule {

    @Provides @Singleton Picasso providePicasso(@ForApplication Context context) {
        return Picasso.with(context);
    }

}
