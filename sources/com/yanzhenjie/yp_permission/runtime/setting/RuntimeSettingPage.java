package com.yanzhenjie.yp_permission.runtime.setting;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import com.xiaomi.youpin.UserMode;
import com.yanzhenjie.yp_permission.source.Source;

public class RuntimeSettingPage {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2470a = Build.MANUFACTURER.toLowerCase();
    private Source b;

    public RuntimeSettingPage(Source source) {
        this.b = source;
    }

    public void a(int i) {
        Intent intent;
        if (f2470a.contains("huawei")) {
            intent = b(this.b.a());
        } else if (f2470a.contains(UserMode.f23179a)) {
            intent = c(this.b.a());
        } else if (f2470a.contains("oppo")) {
            intent = e(this.b.a());
        } else if (f2470a.contains("vivo")) {
            intent = d(this.b.a());
        } else if (f2470a.contains("meizu")) {
            intent = f(this.b.a());
        } else {
            intent = a(this.b.a());
        }
        try {
            this.b.a(intent, i);
        } catch (Exception unused) {
            this.b.a(a(this.b.a()), i);
        }
    }

    private static Intent a(Context context) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", context.getPackageName(), (String) null));
        return intent;
    }

    private static Intent b(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            return a(context);
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity"));
        return intent;
    }

    private static Intent c(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.putExtra("extra_pkgname", context.getPackageName());
        return intent;
    }

    private static Intent d(Context context) {
        Intent intent = new Intent();
        intent.putExtra("packagename", context.getPackageName());
        intent.setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.SoftPermissionDetailActivity"));
        if (a(context, intent)) {
            return intent;
        }
        intent.setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.safeguard.SoftPermissionDetailActivity"));
        return intent;
    }

    private static Intent e(Context context) {
        Intent intent = new Intent();
        intent.putExtra("packageName", context.getPackageName());
        intent.setComponent(new ComponentName("com.color.safecenter", "com.color.safecenter.permission.PermissionManagerActivity"));
        return intent;
    }

    private static Intent f(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return a(context);
        }
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.putExtra("packageName", context.getPackageName());
        intent.setComponent(new ComponentName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity"));
        return intent;
    }

    private static boolean a(Context context, Intent intent) {
        return context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }
}
