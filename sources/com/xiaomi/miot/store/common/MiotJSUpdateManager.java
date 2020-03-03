package com.xiaomi.miot.store.common;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.miot.store.common.update.Callback;
import com.xiaomi.miot.store.common.update.Config;
import com.xiaomi.miot.store.common.update.Constants;
import com.xiaomi.miot.store.common.update.IJSUpdate;
import com.xiaomi.miot.store.common.update.JSPackageLoader;
import com.xiaomi.miot.store.common.update.JSUpdateManager;
import com.xiaomi.miot.store.common.update.ReloadStrategy;
import com.xiaomi.miot.store.module.AppInfoModule;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.util.Device;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.xiaomi.youpin.youpin_common.UserAgent;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class MiotJSUpdateManager implements IJSUpdate {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11374a = "MiotJSUpdateManager";
    private static final String b = "index.android.bundle";
    private static final String c = "shop_builtin_package";
    private static final String d = "https://shopapi.io.mi.com/app/shopv3/rsync3";
    private static final String e = "https://st.shopapi.io.mi.com/app/shopv3/rsync3";
    private static final String f = "User-Agent";
    private static final String g = "DToken";
    private static final int h = 3;
    private JSUpdateManager i;
    private String j;

    public MiotJSUpdateManager(boolean z, boolean z2) {
        a(z, z2);
    }

    private void a(boolean z, boolean z2) {
        Application e2 = RNAppStoreApiManager.a().e();
        SharedPreferences sharedPreferences = e2.getSharedPreferences(MiotStoreConstant.i, 0);
        String string = sharedPreferences.getString(MiotStoreConstant.l, "");
        String string2 = sharedPreferences.getString(MiotStoreConstant.j, (String) null);
        long j2 = sharedPreferences.getLong(MiotStoreConstant.o, 0);
        if (!z && !AppInfoModule.getSdkVersion().equals(string) && !TextUtils.isEmpty(string2)) {
            z = true;
        }
        Config b2 = Config.Builder.a().a(RNAppStoreApiManager.a().j().isRNDebug()).a(string2).d(z2).a(j2).b(RNAppStoreApiManager.a().e().getCacheDir().getPath() + "/rnbundler").b(true).c(z).b(MiotStoreConstant.q).c(b).a(ReloadStrategy.IN_SILENCE).a(3).b();
        LogUtils.d(f11374a, "config====debug:" + b2.a() + ",eTag:" + b2.b() + ",lastupdateTime:" + b2.c() + ",store dir:" + b2.d() + ",force update:" + b2.f() + ",bundle path:" + b2.i());
        StringBuilder sb = new StringBuilder();
        sb.append("https://shopapi.io.mi.com/app/shopv3/rsync3?local=");
        sb.append(RNAppStoreApiManager.a().j().getServerLocalCode());
        this.j = sb.toString();
        if (StoreApiManager.a().b().g()) {
            this.j = "https://st.shopapi.io.mi.com/app/shopv3/rsync3?local=" + RNAppStoreApiManager.a().j().getServerLocalCode();
        }
        this.i = new JSUpdateManager(b2, JSPackageLoader.a(this.j, b(), c()), JSPackageLoader.a(e2, c));
    }

    public void a(Context context, final Callback callback) {
        this.i.a(context, (Callback) new Callback() {
            public void a(Map<String, String> map) {
                MiotJSUpdateManager.this.a(map);
                if (callback != null) {
                    callback.a(map);
                }
            }
        });
    }

    public void a() {
        this.i.a();
    }

    private Map<String, String> b() {
        HashMap hashMap = new HashMap();
        Device.a(RNAppStoreApiManager.a().e(), StoreApiManager.a().b().d());
        hashMap.put("User-Agent", UserAgent.d());
        Object b2 = StoreApiManager.a().b().b("Dev_RnBranch", (Object) "");
        if (b2 != null && (b2 instanceof String)) {
            hashMap.put("Rb", (String) b2);
        }
        return hashMap;
    }

    private JSPackageLoader.INetworkHandler c() {
        return new JSPackageLoader.INetworkHandler() {
            public void a(OkHttpClient okHttpClient) {
            }

            public void a(Response response) {
                String header = response.header("Date");
                if (!TextUtils.isEmpty(header)) {
                    try {
                        LogUtils.d("update server time:", header);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
                        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                        Device.a((int) (simpleDateFormat.parse(header).getTime() / 1000));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    public void a(Map<String, String> map) {
        if (map == null) {
            LogUtils.d(f11374a, "update result is null!");
        } else if (!TextUtils.isEmpty(map.get(Constants.f11398a))) {
            SharedPreferences sharedPreferences = RNAppStoreApiManager.a().e().getSharedPreferences(MiotStoreConstant.i, 0);
            sharedPreferences.edit().putString(MiotStoreConstant.l, AppInfoModule.getSdkVersion()).apply();
            sharedPreferences.edit().putString(MiotStoreConstant.j, map.get(Constants.b)).apply();
            sharedPreferences.edit().putLong(MiotStoreConstant.o, Long.valueOf(map.get(Constants.d)).longValue()).apply();
        }
    }

    public static int a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(MiotStoreConstant.i, 0);
        if (str.equals(sharedPreferences.getString(MiotStoreConstant.m, ""))) {
            return sharedPreferences.getInt(MiotStoreConstant.n, 0);
        }
        return 0;
    }

    public static void a(Context context, String str, int i2) {
        if (!TextUtils.isEmpty(str)) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(MiotStoreConstant.i, 0);
            if (!str.equals(sharedPreferences.getString(MiotStoreConstant.m, ""))) {
                sharedPreferences.edit().putString(MiotStoreConstant.m, str).apply();
            }
            sharedPreferences.edit().putInt(MiotStoreConstant.n, i2).apply();
        }
    }
}
