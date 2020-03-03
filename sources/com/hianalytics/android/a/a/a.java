package com.hianalytics.android.a.a;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.telephony.TelephonyManager;

public final class a {
    public static String a(Context context) {
        String str;
        String b;
        StringBuffer stringBuffer = new StringBuffer("1.0");
        String a2 = com.hianalytics.android.b.a.a.a(context);
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            com.hianalytics.android.b.a.a.h();
            return null;
        }
        Configuration configuration = context.getResources().getConfiguration();
        String str2 = "";
        if (!(configuration == null || configuration.locale == null)) {
            str2 = configuration.locale.toString();
        }
        try {
            str = com.hianalytics.android.b.a.a.b(telephonyManager.getDeviceId());
        } catch (SecurityException e) {
            e.printStackTrace();
            str = "";
        }
        String e2 = com.hianalytics.android.b.a.a.e(context);
        if (!com.hianalytics.android.b.a.a.f(context)) {
            stringBuffer.append(",,,,,");
            stringBuffer.append(e2);
            stringBuffer.append(",");
            stringBuffer.append(str);
            stringBuffer.append(",");
            stringBuffer.append(a2);
            b = ",";
        } else {
            stringBuffer.append(",");
            stringBuffer.append(com.alipay.android.phone.a.a.a.f813a + Build.VERSION.RELEASE);
            stringBuffer.append(",");
            stringBuffer.append(str2);
            stringBuffer.append(",");
            stringBuffer.append(Build.MODEL);
            stringBuffer.append(",");
            stringBuffer.append(Build.DISPLAY);
            stringBuffer.append(",");
            stringBuffer.append(e2);
            stringBuffer.append(",");
            stringBuffer.append(str);
            stringBuffer.append(",");
            stringBuffer.append(a2);
            stringBuffer.append(",");
            b = com.hianalytics.android.b.a.a.b(context);
        }
        stringBuffer.append(b);
        com.hianalytics.android.b.a.a.h();
        return stringBuffer.toString();
    }
}
