package com.alipay.mobile.common.logging.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Map;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class NetUtil {
    public static final String TYPE_GSM = "GSM";
    public static final String TYPE_TDS_HSDPSA = "TDS-HSDPSA";
    public static final String TYPE_TDS_HSUPA = "TDS-HSUPA";
    public static final String TYPE_TD_CDMA = "TD-CDMA";
    public static final String TYPE_WIFI = "WIFI";

    public static NetworkInfo getActiveNetworkInfo(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Throwable th) {
            Log.w("LoggingNetUtil", th);
            return null;
        }
    }

    public static String getNetworkType(Context context) {
        return getNetworkType(getActiveNetworkInfo(context));
    }

    public static String getNetworkType(NetworkInfo networkInfo) {
        String str = null;
        if (networkInfo == null) {
            return null;
        }
        if ("WIFI".equalsIgnoreCase(networkInfo.getTypeName())) {
            return "WIFI";
        }
        if (!TextUtils.isEmpty(networkInfo.getSubtypeName())) {
            str = networkInfo.getSubtypeName();
        } else if (networkInfo.getSubtype() == 16) {
            str = TYPE_GSM;
        } else if (networkInfo.getSubtype() == 17) {
            str = TYPE_TD_CDMA;
        } else if (networkInfo.getSubtype() == 18) {
            str = TYPE_TDS_HSDPSA;
        } else if (networkInfo.getSubtype() == 19) {
            str = TYPE_TDS_HSUPA;
        }
        if (str == null || TextUtils.isEmpty(networkInfo.getExtraInfo())) {
            return str;
        }
        return str + "|" + networkInfo.getExtraInfo();
    }

    public static boolean isNetworkConnected(Context context) {
        try {
            NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            Log.w("LoggingNetUtil", th);
            return false;
        }
    }

    public static String formatParamStringForGET(Map<String, String> map) {
        if (map == null || map.size() <= 0) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            for (Map.Entry next : map.entrySet()) {
                arrayList.add(new BasicNameValuePair((String) next.getKey(), (String) next.getValue()));
            }
            return URLEncodedUtils.format(arrayList, "utf-8");
        } catch (Throwable th) {
            Log.w("LoggingNetUtil", th);
            return null;
        }
    }
}
