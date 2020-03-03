package com.yanzhenjie.yp_permission.overlay.setting;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import com.yanzhenjie.yp_permission.source.Source;

public class OverlaySettingPage {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2462a = Build.MANUFACTURER.toLowerCase();
    private Source b;

    public OverlaySettingPage(Source source) {
        this.b = source;
    }

    public void a(int i) {
        if (f2462a.contains("meizu")) {
            if (!b(i) && !c(i)) {
                d(i);
            }
        } else if (!c(i)) {
            d(i);
        }
    }

    private boolean b(int i) {
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.putExtra("packageName", this.b.a().getPackageName());
        intent.setComponent(new ComponentName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity"));
        try {
            this.b.a(intent, i);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean c(int i) {
        Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION");
        intent.setData(Uri.fromParts("package", this.b.a().getPackageName(), (String) null));
        try {
            this.b.a(intent, i);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private void d(int i) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this.b.a().getPackageName(), (String) null));
        this.b.a(intent, i);
    }
}
