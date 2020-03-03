package com.ximalaya.ting.android.opensdk.auth.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.mi.util.permission.Permission;

public final class c {
    public static boolean a(Context context) {
        return context == null || context.checkCallingOrSelfPermission(Permission.y) == 0;
    }

    public static boolean b(Context context) {
        NetworkInfo e;
        if (context == null || (e = e(context)) == null || !e.isConnected()) {
            return false;
        }
        return true;
    }

    private static boolean c(Context context) {
        NetworkInfo e;
        if (context == null || (e = e(context)) == null || 1 != e.getType() || !e.isConnected()) {
            return false;
        }
        return true;
    }

    private static boolean d(Context context) {
        NetworkInfo e;
        if (context == null || (e = e(context)) == null || e == null || e.getType() != 0 || !e.isConnected()) {
            return false;
        }
        return true;
    }

    private static NetworkInfo e(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    private static NetworkInfo a(Context context, int i) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(i);
    }

    private static int f(Context context) {
        NetworkInfo e;
        if (context == null || (e = e(context)) == null) {
            return -1;
        }
        return e.getType();
    }

    private static void g(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager.getInstance().removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }
}
