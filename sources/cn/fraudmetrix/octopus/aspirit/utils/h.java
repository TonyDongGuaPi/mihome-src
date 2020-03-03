package cn.fraudmetrix.octopus.aspirit.utils;

import android.util.Log;

public class h {
    public static void a(String str) {
        if (Log.isLoggable("OctopusLog", 3)) {
            Log.d("OctopusLog", str);
        }
    }

    public static void b(String str) {
    }

    public static void c(String str) {
        if (Log.isLoggable("OctopusLog", 6)) {
            Log.e("OctopusLog", str);
        }
    }
}
