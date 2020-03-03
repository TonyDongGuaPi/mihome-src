package com.xiaomi.phonenum.phone;

import android.content.Context;
import android.os.Build;

public class PhoneInfoManager {

    /* renamed from: a  reason: collision with root package name */
    private static PhoneUtil f12576a;

    public static synchronized PhoneUtil a(Context context) {
        synchronized (PhoneInfoManager.class) {
            Context applicationContext = context.getApplicationContext();
            if (f12576a != null) {
                PhoneUtil phoneUtil = f12576a;
                return phoneUtil;
            } else if (Build.VERSION.SDK_INT <= 20) {
                f12576a = new KKPhoneInfo(applicationContext);
                PhoneUtil phoneUtil2 = f12576a;
                return phoneUtil2;
            } else {
                if (Build.VERSION.SDK_INT >= 26) {
                    f12576a = new OPhoneInfo(applicationContext);
                } else if (Build.VERSION.SDK_INT >= 24) {
                    f12576a = new NPhoneInfo(applicationContext);
                } else if (Build.VERSION.SDK_INT >= 23) {
                    f12576a = new MPhoneInfo(applicationContext);
                } else if (Build.VERSION.SDK_INT >= 22) {
                    f12576a = new LSPhoneInfo(applicationContext);
                } else if (Build.VERSION.SDK_INT >= 21) {
                    f12576a = new LPhoneInfo(applicationContext);
                }
                PhoneUtil phoneUtil3 = f12576a;
                return phoneUtil3;
            }
        }
    }

    public static void a(PhoneUtil phoneUtil) {
        f12576a = phoneUtil;
    }
}
