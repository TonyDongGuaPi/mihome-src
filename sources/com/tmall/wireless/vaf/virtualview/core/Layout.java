package com.tmall.wireless.vaf.virtualview.core;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.libra.Utils;
import com.libra.expr.common.ExprCode;
import com.libra.virtualview.common.StringBase;
import com.tmall.wireless.vaf.framework.VafContext;
import java.util.ArrayList;
import java.util.List;

public abstract class Layout extends ViewBase {
    private static final String ah = "Layout_TMTEST";

    /* renamed from: a  reason: collision with root package name */
    protected List<ViewBase> f9381a = new ArrayList();

    public Layout(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
    }

    public Params a() {
        return new Params();
    }

    public final List<ViewBase> b() {
        return this.f9381a;
    }

    public void c() {
        super.c();
        int size = this.f9381a.size();
        for (int i = 0; i < size; i++) {
            this.f9381a.get(i).c();
        }
    }

    public void d() {
        super.d();
        int size = this.f9381a.size();
        for (int i = 0; i < size; i++) {
            this.f9381a.get(i).d();
        }
        this.f9381a.clear();
    }

    public ViewBase a(int i) {
        ViewBase a2 = super.a(i);
        if (a2 == null) {
            int size = this.f9381a.size();
            for (int i2 = 0; i2 < size; i2++) {
                a2 = this.f9381a.get(i2).a(i);
                if (a2 != null) {
                    break;
                }
            }
        }
        return a2;
    }

    public ViewBase a(String str) {
        ViewBase a2 = super.a(str);
        if (a2 == null) {
            int size = this.f9381a.size();
            for (int i = 0; i < size; i++) {
                a2 = this.f9381a.get(i).a(str);
                if (a2 != null) {
                    break;
                }
            }
        }
        return a2;
    }

    public boolean a(int i, int i2) {
        boolean z;
        int size = this.f9381a.size() - 1;
        while (true) {
            if (size < 0) {
                z = false;
                break;
            }
            ViewBase viewBase = this.f9381a.get(size);
            int S = viewBase.S();
            int T = viewBase.T();
            int comMeasuredWidth = viewBase.getComMeasuredWidth();
            int comMeasuredHeight = viewBase.getComMeasuredHeight();
            if (i >= S && i < S + comMeasuredWidth && i2 >= T && i2 <= T + comMeasuredHeight) {
                z = viewBase.a(i, i2);
                break;
            }
            size--;
        }
        return !z ? super.a(i, i2) : z;
    }

    public boolean a(int i, int i2, boolean z) {
        boolean z2;
        int size = this.f9381a.size() - 1;
        while (true) {
            if (size < 0) {
                z2 = false;
                break;
            }
            ViewBase viewBase = this.f9381a.get(size);
            int S = viewBase.S();
            int T = viewBase.T();
            int comMeasuredWidth = viewBase.getComMeasuredWidth();
            int comMeasuredHeight = viewBase.getComMeasuredHeight();
            if (i >= S && i < S + comMeasuredWidth && i2 >= T && i2 <= T + comMeasuredHeight) {
                z2 = viewBase.a(i, i2, z);
                break;
            }
            size--;
        }
        return !z2 ? super.a(i, i2, z) : z2;
    }

    public ViewBase b(int i) {
        if (i <= 0 || i >= this.f9381a.size()) {
            return null;
        }
        return this.f9381a.get(i);
    }

    public void e() {
        super.e();
        int size = this.f9381a.size();
        for (int i = 0; i < size; i++) {
            this.f9381a.get(i).e();
        }
    }

