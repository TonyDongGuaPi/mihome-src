package com.tencent.a.a.a.a;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import com.paytm.pgsdk.PaytmConstants;
import com.tencent.wxop.stat.common.f;
import org.cybergarage.http.HTTP;
import org.json.JSONObject;

public final class h {
    static String a(Context context) {
        try {
            if (a(context, "android.permission.READ_PHONE_STATE")) {
                String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                return deviceId != null ? deviceId : "";
            }
            Log.i(PaytmConstants.f8536a, "Could not get permission of android.permission.READ_PHONE_STATE");
            return "";
        } catch (Throwable th) {
            Log.w(PaytmConstants.f8536a, th);
            return "";
        }
    }

    private static void a(String str, Throwable th) {
        Log.e(PaytmConstants.f8536a, str, th);
    }

    static void a(JSONObject jSONObject, String str, String str2) {
        if (a(str2)) {
            jSONObject.put(str, str2);
        }
    }

    static boolean a(Context context, String str) {
        try {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Throwable th) {
            a("checkPermission error", th);
            return false;
        }
    }

    static boolean a(String str) {
        return (str == null || str.trim().length() == 0) ? false : true;
    }

    static String b(Context context) {
        String str;
        if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
            try {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                return wifiManager == null ? "" : wifiManager.getConnectionInfo().getMacAddress();
            } catch (Exception e) {
                str = "get wifi address error" + e;
            }
        } else {
            str = "Could not get permission of android.permission.ACCESS_WIFI_STATE";
            Log.i(PaytmConstants.f8536a, str);
            return "";
        }
    }

    public static boolean b(String str) {
        return str != null && str.trim().length() >= 40;
    }

    static String c(String str) {
        if (str == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(f.b(Base64.decode(str.getBytes("UTF-8"), 0)), "UTF-8").trim().replace(HTTP.TAB, "").replace("\n", "").replace("\r", "");
        } catch (Throwable th) {
            a("decode error", th);
            return str;
        }
    }

    static String d(String str) {
        if (str == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(Base64.encode(f.a(str.getBytes("UTF-8")), 0), "UTF-8").trim().replace(HTTP.TAB, "").replace("\n", "").replace("\r", "");
        } catch (Throwable th) {
            a("decode error", th);
            return str;
        }
    }
}
