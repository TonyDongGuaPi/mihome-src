package com.yanzhenjie.yp_permission.runtime.setting;

import com.yanzhenjie.yp_permission.PermissionActivity;
import com.yanzhenjie.yp_permission.Setting;
import com.yanzhenjie.yp_permission.source.Source;
import com.yanzhenjie.yp_permission.util.MainExecutor;

public class RuntimeSetting implements PermissionActivity.RequestListener, Setting {

    /* renamed from: a  reason: collision with root package name */
    private static final MainExecutor f2468a = new MainExecutor();
    private Source b;
    /* access modifiers changed from: private */
    public Setting.Action c;

    public void d() {
    }

    public RuntimeSetting(Source source) {
        this.b = source;
    }

    public void c() {
        new RuntimeSettingPage(this.b).a(-1);
    }

    public void a(int i) {
        new RuntimeSettingPage(this.b).a(i);
    }

    public Setting a(Setting.Action action) {
        this.c = action;
        return this;
    }

    public void b() {
        PermissionActivity.permissionSetting(this.b.a(), this);
    }

    public void a() {
        f2468a.a(new Runnable() {
            public void run() {
                if (RuntimeSetting.this.c != null) {
                    RuntimeSetting.this.c.a();
                }
            }
        }, 100);
    }
}
