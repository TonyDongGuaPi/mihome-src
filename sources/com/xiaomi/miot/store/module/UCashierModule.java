package com.xiaomi.miot.store.module;

import android.app.Activity;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.xiaomi.miot.store.api.ICallback;
import com.xiaomi.miot.store.common.RNAppStoreApiManager;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.xiaomi.youpin.youpin_constants.YPStoreConstant;
import java.util.Map;
import javax.annotation.Nullable;

public class UCashierModule extends ReactContextBaseJavaModule {
    public String getName() {
        return "UCashierAndroid";
    }

    public UCashierModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Nullable
    public Map<String, Object> getConstants() {
        return super.getConstants();
    }

    public void initialize() {
        super.initialize();
    }

    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        StoreApiManager.a().g();
    }

    @ReactMethod
    public void pay(String str, final Callback callback) {
        Activity i = RNAppStoreApiManager.a().i();
        if (i != null) {
            StoreApiManager.a().b(YPStoreConstant.PAY_UCASHIER, i, str, new ICallback() {
                public void callback(Map map) {
                    callback.invoke(map.get("code"), map.get("message"), map.get("result"));
                }
            });
            return;
        }
        LogUtils.e("UCashierModule", "UCashier not implement!");
        callback.invoke(-1, "", "");
    }
}
