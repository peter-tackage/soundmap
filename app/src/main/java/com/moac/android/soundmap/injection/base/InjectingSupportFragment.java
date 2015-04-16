package com.moac.android.soundmap.injection.base;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class InjectingSupportFragment extends android.support.v4.app.Fragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        if (mObjectGraph == null) {
//            ObjectGraph appGraph = ((Injector) getActivity()).getComponent();
//            List<Object> fragmentModules = getModules();
//            mObjectGraph = appGraph.plus(fragmentModules.toArray());
//            inject(this);
//        }
    }

    @Override
    public void onDestroy() {
//        mObjectGraph = null;
        super.onDestroy();
    }

    //   @Override
    //  public final ObjectGraph getComponent() {
//        return mObjectGraph;
//    }

//    @Override
//    public void inject(Object target) {
//        mObjectGraph.inject(target);
//    }

    protected List<Object> getModules() {
        return new ArrayList<>();
    }
}
