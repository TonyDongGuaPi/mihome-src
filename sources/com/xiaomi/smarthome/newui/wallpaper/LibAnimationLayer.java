package com.xiaomi.smarthome.newui.wallpaper;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.xiaomi.smarthome.newui.wallpaper.LibAnimationConfig;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class LibAnimationLayer {

    /* renamed from: a  reason: collision with root package name */
    private final LibAnimationConfig.Layer f20800a;
    private boolean b = false;
    /* access modifiers changed from: private */
    public List<LibAnimationLayer> c = new ArrayList();
    /* access modifiers changed from: private */
    public List<LibAnimationAction> d = new ArrayList();
    private WeakReference<View> e;
    /* access modifiers changed from: private */
    public LibAnimationLooper f = null;
    /* access modifiers changed from: private */
    public LibAnimationLooper g = null;

    public LibAnimationLayer(LibAnimationComposition libAnimationComposition, LibAnimationConfig.Layer layer, FrameLayout frameLayout) {
        View view;
        this.f20800a = layer;
        this.b = !layer.l.isEmpty();
        if (this.b) {
            view = new FrameLayout(frameLayout.getContext());
        } else {
            view = new ImageView(frameLayout.getContext());
        }
        libAnimationComposition.a(view, layer.c);
        if (this.b) {
            for (LibAnimationConfig.Layer libAnimationLayer : layer.l) {
                this.c.add(new LibAnimationLayer(libAnimationComposition, libAnimationLayer, (FrameLayout) view));
            }
        }
        frameLayout.addView(view);
        this.e = new WeakReference<>(view);
        for (LibAnimationConfig.Action libAnimationAction : layer.m) {
            this.d.add(new LibAnimationAction(libAnimationComposition, this, libAnimationAction));
        }
    }

    public void a(LibAnimationComposition libAnimationComposition) {
        View b2 = b();
        if (b2 != null) {
            b2.setLayoutParams(new FrameLayout.LayoutParams((int) (this.f20800a.f * libAnimationComposition.b()), (int) (this.f20800a.g * libAnimationComposition.c())));
            b2.setX(this.f20800a.d * libAnimationComposition.b());
            b2.setY(this.f20800a.e * libAnimationComposition.c());
            b2.setAlpha(1.0f - this.f20800a.h);
        }
        if (this.b) {
            for (LibAnimationLayer a2 : this.c) {
                a2.a(libAnimationComposition);
            }
        }
    }

    public LibAnimationConfig.Layer a() {
        return this.f20800a;
    }

    public View b() {
        if (this.e == null) {
            return null;
        }
        return (View) this.e.get();
    }

    public boolean c() {
        if (b() == null) {
            return false;
        }
        if (this.g == null && this.f == null) {
            return false;
        }
        return true;
    }

    public boolean d() {
        if (b() == null) {
            return false;
        }
        if (this.g != null) {
            return this.g.f();
        }
        if (this.f != null) {
            return this.f.f();
        }
        return false;
    }

    public void e() {
        if (this.g != null) {
            this.g.c();
        }
        if (this.f != null) {
            this.f.c();
            for (LibAnimationLayer e2 : this.c) {
                e2.e();
            }
        }
    }

    public void f() {
        if (this.g != null) {
            this.g.d();
        }
        if (this.f != null) {
            this.f.d();
            for (LibAnimationLayer f2 : this.c) {
                f2.f();
            }
        }
    }

    public void g() {
        if (this.g != null) {
            this.g.e();
            for (LibAnimationAction e2 : this.d) {
                e2.e();
            }
            this.g = null;
        }
        if (this.f != null) {
            this.f.e();
            this.f = null;
        }
        for (LibAnimationLayer g2 : this.c) {
            g2.g();
        }
        this.e = null;
    }

    public void a(LibAnimationLooper libAnimationLooper) {
        if (b() == null) {
            libAnimationLooper.h();
            libAnimationLooper.h();
        } else if (this.g == null && this.f == null) {
            LibAnimation.a("layer start");
            c(libAnimationLooper);
            b(libAnimationLooper);
        }
    }

    private void b(LibAnimationLooper libAnimationLooper) {
        if (this.f == null) {
            LibAnimation.a("sublayer start");
            final LibAnimationLooper libAnimationLooper2 = libAnimationLooper;
            this.f = new LibAnimationLooper(this.f20800a.f20799a, 1, this.f20800a.b, this.c.size() * 2) {
                /* access modifiers changed from: protected */
                public void a() {
                    LibAnimation.a("sublayer end");
                    LibAnimationLooper unused = LibAnimationLayer.this.f = null;
                    libAnimationLooper2.h();
                }

                /* access modifiers changed from: protected */
                public void b() {
                    if (LibAnimationLayer.this.b() == null) {
                        e();
                        a();
                        return;
                    }
                    LibAnimation.a("sublayer begin");
                    for (LibAnimationLayer a2 : LibAnimationLayer.this.c) {
                        a2.a((LibAnimationLooper) this);
                    }
                }
            };
            if (this.f.g()) {
                this.f = null;
            }
        }
    }

    private void c(LibAnimationLooper libAnimationLooper) {
        if (this.g == null) {
            LibAnimation.a("actions start");
            final LibAnimationLooper libAnimationLooper2 = libAnimationLooper;
            this.g = new LibAnimationLooper(this.f20800a.i, this.f20800a.k, this.f20800a.j, this.d.size()) {

                /* renamed from: a  reason: collision with root package name */
                int f20802a = 0;

                private void a(int i) {
                    if (LibAnimationLayer.this.b() == null) {
                        e();
                        a();
                        return;
                    }
                    this.f20802a = i;
                    LibAnimation.a("action " + i);
                    ((LibAnimationAction) LibAnimationLayer.this.d.get(i)).a((LibAnimationLooper) this);
                }

                public void c() {
                    super.c();
                    ((LibAnimationAction) LibAnimationLayer.this.d.get(this.f20802a)).c();
                }

                public void d() {
                    super.d();
                    ((LibAnimationAction) LibAnimationLayer.this.d.get(this.f20802a)).d();
                }

                /* access modifiers changed from: protected */
                public void a() {
                    LibAnimation.a("action end");
                    LibAnimationLooper unused = LibAnimationLayer.this.g = null;
                    libAnimationLooper2.h();
                }

                /* access modifiers changed from: protected */
                public void a(int i, int i2) {
                    int i3 = i + 1;
                    if (i3 < i2) {
                        a(i3);
                    }
                }

                /* access modifiers changed from: protected */
                public void b() {
                    LibAnimation.a("action begin");
                    a(0);
                }
            };
            if (this.g.g()) {
                this.g = null;
            }
        }
    }
}
