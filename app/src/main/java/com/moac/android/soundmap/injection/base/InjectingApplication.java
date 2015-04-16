package com.moac.android.soundmap.injection.base;

import android.app.Application;

public abstract class InjectingApplication extends Application  {


    @Override
    public void onCreate() {
        super.onCreate();
//        mObjectGraph = ObjectGraph.create(getModules().toArray());
//        mObjectGraph.inject(this);

    }
//
//    @Override
//    public ObjectGraph getComponent() {
//        return mObjectGraph;
//    }
//
//    @Override
//    public void inject(Object target) {
//        mObjectGraph.inject(target);
//    }
//
//    protected List<Object> getModules() {
//        return new ArrayList<>();
//    }

}
