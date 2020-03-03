package com.xiaomi.miot.store.component.pullrefresh;

import android.view.View;
import com.alipay.sdk.widget.j;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.xiaomi.miot.store.component.pullrefresh.youpinptr.PtrDefaultHandler;
import com.xiaomi.miot.store.component.pullrefresh.youpinptr.PtrFrameLayout;
import com.xiaomi.youpin.log.LogUtils;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "AndroidPullRefreshLayout")
public class YPPullRefreshLayoutManager extends ViewGroupManager<PtrFrameLayout> {
    protected static final String REACT_CLASS = "AndroidPullRefreshLayout";
    public static final String TAG = "NativePtr";

    @Nullable
    public Map<String, Object> getExportedViewConstants() {
        return null;
    }

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public PtrFrameLayout createViewInstance(ThemedReactContext themedReactContext) {
        PtrFrameLayout ptrFrameLayout = new PtrFrameLayout(themedReactContext);
        ptrFrameLayout.setPullToRefresh(false);
        ptrFrameLayout.disableWhenHorizontalMove(true);
        ptrFrameLayout.setResistance(1.7f);
        ptrFrameLayout.setRatioOfHeaderHeightToRefresh(1.2f);
        ptrFrameLayout.setDurationToClose(300);
        ptrFrameLayout.setDurationToCloseHeader(500);
        ptrFrameLayout.setKeepHeaderWhenRefresh(true);
        return ptrFrameLayout;
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(PtrFrameLayout ptrFrameLayout, boolean z) {
        ptrFrameLayout.setEnabled(z);
    }

    @ReactProp(name = "styleType")
    public void setStyleType(PtrFrameLayout ptrFrameLayout, int i) {
        ptrFrameLayout.setStyleType(i);
    }

    @ReactProp(name = "refreshing")
    public void setRefreshing(PtrFrameLayout ptrFrameLayout, boolean z) {
        LogUtils.d("NativePtr", " setRefreshing   *********    " + z);
        if (!z) {
            ptrFrameLayout.refreshPreComplete();
        }
    }

    @ReactProp(name = "contentOffsetY")
    public void setContentOffsetY(PtrFrameLayout ptrFrameLayout, int i) {
        LogUtils.d("NativePtr", " setContentOffsetY   *********    " + i);
        if (i > 0) {
            ptrFrameLayout.setHeaderOffsetY(i);
        }
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(final ThemedReactContext themedReactContext, final PtrFrameLayout ptrFrameLayout) {
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                return !view.canScrollVertically(-1);
            }

            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                ptrFrameLayout.getHandler().postDelayed(new Runnable() {
                    public void run() {
                        LogUtils.d("NativePtr", " addEventEmitters   *********" + ptrFrameLayout.getId());
                        ((UIManagerModule) themedReactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(new RefreshEvent(ptrFrameLayout.getId()));
                    }
                }, 1000);
            }
        });
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        LogUtils.d("NativePtr", " getExportedCustomDirectEventTypeConstants   *********");
        return MapBuilder.builder().put("topRefresh", MapBuilder.of("registrationName", j.e)).build();
    }
}
