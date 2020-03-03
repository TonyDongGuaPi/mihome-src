package com.xiaomi.jr.mipay.common;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import com.alipay.android.phone.a.a.a;
import com.xiaomi.jr.common.utils.AppUtils;
import com.xiaomi.jr.common.utils.HashUtils;
import com.xiaomi.jr.common.utils.MiuiClient;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.http.utils.ParamUtils;
import com.xiaomi.jr.mipay.common.model.DeviceIdInfo;
import com.xiaomi.jr.mipay.common.util.MipayClient;
import java.io.IOException;
import java.util.TreeMap;
import retrofit2.Response;

public class DeviceManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10938a = "api/device";
    private static final String b = "DeviceManager";
    private static String c;

    public static void a() {
        c = null;
    }

    public static String a(Context context) {
        if (c == null) {
            synchronized (DeviceManager.class) {
                if (c == null) {
                    b(context);
                }
            }
        }
        return c;
    }

    private static void b(Context context) {
        String str;
        Utils.b();
        TreeMap treeMap = new TreeMap();
        treeMap.put("la", MipayClient.a());
        treeMap.put("co", MipayClient.b());
        MipayClient.TelephonyInfo c2 = MipayClient.c(context);
        treeMap.put("carrier", c2.a());
        treeMap.put(MipayConstants.z, c2.b());
        treeMap.put(MipayConstants.A, c2.c());
        treeMap.put("iccid", c2.h());
        treeMap.put(MipayConstants.W, c2.d());
        treeMap.put(MipayConstants.X, c2.e());
        treeMap.put(MipayConstants.Z, c2.f());
        treeMap.put(MipayConstants.aa, String.valueOf(c2.g()));
        treeMap.put("uuid", c2.j());
        treeMap.put("imei", c2.i());
        treeMap.put("imsi", c2.l());
        treeMap.put(MipayConstants.Y, c2.n());
        treeMap.put(MipayConstants.ab, c2.m());
        treeMap.put("model", Build.MODEL);
        treeMap.put("device", Build.DEVICE);
        treeMap.put("product", Build.PRODUCT);
        treeMap.put("manufacturer", Build.MANUFACTURER);
        treeMap.put("brand", Build.BRAND);
        treeMap.put("buildType", Build.TYPE);
        treeMap.put("sdk", String.valueOf(Build.VERSION.SDK_INT));
        treeMap.put("systemVersion", Build.VERSION.INCREMENTAL);
        treeMap.put("systemRelease", Build.VERSION.RELEASE);
        treeMap.put("os", MiuiClient.a() ? "MIUI" : a.f813a);
        String d = MiuiClient.d();
        if (d.equals("dev")) {
            d = "development";
        }
        treeMap.put("miuiVersion", d);
        treeMap.put("miuiUiVersion", MipayClient.e());
        treeMap.put("miuiUiVersionCode", String.valueOf(MipayClient.f()));
        treeMap.put("platform", a.f813a);
        MipayClient.DisplayInfo b2 = MipayClient.b(context);
        treeMap.put("displayResolution", b2.c());
        treeMap.put("displayDensity", String.valueOf(b2.d()));
        treeMap.put("screenSize", String.valueOf(b2.f()));
        treeMap.put("version", AppUtils.g(context));
        treeMap.put("versionCode", String.valueOf(AppUtils.f(context)));
        treeMap.put("package", context.getPackageName());
        treeMap.put("apkSign", HashUtils.a(AppUtils.a(context)));
        treeMap.put("apkChannel", "");
        treeMap.put("romChannel", "");
        MipayClient.WifiNetworkInfo d2 = MipayClient.d(context);
        treeMap.put("mac", d2.a());
        treeMap.put(MipayConstants.ae, d2.d());
        treeMap.put(MipayConstants.af, d2.b());
        treeMap.put(MipayConstants.ag, d2.c());
        treeMap.put("androidId", MipayClient.e(context));
        treeMap.put(MipayConstants.S, Build.HARDWARE);
        treeMap.put(MipayConstants.T, Build.DISPLAY);
        treeMap.put(MipayConstants.U, Build.TAGS);
        treeMap.put(MipayConstants.V, Build.HOST);
        treeMap.put(MipayConstants.ac, MipayClient.f(context));
        treeMap.put(MipayConstants.ad, MipayClient.g());
        treeMap.put(MipayConstants.ai, MipayClient.g(context));
        MipayClient.CPUInfo k = MipayClient.k();
        treeMap.put(MipayConstants.aj, k.a());
        treeMap.put(MipayConstants.ak, k.b());
        treeMap.put(MipayConstants.al, k.c());
        treeMap.put(MipayConstants.am, k.d());
        treeMap.put(MipayConstants.an, String.valueOf(MipayClient.h()));
        treeMap.put(MipayConstants.ao, String.valueOf(MipayClient.i()));
        treeMap.put(MipayConstants.ap, String.valueOf(MipayClient.j()));
        treeMap.put(MipayConstants.aq, MipayClient.h(context));
        Location a2 = Utils.a(context);
        if (a2 != null) {
            str = a2.getLatitude() + "," + a2.getLongitude();
        } else {
            str = "";
        }
        treeMap.put("location", str);
        treeMap.put("xiaomiDeviceToken", MipayClient.i(context));
        try {
            Response<DeviceIdInfo> execute = MipayManager.b().a(ParamUtils.a(treeMap)).execute();
            if (execute.isSuccessful() && execute.body() != null && execute.body().j == 200) {
                synchronized (DeviceManager.class) {
                    c = execute.body().f10949a;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