    public void a(Canvas canvas) {
        super.a(canvas);
        int size = this.f9381a.size();
        for (int i = 0; i < size; i++) {
            ViewBase viewBase = this.f9381a.get(i);
            if (viewBase.Y()) {
                viewBase.a(canvas);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(Canvas canvas) {
        super.b(canvas);
        c(canvas);
    }

    public void f() {
        super.f();
        if (this.j == null) {
            this.j = new Paint();
            this.j.setStyle(Paint.Style.FILL);
            this.j.setAntiAlias(true);
        }
    }

    /* access modifiers changed from: protected */
    public boolean g() {
        boolean g = super.g();
        int size = this.f9381a.size();
        for (int i = 0; i < size; i++) {
            this.f9381a.get(i).g();
        }
        return g;
    }

    public void a(ViewBase viewBase) {
        this.f9381a.add(viewBase);
        viewBase.X = this;
        viewBase.g();
    }

    public boolean b(ViewBase viewBase) {
        if (!this.f9381a.contains(viewBase)) {
            return false;
        }
        this.f9381a.remove(viewBase);
        viewBase.X = null;
        return true;
    }

    /* access modifiers changed from: protected */
    public void a(ViewBase viewBase, int i, int i2) {
        Params ae = viewBase.ae();
        viewBase.measureComponent(a(i, this.J + this.K + (this.o << 1) + ae.d + ae.f, ae.f9382a), a(i2, this.L + this.M + (this.o << 1) + ae.h + ae.j, ae.b));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0036, code lost:
        if (r7 == -2) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0021, code lost:
        if (r7 == -2) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002a, code lost:
        if (r7 == -2) goto L_0x003a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(int r5, int r6, int r7) {
        /*
            int r0 = android.view.View.MeasureSpec.getMode(r5)
            int r5 = android.view.View.MeasureSpec.getSize(r5)
            int r5 = r5 - r6
            r6 = 0
            int r5 = java.lang.Math.max(r6, r5)
            r1 = -2
            r2 = -1
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = 1073741824(0x40000000, float:2.0)
            if (r0 == r3) goto L_0x002d
            if (r0 == 0) goto L_0x0024
            if (r0 == r4) goto L_0x001b
            goto L_0x0039
        L_0x001b:
            if (r7 < 0) goto L_0x001e
            goto L_0x002f
        L_0x001e:
            if (r7 != r2) goto L_0x0021
            goto L_0x003b
        L_0x0021:
            if (r7 != r1) goto L_0x0039
            goto L_0x0033
        L_0x0024:
            if (r7 < 0) goto L_0x0027
            goto L_0x002f
        L_0x0027:
            if (r7 != r2) goto L_0x002a
        L_0x0029:
            goto L_0x003a
        L_0x002a:
            if (r7 != r1) goto L_0x0039
            goto L_0x0029
        L_0x002d:
            if (r7 < 0) goto L_0x0031
        L_0x002f:
            r5 = r7
            goto L_0x003b
        L_0x0031:
            if (r7 != r2) goto L_0x0036
        L_0x0033:
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x003b
        L_0x0036:
            if (r7 != r1) goto L_0x0039
            goto L_0x0033
        L_0x0039:
            r5 = 0
        L_0x003a:
            r4 = 0
        L_0x003b:
            int r5 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tmall.wireless.vaf.virtualview.core.Layout.a(int, int, int):int");
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, float f) {
        boolean a2 = super.a(i, f);
        if (!a2) {
            return false;
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, float f) {
        boolean b = super.b(i, f);
        if (!b) {
            return false;
        }
        return b;
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, int i2) {
        boolean b = super.b(i, i2);
        if (!b) {
            return false;
        }
        return b;
    }

    /* access modifiers changed from: protected */
    public boolean c(int i, int i2) {
        boolean c = super.c(i, i2);
        if (!c) {
            return false;
        }
        return c;
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, String str) {
        boolean a2 = super.a(i, str);
        if (!a2) {
            return false;
        }
        return a2;
    }

    public static class Params {

        /* renamed from: a  reason: collision with root package name */
        public int f9382a = 0;
        public int b = 0;
        public int c;
        public int d = 0;
        protected boolean e;
        public int f = 0;
        protected boolean g;
        public int h = 0;
        protected boolean i;
        public int j = 0;
        protected boolean k;

        public boolean a(int i2, int i3) {
            return false;
        }

        public boolean a(int i2, ExprCode exprCode) {
            return false;
        }

        public boolean a(int i2, float f2) {
            switch (i2) {
                case StringBase.w:
                    this.h = Utils.a((double) f2);
                    this.i = true;
                    return true;
                case StringBase.v:
                    this.f = Utils.a((double) f2);
                    this.g = true;
                    return true;
                case StringBase.u:
                    this.d = Utils.a((double) f2);
                    this.e = true;
                    return true;
                case StringBase.x:
                    this.j = Utils.a((double) f2);
                    this.k = true;
                    return true;
                case StringBase.p:
                    this.b = Utils.a((double) f2);
                    return true;
                case StringBase.cg:
                    this.c = Utils.a((double) f2);
                    if (!this.e) {
                        this.d = this.c;
                    }
                    if (!this.g) {
                        this.f = this.c;
                    }
                    if (!this.i) {
                        this.h = this.c;
                    }
                    if (this.k) {
                        return true;
                    }
                    this.j = this.c;
                    return true;
                case StringBase.o:
                    this.f9382a = Utils.a((double) f2);
                    return true;
                default:
                    return false;
            }
        }

        public boolean b(int i2, float f2) {
            switch (i2) {
                case StringBase.w:
                    this.h = Utils.b((double) f2);
                    this.i = true;
                    return true;
                case StringBase.v:
                    this.f = Utils.b((double) f2);
                    this.g = true;
                    return true;
                case StringBase.u:
                    this.d = Utils.b((double) f2);
                    this.e = true;
                    return true;
                case StringBase.x:
                    this.j = Utils.b((double) f2);
                    this.k = true;
                    return true;
                case StringBase.p:
                    if (f2 > 0.0f) {
                        this.b = Utils.b((double) f2);
                        return true;
                    }
                    this.b = (int) f2;
                    return true;
                case StringBase.cg:
                    this.c = Utils.b((double) f2);
                    if (!this.e) {
                        this.d = this.c;
                    }
                    if (!this.g) {
                        this.f = this.c;
                    }
                    if (!this.i) {
                        this.h = this.c;
                    }
                    if (this.k) {
                        return true;
                    }
                    this.j = this.c;
                    return true;
                case StringBase.o:
                    if (f2 > 0.0f) {
                        this.f9382a = Utils.b((double) f2);
                        return true;
                    }
                    this.f9382a = (int) f2;
                    return true;
                default:
                    return false;
            }
        }

        public boolean b(int i2, int i3) {
            switch (i2) {
                case StringBase.w:
                    this.h = Utils.a((double) i3);
                    this.i = true;
                    return true;
                case StringBase.v:
                    this.f = Utils.a((double) i3);
                    this.g = true;
                    return true;
                case StringBase.u:
                    this.d = Utils.a((double) i3);
                    this.e = true;
                    return true;
                case StringBase.x:
                    this.j = Utils.a((double) i3);
                    this.k = true;
                    return true;
                case StringBase.p:
                    this.b = Utils.a((double) i3);
                    return true;
                case StringBase.cg:
                    this.c = Utils.a((double) i3);
                    if (!this.e) {
                        this.d = this.c;
                    }
                    if (!this.g) {
                        this.f = this.c;
                    }
                    if (!this.i) {
                        this.h = this.c;
                    }
                    if (this.k) {
                        return true;
                    }
                    this.j = this.c;
                    return true;
                case StringBase.o:
                    this.f9382a = Utils.a((double) i3);
                    return true;
                default:
                    return false;
            }
        }

        public boolean c(int i2, int i3) {
            switch (i2) {
                case StringBase.w:
                    this.h = Utils.b((double) i3);
                    this.i = true;
                    return true;
                case StringBase.v:
                    this.f = Utils.b((double) i3);
                    this.g = true;
                    return true;
                case StringBase.u:
                    this.d = Utils.b((double) i3);
                    this.e = true;
                    return true;
                case StringBase.x:
                    this.j = Utils.b((double) i3);
                    this.k = true;
                    return true;
                case StringBase.p:
                    if (i3 > 0) {
                        this.b = Utils.b((double) i3);
                        return true;
                    }
                    this.b = i3;
                    return true;
                case StringBase.cg:
                    this.c = Utils.b((double) i3);
                    if (!this.e) {
                        this.d = this.c;
                    }
                    if (!this.g) {
                        this.f = this.c;
                    }
                    if (!this.i) {
                        this.h = this.c;
                    }
                    if (this.k) {
                        return true;
                    }
                    this.j = this.c;
                    return true;
                case StringBase.o:
                    if (i3 > 0) {
                        this.f9382a = Utils.b((double) i3);
                        return true;
                    }
                    this.f9382a = i3;
                    return true;
                default:
                    return false;
            }
        }
    }
}
