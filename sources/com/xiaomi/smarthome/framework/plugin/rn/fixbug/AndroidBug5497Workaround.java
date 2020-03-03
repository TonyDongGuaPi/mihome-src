package com.xiaomi.smarthome.framework.plugin.rn.fixbug;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

public class AndroidBug5497Workaround {
    private static AndroidBug5497Workaround f;

    /* renamed from: a  reason: collision with root package name */
    private View f17273a;
    private FrameLayout b;
    private int c;
    private FrameLayout.LayoutParams d;
    /* access modifiers changed from: private */
    public boolean e = false;

    public static AndroidBug5497Workaround a() {
        if (f == null) {
            synchronized (AndroidBug5497Workaround.class) {
                if (f == null) {
                    f = new AndroidBug5497Workaround();
                }
            }
        }
        return f;
    }

    public void a(Activity activity) {
        this.b = (FrameLayout) activity.findViewById(16908290);
        this.f17273a = this.b.getChildAt(0);
        this.f17273a.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (AndroidBug5497Workaround.this.e) {
                    AndroidBug5497Workaround.this.c();
                }
            }
        });
        this.d = (FrameLayout.LayoutParams) this.f17273a.getLayoutParams();
    }

    private AndroidBug5497Workaround() {
    }

    /* access modifiers changed from: private */
    public void c() {
        int d2;
        if (this.f17273a != null && this.d != null && (d2 = d()) != this.c) {
            int height = this.f17273a.getRootView().getHeight();
            int i = height - d2;
            if (i > height / 4) {
                this.d.height = height - i;
            } else {
                this.d.height = this.b.getHeight();
            }
            this.f17273a.requestLayout();
            this.c = d2;
        }
    }

    private int d() {
        Rect rect = new Rect();
        this.f17273a.getWindowVisibleDisplayFrame(rect);
        return rect.bottom;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void b() {
        this.f17273a = null;
        this.d = null;
        this.b = null;
        f = null;
    }
}
