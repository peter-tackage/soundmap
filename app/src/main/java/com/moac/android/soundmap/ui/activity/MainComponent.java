package com.moac.android.soundmap.ui.activity;

import com.moac.android.soundmap.injection.component.ApplicationComponent;
import com.moac.android.soundmap.injection.component.BaseActivityComponent;
import com.moac.android.soundmap.injection.module.ActivityModule;
import com.moac.android.soundmap.injection.module.PerActivity;
import com.moac.android.soundmap.ui.activity.MainActivity;
import com.moac.android.soundmap.ui.fragment.SoundMapFragment;

import dagger.Component;

/**
 * @author Peter Tackage
 * @since 16/04/15
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface MainComponent extends BaseActivityComponent {
    void inject(MainActivity mainActivity);
    void inject(SoundMapFragment mapFragment);
}
