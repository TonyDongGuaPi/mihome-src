package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.scrollview;

import android.support.v4.view.ViewCompat;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.views.scroll.FpsListener;
import com.facebook.react.views.scroll.ReactScrollViewCommandHelper;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.facebook.react.views.scroll.ScrollEventType;
import com.facebook.yoga.YogaConstants;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "MIOTAndroidScrollView")
public class MIOTScrollViewManager extends ViewGroupManager<MIOTScrollView> implements ReactScrollViewCommandHelper.ScrollCommandHandler<MIOTScrollView> {
    public static final String REACT_CLASS = "MIOTAndroidScrollView";
    private static final int[] SPACING_TYPES = {8, 0, 2, 1, 3};
    @Nullable
    private FpsListener mFpsListener;

    public String getName() {
        return REACT_CLASS;
    }

    public MIOTScrollViewManager() {
        this((FpsListener) null);
    }

    public MIOTScrollViewManager(@Nullable FpsListener fpsListener) {
        this.mFpsListener = null;
        this.mFpsListener = fpsListener;
    }

    public MIOTScrollView createViewInstance(ThemedReactContext themedReactContext) {
        return new MIOTScrollView(themedReactContext, this.mFpsListener);
    }

    @ReactProp(defaultBoolean = true, name = "scrollEnabled")
    public void setScrollEnabled(MIOTScrollView mIOTScrollView, boolean z) {
        mIOTScrollView.setScrollEnabled(z);
    }

    @ReactProp(name = "showsVerticalScrollIndicator")
    public void setShowsVerticalScrollIndicator(MIOTScrollView mIOTScrollView, boolean z) {
        mIOTScrollView.setVerticalScrollBarEnabled(z);
    }

    @ReactProp(name = "decelerationRate")
    public void setDecelerationRate(MIOTScrollView mIOTScrollView, float f) {
        mIOTScrollView.setDecelerationRate(f);
    }

    @ReactProp(name = "removeClippedSubviews")
    public void setRemoveClippedSubviews(MIOTScrollView mIOTScrollView, boolean z) {
        mIOTScrollView.setRemoveClippedSubviews(z);
    }

    @ReactProp(name = "sendMomentumEvents")
    public void setSendMomentumEvents(MIOTScrollView mIOTScrollView, boolean z) {
        mIOTScrollView.setSendMomentumEvents(z);
    }

    @ReactProp(name = "pagingEnabled")
    public void setPagingEnabled(MIOTScrollView mIOTScrollView, boolean z) {
        mIOTScrollView.setPagingEnabled(z);
    }

    @ReactProp(customType = "Color", defaultInt = 0, name = "endFillColor")
    public void setBottomFillColor(MIOTScrollView mIOTScrollView, int i) {
        mIOTScrollView.setEndFillColor(i);
    }

    @ReactProp(name = "overScrollMode")
    public void setOverScrollMode(MIOTScrollView mIOTScrollView, String str) {
        mIOTScrollView.setOverScrollMode(ReactScrollViewHelper.parseOverScrollMode(str));
    }

    @ReactProp(name = "nestedScrollEnabled")
    public void setNestedScrollEnabled(MIOTScrollView mIOTScrollView, boolean z) {
        ViewCompat.setNestedScrollingEnabled(mIOTScrollView, z);
    }

    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return ReactScrollViewCommandHelper.getCommandsMap();
    }

    public void receiveCommand(MIOTScrollView mIOTScrollView, int i, @Nullable ReadableArray readableArray) {
        ReactScrollViewCommandHelper.receiveCommand(this, mIOTScrollView, i, readableArray);
    }

    public void flashScrollIndicators(MIOTScrollView mIOTScrollView) {
        mIOTScrollView.flashScrollIndicators();
    }

    public void scrollTo(MIOTScrollView mIOTScrollView, ReactScrollViewCommandHelper.ScrollToCommandData scrollToCommandData) {
        if (scrollToCommandData.mAnimated) {
            mIOTScrollView.smoothScrollTo(scrollToCommandData.mDestX, scrollToCommandData.mDestY);
        } else {
            mIOTScrollView.scrollTo(scrollToCommandData.mDestX, scrollToCommandData.mDestY);
        }
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(MIOTScrollView mIOTScrollView, int i, float f) {
        if (!YogaConstants.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        if (i == 0) {
            mIOTScrollView.setBorderRadius(f);
        } else {
            mIOTScrollView.setBorderRadius(f, i - 1);
        }
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(MIOTScrollView mIOTScrollView, @Nullable String str) {
        mIOTScrollView.setBorderStyle(str);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidth(MIOTScrollView mIOTScrollView, int i, float f) {
        if (!YogaConstants.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        mIOTScrollView.setBorderWidth(SPACING_TYPES[i], f);
    }

    @ReactPropGroup(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public void setBorderColor(MIOTScrollView mIOTScrollView, int i, Integer num) {
        float f = Float.NaN;
        float intValue = num == null ? Float.NaN : (float) (num.intValue() & 16777215);
        if (num != null) {
            f = (float) (num.intValue() >>> 24);
        }
        mIOTScrollView.setBorderColor(SPACING_TYPES[i], intValue, f);
    }

    @ReactProp(name = "overflow")
    public void setOverflow(MIOTScrollView mIOTScrollView, @Nullable String str) {
        mIOTScrollView.setOverflow(str);
    }

    public void scrollToEnd(MIOTScrollView mIOTScrollView, ReactScrollViewCommandHelper.ScrollToEndCommandData scrollToEndCommandData) {
        int height = mIOTScrollView.getChildAt(0).getHeight() + mIOTScrollView.getPaddingBottom();
        if (scrollToEndCommandData.mAnimated) {
            mIOTScrollView.smoothScrollTo(mIOTScrollView.getScrollX(), height);
        } else {
            mIOTScrollView.scrollTo(mIOTScrollView.getScrollX(), height);
        }
    }

    @ReactProp(name = "persistentScrollbar")
    public void setPersistentScrollbar(MIOTScrollView mIOTScrollView, boolean z) {
        mIOTScrollView.setScrollbarFadingEnabled(!z);
    }

    @ReactProp(name = "scrollYDuration")
    public void setScrollYDuration(MIOTScrollView mIOTScrollView, int i) {
        mIOTScrollView.setScrollYDuration(i);
    }

    @ReactProp(name = "offsetPercent")
    public void setOffsetPercent(MIOTScrollView mIOTScrollView, float f) {
        mIOTScrollView.setOffsetPercent(f);
    }

    @ReactProp(name = "snapHeight")
    public void setSnapHeight(MIOTScrollView mIOTScrollView, int i) {
        mIOTScrollView.setSnapHeight((int) (((float) i) * DisplayMetricsHolder.getScreenDisplayMetrics().density));
    }

    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return createExportedCustomDirectEventTypeConstants();
    }

    public static Map<String, Object> createExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put(ScrollEventType.SCROLL.getJSEventName(), MapBuilder.of("registrationName", "onScroll")).put(ScrollEventType.BEGIN_DRAG.getJSEventName(), MapBuilder.of("registrationName", "onScrollBeginDrag")).put(ScrollEventType.END_DRAG.getJSEventName(), MapBuilder.of("registrationName", "onScrollEndDrag")).put(ScrollEventType.MOMENTUM_BEGIN.getJSEventName(), MapBuilder.of("registrationName", "onMomentumScrollBegin")).put(ScrollEventType.MOMENTUM_END.getJSEventName(), MapBuilder.of("registrationName", "onMomentumScrollEnd")).build();
    }
}
