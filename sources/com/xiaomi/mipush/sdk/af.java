package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;

final class af implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String[] f11527a;
    final /* synthetic */ Context b;

    af(String[] strArr, Context context) {
        this.f11527a = strArr;
        this.b = context;
    }

    public void run() {
        try {
            for (String str : this.f11527a) {
                if (!TextUtils.isEmpty(str)) {
                    PackageInfo packageInfo = this.b.getPackageManager().getPackageInfo(str, 4);
                    if (packageInfo != null) {
                        MiPushClient.b(this.b, packageInfo);
                    }
                }
            }
        } catch (Throwable th) {
            b.a(th);
        }
    }
}
