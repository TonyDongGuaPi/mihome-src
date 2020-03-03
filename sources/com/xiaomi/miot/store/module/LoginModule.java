package com.xiaomi.miot.store.module;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableMap;
import com.xiaomi.miot.store.common.RNAppStoreApiManager;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.xiaomi.youpin.youpin_common.StoreApiProvider;
import java.util.Map;
import javax.annotation.Nullable;

public class LoginModule extends ReactContextBaseJavaModule {
    private static final int LOGIN_CAPT_CHA = 5;
    private static final int LOGIN_FAILED = 1;
    private static final int LOGIN_NEED_NOTIFICATION = 6;
    private static final int LOGIN_NO_ACCOUNT = 2;
    private static final int LOGIN_SUCCEED = 0;
    private static final int LOGIN_TWO_STEP = 4;
    private static final int LOGIN_WRONG_PWD = 3;
    public static final String TAG = "LoginModule";
    public static final String XIAOMI_ACCOUNT_TYPE = "com.xiaomi";

    public String getName() {
        return "LoginAndroid";
    }

    @ReactMethod
    public void startRegisterReceiver() {
    }

    public LoginModule(ReactApplicationContext reactApplicationContext) {
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

    @ReactMethod
    public void openLoginPage() {
        LogUtils.d(TAG, "openLoginPage");
        StoreApiProvider b = StoreApiManager.a().b();
        Activity i = RNAppStoreApiManager.a().i();
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        boolean z = false;
        sb.append(b == null);
        sb.append(" a: ");
        if (i == null) {
            z = true;
        }
        sb.append(z);
        LogUtils.d(TAG, sb.toString());
        if (b != null) {
            LogUtils.d(TAG, "open");
            b.a(i);
        }
    }

    @ReactMethod
    public void openCustomLoginPage(ReadableMap readableMap) {
        LogUtils.d(TAG, "openCustomLoginPage");
        StoreApiProvider b = StoreApiManager.a().b();
        Activity i = RNAppStoreApiManager.a().i();
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        boolean z = false;
        sb.append(b == null);
        sb.append(" a: ");
        if (i == null) {
            z = true;
        }
        sb.append(z);
        LogUtils.d(TAG, sb.toString());
        if (b != null) {
            String str = "";
            if (readableMap.hasKey("type")) {
                str = readableMap.getString("type");
            }
            LogUtils.d(TAG, "open  " + str);
            b.a(i, str);
        }
    }

    @ReactMethod
    public void getAccountInfo(ReadableMap readableMap, @NonNull Callback callback) {
        LogUtils.d(TAG, readableMap.toString());
        StoreApiProvider b = StoreApiManager.a().b();
        if (b != null) {
            String i = b.i();
            new GetAccountInfoTask(getReactApplicationContext()).execute(new ReadableMap[]{readableMap});
            if (!TextUtils.isEmpty(i) && !TextUtils.equals(i, "0")) {
                WritableMap createMap = Arguments.createMap();
                createMap.putString("userId", i);
                callback.invoke(0, createMap);
                return;
            }
        }
        callback.invoke(-1, null);
    }

    static class GetAccountInfoTask extends GuardedAsyncTask<ReadableMap, Void> {
        protected GetAccountInfoTask(ReactContext reactContext) {
            super(reactContext);
        }

        /* access modifiers changed from: protected */
        public void doInBackgroundGuarded(ReadableMap... readableMapArr) {
            ReadableMap readableMap = readableMapArr[0];
            StoreApiProvider b = StoreApiManager.a().b();
            WritableMap createMap = Arguments.createMap();
            createMap.putString("uid", "0");
            createMap.putMap("tokens", (ReadableMap) null);
            if (b != null) {
                String i = b.i();
                WritableMap createMap2 = Arguments.createMap();
                ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
                while (keySetIterator.hasNextKey()) {
                    String nextKey = keySetIterator.nextKey();
                    readableMap.getString(nextKey);
                    try {
                        if (!TextUtils.isEmpty(b.a(nextKey))) {
                            createMap2.putBoolean(nextKey, true);
                        }
                    } catch (Throwable unused) {
                    }
                }
                createMap.putString("uid", i);
                createMap.putMap("tokens", createMap2);
            }
            LogUtils.d(LoginModule.TAG, "send update auth js event.");
            RNAppStoreApiManager.a().a("updateAuth", createMap);
        }
    }
}
