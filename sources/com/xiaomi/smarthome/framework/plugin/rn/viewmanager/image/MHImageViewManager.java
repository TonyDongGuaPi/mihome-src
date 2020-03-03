package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.image;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.views.image.GlobalImageLoadListener;
import com.facebook.react.views.image.ReactImageManager;
import com.facebook.react.views.image.ReactImageView;

@ReactModule(name = "MHImageView")
public class MHImageViewManager extends ReactImageManager {
    static final String REACT_CLASS = "MHImageView";

    public String getName() {
        return REACT_CLASS;
    }

    public ReactImageView createViewInstance(ThemedReactContext themedReactContext) {
        return new NoFilterImageView(themedReactContext, getDraweeControllerBuilder(), (GlobalImageLoadListener) null, getCallerContext());
    }
}
