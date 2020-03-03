package com.xiaomi.base.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkState {

    /* renamed from: a  reason: collision with root package name */
    public static final int f10032a = 2;
    public static final int b = 0;
    public static final int c = 1;

    public static int a(Context context) {
        NetworkInfo activeNetworkInfo;
        if (!(context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null)) {
            if (activeNetworkInfo.getType() == 1) {
                return 1;
            }
            if (activeNetworkInfo.getType() == 0) {
                return 2;
            }
        }
        return 0;
    }
}
