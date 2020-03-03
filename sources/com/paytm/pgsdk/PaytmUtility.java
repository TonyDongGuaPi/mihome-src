package com.paytm.pgsdk;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import java.net.URLEncoder;
import org.json.JSONObject;

public class PaytmUtility {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8550a = "=";
    private static final String b = "&";
    private static final String c = "PGSDK";
    private static final String d = "UTF-8";

    protected static synchronized String a(Bundle bundle) {
        String stringBuffer;
        synchronized (PaytmUtility.class) {
            try {
                a("Extracting Strings from Bundle...");
                boolean z = true;
                StringBuffer stringBuffer2 = new StringBuffer();
                for (String str : bundle.keySet()) {
                    if (z) {
                        z = false;
                    } else {
                        stringBuffer2.append("&");
                    }
                    stringBuffer2.append(str);
                    stringBuffer2.append("=");
                    stringBuffer2.append(bundle.getString(str));
                }
                a("Extracted String is " + stringBuffer2.toString());
                stringBuffer = stringBuffer2.toString();
            } catch (Exception e) {
                a(e);
                return null;
            }
        }
        return stringBuffer;
    }

    protected static synchronized String b(Bundle bundle) {
        String stringBuffer;
        synchronized (PaytmUtility.class) {
            try {
                a("Extracting Strings from Bundle...");
                boolean z = true;
                StringBuffer stringBuffer2 = new StringBuffer();
                for (String str : bundle.keySet()) {
                    if (z) {
                        z = false;
                    } else {
                        stringBuffer2.append("&");
                    }
                    stringBuffer2.append(URLEncoder.encode(str, "UTF-8"));
                    stringBuffer2.append("=");
                    stringBuffer2.append(URLEncoder.encode(bundle.getString(str), "UTF-8"));
                }
                a("URL encoded String is " + stringBuffer2.toString());
                stringBuffer = stringBuffer2.toString();
            } catch (Exception e) {
                a(e);
                return null;
            }
        }
        return stringBuffer;
    }

    protected static synchronized void a(String str) {
        synchronized (PaytmUtility.class) {
            Log.c(c, str);
        }
    }

    protected static synchronized void a(Exception exc) {
        synchronized (PaytmUtility.class) {
            exc.printStackTrace();
        }
    }

    protected static synchronized boolean a(Context context) {
        synchronized (PaytmUtility.class) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            boolean isConnected = activeNetworkInfo.isConnected();
            return isConnected;
        }
    }

    protected static String c(Bundle bundle) {
        JSONObject jSONObject;
        if (bundle != null) {
            try {
                if (bundle.size() > 0) {
                    jSONObject = new JSONObject();
                    for (String str : bundle.keySet()) {
                        jSONObject.put(str, bundle.get(str));
                    }
                    a("JSON string is " + jSONObject);
                    return jSONObject.toString();
                }
            } catch (Exception e) {
                a(e);
                return null;
            }
        }
        jSONObject = null;
        a("JSON string is " + jSONObject);
        return jSONObject.toString();
    }

    protected static String d(Bundle bundle) {
        JSONObject jSONObject;
        if (bundle != null) {
            try {
                if (bundle.size() > 0) {
                    jSONObject = new JSONObject();
                    for (String str : bundle.keySet()) {
                        jSONObject.put(URLEncoder.encode(str, "UTF-8"), URLEncoder.encode(bundle.getString(str), "UTF-8"));
                    }
                    a("URL encoded JSON string is " + jSONObject);
                    return jSONObject.toString();
                }
            } catch (Exception e) {
                a(e);
                return null;
            }
        }
        jSONObject = null;
        a("URL encoded JSON string is " + jSONObject);
        return jSONObject.toString();
    }
}
