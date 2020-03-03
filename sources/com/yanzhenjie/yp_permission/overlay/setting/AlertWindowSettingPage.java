package com.yanzhenjie.yp_permission.overlay.setting;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import com.xiaomi.youpin.UserMode;
import com.yanzhenjie.yp_permission.source.Source;

public class AlertWindowSettingPage {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2461a = Build.MANUFACTURER.toLowerCase();
    private Source b;

    public AlertWindowSettingPage(Source source) {
        this.b = source;
    }

    public void a(int i) {
        Intent intent;
        if (f2461a.contains("huawei")) {
            intent = b(this.b.a());
        } else if (f2461a.contains(UserMode.f23179a)) {
            intent = c(this.b.a());
        } else if (f2461a.contains("oppo")) {
            intent = d(this.b.a());
        } else if (f2461a.contains("vivo")) {
            intent = e(this.b.a());
        } else if (f2461a.contains("meizu")) {
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

    private Intent b(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity"));
        if (a(context, intent)) {
            return intent;
        }
        intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity"));
        if (a(context, intent)) {
            return intent;
        }
        intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.notificationmanager.ui.NotificationManagmentActivity"));
        return intent;
    }

    private Intent c(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.putExtra("extra_pkgname", context.getPackageName());
        if (a(context, intent)) {
            return intent;
        }
        intent.setPackage("com.miui.securitycenter");
        if (a(context, intent)) {
            return intent;
        }
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        return intent;
    }

    private Intent d(Context context) {
        Intent intent = new Intent();
        intent.putExtra("packageName", context.getPackageName());
        intent.setClassName("com.color.safecenter", "com.color.safecenter.permission.floatwindow.FloatWindowListActivity");
        if (a(context, intent)) {
            return intent;
        }
        intent.setClassName("com.coloros.safecenter", "com.coloros.safecenter.sysfloatwindow.FloatWindowListActivity");
        if (a(context, intent)) {
            return intent;
        }
        intent.setClassName("com.oppo.safe", "com.oppo.safe.permission.PermissionAppListActivity");
        return intent;
    }

    private Intent e(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.FloatWindowManager");
        intent.putExtra("packagename", context.getPackageName());
        if (a(context, intent)) {
            return intent;
        }
        intent.setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.safeguard.SoftPermissionDetailActivity"));
        return intent;
    }

    private Intent f(Context context) {
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.putExtra("packageName", context.getPackageName());
        intent.setComponent(new ComponentName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity"));
        return intent;
    }

    private static boolean a(Context context, Intent intent) {
        return context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }
}
