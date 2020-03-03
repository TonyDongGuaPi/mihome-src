package com.xiaomi.jr.appbase.utils;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;
import com.miui.tsmclient.net.TSMAuthContants;
import com.xiaomi.jr.account.XiaomiAccountManager;
import com.xiaomi.jr.appbase.CustomizedSnippets;
import com.xiaomi.jr.common.utils.AppUtils;
import com.xiaomi.jr.common.utils.Client;
import com.xiaomi.jr.common.utils.DeviceHelper;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.MiuiClient;
import com.xiaomi.jr.common.utils.UrlUtils;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.http.utils.CacheUtils;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;

public class WebUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1406a = "MiFiWebUtils";
    private static HashMap<String, String> b;

    public static String a(String str) {
        return UrlUtils.b(str, "from");
    }

    public static boolean b(String str) {
        return UrlUtils.a(str, "back", true);
    }

    public static String c(String str) {
        return UrlUtils.b(str, AppConstants.f);
    }

    public static String d(String str) {
        return UrlUtils.b(str, "userId");
    }

    public static String e(String str) {
        return UrlUtils.b(str, "cUserId");
    }

    public static String a(String str, String str2) {
        return UrlUtils.a(str, "from", str2);
    }

    public static String a(String str, boolean z) {
        return UrlUtils.a(str, "back", String.valueOf(z));
    }

    public static String a(String str, @NonNull Location location) {
        return UrlUtils.a(UrlUtils.a(str, "longitude", String.valueOf(location.getLongitude())), "latitude", String.valueOf(location.getLatitude()));
    }

    public static boolean f(String str) {
        Uri parse = Uri.parse(str);
        return !parse.isOpaque() && parse.getBooleanQueryParameter("mifi_pdf", false);
    }

    public static String a(Context context, String str) {
        if (!MiFiUrlUtils.b(str)) {
            return "__placeholder__";
        }
        String a2 = a(str);
        if (TextUtils.isEmpty(a2)) {
            Toast.makeText(context.getApplicationContext(), String.format(Locale.getDefault(), "From parameter is required in Url %s", new Object[]{str}), 1).show();
            new Throwable().printStackTrace();
        }
        return a2;
    }

    public static boolean g(String str) {
        Uri parse = Uri.parse(str);
        if (parse.isOpaque() || parse.getBooleanQueryParameter(AppConstants.g, false)) {
            return true;
        }
        if (str.startsWith("http") || TextUtils.equals(parse.getScheme(), AppConstants.z)) {
            return false;
        }
        return true;
    }

    public static boolean h(String str) {
        return str.startsWith(AppConstants.B) || str.startsWith(AppConstants.C);
    }

    public static boolean i(String str) {
        return str.startsWith("intent:");
    }

    public static Intent j(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return Intent.parseUri(str, 1);
        } catch (URISyntaxException e) {
            MifiLog.e(f1406a, "parse intent failed : " + e.toString());
            return null;
        }
    }

    public static void a() {
        HashSet hashSet = new HashSet();
        hashSet.add(TSMAuthContants.PARAM_SESSION_ID);
        hashSet.add("sign");
        hashSet.add("longitude");
        hashSet.add("latitude");
        hashSet.add(DeviceTagInterface.e);
        hashSet.add("bssid");
        CacheUtils.a(AppConstants.n + ".*", hashSet);
    }

    public static Map<String, String> a(Context context) {
        HashMap hashMap = new HashMap();
        hashMap.putAll(b(context));
        hashMap.put("deviceIdMd5", Client.c(context));
        hashMap.put("defaultImeiMd5", Client.i(context));
        hashMap.put(DeviceTagInterface.e, Client.m(context));
        hashMap.put("bssid", Client.n(context));
        hashMap.put("hasLogin", String.valueOf(XiaomiAccountManager.a().d()));
        a(context, (Map<String, String>) hashMap);
        return hashMap;
    }

    private static Map<String, String> b(Context context) {
        if (b == null) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("app", context.getPackageName());
            hashMap.put("system", String.valueOf(1));
            hashMap.put("versionCode", String.valueOf(AppUtils.f(context)));
            hashMap.put("versionName", AppUtils.g(context));
            hashMap.put("channel", (String) CustomizedSnippets.a(CustomizedSnippets.f1388a, context));
            hashMap.put("model", Build.MODEL);
            hashMap.put("device", Build.DEVICE);
            hashMap.put("isMiui", String.valueOf(MiuiClient.a()));
            hashMap.put("isTablet", String.valueOf(DeviceHelper.f10365a));
            hashMap.put("incremental", Build.VERSION.INCREMENTAL);
            hashMap.put("sdk", String.valueOf(Build.VERSION.SDK_INT));
            hashMap.put("release", Build.VERSION.RELEASE);
            hashMap.put("androidId", Client.a(context));
            hashMap.put("serial", Client.a());
            hashMap.put(TSMAuthContants.PARAM_SESSION_ID, Client.e());
            hashMap.put("oaid", Client.k(context));
            b = hashMap;
        }
        return b;
    }

    private static void a(Context context, @NonNull Map<String, String> map) {
        Location a2 = Utils.a(context);
        if (a2 != null) {
            map.put("longitude", String.valueOf(a2.getLongitude()));
            map.put("latitude", String.valueOf(a2.getLatitude()));
        }
    }
}
