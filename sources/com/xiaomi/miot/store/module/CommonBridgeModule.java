package com.xiaomi.miot.store.module;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.alipay.sdk.util.i;
import com.amap.api.services.cloud.CloudSearch;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.MapBuilder;
import com.google.android.exoplayer2.C;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.xiaomi.miot.store.api.ICallback;
import com.xiaomi.miot.store.api.RNStoreApiProvider;
import com.xiaomi.miot.store.common.MiotStoreConstant;
import com.xiaomi.miot.store.common.RNAppStoreApiManager;
import com.xiaomi.miot.store.ui.MiotStoreMainActivity;
import com.xiaomi.miot.store.ui.MiotTakeMediaActivity;
import com.xiaomi.miot.store.utils.JSONMapUtils;
import com.xiaomi.miot.store.utils.ScreenshotManager;
import com.xiaomi.miot.store.utils.Utils;
import com.xiaomi.miot.store.utils.entity.ResultBean;
import com.xiaomi.qrcode2.QrCodeCallback;
import com.xiaomi.qrcode2.QrCodeRouter;
import com.xiaomi.smarthome.library.bluetooth.OTAErrorCode;
import com.xiaomi.youpin.UserMode;
import com.xiaomi.youpin.common.AppIdManager;
import com.xiaomi.youpin.common.util.AppInfo;
import com.xiaomi.youpin.cookie.YouPinCookieManager;
import com.xiaomi.youpin.util.FileUtils;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.xiaomi.youpin.youpin_common.StoreApiProvider;
import com.xiaomi.youpin.youpin_common.UserAgent;
import com.xiaomi.youpin.youpin_common.api.IStoreCallback;
import com.xiaomi.youpin.youpin_common.api.StoreBaseCallback;
import com.xiaomi.youpin.youpin_common.login.MiServiceTokenInfo;
import com.xiaomi.youpin.youpin_common.login.YouPinCookieUtils;
import com.xiaomi.youpin.youpin_common.statistic.StatManager;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public class CommonBridgeModule extends ReactContextBaseJavaModule {
    private static final String INNER_VERSION = "20191128";
    private Handler mUiHandler = new Handler(Looper.getMainLooper());

    @ReactMethod
    public void appendUserAgent(String str) {
    }

    @ReactMethod
    public void exportLog() {
    }

    @Deprecated
    @ReactMethod
    public void getAllCookie(Callback callback) {
    }

    public String getName() {
        return "CommonBridgeModule";
    }

    @Deprecated
    @ReactMethod
    public void setUserAgent(String str) {
    }

    @ReactMethod
    public void stat(String str, String str2) {
    }

    public CommonBridgeModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public Map<String, Object> getConstants() {
        RNAppStoreApiManager.a().e();
        String str = "";
        if (!RNAppStoreApiManager.a().j().isRNDebug()) {
            str = RNAppStoreApiManager.a().d();
        }
        HashMap newHashMap = MapBuilder.newHashMap();
        newHashMap.put("StatusBarHeight", Integer.valueOf(SystemUIModule.getStatusBarHeight()));
        newHashMap.put("BundlePath", str);
        newHashMap.put("SDK_INT", Integer.valueOf(Build.VERSION.SDK_INT));
        newHashMap.put("PackageName", AppInfo.b());
        newHashMap.put("OsName", AppInfo.d());
        newHashMap.put("OsVersion", AppInfo.e());
        newHashMap.put("AppVersion", AppInfo.f());
        newHashMap.put("AppVersionCode", Integer.valueOf(AppInfo.g()));
        newHashMap.put("DeviceModel", AppInfo.h());
        newHashMap.put("OsIncremental", AppInfo.i());
        newHashMap.put("IMEI", AppIdManager.a().b());
        newHashMap.put("DeviceId", AppIdManager.a().c());
        newHashMap.put("UA_pixels", AppInfo.l());
        newHashMap.put("SDCARD_PATH_BUNDLE", MiotStoreConstant.h);
        newHashMap.put("IsMiui", Boolean.valueOf(AppInfo.m()));
        newHashMap.put("MiotSDKVer", AppInfoModule.getSdkVersion());
        newHashMap.put("OSBrand", Build.BRAND);
        newHashMap.put(TbsCoreSettings.TBS_SETTINGS_APP_KEY, StoreApiManager.a().b().d());
        newHashMap.put("UserAgent", UserAgent.d());
        newHashMap.put("BrowserUserAgent", UserAgent.e());
        newHashMap.put(CloudSearch.SearchBound.LOCAL_SHAPE, RNAppStoreApiManager.a().j().getServerLocalCode());
        newHashMap.put("SupportCheckYoupin", 1);
        newHashMap.put("innerVersion", INNER_VERSION);
        newHashMap.put("SupportCustomerServiceChat", true);
        newHashMap.put("SupportMessageCenter", true);
        newHashMap.put("IsSupportPullRefresh", true);
        Map<String, Object> d = StoreApiManager.a().d();
        if (d != null && d.size() > 0) {
            newHashMap.putAll(d);
        }
        return newHashMap;
    }

    @ReactMethod
    public void openActivity(final String str) {
        this.mUiHandler.post(new Runnable() {
            public void run() {
                RNAppStoreApiManager.a().j().openUrl(str, "");
            }
        });
    }

    @ReactMethod
    public void updateLoginInfo(String str, String str2, boolean z) {
        Activity i;
        if (z && (i = RNAppStoreApiManager.a().i()) != null) {
            StoreApiManager.a().b().a(i);
        }
    }

    @ReactMethod
    public void setCookie(String str, String str2, String str3) {
        if (!str3.startsWith(ConnectionHelper.HTTP_PREFIX)) {
            str3 = ConnectionHelper.HTTP_PREFIX + str3;
        }
        YouPinCookieManager.a().a(str3, str + "=" + str2 + "; domain=" + str3);
    }

    @ReactMethod
    public void getCookie(String str, String str2, Callback callback) {
        if (!str2.startsWith(ConnectionHelper.HTTP_PREFIX)) {
            str2 = ConnectionHelper.HTTP_PREFIX + str2;
        }
        String b = YouPinCookieManager.a().b(str2);
        if (!TextUtils.isEmpty(b)) {
            String[] split = b.split(i.b);
            int length = split.length;
            int i = 0;
            while (i < length) {
                String[] split2 = split[i].split("=");
                if (split2 == null || split2.length != 2 || !split2[0].trim().equals(str)) {
                    i++;
                } else {
                    callback.invoke(split2[1].trim());
                    return;
                }
            }
        }
        callback.invoke(new Object[0]);
    }

    @ReactMethod
    public void removeCookie(String str, String str2) {
        if (!str2.startsWith(ConnectionHelper.HTTP_PREFIX)) {
            str2 = ConnectionHelper.HTTP_PREFIX + str2;
        }
        YouPinCookieManager.a().a(str2);
    }

    @ReactMethod
    public void removeAllCookies() {
        YouPinCookieManager.a().b();
    }

    @ReactMethod
    public void getUserAgent(Callback callback) {
        callback.invoke(UserAgent.d());
    }

    @ReactMethod
    public void openUri(final String str, final String str2, final Callback callback) {
        this.mUiHandler.post(new Runnable() {
            public void run() {
                RNAppStoreApiManager.a().j().openUrl(str2, str);
                if (callback != null) {
                    callback.invoke(new Object[0]);
                }
            }
        });
    }

    @ReactMethod
    public void back(String str, Callback callback) {
        this.mUiHandler.post(new Runnable() {
            public void run() {
                Activity access$000 = CommonBridgeModule.this.getCurrentActivity();
                if (access$000 == null) {
                    return;
                }
                if (access$000 instanceof MiotStoreMainActivity) {
                    Utils.a(access$000);
                    access$000.finish();
                    return;
                }
                access$000.onBackPressed();
            }
        });
    }

    @ReactMethod
    public void getSupportPayList(Callback callback) {
        Set<String> e = StoreApiManager.a().e();
        WritableArray createArray = Arguments.createArray();
        for (String pushString : e) {
            createArray.pushString(pushString);
        }
        callback.invoke(createArray);
    }

    @ReactMethod
    public void pay(String str, String str2, final Callback callback) {
        StoreApiManager.a().b(str, RNAppStoreApiManager.a().i(), str2, new ICallback() {
            public void callback(Map map) {
                if (map != null) {
                    WritableMap createMap = Arguments.createMap();
                    for (String str : map.keySet()) {
                        createMap.putString(str, map.get(str).toString());
                    }
                    callback.invoke(createMap);
                    return;
                }
                callback.invoke(new Object[0]);
            }
        });
    }

    @ReactMethod
    public void getSupportShareList(Callback callback) {
        Set<String> f = StoreApiManager.a().f();
        WritableArray createArray = Arguments.createArray();
        for (String pushString : f) {
            createArray.pushString(pushString);
        }
        callback.invoke(createArray);
    }

    @ReactMethod
    public void share(final String str, final String str2, final Callback callback) {
        this.mUiHandler.post(new Runnable() {
            public void run() {
                StoreApiManager.a().a(str, RNAppStoreApiManager.a().i(), str2, new ICallback() {
                    public void callback(Map map) {
                        if (map != null) {
                            WritableMap createMap = Arguments.createMap();
                            for (String str : map.keySet()) {
                                createMap.putString(str, map.get(str).toString());
                            }
                            callback.invoke(createMap);
                            return;
                        }
                        callback.invoke(new Object[0]);
                    }
                });
            }
        });
    }

    @ReactMethod
    public void getDToken(Promise promise) {
        promise.resolve("");
    }

    @ReactMethod
    public void shouldOpenUrl(String str, String str2, Promise promise) {
        promise.resolve(RNAppStoreApiManager.a().j().shouldOpenUrl(str, str2));
    }

    @ReactMethod
    public void reload() {
        Activity i = RNAppStoreApiManager.a().i();
        if (i != null) {
            i.runOnUiThread(new Runnable() {
                public void run() {
                    RNAppStoreApiManager.a().s();
                }
            });
        }
    }

    @ReactMethod
    public void getBundlePath(Callback callback) {
        String d = RNAppStoreApiManager.a().d();
        WritableMap createMap = Arguments.createMap();
        createMap.putString("BundlePath", d);
        callback.invoke(createMap);
    }

    @ReactMethod
    public void setResult(String str) {
        Activity i = RNAppStoreApiManager.a().i();
        if (i == null) {
            return;
        }
        if (str == null) {
            i.setResult(0);
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("result", str);
        i.setResult(-1, intent);
    }

    @ReactMethod
    public void reportCached(boolean z) {
        StatManager.a().a(z);
    }

    @ReactMethod
    public void stat2(String str, String str2, String str3, String str4) {
        StatManager.a().a(str, str2, str3, str4, StatManager.d, (JSONObject) null);
    }

    @ReactMethod
    public void stat3(String str, String str2, String str3, String str4, ReadableMap readableMap) {
        StatManager.a().a(str, str2, str3, str4, StatManager.d, JSONMapUtils.a(readableMap));
    }

    @ReactMethod
    public void scanBanner(String str, int i, final Callback callback) {
        if (getCurrentActivity() == null) {
            callback.invoke(-1, "");
            return;
        }
        QrCodeRouter.a(getCurrentActivity().getApplicationContext(), str, new QrCodeCallback() {
            public void onSuccess(String str) {
                callback.invoke(0, str);
            }

            public void onFail(int i, String str) {
                callback.invoke(1, "");
            }
        });
    }

    @ReactMethod
    public void makeQRCode(int i, int i2, String str, Callback callback) {
        if (getCurrentActivity() == null) {
            callback.invoke(-1, "");
            return;
        }
        try {
            callback.invoke(0, com.xiaomi.qrcode2.Utils.a((Context) getCurrentActivity(), i, i2, str));
        } catch (Exception e) {
            callback.invoke(-1, e.getMessage());
        }
    }

    @ReactMethod
    public void notifyDataChanged(int i, ReadableMap readableMap) {
        if (getCurrentActivity() != null) {
            Intent intent = new Intent(MiotStoreConstant.f);
            intent.putExtra("type", i);
            LocalBroadcastManager.getInstance(getCurrentActivity()).sendBroadcast(intent);
        }
    }

    @ReactMethod
    public void takePhoto(final Callback callback) {
        if (getCurrentActivity() == null) {
            callback.invoke(-1, "");
            return;
        }
        final Context applicationContext = getCurrentActivity().getApplicationContext();
        Intent intent = new Intent(getCurrentActivity(), MiotTakeMediaActivity.class);
        intent.putExtra(MiotTakeMediaActivity.KEY_TAKE_MEDIA, MiotTakeMediaActivity.TYPE_PHOTO);
        getCurrentActivity().startActivity(intent);
        AnonymousClass8 r1 = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                LocalBroadcastManager.getInstance(applicationContext).unregisterReceiver(this);
                String str = "";
                if (intent != null) {
                    str = intent.getStringExtra(MiotTakeMediaActivity.TAKE_RESULT);
                }
                if (TextUtils.isEmpty(str)) {
                    callback.invoke(1, "");
                    return;
                }
                callback.invoke(0, str);
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MiotTakeMediaActivity.TakeMediaResultBroadCast);
        LocalBroadcastManager.getInstance(applicationContext).registerReceiver(r1, intentFilter);
    }

    @ReactMethod
    public void takeVideo(final Callback callback) {
        if (getCurrentActivity() == null) {
            callback.invoke(-1, "");
            return;
        }
        final Context applicationContext = getCurrentActivity().getApplicationContext();
        Intent intent = new Intent(getCurrentActivity(), MiotTakeMediaActivity.class);
        intent.putExtra(MiotTakeMediaActivity.KEY_TAKE_MEDIA, MiotTakeMediaActivity.TYPE_VIDEO);
        getCurrentActivity().startActivity(intent);
        AnonymousClass9 r1 = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                LocalBroadcastManager.getInstance(applicationContext).unregisterReceiver(this);
                String str = "";
                if (intent != null) {
                    str = intent.getStringExtra(MiotTakeMediaActivity.TAKE_RESULT);
                }
                if (TextUtils.isEmpty(str)) {
                    callback.invoke(1, "");
                    return;
                }
                callback.invoke(0, str);
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MiotTakeMediaActivity.TakeMediaResultBroadCast);
        LocalBroadcastManager.getInstance(applicationContext).registerReceiver(r1, intentFilter);
    }

    @ReactMethod
    public void checkYoupin(Callback callback) {
        PackageInfo packageInfo;
        Application e = RNAppStoreApiManager.a().e();
        if (e == null) {
            callback.invoke(new Object[0]);
        }
        try {
            packageInfo = e.getPackageManager().getPackageInfo("com.xiaomi.youpin", 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        if (packageInfo != null) {
            callback.invoke(1);
            return;
        }
        callback.invoke(0);
    }

    @ReactMethod
    public void openYoupin(String str) {
        Application e = RNAppStoreApiManager.a().e();
        if (e != null) {
            try {
                Intent intent = new Intent();
                intent.setClassName("com.xiaomi.youpin", "com.xiaomi.youpin.activity.SplashActivity");
                if (!TextUtils.isEmpty(str)) {
                    intent.setData(Uri.parse(str));
                }
                intent.setFlags(C.ENCODING_PCM_MU_LAW);
                e.startActivity(intent);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @ReactMethod
    public void installYoupin() {
        Application e = RNAppStoreApiManager.a().e();
        if (e != null) {
            try {
                String[] list = e.getAssets().list("yp");
                String str = null;
                if (list != null && list.length > 0) {
                    str = "yp" + File.separator + list[0];
                }
                if (!TextUtils.isEmpty(str)) {
                    String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "SmartHome" + File.separator + "YouPin-Mishop-release.apk";
                    FileUtils.a(e, str, str2);
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    intent.setDataAndType(Uri.fromFile(new File(str2)), "application/vnd.android.package-archive");
                    e.startActivity(intent);
                    return;
                }
                RNAppStoreApiManager.a().b("https://home.mi.com/about");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @ReactMethod
    public void openLoginPage() {
        Activity i = RNAppStoreApiManager.a().i();
        StoreApiProvider b = StoreApiManager.a().b();
        if (b != null) {
            b.a(i);
        }
    }

    @ReactMethod
    public void updateToken(String str, boolean z, final Callback callback) {
        StoreApiProvider b = StoreApiManager.a().b();
        if (b != null) {
            final WritableMap createMap = Arguments.createMap();
            createMap.putString("uid", b.i());
            createMap.putString("sid", str);
            if (b.m()) {
                createMap.putString("mode", UserMode.b);
            } else {
                createMap.putString("mode", UserMode.f23179a);
            }
            if (!b.k()) {
                createMap.putInt("error", -1);
                createMap.putString("error_msg", "not login");
                callback.invoke(createMap);
                return;
            }
            final boolean[] zArr = {false};
            if (z) {
                b.a(str, (StoreBaseCallback<MiServiceTokenInfo>) new StoreBaseCallback<MiServiceTokenInfo>() {
                    public void onSuccess(MiServiceTokenInfo miServiceTokenInfo) {
                        if (!zArr[0]) {
                            zArr[0] = true;
                            YouPinCookieUtils.a(miServiceTokenInfo);
                            createMap.putInt("error", 0);
                            createMap.putString("token", miServiceTokenInfo.c);
                            callback.invoke(createMap);
                        }
                    }

                    public void onFail(int i, String str) {
                        if (!zArr[0]) {
                            zArr[0] = true;
                            createMap.putInt("error", i);
                            createMap.putString("error_msg", str);
                            callback.invoke(createMap);
                        }
                    }
                });
                return;
            }
            String a2 = b.a(str);
            if (TextUtils.isEmpty(a2)) {
                createMap.putInt("error", -3);
                createMap.putString("error_msg", "no sid serviceToken in app cache");
                callback.invoke(createMap);
                return;
            }
            createMap.putInt("error", 0);
            createMap.putString("token", a2);
            callback.invoke(createMap);
        }
    }

    @ReactMethod
    public void getUserInfo(final Callback callback) {
        StoreApiProvider b = StoreApiManager.a().b();
        if (b != null) {
            b.a((IStoreCallback<Map<String, Object>>) new IStoreCallback<Map<String, Object>>() {
                public void onSuccess(Map<String, Object> map) {
                    WritableNativeMap makeNativeMap = Arguments.makeNativeMap(map);
                    callback.invoke(0, makeNativeMap);
                }

                public void onFailed(int i, String str) {
                    callback.invoke(Integer.valueOf(i));
                }
            });
        }
    }

    @ReactMethod
    public void shareCustom(ReadableMap readableMap, Callback callback) {
        RNStoreApiProvider j = RNAppStoreApiManager.a().j();
        if (j != null) {
            j.shareCustom(readableMap, callback);
        }
    }

    @ReactMethod
    public void bindWeixin(Callback callback) {
        RNStoreApiProvider j = RNAppStoreApiManager.a().j();
        if (j != null) {
            j.bindWeixin(callback);
        }
    }

    @ReactMethod
    public void showAppstoreComment() {
        RNStoreApiProvider j = RNAppStoreApiManager.a().j();
        if (j != null) {
            j.showAppstoreComment();
        }
    }

    @ReactMethod
    public void getStatInfo(Callback callback) {
        String j = StatManager.a().j();
        if (callback != null) {
            callback.invoke(j);
        }
    }

    @ReactMethod
    public void startCustomerServiceChat(ReadableMap readableMap, Callback callback) {
        if (readableMap != null) {
            ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
            HashMap hashMap = new HashMap();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                hashMap.put(nextKey, readableMap.getString(nextKey));
            }
            RNStoreApiProvider j = RNAppStoreApiManager.a().j();
            if (j != null) {
                int startCustomerServiceChat = j.startCustomerServiceChat(getCurrentActivity(), hashMap);
                if (callback != null) {
                    callback.invoke(Integer.valueOf(startCustomerServiceChat));
                }
            }
        }
    }

    @ReactMethod
    public void setSharedValue(String str, String str2, final Callback callback) {
        StoreApiProvider b = StoreApiManager.a().b();
        if (b != null) {
            b.a(str, str2, new IStoreCallback<Void>() {
                public void onSuccess(Void voidR) {
                    if (callback != null) {
                        callback.invoke(0);
                    }
                }

                public void onFailed(int i, String str) {
                    if (callback != null) {
                        callback.invoke(Integer.valueOf(i));
                    }
                }
            });
        } else if (callback != null) {
            callback.invoke(1);
        }
    }

    @ReactMethod
    public void getSharedValue(String str, final Callback callback) {
        StoreApiProvider b = StoreApiManager.a().b();
        if (b != null) {
            b.a(str, (IStoreCallback<String>) new IStoreCallback<String>() {
                public void onSuccess(String str) {
                    if (callback != null) {
                        callback.invoke(0, str);
                    }
                }

                public void onFailed(int i, String str) {
                    if (callback != null) {
                        callback.invoke(Integer.valueOf(i));
                    }
                }
            });
        } else if (callback != null) {
            callback.invoke(1);
        }
    }

    @ReactMethod
    public void insertEventToCalendar(ReadableMap readableMap, Callback callback) {
        RNStoreApiProvider j = RNAppStoreApiManager.a().j();
        if (j != null) {
            j.insertEventToCalendar(getCurrentActivity(), readableMap, callback);
        }
    }

    @ReactMethod
    public void iniVerify(ReadableMap readableMap) {
        Activity i = RNAppStoreApiManager.a().i();
        RNStoreApiProvider j = RNAppStoreApiManager.a().j();
        if (i != null && j != null) {
            j.iniVerify(i, readableMap);
        }
    }

    @ReactMethod
    public void startVerify(ReadableMap readableMap, Callback callback) {
        RNStoreApiProvider j = RNAppStoreApiManager.a().j();
        if (j != null) {
            j.startVerify(readableMap, callback);
        }
    }

    @ReactMethod
    public void CaptureScreen(ReadableMap readableMap, final Callback callback) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            ResultBean b = ScreenshotManager.a().b();
            int a2 = b.a();
            WritableMap createMap = Arguments.createMap();
            createMap.putString("errMsg", b.b());
            callback.invoke(Integer.valueOf(a2), createMap);
            return;
        }
        ScreenshotManager.a().a(currentActivity, readableMap, (ScreenshotManager.Callback) new ScreenshotManager.Callback() {
            public void callback(ResultBean resultBean) {
                int a2 = resultBean.a();
                WritableMap createMap = Arguments.createMap();
                if (a2 == 0) {
                    createMap.putString("localpath", resultBean.e());
                    createMap.putInt("width", resultBean.c());
                    createMap.putInt("height", resultBean.d());
                    createMap.putInt("size", resultBean.f());
                } else {
                    createMap.putString("errMsg", resultBean.b());
                }
                callback.invoke(Integer.valueOf(a2), createMap);
            }
        });
    }

    @ReactMethod
    public void SaveImage(ReadableMap readableMap, final Callback callback) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            ResultBean b = ScreenshotManager.a().b();
            int a2 = b.a();
            WritableMap createMap = Arguments.createMap();
            createMap.putString("errMsg", b.b());
            callback.invoke(Integer.valueOf(a2), createMap);
            return;
        }
        ScreenshotManager.a().b(currentActivity, readableMap, new ScreenshotManager.Callback() {
            public void callback(ResultBean resultBean) {
                int a2 = resultBean.a();
                WritableMap createMap = Arguments.createMap();
                if (a2 == 0) {
                    createMap.putString("localpath", resultBean.e());
                    createMap.putInt("width", resultBean.c());
                    createMap.putInt("height", resultBean.d());
                    createMap.putInt("size", resultBean.f());
                } else {
                    createMap.putString("errMsg", resultBean.b());
                }
                callback.invoke(Integer.valueOf(a2), createMap);
            }
        });
    }

    @ReactMethod
    public void isAppInstalled(String str, Callback callback) {
        PackageInfo packageInfo;
        Application e = RNAppStoreApiManager.a().e();
        if (e == null) {
            callback.invoke(-1, "未初始化");
        }
        try {
            packageInfo = e.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        if (packageInfo != null) {
            callback.invoke(0);
            return;
        }
        callback.invoke(-1, "未找到应用");
    }

    @ReactMethod
    public void showPictureSelect(ReadableMap readableMap, final Callback callback) {
        Activity i = RNAppStoreApiManager.a().i();
        if (i != null) {
            StoreApiManager.a().a(i, ((ReadableNativeMap) readableMap).toHashMap(), (ICallback) new ICallback() {
                public void callback(Map map) {
                    String str = (String) map.get("code");
                    String[] strArr = (String[]) map.get("images");
                    if (TextUtils.isEmpty(str) || !"0".equals(str) || strArr == null) {
                        callback.invoke(str, "no image");
                        return;
                    }
                    WritableArray fromArray = Arguments.fromArray(strArr);
                    callback.invoke(0, fromArray);
                }
            });
        }
    }

    @ReactMethod
    public void getWeexInfo(Callback callback) {
        HashMap<String, Object> o = StoreApiManager.a().b().o();
        if (o == null) {
            o = new HashMap<>();
        }
        callback.invoke(Arguments.makeNativeMap((Map<String, Object>) o));
    }

    @ReactMethod
    public void openSysAppSettings(Callback callback) {
        Activity currentActivity = getCurrentActivity();
        WritableMap createMap = Arguments.createMap();
        if (currentActivity == null) {
            createMap.putInt("code", OTAErrorCode.n);
            createMap.putString("message", "方法未实现");
        } else {
            createMap.putInt("code", 0);
            Intent intent = new Intent();
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", currentActivity.getApplicationContext().getPackageName(), (String) null));
            currentActivity.startActivity(intent);
        }
        callback.invoke(createMap);
    }

    @ReactMethod
    public void openChangePasswordPageWithCallback(Callback callback) {
        RNStoreApiProvider j = RNAppStoreApiManager.a().j();
        if (j != null) {
            j.openChangePasswordPage(getCurrentActivity(), callback);
        }
    }
}
