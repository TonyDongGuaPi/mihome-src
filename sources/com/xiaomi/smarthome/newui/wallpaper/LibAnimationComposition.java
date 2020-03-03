package com.xiaomi.smarthome.newui.wallpaper;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.xiaomi.smarthome.newui.wallpaper.LibAnimationConfig;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibAnimationComposition {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final List<LibAnimationLayer> f20794a = new ArrayList();
    private WeakReference<LibAnimationView> b;
    private final LibAnimationConfig c;
    private final float d;
    private final float e;
    /* access modifiers changed from: private */
    public boolean f = false;
    private LibAnimationBitmapLoader g;
    private Map<String, Bitmap> h = new HashMap();
    /* access modifiers changed from: private */
    public LibAnimationLooper i = null;

    /* access modifiers changed from: package-private */
    public void a(LibAnimationView libAnimationView) {
        if (this.b != null) {
            if (libAnimationView == null) {
                this.b.clear();
            } else {
                this.b = new WeakReference<>(libAnimationView);
            }
        }
    }

    public LibAnimationComposition(LibAnimationConfig libAnimationConfig, LibAnimationView libAnimationView, LibAnimationBitmapLoader libAnimationBitmapLoader) {
        this.b = new WeakReference<>(libAnimationView);
        this.c = libAnimationConfig;
        this.g = libAnimationBitmapLoader;
        ViewGroup.LayoutParams layoutParams = libAnimationView.getLayoutParams();
        if (layoutParams.width < 1) {
            Point point = new Point();
            ((WindowManager) libAnimationView.getContext().getSystemService("window")).getDefaultDisplay().getSize(point);
            float f2 = ((float) point.x) / ((float) libAnimationConfig.e);
            this.e = f2;
            this.d = f2;
            libAnimationView.setMinimumWidth(point.x);
            libAnimationView.setMinimumHeight((point.x * libAnimationConfig.f) / libAnimationConfig.e);
        } else {
            this.d = ((float) layoutParams.width) / ((float) libAnimationConfig.e);
            this.e = ((float) layoutParams.height) / ((float) libAnimationConfig.f);
        }
        for (LibAnimationConfig.Layer libAnimationLayer : libAnimationConfig.g) {
            LibAnimationLayer libAnimationLayer2 = new LibAnimationLayer(this, libAnimationLayer, libAnimationView);
            this.f20794a.add(libAnimationLayer2);
            libAnimationLayer2.a(this);
        }
        a((View) libAnimationView, libAnimationConfig.d);
    }

    public LibAnimationConfig a() {
        return this.c;
    }

    public float b() {
        return this.d;
    }

    public float c() {
        return this.e;
    }

    private static final Integer a(String str) {
        if (str != null && str.startsWith("#")) {
            return Integer.valueOf(Color.parseColor(str));
        }
        return null;
    }

    public void a(View view, String str) {
        if (!TextUtils.isEmpty(str)) {
            Integer a2 = a(str);
            if (a2 != null) {
                view.setBackgroundColor(a2.intValue());
                return;
            }
            Bitmap bitmap = this.h.get(str);
            if (bitmap == null) {
                if (!this.h.containsKey(str)) {
                    LibAnimationBitmapLoader libAnimationBitmapLoader = this.g;
                    bitmap = libAnimationBitmapLoader.a(this.c.c + str);
                    this.h.put(str, bitmap);
                    if (bitmap == null) {
                        return;
                    }
                } else {
                    return;
                }
            }
            view.setBackgroundDrawable(new BitmapDrawable(view.getContext().getResources(), bitmap));
        }
    }

    public void a(boolean z) {
        this.f = z;
    }

    public boolean d() {
        return this.f;
    }

    public boolean e() {
        return this.i != null;
    }

    public boolean f() {
        if (this.i != null) {
            return this.i.f();
        }
        return false;
    }

    public void g() {
        if (this.i != null) {
            if (Build.VERSION.SDK_INT >= 19) {
                this.i.c();
                for (LibAnimationLayer e2 : this.f20794a) {
                    e2.e();
                }
                return;
            }
            i();
        }
    }

    public void h() {
        if (this.i != null) {
            if (Build.VERSION.SDK_INT >= 19) {
                this.i.d();
                for (LibAnimationLayer f2 : this.f20794a) {
                    f2.f();
                }
                return;
            }
            j();
        }
    }

    public void i() {
        if (this.i != null) {
            this.i.e();
            this.i = null;
        }
        for (LibAnimationLayer g2 : this.f20794a) {
            g2.g();
        }
        for (Bitmap next : this.h.values()) {
            if (next != null && !next.isRecycled()) {
                next.recycle();
            }
        }
        this.h.clear();
        if (this.b != null) {
            LibAnimationView libAnimationView = (LibAnimationView) this.b.get();
            if (libAnimationView != null) {
                libAnimationView.removeAllViews();
            }
            this.b.clear();
        }
        this.b = null;
        this.f20794a.clear();
    }

    private static class MyLibAnimationLooper extends LibAnimationLooper {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<LibAnimationComposition> f20795a;

        public MyLibAnimationLooper(LibAnimationComposition libAnimationComposition, int i, int i2, int i3, int i4) {
            this.f20795a = new WeakReference<>(libAnimationComposition);
            a(i, i2, i3, i4);
        }

        /* access modifiers changed from: protected */
        public void a() {
            LibAnimationComposition libAnimationComposition = (LibAnimationComposition) this.f20795a.get();
            if (libAnimationComposition != null && libAnimationComposition.i != null) {
                LibAnimation.a("composition end");
                LibAnimationLooper unused = libAnimationComposition.i = null;
                for (LibAnimationLayer a2 : libAnimationComposition.f20794a) {
                    a2.a(libAnimationComposition);
                }
                if (libAnimationComposition.f) {
                    libAnimationComposition.j();
                }
            }
        }

        /* access modifiers changed from: protected */
        public void b() {
            LibAnimationComposition libAnimationComposition = (LibAnimationComposition) this.f20795a.get();
            if (libAnimationComposition != null && libAnimationComposition.f20794a != null) {
                LibAnimation.a("composition begin");
                for (LibAnimationLayer a2 : libAnimationComposition.f20794a) {
                    a2.a((LibAnimationLooper) this);
                }
            }
        }
    }

    public void j() {
        if (this.b == null || this.b.get() == null) {
            i();
        } else if (this.i == null) {
            LibAnimation.a("composition start");
            this.i = new MyLibAnimationLooper(this, this.c.f20796a, 1, this.c.b, this.f20794a.size() * 2);
            if (this.i.g()) {
                this.i = null;
            }
        }
    }
}
