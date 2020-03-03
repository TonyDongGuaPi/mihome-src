package com.xiaomi.youpin.hawkeye.display.ui;

import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.xiaomi.youpin.hawkeye.HawkEye;
import com.xiaomi.youpin.hawkeye.entity.BaseInfo;
import com.xiaomi.youpin.hawkeye.utils.UIUtils;

public class FloatViewManager {

    /* renamed from: a  reason: collision with root package name */
    private WindowManager f23359a;
    private DisplayMetrics b;
    private WindowManager.LayoutParams c;
    private DisplayDataFloatPanel d;
    private CircleView e;
    private boolean f;

    private static class FloatViewManagerHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static FloatViewManager f23360a = new FloatViewManager();

        private FloatViewManagerHolder() {
        }
    }

    private FloatViewManager() {
        this.b = new DisplayMetrics();
        g().getDefaultDisplay().getMetrics(this.b);
    }

    private WindowManager g() {
        if (this.f23359a == null) {
            this.f23359a = (WindowManager) HawkEye.d().getSystemService("window");
        }
        return this.f23359a;
    }

    public static FloatViewManager a() {
        return FloatViewManagerHolder.f23360a;
    }

    private void h() {
        if (this.d == null) {
            this.d = new DisplayDataFloatPanel(HawkEye.d());
            this.c = new WindowManager.LayoutParams(-2, -2, b(), 131080, -3);
            this.c.gravity = 51;
            this.c.x = UIUtils.a(30.0f);
            this.c.y = UIUtils.a(50.0f);
        }
        g().addView(this.d, this.c);
        this.f = true;
    }

    public static int b() {
        if (Build.VERSION.SDK_INT > 25) {
            return 2038;
        }
        if (Build.VERSION.SDK_INT != 25 && Build.VERSION.SDK_INT >= 19) {
            return 2005;
        }
        return 2003;
    }

    public void a(BaseInfo baseInfo) {
        if (baseInfo != null && this.d != null) {
            this.d.addDisplayData(baseInfo);
        }
    }

    public void c() {
        if (!this.f) {
            h();
        }
    }

    public void d() {
        f();
    }

    public void e() {
        if (this.f) {
            f();
        } else {
            h();
        }
    }

    public void f() {
        if (this.d != null && this.f) {
            g().removeView(this.d);
            this.f = false;
        }
    }
}
