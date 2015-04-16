package com.moac.android.soundmap.injection.component;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.moac.android.soundmap.injection.module.ActivityModule;
import com.moac.android.soundmap.injection.module.ForActivity;
import com.moac.android.soundmap.injection.module.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface BaseActivityComponent {
    Activity getActivity();

    @ForActivity Context getActivityContext();
}
