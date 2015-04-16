package com.moac.android.soundmap.injection.component;

import android.app.Activity;
import android.content.Context;

import com.moac.android.soundmap.injection.module.ActivityModule;
import com.moac.android.soundmap.injection.module.ForActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface BaseActivityComponent {
    Activity getActivity();

    @ForActivity Context getActivityContext();
}
