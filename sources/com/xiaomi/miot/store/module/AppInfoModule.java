package com.xiaomi.miot.store.module;

import android.os.Build;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.common.MapBuilder;
import com.xiaomi.miot.store.common.MiotStoreConstant;
import com.xiaomi.miot.store.common.RNAppStoreApiManager;
import com.xiaomi.youpin.common.AppIdManager;
import com.xiaomi.youpin.common.util.AppInfo;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.xiaomi.youpin.youpin_common.UserAgent;
import com.xiaomi.youpin.youpin_constants.YPStoreConstant;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class AppInfoModule extends ReactContextBaseJavaModule {
    static final String TAG = "AppInfoModule";

    public static String getSdkVersion() {
        return YPStoreConstant.RN_SDK_VERSION;
    }

    public String getName() {
        return "AppInfoAndroid";
    }

    public AppInfoModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public Map<String, Object> createConstantsMap() {
        HashMap newHashMap = MapBuilder.newHashMap();
        RNAppStoreApiManager.a().e();
        newHashMap.put("SDK_INT", Integer.valueOf(Build.VERSION.SDK_INT));
        newHashMap.put("PackageName", AppInfo.b());
        newHashMap.put("OsName", AppInfo.d());
        newHashMap.put("OsVersion", AppInfo.e());
        newHashMap.put("AppVersion", AppInfo.f());
        newHashMap.put("AppVersionCode", Integer.valueOf(AppInfo.g()));
        newHashMap.put("DeviceModel", AppInfo.h());
        newHashMap.put("OsIncremental", AppInfo.i());
        newHashMap.put("IMEI", AppIdManager.a().b());
        newHashMap.put("DeviceId", AppIdManager.a().c());
        newHashMap.put("UA_pixels", AppInfo.l());
        newHashMap.put("SDCARD_PATH_BUNDLE", MiotStoreConstant.h);
        newHashMap.put("IsMiui", Boolean.valueOf(AppInfo.m()));
        newHashMap.put("MiotSDKVer", getSdkVersion());
        newHashMap.put("OSBrand", Build.BRAND);
        newHashMap.put("UserAgent", UserAgent.d());
        return newHashMap;
    }

    @Nullable
    public Map<String, Object> getConstants() {
        Map<String, Object> createConstantsMap = createConstantsMap();
        Map<String, Object> d = StoreApiManager.a().d();
        if (d != null && d.size() > 0) {
            createConstantsMap.putAll(d);
        }
        return createConstantsMap;
    }
}
