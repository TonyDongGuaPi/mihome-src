package com.xiaomi.youpin.app_sdk.rn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.WritableMap;
import com.xiaomi.miot.store.api.ICallback;
import com.xiaomi.miot.store.api.IMiotStoreApi;
import com.xiaomi.miot.store.api.MiotStoreApi;
import com.xiaomi.miot.store.api.RNStoreApiProvider;
import com.xiaomi.miot.store.common.MiotStoreConstant;
import com.xiaomi.miot.store.common.RNAppStoreApiManager;
import com.xiaomi.miot.store.verify.VerificationCallback;
import com.xiaomi.miot.store.verify.YouPinVerificationManager;
import com.xiaomi.verificationsdk.internal.VerifyResult;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import com.xiaomi.youpin.common.util.UrlUtils;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.share.config.YouPinShareApi;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import java.util.Map;

public abstract class AbsRNStoreApiProviderImpl implements RNStoreApiProvider {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23203a = "RNStoreApiProviderImp";

    public void bindWeixin(Callback callback) {
    }

    public void deleteChat(String str, String str2, Callback callback) {
    }

    public boolean enableStore() {
        return true;
    }

    public void getBadgeCount(String str, String str2, Callback callback) {
    }

    public void getChatList(Callback callback) {
    }

    public String getServerLocalCode() {
        return "cn";
    }

    public void handleHiddenException(String str, Throwable th) {
    }

    public boolean isRNDebug() {
        return false;
    }

    public void logout() {
    }

    public void onActivityCreate(Activity activity) {
    }

    public void onActivityDestroy(Activity activity) {
    }

    public void onActivityPause(Activity activity) {
    }

    public void onActivityResume(Activity activity) {
    }

    public void onInitial(IMiotStoreApi iMiotStoreApi) {
    }

    public void onReactContextInitialed() {
    }

    public void openChangePasswordPage(Activity activity, Callback callback) {
    }

    public void openLoginPage(Context context) {
    }

    public void setBadge(String str, String str2, int i) {
    }

    public void showAppstoreComment() {
    }

    public int startCustomerServiceChat(Context context, Map<String, String> map) {
        return 0;
    }

    public boolean useFragment() {
        return true;
    }

    public void handleException(Throwable th) {
        if (th != null) {
            th.printStackTrace();
        }
    }

    public void navigateBack(Activity activity, String str) {
        LogUtils.d(f23203a, "navigateBack url: " + str);
        if (activity != null && str != null) {
            if ("main".equals(UrlUtils.a(str))) {
                activity.finish();
            }
            LocalBroadcastManager.getInstance(activity).sendBroadcast(new Intent(MiotStoreConstant.d));
        }
    }

    public String shouldOpenUrl(String str, String str2) {
        return UrlDispatchManger.a().a(MiotStoreApi.a().getActivity(), str2, true, -1) ? "" : str2;
    }

    public void openUrl(String str, String str2) {
        LogUtils.d("BackgroundPage", "openUri:" + str);
        UrlDispatchManger.a().a(MiotStoreApi.a().getActivity(), str, false, -1);
    }

    public void shareCustom(ReadableMap readableMap, final Callback callback) {
        int i = readableMap.hasKey("type") ? readableMap.getInt("type") : 0;
        String string = readableMap.hasKey("channel") ? readableMap.getString("channel") : "";
        String string2 = readableMap.hasKey("image") ? readableMap.getString("image") : "";
        String string3 = readableMap.hasKey("url") ? readableMap.getString("url") : "";
        if (i == 1) {
            YouPinShareApi.a(StoreApiManager.a().c(), string, string2, (YouPinShareApi.Callback) new YouPinShareApi.Callback() {
                public void a(int i, String str) {
                    callback.invoke(Integer.valueOf(i), str, "");
                }
            });
        } else if (TextUtils.isEmpty(string3)) {
            callback.invoke(-2);
        } else {
            Activity i2 = RNAppStoreApiManager.a().i();
            if (!TextUtils.isEmpty(string)) {
                StoreApiManager.a().a(string, i2, string3, new ICallback() {
                    public void callback(Map map) {
                        if (callback != null) {
                            callback.invoke(map.get("result"));
                        }
                    }
                });
            } else {
                YouPinShareApi.a(((ReadableNativeMap) readableMap).toHashMap(), new YouPinShareApi.Callback() {
                    public void a(int i, String str) {
                        callback.invoke(Integer.valueOf(i), str);
                    }
                }, i2);
            }
        }
    }

    public void iniVerify(Activity activity, ReadableMap readableMap) {
        LogUtils.d(f23203a, "iniVerify");
        YouPinVerificationManager.a().a(activity);
    }

    public void startVerify(ReadableMap readableMap, final Callback callback) {
        LogUtils.d(f23203a, "startVerify");
        boolean z = true;
        boolean z2 = readableMap.hasKey("isVoiceover") && readableMap.getBoolean("isVoiceover");
        if (!readableMap.hasKey("isBizActiveVerify") || !readableMap.getBoolean("isBizActiveVerify")) {
            z = false;
        }
        YouPinVerificationManager.a().a(z2, z, new VerificationCallback() {

            /* renamed from: a  reason: collision with root package name */
            boolean f23207a = false;

            public void a(VerifyResult verifyResult) {
                LogUtils.d(AbsRNStoreApiProviderImpl.f23203a, "onVerifySuccess");
                if (!this.f23207a) {
                    this.f23207a = true;
                    if (callback != null) {
                        WritableMap createMap = Arguments.createMap();
                        String c2 = verifyResult.c();
                        String a2 = verifyResult.a();
                        String b2 = verifyResult.b();
                        createMap.putInt("result", 0);
                        createMap.putString("clientId", c2);
                        createMap.putString("eventId", a2);
                        createMap.putString("flag", b2);
                        callback.invoke(createMap);
                    }
                }
            }

            public void a() {
                LogUtils.d(AbsRNStoreApiProviderImpl.f23203a, "onVerifyCancel");
                if (!this.f23207a) {
                    this.f23207a = true;
                    if (callback != null) {
                        WritableMap createMap = Arguments.createMap();
                        createMap.putInt("result", -100);
                        createMap.putString("errMsg", "用户取消了验证");
                        callback.invoke(createMap);
                    }
                }
            }

            public void a(int i, String str) {
                LogUtils.d(AbsRNStoreApiProviderImpl.f23203a, "onVerifyFail");
                if (!this.f23207a) {
                    this.f23207a = true;
                    if (callback != null) {
                        WritableMap createMap = Arguments.createMap();
                        createMap.putInt("result", i);
                        createMap.putString("errMsg", str);
                        callback.invoke(createMap);
                    }
                }
            }
        });
    }

    public void insertEventToCalendar(Activity activity, ReadableMap readableMap, Callback callback) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("result", -1);
        callback.invoke(createMap);
    }
}
