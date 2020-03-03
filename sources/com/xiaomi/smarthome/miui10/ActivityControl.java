package com.xiaomi.smarthome.miui10;

import android.app.Activity;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;

public class ActivityControl {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20128a = "ActivityControl";
    private final Activity b;
    private boolean c;
    private boolean d;

    public ActivityControl(Activity activity) {
        this.b = activity;
    }

    public void a() {
        this.c = true;
        LogUtil.c(f20128a, Constants.Event.FINISH);
        d();
    }

    public void b() {
        this.d = true;
    }

    public void c() {
        this.d = false;
        LogUtil.c(f20128a, "onStop");
        d();
    }

    private void d() {
        if (!this.d && this.c && this.b != null && !this.b.isFinishing()) {
            this.b.finish();
        }
    }
}
