package com.mipay.common.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class i {
    public static String a(long j) {
        String str;
        Object[] objArr;
        if (j % 100 == 0) {
            str = "%d";
            objArr = new Object[]{Long.valueOf(j / 100)};
        } else if (j % 10 == 0) {
            str = "%.1f";
            double d = (double) j;
            Double.isNaN(d);
            objArr = new Object[]{Double.valueOf(d / 100.0d)};
        } else {
            str = "%.2f";
            double d2 = (double) j;
            Double.isNaN(d2);
            objArr = new Object[]{Double.valueOf(d2 / 100.0d)};
        }
        return String.format(str, objArr);
    }

    public static boolean a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String b(long j) {
        double d = (double) j;
        Double.isNaN(d);
        return String.format("%.2f", new Object[]{Double.valueOf(d / 100.0d)});
    }

    public static boolean b(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
    }

    public static boolean c(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 0;
    }

    public static boolean d(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.isActiveNetworkMetered();
    }

    public static int e(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.getType();
        }
        return -1;
    }
}
