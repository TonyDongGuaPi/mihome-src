package com.xiaomi.push;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.alipay.sdk.util.i;

public class ec extends ef {

    /* renamed from: a  reason: collision with root package name */
    private String f12705a;

    public ec(Context context, int i, String str) {
        super(context, i);
        this.f12705a = str;
    }

    private String[] f() {
        if (TextUtils.isEmpty(this.f12705a)) {
            return null;
        }
        String b = bc.b(this.f12705a);
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        if (b.contains(",")) {
            return b.split(",");
        }
        return new String[]{b};
    }

    public int a() {
        return 24;
    }

    public String b() {
        String[] f = f();
        if (f == null || f.length <= 0) {
            return null;
        }
        PackageManager packageManager = this.d.getPackageManager();
        StringBuilder sb = new StringBuilder();
        for (String packageInfo : f) {
            try {
                PackageInfo packageInfo2 = packageManager.getPackageInfo(packageInfo, 16384);
                if (packageInfo2 != null) {
                    if (sb.length() > 0) {
                        sb.append(i.b);
                    }
                    sb.append(packageInfo2.applicationInfo.loadLabel(packageManager).toString());
                    sb.append(",");
                    sb.append(packageInfo2.packageName);
                    sb.append(",");
                    sb.append(packageInfo2.versionName);
                    sb.append(",");
                    sb.append(packageInfo2.versionCode);
                }
            } catch (Exception unused) {
            }
        }
        if (sb.length() > 0) {
            return sb.toString();
        }
        return null;
    }

    public hq c() {
        return hq.AppIsInstalled;
    }
}
