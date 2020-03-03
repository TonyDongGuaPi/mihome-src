package com.xiaomi.miot.store.module;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.xiaomi.miot.store.common.RNAppStoreApiManager;
import com.xiaomi.youpin.log.LogUtils;
import java.util.Map;
import javax.annotation.Nullable;

public class BroadcastModule extends ReactContextBaseJavaModule {
    private static final String TAG = "BroadcastModule";

    public String getName() {
        return "BroadcastAndroid";
    }

    public BroadcastModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Nullable
    public Map<String, Object> getConstants() {
        return super.getConstants();
    }

    public void initialize() {
        super.initialize();
    }

    public boolean canOverrideExistingModule() {
        return super.canOverrideExistingModule();
    }

    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
    }

    @ReactMethod
    public void sendBroadcast(String str, ReadableMap readableMap) {
        LogUtils.d(TAG, "sendBroadcast(): " + str);
        Intent intent = new Intent(str);
        if (readableMap != null) {
            ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                LogUtils.d(TAG, "sendBroadcast(): " + nextKey + ", " + readableMap.getString(nextKey));
                intent.putExtra(nextKey, readableMap.getString(nextKey));
            }
        }
        RNAppStoreApiManager.a().e().sendBroadcast(intent);
    }

    @ReactMethod
    public void sendLocalBroadcast(String str, ReadableMap readableMap) {
        Intent intent = new Intent(str);
        if (readableMap != null) {
            ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                intent.putExtra(nextKey, readableMap.getString(nextKey));
            }
        }
        LocalBroadcastManager.getInstance(RNAppStoreApiManager.a().e()).sendBroadcast(intent);
    }
}
