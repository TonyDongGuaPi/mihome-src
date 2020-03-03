package com.alipay.deviceid.module.x;

import android.content.Context;
import android.os.Build;
import com.miui.tsmclient.util.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class z {
    public static synchronized void a(String str) {
        synchronized (z.class) {
            y.a(str);
        }
    }

    public static synchronized void a(Throwable th) {
        synchronized (z.class) {
            y.a(th);
        }
    }

    public static synchronized void a(Context context, String str, String str2, String str3) {
        synchronized (z.class) {
            w wVar = new w(Build.MODEL, context.getApplicationContext().getApplicationInfo().packageName, "security-sdk-token", "6.0.2.20181008", str, str2, str3);
            y.a(context.getFilesDir().getAbsolutePath() + "/log/ap", new SimpleDateFormat(StringUtils.SOURCE_DATE_FORMAT).format(Calendar.getInstance().getTime()) + ".log", wVar.toString());
        }
    }
}
