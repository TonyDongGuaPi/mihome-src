package com.xiaomi.payment;

import android.app.Application;
import com.mibi.common.CommonApplicationDelegate;
import com.miuipub.internal.util.PackageConstants;

public class PaymentApp {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f12144a = false;

    public static synchronized void a(Application application) {
        synchronized (PaymentApp.class) {
            if (!f12144a) {
                PackageConstants.a(application);
                new CommonApplicationDelegate(application).onCreate();
                f12144a = true;
            }
        }
    }
}
