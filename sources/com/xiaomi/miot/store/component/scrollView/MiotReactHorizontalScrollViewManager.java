package com.xiaomi.miot.store.component.scrollView;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.views.scroll.ReactHorizontalScrollViewManager;

public class MiotReactHorizontalScrollViewManager extends ReactHorizontalScrollViewManager {
    private static final String REACT_CLASS = "MiotHorizontalScrollViewAndroid";

    public String getName() {
        return REACT_CLASS;
    }

    public MiotReactHorizontalScrollView createViewInstance(ThemedReactContext themedReactContext) {
        return new MiotReactHorizontalScrollView(themedReactContext);
    }
}
