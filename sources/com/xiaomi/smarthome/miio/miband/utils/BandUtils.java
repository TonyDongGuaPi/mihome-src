package com.xiaomi.smarthome.miio.miband.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import com.google.android.exoplayer2.C;

public class BandUtils {
    public static PackageInfo a(Context context, String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            return context.getApplicationContext().getPackageManager().getPackageInfo(str, 0);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void b(Context context, String str) throws ActivityNotFoundException {
        Intent launchIntentForPackage = context.getApplicationContext().getPackageManager().getLaunchIntentForPackage(str);
        launchIntentForPackage.addFlags(C.ENCODING_PCM_MU_LAW);
        context.startActivity(launchIntentForPackage);
    }
}
