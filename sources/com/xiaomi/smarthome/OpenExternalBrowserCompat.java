package com.xiaomi.smarthome;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OpenExternalBrowserCompat {

    /* renamed from: a  reason: collision with root package name */
    private static final String f13395a = "OpenExternalBrowserComp";

    private OpenExternalBrowserCompat() {
    }

    public static boolean a(Context context, String str) {
        try {
            context.startActivity(b(context, str));
            return true;
        } catch (Exception e) {
            LogUtil.b(f13395a, "open: " + Log.getStackTraceString(e));
            return false;
        }
    }

    private static Intent b(Context context, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(524288);
        intent.setData(Uri.parse(str));
        Set<String> a2 = a(context);
        if (a2.contains("com.android.browser")) {
            intent.setPackage("com.android.browser");
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            intent.putExtra("com.android.browser.application_id", "com.android.browser");
            return intent;
        } else if (!a2.contains("com.android.chrome")) {
            return intent;
        } else {
            intent.setPackage("com.android.chrome");
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            intent.putExtra("com.android.browser.application_id", "com.android.chrome");
            return intent;
        }
    }

    private static Set<String> a(Context context) {
        HashSet hashSet = new HashSet();
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        for (int i = 0; i < queryIntentActivities.size(); i++) {
            hashSet.add(queryIntentActivities.get(i).activityInfo.packageName);
        }
        return hashSet;
    }
}
