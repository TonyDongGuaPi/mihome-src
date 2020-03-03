package com.xiaomi.smarthome.shop.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

public class TelephonyUtils {
    public static boolean a(Context context) {
        String simOperator = ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
        return "46000".equals(simOperator) || "46002".equals(simOperator) || "46007".equals(simOperator);
    }

    public static boolean b(Context context) {
        return "46001".equals(((TelephonyManager) context.getSystemService("phone")).getSimOperator());
    }

    public static boolean c(Context context) {
        return "46003".equals(((TelephonyManager) context.getSystemService("phone")).getSimOperator());
    }
}
