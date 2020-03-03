package com.xiaomi.verificationsdk.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public class NetWorkUtils {
    public static Bundle a(String str) {
        String str2 = new String(str);
        int indexOf = str2.indexOf(63);
        if (indexOf <= 0 || !str2.substring(indexOf + 1).startsWith("code=")) {
            return null;
        }
        return b(str2);
    }

    private static Bundle b(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            try {
                for (NameValuePair next : URLEncodedUtils.parse(new URI(str), "UTF-8")) {
                    if (!TextUtils.isEmpty(next.getName()) && !TextUtils.isEmpty(next.getValue())) {
                        bundle.putString(next.getName(), next.getValue());
                    }
                }
            } catch (URISyntaxException e) {
                Log.e("openauth", e.getMessage());
            }
        }
        return bundle;
    }

    public static boolean a(Context context) {
        NetworkInfo[] allNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (!(connectivityManager == null || (allNetworkInfo = connectivityManager.getAllNetworkInfo()) == null)) {
            for (NetworkInfo state : allNetworkInfo) {
                if (state.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }
}
