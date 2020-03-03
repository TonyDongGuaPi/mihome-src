package com.xiaomi.miot.store.module;

import android.content.Context;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.toast.ToastModule;
import com.xiaomiyoupin.toast.YPDToast;
import java.util.HashMap;
import java.util.Map;

@ReactModule(name = "ToastAndroid")
public class CustomToastModule extends ReactContextBaseJavaModule {
    private static final String DURATION_LONG_KEY = "LONG";
    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String GRAVITY_BOTTOM_KEY = "BOTTOM";
    private static final String GRAVITY_CENTER = "CENTER";
    private static final String GRAVITY_TOP_KEY = "TOP";
    private static final String ICON_TYPE_ERROR = "ERROR";
    private static final String ICON_TYPE_INFO = "INFO";
    private static final String ICON_TYPE_OK = "OK";
    /* access modifiers changed from: private */
    public int mToastID = -1;

    public boolean canOverrideExistingModule() {
        return true;
    }

    public String getName() {
        return ToastModule.NAME;
    }

    public CustomToastModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public Map<String, Object> getConstants() {
        HashMap newHashMap = MapBuilder.newHashMap();
        newHashMap.put(DURATION_SHORT_KEY, 0);
        newHashMap.put(DURATION_LONG_KEY, 1);
        newHashMap.put(GRAVITY_TOP_KEY, 49);
        newHashMap.put(GRAVITY_BOTTOM_KEY, 81);
        newHashMap.put(GRAVITY_CENTER, 17);
        newHashMap.put("INFO", 1);
        newHashMap.put("OK", 2);
        newHashMap.put(ICON_TYPE_ERROR, 3);
        return newHashMap;
    }

    @ReactMethod
    public void showLoading(final String str) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                int unused = CustomToastModule.this.mToastID = YPDToast.getInstance().toast(CustomToastModule.this.getReactApplicationContext(), str, 2);
            }
        });
    }

    @ReactMethod
    public void hideLoading() {
        if (this.mToastID != -1) {
            UiThreadUtil.runOnUiThread(new Runnable() {
                public void run() {
                    YPDToast.getInstance().dismiss(CustomToastModule.this.mToastID);
                }
            });
        }
    }

    @ReactMethod
    public void showToast(final String str, final int i) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                YPDToast.getInstance().toast(CustomToastModule.this.getReactApplicationContext(), str, 5, true, i);
            }
        });
    }

    @ReactMethod
    public void show(final String str, int i) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                YPDToast.getInstance().toast((Context) CustomToastModule.this.getReactApplicationContext(), str);
            }
        });
    }

    @ReactMethod
    public void showWithGravity(final String str, int i, int i2) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                YPDToast.getInstance().toast((Context) CustomToastModule.this.getReactApplicationContext(), str);
            }
        });
    }

    @ReactMethod
    public void showWithGravityAndOffset(final String str, int i, int i2, int i3, int i4) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                YPDToast.getInstance().toast((Context) CustomToastModule.this.getReactApplicationContext(), str);
            }
        });
    }
}
