package com.mibi.common.utils;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import com.alipay.sdk.util.i;
import com.xiaomi.payment.hybrid.MibiHybridUtils;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;

public class UrlUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7597a = "UrlUtils";
    private static final String[] b = {MibiHybridUtils.f12336a, "https://app.mibi.xiaomi.com", "http://staging.app.mibi.xiaomi.com"};
    private static final String[] c = {"https://account.xiaomi.com", "http://account.preview.n.xiaomi.net"};

    private UrlUtils() {
    }

    public static boolean a(String str) {
        return a(str, b);
    }

    public static boolean b(String str) {
        return a(str, c);
    }

    public static boolean a(String str, String str2) {
        String[] split = Uri.parse(str).getHost().split("\\.");
        String[] split2 = Uri.parse(str2).getHost().split("\\.");
        if (split.length < split2.length) {
            return false;
        }
        for (int i = 1; i <= split2.length; i++) {
            if (!split[split.length - i].equals(split2[split2.length - i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean c(String str) {
        try {
            new URL(str);
            return true;
        } catch (MalformedURLException unused) {
            return false;
        }
    }

    public static boolean a(String str, String[] strArr) {
        if (!c(str)) {
            return false;
        }
        for (String a2 : strArr) {
            if (a(str, a2)) {
                return true;
            }
        }
        return false;
    }

    public static String b(String str, String str2) {
        String str3 = null;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        String cookie = CookieManager.getInstance().getCookie(str);
        if (TextUtils.isEmpty(cookie)) {
            return null;
        }
        Scanner scanner = new Scanner(cookie);
        scanner.useDelimiter(i.b);
        while (true) {
            if (!scanner.hasNext()) {
                break;
            }
            String next = scanner.next();
            if (!TextUtils.isEmpty(next)) {
                String[] split = next.split("=");
                if (split.length == 2 && str2.equals(split[0].trim())) {
                    str3 = split[1].trim();
                    break;
                }
            }
        }
        scanner.close();
        return str3;
    }

    public static boolean d(String str) {
        return URLUtil.isHttpsUrl(str);
    }

    public static String a(String str, JSONObject jSONObject) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (jSONObject == null) {
            return str;
        }
        return a(Uri.parse(str), jSONObject).toString();
    }

    public static Uri a(Uri uri, JSONObject jSONObject) {
        Object obj;
        if (uri == null) {
            return null;
        }
        if (jSONObject == null) {
            return uri;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                obj = jSONObject.get(next);
            } catch (JSONException e) {
                e.printStackTrace();
                obj = null;
            }
            uri = a(uri, next, obj);
        }
        return uri;
    }

    public static String a(String str, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (bundle == null || bundle.isEmpty()) {
            return str;
        }
        Uri parse = Uri.parse(str);
        for (String str2 : bundle.keySet()) {
            parse = a(parse, str2, bundle.get(str2));
        }
        return parse.toString();
    }

    public static Uri a(Uri uri, String str, Object obj) {
        String str2;
        if (uri == null || str == null) {
            return uri;
        }
        if (!(obj instanceof Integer) && !(obj instanceof Boolean) && !(obj instanceof String)) {
            return uri;
        }
        try {
            str2 = URLEncoder.encode(obj.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(f7597a, "encode value failed", e);
            str2 = null;
        }
        return !TextUtils.isEmpty(str2) ? uri.buildUpon().appendQueryParameter(str, str2).build() : uri;
    }

    public static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Uri parse = Uri.parse(str);
        try {
            parse = parse.buildUpon().appendQueryParameter("_wvt", URLEncoder.encode(String.valueOf(System.currentTimeMillis()), "UTF-8")).build();
        } catch (UnsupportedEncodingException e) {
            Log.e(f7597a, "encode value failed", e);
        }
        return parse.toString();
    }

    public static JSONObject a(Uri uri) {
        JSONObject jSONObject = new JSONObject();
        for (String next : uri.getQueryParameterNames()) {
            try {
                jSONObject.put(next, uri.getQueryParameter(next));
            } catch (JSONException e) {
                Log.d(f7597a, "failed to generate order in json", e);
            }
        }
        return jSONObject;
    }

    public static Bundle b(Uri uri) {
        Bundle bundle = new Bundle();
        for (String next : uri.getQueryParameterNames()) {
            bundle.putString(next, uri.getQueryParameter(next));
        }
        return bundle;
    }
}
