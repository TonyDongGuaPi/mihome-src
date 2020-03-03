package com.swmansion.rnscreens;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.view.ReactViewGroup;
import com.facebook.react.views.view.ReactViewManager;
import com.swmansion.rnscreens.ScreenStackHeaderSubview;

@ReactModule(name = "RNSScreenStackHeaderSubview")
public class ScreenStackHeaderSubviewManager extends ReactViewManager {
    protected static final String REACT_CLASS = "RNSScreenStackHeaderSubview";

    public String getName() {
        return REACT_CLASS;
    }

    private static class SubviewShadowNode extends LayoutShadowNode {
        private SubviewShadowNode() {
        }

        public void setLocalData(Object obj) {
            ScreenStackHeaderSubview.Measurements measurements = (ScreenStackHeaderSubview.Measurements) obj;
            setStyleWidth((float) measurements.f8961a);
            setStyleHeight((float) measurements.b);
        }
    }

    public ReactViewGroup createViewInstance(ThemedReactContext themedReactContext) {
        return new ScreenStackHeaderSubview(themedReactContext);
    }

    public LayoutShadowNode createShadowNodeInstance(ReactApplicationContext reactApplicationContext) {
        return new SubviewShadowNode();
    }

    @ReactProp(name = "type")
    public void setType(ScreenStackHeaderSubview screenStackHeaderSubview, String str) {
        if ("left".equals(str)) {
            screenStackHeaderSubview.setType(ScreenStackHeaderSubview.Type.LEFT);
        } else if ("center".equals(str)) {
            screenStackHeaderSubview.setType(ScreenStackHeaderSubview.Type.CENTER);
        } else if ("title".equals(str)) {
            screenStackHeaderSubview.setType(ScreenStackHeaderSubview.Type.TITLE);
        } else if ("right".equals(str)) {
            screenStackHeaderSubview.setType(ScreenStackHeaderSubview.Type.RIGHT);
        }
    }
}
