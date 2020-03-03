package com.xiaomi.miot.store.component.viewOverflow;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

public class RNViewOverflowManager extends ViewGroupManager<RNViewOverflowLayout> {
    public static final String REACT_CLASS = "RNViewOverflow";

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public RNViewOverflowLayout createViewInstance(ThemedReactContext themedReactContext) {
        return new RNViewOverflowLayout(themedReactContext);
    }

    @ReactProp(defaultFloat = 0.0f, name = "borderRadius")
    public void setBorderRadius(RNViewOverflowLayout rNViewOverflowLayout, float f) {
        rNViewOverflowLayout.setBorderRadius(f);
    }
}
