package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.blurview;

import android.view.View;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.modal.ReactModalHostView;

public class BlurViewManager extends SimpleViewManager<BlurringView> {
    public static final String REACT_CLASS = "BlurView";
    private static ThemedReactContext context = null;
    public static final int defaultRadius = 10;
    public static final int defaultSampling = 10;

    public String getName() {
        return REACT_CLASS;
    }

    public BlurringView createViewInstance(ThemedReactContext themedReactContext) {
        context = themedReactContext;
        BlurringView blurringView = new BlurringView(themedReactContext);
        blurringView.setBlurRadius(10);
        blurringView.setDownsampleFactor(10);
        return blurringView;
    }

    @ReactProp(defaultInt = 10, name = "blurRadius")
    public void setRadius(BlurringView blurringView, int i) {
        blurringView.setBlurRadius(i);
        blurringView.invalidate();
    }

    @ReactProp(customType = "Color", name = "overlayColor")
    public void setColor(BlurringView blurringView, int i) {
        blurringView.setOverlayColor(i);
        blurringView.invalidate();
    }

    @ReactProp(defaultInt = 10, name = "downsampleFactor")
    public void setDownsampleFactor(BlurringView blurringView, int i) {
        blurringView.setDownsampleFactor(i);
    }

    @ReactProp(name = "viewRef")
    public void setViewRef(BlurringView blurringView, int i) {
        View findViewById;
        if (context != null && context.getCurrentActivity() != null && (findViewById = context.getCurrentActivity().findViewById(i)) != null) {
            if (findViewById instanceof ReactModalHostView) {
                blurringView.setmIsBlurReactModalHostView(true);
            } else {
                blurringView.setmIsBlurReactModalHostView(false);
            }
            blurringView.setBlurredView(findViewById);
        }
    }
}
