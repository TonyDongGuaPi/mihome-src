package com.xiaomi.miot.store.module.storage;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
import com.xiaomi.youpin.common.cache.ICache;
import com.xiaomi.youpin.common.cache.MemoryCache;

@ReactModule(name = "MemoryAsyncStorage")
public class MemoryAsyncStorage extends AbstractAsyncStorageModule {
    protected static final String NAME = "MemoryAsyncStorage";

    public String getName() {
        return NAME;
    }

    public MemoryAsyncStorage(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void multiGet(ReadableArray readableArray, Callback callback) {
        super.multiGet(readableArray, callback);
    }

    @ReactMethod
    public void multiSet(ReadableArray readableArray, Callback callback) {
        super.multiSet(readableArray, callback);
    }

    @ReactMethod
    public void multiRemove(ReadableArray readableArray, Callback callback) {
        super.multiRemove(readableArray, callback);
    }

    @ReactMethod
    public void multiMerge(ReadableArray readableArray, Callback callback) {
        super.multiMerge(readableArray, callback);
    }

    @ReactMethod
    public void clear(Callback callback) {
        super.clear(callback);
    }

    @ReactMethod
    public void getAllKeys(Callback callback) {
        super.getAllKeys(callback);
    }

    /* access modifiers changed from: package-private */
    public ICache<String> createCache() {
        return new MemoryCache(100);
    }
}
