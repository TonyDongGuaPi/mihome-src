package com.tmall.wireless.vaf.virtualview.layout;

import android.util.Log;
import android.view.View;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.Helper.RtlHelper;
import com.tmall.wireless.vaf.virtualview.core.Layout;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;

public class RatioLayout extends Layout {
    private static final String am = "RatioLayout_TMTEST";
    protected int ah = 1;
    protected int ai = 0;
    protected int aj = 0;
    protected int ak;
    protected int al;

    public RatioLayout(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
    }

    /* renamed from: am */
    public Params a() {
        return new Params();
    }

    public void onComMeasure(int i, int i2) {
        if (this.G > 0) {
            switch (this.G) {
                case 1:
                    if (1073741824 == View.MeasureSpec.getMode(i)) {
                        i2 = View.MeasureSpec.makeMeasureSpec((int) ((((float) View.MeasureSpec.getSize(i)) * this.I) / this.H), 1073741824);
                        break;
                    }
                    break;
                case 2:
                    if (1073741824 == View.MeasureSpec.getMode(i2)) {
                        i = View.MeasureSpec.makeMeasureSpec((int) ((((float) View.MeasureSpec.getSize(i2)) * this.H) / this.I), 1073741824);
                        break;
                    }
                    break;
            }
        }
        switch (this.ah) {
            case 0:
                j(i, i2);
                return;
            case 1:
                i(i, i2);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void b(ViewBase viewBase, int i, int i2) {
        int i3;
        Params params = (Params) viewBase.ae();
        int a2 = a(i2, this.L + this.M + (this.o << 1) + params.h + params.j, params.b);
        if (params.l > 0.0f) {
            i3 = a(i, this.J + this.K + (this.o << 1), params.f9382a, params.l);
        } else {
            i3 = a(i, this.J + this.K + (this.o << 1) + params.d + params.f, params.f9382a);
        }
        viewBase.measureComponent(i3, a2);
    }

    /* access modifiers changed from: protected */
    public void c(ViewBase viewBase, int i, int i2) {
        int i3;
        Params params = (Params) viewBase.ae();
        int a2 = a(i, this.J + this.K + (this.o << 1) + params.d + params.f, params.f9382a);
        if (params.l > 0.0f) {
            i3 = a(i2, this.L + this.M + (this.o << 1), params.b, params.l);
        } else {
            i3 = a(i2, this.L + this.M + (this.o << 1) + params.h + params.j, params.b);
        }
        viewBase.measureComponent(a2, i3);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002f, code lost:
        if (r6 >= 0) goto L_0x0034;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(int r4, int r5, int r6, float r7) {
        /*
            r3 = this;
            int r0 = android.view.View.MeasureSpec.getMode(r4)
            int r4 = android.view.View.MeasureSpec.getSize(r4)
            int r4 = r4 - r5
            int r5 = r3.al
            int r4 = r4 - r5
            r5 = 0
            int r4 = java.lang.Math.max(r5, r4)
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = 1073741824(0x40000000, float:2.0)
            if (r0 == r1) goto L_0x0032
            if (r0 == 0) goto L_0x0032
            if (r0 == r2) goto L_0x001c
            goto L_0x0032
        L_0x001c:
            r0 = 0
            int r0 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x002f
            float r4 = (float) r4
            float r7 = r7 * r4
            int r4 = r3.ak
            float r4 = (float) r4
            float r7 = r7 / r4
            int r4 = (int) r7
            if (r4 >= 0) goto L_0x002d
            r6 = 0
            goto L_0x0034
        L_0x002d:
            r6 = r4
            goto L_0x0034
        L_0x002f:
            if (r6 < 0) goto L_0x0032
            goto L_0x0034
        L_0x0032:
            r6 = 0
            r2 = 0
        L_0x0034:
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r6, r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tmall.wireless.vaf.virtualview.layout.RatioLayout.a(int, int, int, float):int");
    }

    private void i(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        this.al = 0;
        an();
        int size3 = this.f9381a.size();
        boolean z = false;
        for (int i3 = 0; i3 < size3; i3++) {
            ViewBase viewBase = (ViewBase) this.f9381a.get(i3);
            if (!viewBase.I()) {
                Params params = (Params) viewBase.ae();
                if ((1073741824 != mode2 && -1 == params.b) || params.l > 0.0f) {
                    z = true;
                }
                b(viewBase, i, i2);
                if (params.l <= 0.0f) {
                    this.al += viewBase.af();
                } else {
                    this.al += params.d + params.f;
                }
            }
        }
        d(k(mode, size), l(mode2, size2));
        if (z) {
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getComMeasuredWidth(), 1073741824);
            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getComMeasuredHeight(), 1073741824);
            int size4 = this.f9381a.size();
            for (int i4 = 0; i4 < size4; i4++) {
                ViewBase viewBase2 = (ViewBase) this.f9381a.get(i4);
                if (!viewBase2.I()) {
                    Params params2 = (Params) viewBase2.ae();
                    if (-1 == params2.b || params2.l > 0.0f) {
                        b(viewBase2, makeMeasureSpec, makeMeasureSpec2);
                    }
                }
            }
        }
    }

    private void an() {
        this.ak = 0;
        int size = this.f9381a.size();
        for (int i = 0; i < size; i++) {
            ViewBase viewBase = (ViewBase) this.f9381a.get(i);
            if (!viewBase.I()) {
                this.ak = (int) (((float) this.ak) + ((Params) viewBase.ae()).l);
            }
        }
    }

    private final void j(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        this.al = 0;
        an();
        int size3 = this.f9381a.size();
        boolean z = false;
        for (int i3 = 0; i3 < size3; i3++) {
            ViewBase viewBase = (ViewBase) this.f9381a.get(i3);
            if (!viewBase.I()) {
                Params params = (Params) viewBase.ae();
                if ((1073741824 != mode && -1 == params.f9382a) || params.l > 0.0f) {
                    z = true;
                }
                c(viewBase, i, i2);
                if (params.l <= 0.0f) {
                    this.al += viewBase.ag();
                } else {
                    this.al += params.h + params.j;
                }
            }
        }
        d(k(mode, size), l(mode2, size2));
        if (z) {
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getComMeasuredWidth(), 1073741824);
            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getComMeasuredHeight(), 1073741824);
            int size4 = this.f9381a.size();
            for (int i4 = 0; i4 < size4; i4++) {
                ViewBase viewBase2 = (ViewBase) this.f9381a.get(i4);
                if (!viewBase2.I()) {
                    Params params2 = (Params) viewBase2.ae();
                    if (-1 == params2.f9382a || params2.l > 0.0f) {
                        c(viewBase2, makeMeasureSpec, makeMeasureSpec2);
                    }
                }
            }
        }
    }

    private int k(int i, int i2) {
        int af;
        if (Integer.MIN_VALUE == i) {
            if (1 == this.ah || this.ah != 0) {
                return i2;
            }
            int size = this.f9381a.size();
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                ViewBase viewBase = (ViewBase) this.f9381a.get(i4);
                if (!viewBase.I() && (af = viewBase.af()) > i3) {
                    i3 = af;
                }
            }
            this.ai = i3;
            return Math.min(i2, i3 + this.J + this.K + (this.o << 1));
        } else if (1073741824 == i) {
            return i2;
        } else {
            Log.e(am, "getRealWidth error mode:" + i);
            return i2;
        }
    }

    private int l(int i, int i2) {
        int ag;
        int ag2;
        int i3 = 0;
        if (Integer.MIN_VALUE == i) {
            if (1 == this.ah) {
                int size = this.f9381a.size();
                int i4 = 0;
                while (i3 < size) {
                    ViewBase viewBase = (ViewBase) this.f9381a.get(i3);
                    if (!viewBase.I() && (ag2 = viewBase.ag()) > i4) {
                        i4 = ag2;
                    }
                    i3++;
                }
                this.aj = i4;
                return Math.min(i2, i4 + this.L + this.M + (this.o << 1));
            }
            int i5 = this.ah;
            return i2;
        } else if (1073741824 == i) {
            return i2;
        } else {
            if (1 == this.ah) {
                int size2 = this.f9381a.size();
                int i6 = 0;
                while (i3 < size2) {
                    ViewBase viewBase2 = (ViewBase) this.f9381a.get(i3);
                    if (!viewBase2.I() && (ag = viewBase2.ag()) > i6) {
                        i6 = ag;
                    }
                    i3++;
                }
                this.aj = i6;
                return i6 + this.L + this.M + (this.o << 1);
            } else if (this.ah != 0) {
                return i2;
            } else {
                int size3 = this.f9381a.size();
                int i7 = 0;
                while (i3 < size3) {
                    ViewBase viewBase3 = (ViewBase) this.f9381a.get(i3);
                    if (!viewBase3.I()) {
                        i7 += viewBase3.ag();
                    }
                    i3++;
                }
                return i7 + this.L + this.M + (this.o << 1);
            }
        }
    }

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = 0;
        switch (this.ah) {
            case 0:
                int i8 = i2 + this.L + this.o;
                int size = this.f9381a.size();
                while (i7 < size) {
                    ViewBase viewBase = (ViewBase) this.f9381a.get(i7);
                    if (!viewBase.I()) {
                        Params params = (Params) viewBase.ae();
                        int comMeasuredWidth = viewBase.getComMeasuredWidth();
                        int comMeasuredHeight = viewBase.getComMeasuredHeight();
                        int i9 = i8 + params.h;
                        if ((params.m & 4) != 0) {
                            i5 = ((i3 + i) - comMeasuredWidth) >> 1;
                        } else if ((params.m & 2) != 0) {
                            i5 = (((i3 - this.K) - this.o) - params.f) - comMeasuredWidth;
                        } else {
                            i5 = this.J + i + this.o + params.d;
                        }
                        int a2 = RtlHelper.a(ak(), i, L(), i5, comMeasuredWidth);
                        viewBase.comLayout(a2, i9, comMeasuredWidth + a2, i9 + comMeasuredHeight);
                        i8 = i9 + comMeasuredHeight + params.j;
                    }
                    i7++;
                }
                return;
            case 1:
                int i10 = this.J + i + this.o;
                int size2 = this.f9381a.size();
                while (i7 < size2) {
                    ViewBase viewBase2 = (ViewBase) this.f9381a.get(i7);
                    if (!viewBase2.I()) {
                        Params params2 = (Params) viewBase2.ae();
                        int comMeasuredWidth2 = viewBase2.getComMeasuredWidth();
                        int comMeasuredHeight2 = viewBase2.getComMeasuredHeight();
                        int i11 = i10 + params2.d;
                        if ((params2.m & 32) != 0) {
                            i6 = ((i4 + i2) - comMeasuredHeight2) >> 1;
                        } else if ((params2.m & 16) != 0) {
                            i6 = (((i4 - comMeasuredHeight2) - this.M) - this.o) - params2.j;
                        } else {
                            i6 = this.L + i2 + this.o + params2.h;
                        }
                        int a3 = RtlHelper.a(ak(), i, L(), i11, comMeasuredWidth2);
                        viewBase2.comLayout(a3, i6, a3 + comMeasuredWidth2, comMeasuredHeight2 + i6);
                        i10 = i11 + comMeasuredWidth2 + params2.f;
                    }
                    i7++;
                }
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public boolean c(int i, int i2) {
        boolean c = super.c(i, i2);
        if (c) {
            return c;
        }
        if (i != -1439500848) {
            return false;
        }
        this.ah = i2;
        return true;
    }

    public static class Params extends Layout.Params {
        public float l = 0.0f;
        public int m;

        public boolean b(int i, float f) {
            boolean b = super.b(i, f);
            if (b) {
                return b;
            }
            if (i != 1999032065) {
                return false;
            }
            this.l = f;
            return true;
        }

        public boolean c(int i, int i2) {
            boolean c = super.c(i, i2);
            if (c) {
                return c;
            }
            if (i == 516361156) {
                this.m = i2;
                return true;
            } else if (i != 1999032065) {
                return false;
            } else {
                this.l = (float) i2;
                return true;
            }
        }
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new RatioLayout(vafContext, viewCache);
        }
    }
}
