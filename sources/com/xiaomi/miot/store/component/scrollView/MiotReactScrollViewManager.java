package com.xiaomi.miot.store.component.scrollView;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.scroll.ReactScrollViewManager;

public class MiotReactScrollViewManager extends ReactScrollViewManager {
    private static final String REACT_CLASS = "MiotScrollViewAndroid";

    public String getName() {
        return REACT_CLASS;
    }

    public MiotReactScrollView createViewInstance(ThemedReactContext themedReactContext) {
        return new MiotReactScrollView(themedReactContext);
    }

    @ReactProp(name = "maxVelocity")
    public void setMaxVelocity(MiotReactScrollView miotReactScrollView, int i) {
        miotReactScrollView.setMaxVelocity(i);
    }

    @ReactProp(defaultBoolean = true, name = "velocityRestrictionEnabled")
    public void setVelocityRestrictionEnabled(MiotReactScrollView miotReactScrollView, boolean z) {
        miotReactScrollView.setVelocityRestrictionEnabled(z);
    }
}
