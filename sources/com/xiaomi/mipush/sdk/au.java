package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;

class au implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String[] f11537a;
    final /* synthetic */ Context b;
    final /* synthetic */ at c;

    au(at atVar, String[] strArr, Context context) {
        this.c = atVar;
        this.f11537a = strArr;
        this.b = context;
    }

    public void run() {
        int i = 0;
        while (i < this.f11537a.length) {
            try {
                if (!TextUtils.isEmpty(this.f11537a[i])) {
                    if (i > 0) {
                        Thread.sleep(((long) ((Math.random() * 2.0d) + 1.0d)) * 1000);
                    }
                    PackageInfo packageInfo = this.b.getPackageManager().getPackageInfo(this.f11537a[i], 4);
                    if (packageInfo != null) {
                        this.c.a(this.b, packageInfo);
                    }
                }
                i++;
            } catch (Throwable th) {
                b.a(th);
                return;
            }
        }
    }
}
