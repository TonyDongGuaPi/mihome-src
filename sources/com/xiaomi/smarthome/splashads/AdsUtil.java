package com.xiaomi.smarthome.splashads;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.facebook.internal.AnalyticsEvents;
import com.miui.tsmclient.net.TSMAuthContants;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.youpin.common.util.AppInfo;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class AdsUtil {
    public static JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("deviceInfo", e());
            jSONObject.put("userInfo", d());
            jSONObject.put("appInfo", c());
            jSONObject.put("impRequests", b());
            jSONObject.put("fetchMode", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        DisplayMetrics displayMetrics = SHApplication.getAppContext().getResources().getDisplayMetrics();
        try {
            jSONObject.put("width", displayMetrics.widthPixels);
            jSONObject.put("height", displayMetrics.heightPixels);
            jSONObject.put("adsCount", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("packageName", AppInfo.b());
            jSONObject.put("version", AppInfo.g() + "");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static JSONObject d() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("imei", AppInfo.j());
            String f = f();
            jSONObject.put("mac", a(f));
            jSONObject.put(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, f);
            jSONObject.put("connectionType", b(SHApplication.getAppContext()));
            jSONObject.put("androidId", a(SHApplication.getAppContext()));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static JSONObject e() {
        JSONObject jSONObject = new JSONObject();
        try {
            DisplayMetrics displayMetrics = SHApplication.getAppContext().getResources().getDisplayMetrics();
            jSONObject.put("screenWidth", displayMetrics.widthPixels);
            jSONObject.put("screenHeight", displayMetrics.heightPixels);
            jSONObject.put("screenDensity", displayMetrics.density + "");
            jSONObject.put("os", "android");
            jSONObject.put("make", Build.MANUFACTURER);
            jSONObject.put("model", Build.MODEL);
            jSONObject.put(TSMAuthContants.PARAM_ANDROID_VERSION, Build.VERSION.RELEASE);
            if (SystemApi.c()) {
                jSONObject.put("miuiVersion", Build.VERSION.INCREMENTAL);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static String a(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String b(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (!activeNetworkInfo.isConnected()) {
            return null;
        }
        if (activeNetworkInfo.getType() == 1) {
            return "wifi";
        }
        switch (((TelephonyManager) context.getSystemService("phone")).getNetworkType()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return "2g";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return "3g";
            case 13:
                return "4g";
            default:
                return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
    }

    public static String a(String str) {
        try {
            return a(NetworkInterface.getByInetAddress(InetAddress.getByName(str)).getHardwareAddress());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String f() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                            return nextElement.getHostAddress();
                        }
                    }
                }
            }
            return null;
        } catch (SocketException unused) {
            return null;
        }
    }

    public static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length);
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            String hexString = Integer.toHexString(bArr[i] & 255);
            if (hexString.length() == 1) {
                stringBuffer.append("0");
                stringBuffer.append(hexString);
            } else {
                stringBuffer.append(hexString);
            }
            if (i != length - 1) {
                stringBuffer.append(":");
            }
        }
        return String.valueOf(stringBuffer);
    }
}
