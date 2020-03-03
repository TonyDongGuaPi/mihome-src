package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;
import com.drew.metadata.exif.makernotes.OlympusFocusInfoMakernoteDirectory;

final class y implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f11568a;

    y(Context context) {
        this.f11568a = context;
    }

    public void run() {
        try {
            PackageInfo packageInfo = this.f11568a.getPackageManager().getPackageInfo(this.f11568a.getPackageName(), OlympusFocusInfoMakernoteDirectory.s);
            x.c(this.f11568a);
            x.d(this.f11568a, packageInfo);
            x.c(this.f11568a, packageInfo);
        } catch (Throwable th) {
            Log.e("ManifestChecker", "", th);
        }
    }
}
