package com.xiaomi.miot.store.module;

import android.support.annotation.NonNull;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public class ConcurrentEventModule extends BaseJavaModule {
    ConcurrentHashMap<String, CountDownLatch> eventLatchMap;
    ConcurrentHashMap<String, ReadableMap> eventResultMap;

    public String getName() {
        return "ConcurrentEventAndroid";
    }

    private ConcurrentEventModule() {
        this.eventLatchMap = new ConcurrentHashMap<>();
        this.eventResultMap = new ConcurrentHashMap<>();
    }

    private static class Instance {
        static ConcurrentEventModule module = new ConcurrentEventModule();

        private Instance() {
        }
    }

    public static ConcurrentEventModule getInstance() {
        return Instance.module;
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

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        if (this.eventResultMap != null) {
            this.eventResultMap.clear();
            this.eventResultMap = null;
        }
        if (this.eventLatchMap != null) {
            this.eventLatchMap.clear();
            this.eventLatchMap = null;
        }
        super.finalize();
    }

    public ReadableMap waitEvent(@NonNull String str, @NonNull CountDownLatch countDownLatch) {
        if (this.eventLatchMap.containsKey(str)) {
            return null;
        }
        this.eventLatchMap.put(str, countDownLatch);
        try {
            countDownLatch.await(500, TimeUnit.MILLISECONDS);
            if (this.eventLatchMap.containsKey(str)) {
                this.eventLatchMap.remove(str);
            }
            ReadableMap readableMap = this.eventResultMap.get(str);
            if (this.eventResultMap.containsKey(str)) {
                this.eventResultMap.remove(str);
            }
            return readableMap;
        } catch (Exception e) {
            e.printStackTrace();
            if (this.eventLatchMap.containsKey(str)) {
                this.eventLatchMap.remove(str);
            }
            ReadableMap readableMap2 = this.eventResultMap.get(str);
            if (this.eventResultMap.containsKey(str)) {
                this.eventResultMap.remove(str);
            }
            return readableMap2;
        } catch (Throwable unused) {
            if (this.eventLatchMap.containsKey(str)) {
                this.eventLatchMap.remove(str);
            }
            ReadableMap readableMap3 = this.eventResultMap.get(str);
            if (this.eventResultMap.containsKey(str)) {
                this.eventResultMap.remove(str);
            }
            return readableMap3;
        }
    }

    @ReactMethod
    public void notifyEvent(@NonNull String str, ReadableMap readableMap) {
        CountDownLatch countDownLatch = this.eventLatchMap.get(str);
        if (countDownLatch != null) {
            if (this.eventLatchMap.containsKey(str)) {
                this.eventLatchMap.remove(str);
                this.eventResultMap.put(str, readableMap);
            }
            countDownLatch.countDown();
        }
    }
}
