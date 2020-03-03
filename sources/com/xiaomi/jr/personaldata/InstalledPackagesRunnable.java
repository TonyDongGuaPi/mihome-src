package com.xiaomi.jr.personaldata;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.personaldata.CollectRunnable;
import java.util.ArrayList;

public class InstalledPackagesRunnable extends CollectRunnable {
    private static final long c = 2592000000L;
    private static final long d = Long.MAX_VALUE;

    /* access modifiers changed from: package-private */
    public int b() {
        return 4;
    }

    /* access modifiers changed from: package-private */
    public String c() {
        return "installed_packages";
    }

    /* access modifiers changed from: package-private */
    public long d() {
        return 2592000000L;
    }

    /* access modifiers changed from: package-private */
    public long e() {
        return Long.MAX_VALUE;
    }

    InstalledPackagesRunnable(Context context) {
        super(context);
    }

    /* access modifiers changed from: package-private */
    public String[] a() {
        return new String[0];
    }

    /* access modifiers changed from: package-private */
    public CollectRunnable.CollectResult a(long j, long j2) throws Exception {
        ArrayList arrayList = new ArrayList();
        for (PackageInfo packageInfo : f().getPackageManager().getInstalledPackages(0)) {
            arrayList.add(packageInfo.packageName);
        }
        CollectRunnable.CollectResult collectResult = new CollectRunnable.CollectResult();
        collectResult.f11002a = arrayList.toString();
        collectResult.b = j2;
        MifiLog.c("TestData", "collected " + c() + " count=" + arrayList.size() + " size=" + collectResult.f11002a.length());
        return collectResult;
    }
}
