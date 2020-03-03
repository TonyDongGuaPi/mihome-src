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

public class WxpayModule extends ReactContextBaseJavaModule {
    private static final String KEY_PAY_RESULT_CODE = "pay_result_code";
    private static final String KEY_PAY_RESULT_MSG = "pay_result_msg";
    private static final String TAG = "WxpayModule";

    public String getName() {
        return "WxpayAndroid";
    }

    public WxpayModule(ReactApplicationContext reactApplicationContext) {
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
            StoreApiManager.a().b(YPStoreConstant.PAY_WXPAY, i, str, new ICallback() {
                public void callback(Map map) {
                    if (map != null) {
                        LogUtils.d(WxpayModule.TAG, map.toString());
                        callback.invoke(map.get(WxpayModule.KEY_PAY_RESULT_CODE), map.get(WxpayModule.KEY_PAY_RESULT_MSG));
                        return;
                    }
                    callback.invoke("", "");
                }
            });
            return;
        }
        LogUtils.e(TAG, "Wxpay not implement!");
        callback.invoke("");
    }
}