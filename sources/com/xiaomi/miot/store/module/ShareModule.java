package com.xiaomi.miot.store.module;

import android.app.Activity;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.xiaomi.miot.store.api.ICallback;
import com.xiaomi.miot.store.common.RNAppStoreApiManager;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.xiaomi.youpin.youpin_constants.YPStoreConstant;
import java.util.Map;

public class ShareModule extends ReactContextBaseJavaModule {
    private static final String KEY_CHANNEL = "channel";
    private static final String KEY_RESULT = "result";
    private static final String KEY_URL = "url";
    private final String TAG = com.facebook.react.modules.share.ShareModule.NAME;

    public String getName() {
        return "ShareAndroid";
    }

    public ShareModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void share(ReadableMap readableMap, final Callback callback) {
        if (readableMap != null) {
            String string = readableMap.getString("url");
            LogUtils.d(com.facebook.react.modules.share.ShareModule.NAME, "share(): url:" + string);
            Activity i = RNAppStoreApiManager.a().i();
            if (i == null) {
                return;
            }
            if (isExternShare()) {
                StoreApiManager.a().b().b(i, string);
                return;
            }
            final String string2 = readableMap.getString("channel");
            StoreApiManager.a().a(string2, i, string, new ICallback() {
                public void callback(Map map) {
                    if (callback != null) {
                        callback.invoke(string2, map.get("result"));
                    }
                }
            });
        }
    }

    private boolean isExternShare() {
        Map<String, Object> d = StoreApiManager.a().d();
        if (d != null) {
            return ((Boolean) d.get(YPStoreConstant.EXTERN_SHARE)).booleanValue();
        }
        return false;
    }
}
