package com.xiaomi.miot.store.module;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.xiaomi.miot.store.common.MiotStoreConstant;
import com.xiaomi.miot.store.common.RNAppStoreApiManager;
import com.xiaomi.youpin.log.LogUtils;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class CommonEventModule extends ReactContextBaseJavaModule {
    public boolean canOverrideExistingModule() {
        return true;
    }

    public String getName() {
        return "CommonEventAndroid";
    }

    @ReactMethod
    public void startCommonEventListener() {
    }

    public CommonEventModule(ReactApplicationContext reactApplicationContext) {
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
    }

    public static void sendCommonEvent(HashMap<String, String> hashMap) {
        WritableMap createMap = Arguments.createMap();
        for (Map.Entry next : hashMap.entrySet()) {
            createMap.putString((String) next.getKey(), (String) next.getValue());
        }
        RNAppStoreApiManager.a().a("CommonEvent", createMap);
    }

    static class EventBroadcastReceiver extends BroadcastReceiver {
        ReactApplicationContext reactContext;

        public EventBroadcastReceiver(ReactApplicationContext reactApplicationContext) {
            this.reactContext = reactApplicationContext;
        }

        public void onReceive(Context context, Intent intent) {
            if (MiotStoreConstant.f11377a.equals(intent.getAction())) {
                HashMap hashMap = (HashMap) intent.getSerializableExtra(MiotStoreConstant.b);
                WritableMap createMap = Arguments.createMap();
                for (Map.Entry entry : hashMap.entrySet()) {
                    createMap.putString((String) entry.getKey(), (String) entry.getValue());
                }
                RNAppStoreApiManager.a().a("CommonEvent", createMap);
                LogUtils.d("MiotStore", "send common event...: " + hashMap.toString());
            }
        }
    }
}
