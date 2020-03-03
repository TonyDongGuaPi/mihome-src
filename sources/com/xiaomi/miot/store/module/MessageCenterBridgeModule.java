package com.xiaomi.miot.store.module;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.xiaomi.miot.store.api.RNStoreApiProvider;
import com.xiaomi.miot.store.common.RNAppStoreApiManager;

public class MessageCenterBridgeModule extends ReactContextBaseJavaModule {
    public String getName() {
        return "MessageCenterBridgeModule";
    }

    public MessageCenterBridgeModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void getChatList(Callback callback) {
        RNStoreApiProvider j = RNAppStoreApiManager.a().j();
        if (j != null) {
            j.getChatList(callback);
        }
    }

    @ReactMethod
    public void deleteChat(String str, String str2, Callback callback) {
        RNStoreApiProvider j = RNAppStoreApiManager.a().j();
        if (j != null) {
            j.deleteChat(str, str2, callback);
        }
    }

    @ReactMethod
    public void setBadge(String str, String str2, int i) {
        RNStoreApiProvider j = RNAppStoreApiManager.a().j();
        if (j != null) {
            j.setBadge(str, str2, i);
        }
    }

    @ReactMethod
    public void getBadgeCount(String str, String str2, Callback callback) {
        RNStoreApiProvider j = RNAppStoreApiManager.a().j();
        if (j != null) {
            j.getBadgeCount(str, str2, callback);
        }
    }
}
