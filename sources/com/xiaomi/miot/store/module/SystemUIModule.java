package com.xiaomi.miot.store.module;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.PowerManager;
import android.view.Window;
import android.view.WindowManager;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.ReactConstants;
import com.mijia.model.property.CameraPropertyBase;
import com.xiaomi.miot.store.common.RNAppStoreApiManager;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.youpin.log.LogUtils;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class SystemUIModule extends ReactContextBaseJavaModule {
    private Context mContext;
    PowerManager.WakeLock mWakeLock;

    public String getName() {
        return "SystemUIAndroid";
    }

    public SystemUIModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mContext = reactApplicationContext;
    }

    @Nullable
    public Map<String, Object> getConstants() {
        HashMap newHashMap = MapBuilder.newHashMap();
        newHashMap.put("StatusBarHeight", Integer.valueOf(getStatusBarHeight()));
        newHashMap.put("NavigationBarHeight", Integer.valueOf(getNavigationBarHeight()));
        return newHashMap;
    }

    public void initialize() {
        super.initialize();
    }

    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        if (this.mWakeLock != null) {
            this.mWakeLock.release();
            this.mWakeLock = null;
        }
    }

    public static int getStatusBarHeight() {
        if (Build.VERSION.SDK_INT < 19) {
            return 0;
        }
        Resources resources = RNAppStoreApiManager.a().e().getResources();
        return resources.getDimensionPixelOffset(resources.getIdentifier(PreferenceConstantsInOpenSdk.B, "dimen", "android"));
    }

    public int getNavigationBarHeight() {
        int i;
        String str = Build.BRAND;
        if (str == null) {
            str = "";
        }
        if (str.equalsIgnoreCase("Meizu")) {
            i = getMeiZuSmartBarHeight(this.mContext);
            LogUtils.d("IsMeizu phone", "get smart bar height = " + i);
        } else if (str.equalsIgnoreCase("Huawei")) {
            i = 0;
        } else {
            i = getNavigationBarHeight2(this.mContext);
        }
        LogUtils.d("navigationBarHight = ", String.valueOf(i));
        return i;
    }

    @ReactMethod
    public void setTranslucentStatus(final boolean z, final Callback callback) {
        LogUtils.e("store", "setTranslucentStatus thread pid: " + Thread.currentThread().getId());
        if (Build.VERSION.SDK_INT >= 19) {
            getReactApplicationContext().runOnUiQueueThread(new Runnable() {
                public void run() {
                    try {
                        Window window = RNAppStoreApiManager.a().i().getWindow();
                        WindowManager.LayoutParams attributes = window.getAttributes();
                        if (z) {
                            attributes.flags |= Constants.CALLIGRAPHY_TAG_PRICE;
                        } else {
                            attributes.flags &= -67108865;
                        }
                        window.setAttributes(attributes);
                        SystemUIModule.this.setStatusBarDarkMode(z, RNAppStoreApiManager.a().i());
                        callback.invoke(true);
                    } catch (Exception unused) {
                        callback.invoke(false);
                    }
                }
            });
            return;
        }
        callback.invoke(false);
    }

    public void setStatusBarDarkMode(boolean z, Activity activity) {
        try {
            Class<?> cls = activity.getWindow().getClass();
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Method method = cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE});
            Window window = activity.getWindow();
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(z ? i : 0);
            objArr[1] = Integer.valueOf(i);
            method.invoke(window, objArr);
        } catch (Exception unused) {
        }
    }

    public static int getMeiZuSmartBarHeight(Context context) {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("mz_action_button_min_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static boolean checkDeviceHasNavigationBar(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        boolean z = identifier > 0 ? resources.getBoolean(identifier) : false;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{"qemu.hw.mainkeys"});
            if ("1".equals(str)) {
                return false;
            }
            if ("0".equals(str)) {
                return true;
            }
            return z;
        } catch (Exception e) {
            e.printStackTrace();
            return z;
        }
    }

    private static int getNavigationBarHeight2(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier <= 0 || !checkDeviceHasNavigationBar(context)) {
            return 0;
        }
        return resources.getDimensionPixelSize(identifier);
    }

    @ReactMethod
    public synchronized void enableKeepScreenOn() {
        if (this.mWakeLock == null) {
            RNAppStoreApiManager.a().i();
            this.mWakeLock = ((PowerManager) this.mContext.getSystemService(CameraPropertyBase.l)).newWakeLock(6, ReactConstants.TAG);
            this.mWakeLock.acquire();
        }
    }

    @ReactMethod
    public synchronized void disableKeepScreenOn() {
        if (this.mWakeLock != null) {
            this.mWakeLock.release();
            this.mWakeLock = null;
        }
    }
}
