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

public class UnionMiPayModule extends ReactContextBaseJavaModule {
    private static final String TAG = "UnionMiPayModule";

    public String getName() {
        return "UnionMiPayAndroid";
    }

    public UnionMiPayModule(ReactApplicationContext reactApplicationContext) {
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
            StoreApiManager.a().b(YPStoreConstant.PAY_UNIONMIPAY, i, str, new ICallback() {
                public void callback(Map map) {
                    LogUtils.d("UnionmipayModule", "res: " + map);
                    if (map != null) {
                        callback.invoke(map.get("result"));
                        return;
                    }
                    callback.invoke("");
                }
            });
            return;
        }
        LogUtils.e("UnionmipayModule", "Unionmipay not implement!");
        callback.invoke("");
    }
}
