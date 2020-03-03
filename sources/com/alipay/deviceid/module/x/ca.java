package com.alipay.deviceid.module.x;

import android.content.Context;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ca {
    public static synchronized boolean a(Context context, String str) {
        synchronized (ca.class) {
            try {
                if (Math.abs(System.currentTimeMillis() - bz.b(context, str)) < 86400000) {
                    return true;
                }
            } catch (Throwable th) {
                z.a(th);
            }
            return false;
        }
    }

    public static boolean a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] strArr = {"2016-11-10 2016-11-11", "2016-12-11 2016-12-12"};
        int i = 0;
        while (i < 2) {
            try {
                String[] split = strArr[i].split(" ");
                if (split != null && split.length == 2) {
                    Date date = new Date();
                    Date parse = simpleDateFormat.parse(split[0] + " 00:00:00");
                    Date parse2 = simpleDateFormat.parse(split[1] + " 23:59:59");
                    if (date.after(parse) && date.before(parse2)) {
                        return true;
                    }
                }
                i++;
            } catch (Exception unused) {
            }
        }
        return false;
    }
}
