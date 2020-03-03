package com.xiaomi.jr.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.xiaomi.jr.annotation.anr.ANRProcessor;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.Callable;

public class NetworkUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1419a = "NetworkUtils";

    /* access modifiers changed from: private */
    public static final NetworkInfo k(Context context) {
        if (context == null) {
            return null;
        }
        return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public static boolean b(Context context) {
        NetworkInfo a2 = a(context);
        return a2 != null && a2.isConnectedOrConnecting();
    }

    /* access modifiers changed from: private */
    public static final String l(Context context) {
        if (e(context)) {
            return "WIFI";
        }
        switch (((TelephonyManager) context.getSystemService("phone")).getNetworkType()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return "2G";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return "3G";
            case 13:
                return "4G";
            default:
                return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
    }

    /* access modifiers changed from: private */
    public static final String m(Context context) {
        NetworkInfo a2 = a(context);
        if (a2 != null) {
            return a2.getTypeName();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static final boolean n(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
        if (networkInfo == null || !networkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    public static boolean f(Context context) {
        String c = c(context);
        return TextUtils.equals(c, "WIFI") || TextUtils.equals(c, "3G") || TextUtils.equals(c, "4G");
    }

    public static String a(boolean z) {
        try {
            for (T inetAddresses : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                Iterator<T> it = Collections.list(inetAddresses.getInetAddresses()).iterator();
                while (true) {
                    if (it.hasNext()) {
                        InetAddress inetAddress = (InetAddress) it.next();
                        if (!inetAddress.isLoopbackAddress()) {
                            String hostAddress = inetAddress.getHostAddress();
                            boolean z2 = inetAddress instanceof Inet4Address;
                            if (z) {
                                if (z2) {
                                    return hostAddress;
                                }
                            } else if (!z2) {
                                int indexOf = hostAddress.indexOf(37);
                                return indexOf < 0 ? hostAddress.toUpperCase() : hostAddress.substring(0, indexOf).toUpperCase();
                            }
                        }
                    }
                }
            }
            return "";
        } catch (Exception e) {
            MifiLog.e(f1419a, "Exception throw when getIPaddress - " + e);
            return "";
        }
    }

    public static NetworkInfo a(final Context context) {
        return (NetworkInfo) ANRProcessor.a(new Callable<Object>() {
            public Object call() throws Exception {
                return NetworkUtils.k(context);
            }
        }, 4500);
    }

    public static String c(final Context context) {
        return (String) ANRProcessor.a(new Callable<Object>() {
            public Object call() throws Exception {
                return NetworkUtils.l(context);
            }
        }, 4500);
    }

    public static String d(final Context context) {
        return (String) ANRProcessor.a(new Callable<Object>() {
            public Object call() throws Exception {
                return NetworkUtils.m(context);
            }
        }, 4500);
    }

    public static boolean e(final Context context) {
        return ((Boolean) ANRProcessor.a(new Callable<Object>() {
            public Object call() throws Exception {
                return Boolean.valueOf(NetworkUtils.n(context));
            }
        }, 4500)).booleanValue();
    }
}
