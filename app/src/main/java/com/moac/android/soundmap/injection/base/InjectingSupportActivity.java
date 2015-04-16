package com.moac.android.soundmap.injection.base;

import android.support.v4.app.FragmentActivity;

public abstract class InjectingSupportActivity extends FragmentActivity {

//    private ActivityComponent activityComponent;
//
//    public final Component getComponent() {
//        return activityComponent;
//    }
//
//    @Override
//    public void inject(Object target) {
//        activityComponent(target);
//    }
//
//    @Override
//    protected void onCreate(android.os.Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        DaggerActivityComponent.builder()
//
//                .dripCoffeeModule(new DripCoffeeModule())
//                .build();
//        activityComponent = ((Injector) getApplication()).getComponent().plus(getModules().toArray());
//
//        inject(this);
//    }
//
//    @Override
//    protected void onDestroy() {
//        activityComponent = null;
//        super.onDestroy();
//    }
//
//    protected List<Object> getModules() {
//        return new ArrayList<>();
//    }

}