package com.xiaomi.miot.store.module;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.xiaomi.miot.store.api.RNStoreApiProvider;
import com.xiaomi.miot.store.common.RNAppStoreApiManager;

public class MIMCMsgBridgeModule extends ReactContextBaseJavaModule {
    public String getName() {
        return "MessageCenterMIMCBridgeModule";
    }

    public MIMCMsgBridgeModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void getRecentContactList(Callback callback) {
        RNStoreApiProvider j = RNAppStoreApiManager.a().j();
        if (j != null) {
            j.getRecentContactList(callback);
        }
    }

    @ReactMethod
    public void getMessageHistory(String str, String str2, int i, Callback callback) {
        RNStoreApiProvider j = RNAppStoreApiManager.a().j();
        if (j != null) {
            j.getMessageHistory(str, str2, i, callback);
        }
    }

    @ReactMethod
    public void multiupdateExtra(ReadableMap readableMap, String str, Callback callback) {
        RNStoreApiProvider j = RNAppStoreApiManager.a().j();
        if (j != null) {
            j.multiupdateExtra(readableMap, str, callback);
        }
    }
}
