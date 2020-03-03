package com.yanzhenjie.permission.setting;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.youpin.UserMode;
import com.yanzhenjie.permission.SettingService;
import com.yanzhenjie.permission.source.Source;

public class PermissionSetting implements SettingService {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2422a = 199;
    private static final String b = Build.MANUFACTURER.toLowerCase();
    private Source c;

    public void c() {
    }

    public PermissionSetting(@NonNull Source source) {
        this.c = source;
    }

    public void a() {
        try {
            this.c.a(d(), 199);
        } catch (Exception unused) {
            this.c.a(a(this.c.a()));
        }
    }

    public void a(int i) {
        try {
            this.c.a(d(), i);
        } catch (Exception unused) {
            this.c.a(a(this.c.a()), i);
        }
    }

    public void a(int i, boolean z) {
        Intent d = d();
        if (z) {
            d.addFlags(C.ENCODING_PCM_MU_LAW);
        }
        try {
            this.c.a(d, i);
        } catch (Exception unused) {
            this.c.a(a(this.c.a()), i);
        }
    }

    public void b() {
        this.c.a(a(this.c.a()), 199);
    }

    private Intent d() {
        if (b.contains("huawei")) {
            return b(this.c.a());
        }
        if (b.contains(UserMode.f23179a) && SystemApi.c()) {
            return c(this.c.a());
        }
        if (b.contains("oppo")) {
            return e(this.c.a());
        }
        if (b.contains("vivo")) {
            return d(this.c.a());
        }
        if (b.contains("samsung")) {
            return h(this.c.a());
        }
        if (b.contains("meizu")) {
            return f(this.c.a());
        }
        if (b.contains("smartisan")) {
            return g(this.c.a());
        }
        return a(this.c.a());
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
        if (Build.VERSION.SDK_INT >= 25) {
            intent.setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.SoftPermissionDetailActivity"));
        } else {
            intent.setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.safeguard.SoftPermissionDetailActivity"));
        }
        return intent;
    }

    private static Intent e(Context context) {
        return a(context);
    }

    private static Intent f(Context context) {
        if (Build.VERSION.SDK_INT >= 25) {
            return a(context);
        }
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.putExtra("packageName", context.getPackageName());
        intent.setComponent(new ComponentName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity"));
        return intent;
    }

    private static Intent g(Context context) {
        return a(context);
    }

    private static Intent h(Context context) {
        return a(context);
    }
}
