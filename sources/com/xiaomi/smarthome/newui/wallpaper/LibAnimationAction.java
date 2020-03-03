package com.xiaomi.smarthome.newui.wallpaper;

import android.os.Build;
import com.xiaomi.smarthome.newui.wallpaper.LibAnimationConfig;
import java.util.ArrayList;
import java.util.List;

public class LibAnimationAction {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public LibAnimationLayer f20792a;
    private LibAnimationConfig.Action b;
    /* access modifiers changed from: private */
    public List<LibAnimation> c = new ArrayList();
    /* access modifiers changed from: private */
    public LibAnimationLooper d = null;

    public LibAnimationAction(LibAnimationComposition libAnimationComposition, LibAnimationLayer libAnimationLayer, LibAnimationConfig.Action action) {
        this.f20792a = libAnimationLayer;
        this.b = action;
        for (LibAnimationConfig.Animations libAnimation : action.d) {
            this.c.add(new LibAnimation(libAnimationComposition, libAnimationLayer, libAnimation));
        }
    }

    public boolean a() {
        return this.d != null;
    }

    public boolean b() {
        if (this.d != null) {
            return this.d.f();
        }
        return false;
    }

    public void c() {
        if (this.d != null && !this.d.f()) {
            this.d.c();
            if (Build.VERSION.SDK_INT >= 19) {
                for (LibAnimation d2 : this.c) {
                    d2.d();
                }
            }
        }
    }

    public void d() {
        if (this.d != null && this.d.f()) {
            this.d.d();
            if (Build.VERSION.SDK_INT >= 19) {
                for (LibAnimation e : this.c) {
                    e.e();
                }
            }
        }
    }

    public void e() {
        if (this.d != null) {
            this.d.e();
            for (LibAnimation c2 : this.c) {
                c2.c();
            }
        }
    }

    public void a(LibAnimationLooper libAnimationLooper) {
        if (this.f20792a.b() == null) {
            libAnimationLooper.h();
        } else if (this.d == null) {
            LibAnimation.a("animation start");
            final LibAnimationLooper libAnimationLooper2 = libAnimationLooper;
            this.d = new LibAnimationLooper(this.b.f20797a, this.b.c, this.b.b, this.c.size()) {
                /* access modifiers changed from: protected */
                public void a() {
                    LibAnimation.a("animation end");
                    LibAnimationLooper unused = LibAnimationAction.this.d = null;
                    libAnimationLooper2.h();
                }

                /* access modifiers changed from: protected */
                public void b() {
                    if (LibAnimationAction.this.f20792a.b() == null) {
                        e();
                        a();
                        return;
                    }
                    LibAnimation.a("animation begin");
                    for (LibAnimation a2 : LibAnimationAction.this.c) {
                        a2.a((LibAnimationLooper) this);
                    }
                }
            };
            if (this.d.g()) {
                this.d = null;
            }
        }
    }
}
