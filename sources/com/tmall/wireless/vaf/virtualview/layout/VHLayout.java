package com.tmall.wireless.vaf.virtualview.layout;

import android.util.Log;
import android.view.View;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.Helper.RtlHelper;
import com.tmall.wireless.vaf.virtualview.core.Layout;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;

public class VHLayout extends Layout {
    private static final String ak = "VHLayout_TMTEST";
    public int ah = 1;
    protected int ai;
    protected int aj;

    public VHLayout(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
    }

    /* renamed from: an */
    public Params a() {
        return new Params();
    }

    private int am() {
        if (this.ai <= 0) {
            this.ai = 0;
            int size = this.f9381a.size();
            for (int i = 0; i < size; i++) {
                this.ai += ((ViewBase) this.f9381a.get(i)).af();
            }
        }
        return this.ai;
    }

    private int ao() {
        if (this.aj <= 0) {
            this.aj = 0;
            int size = this.f9381a.size();
            for (int i = 0; i < size; i++) {
                this.aj += ((ViewBase) this.f9381a.get(i)).ag();
            }
        }
        return this.aj;
    }

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9 = 0;
        switch (this.ah) {
            case 0:
                if ((this.N & 8) != 0) {
                    i5 = i2 + this.L + this.o;
                } else if ((this.N & 32) != 0) {
                    i5 = ((i4 + i2) - ao()) >> 1;
                } else {
                    i5 = ((i4 - ao()) - this.M) - this.o;
                }
                int size = this.f9381a.size();
                while (i9 < size) {
                    ViewBase viewBase = (ViewBase) this.f9381a.get(i9);
                    if (!viewBase.I()) {
                        Params params = (Params) viewBase.ae();
                        int comMeasuredWidth = viewBase.getComMeasuredWidth();
                        int comMeasuredHeight = viewBase.getComMeasuredHeight();
                        int i10 = i5 + params.h;
                        if ((params.m & 4) != 0) {
                            i6 = ((i3 + i) - comMeasuredWidth) >> 1;
                        } else if ((params.m & 2) != 0) {
                            i6 = (((i3 - this.K) - this.o) - params.f) - comMeasuredWidth;
                        } else {
                            i6 = this.J + i + this.o + params.d;
                        }
                        int a2 = RtlHelper.a(ak(), i, L(), i6, comMeasuredWidth);
                        viewBase.comLayout(a2, i10, comMeasuredWidth + a2, i10 + comMeasuredHeight);
                        i5 = i10 + comMeasuredHeight + params.j;
                    }
                    i9++;
                }
                return;
            case 1:
                if ((this.N & 1) != 0) {
                    i7 = this.J + i + this.o;
                } else if ((this.N & 4) != 0) {
                    i7 = ((i3 - i) - am()) >> 1;
                } else {
                    i7 = ((i3 - am()) - this.K) - this.o;
                }
                int size2 = this.f9381a.size();
                while (i9 < size2) {
                    ViewBase viewBase2 = (ViewBase) this.f9381a.get(i9);
                    if (!viewBase2.I()) {
                        Params params2 = (Params) viewBase2.ae();
                        int comMeasuredWidth2 = viewBase2.getComMeasuredWidth();
                        int comMeasuredHeight2 = viewBase2.getComMeasuredHeight();
                        int i11 = i7 + params2.d;
                        if ((params2.m & 32) != 0) {
                            i8 = ((i4 + i2) - comMeasuredHeight2) >> 1;
                        } else if ((params2.m & 16) != 0) {
                            i8 = (((i4 - comMeasuredHeight2) - this.M) - this.o) - params2.j;
                        } else {
                            i8 = this.L + i2 + this.o + params2.h;
                        }
                        int a3 = RtlHelper.a(ak(), i, L(), i11, comMeasuredWidth2);
                        viewBase2.comLayout(a3, i8, a3 + comMeasuredWidth2, comMeasuredHeight2 + i8);
                        i7 = i11 + comMeasuredWidth2 + params2.f;
                    }
                    i9++;
                }
                return;
            default:
                return;
        }
    }

    public void onComMeasure(int i, int i2) {
        this.ai = 0;
        this.aj = 0;
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
                i(i, i2);
                return;
            case 1:
                j(i, i2);
                return;
            default:
                return;
        }
    }

    private final void i(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size3 = this.f9381a.size();
        boolean z = false;
        for (int i3 = 0; i3 < size3; i3++) {
            ViewBase viewBase = (ViewBase) this.f9381a.get(i3);
            if (!viewBase.I()) {
                Params params = (Params) viewBase.ae();
                if (1073741824 != mode && -1 == params.f9382a) {
                    z = true;
                }
                a(viewBase, i, i2);
            }
        }
        d(k(mode, size), l(mode2, size2));
        if (z) {
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getComMeasuredWidth(), 1073741824);
            int size4 = this.f9381a.size();
            for (int i4 = 0; i4 < size4; i4++) {
                ViewBase viewBase2 = (ViewBase) this.f9381a.get(i4);
                if (!viewBase2.I() && -1 == viewBase2.ae().f9382a) {
                    a(viewBase2, makeMeasureSpec, i2);
                }
            }
        }
    }

    private void j(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size3 = this.f9381a.size();
        boolean z = false;
        int i3 = 0;
        for (int i4 = 0; i4 < size3; i4++) {
            ViewBase viewBase = (ViewBase) this.f9381a.get(i4);
            if (!viewBase.I()) {
                Layout.Params ae = viewBase.ae();
                if (1073741824 != mode2 && -1 == ae.b) {
                    z = true;
                }
                if (mode != 0) {
                    a(viewBase, View.MeasureSpec.makeMeasureSpec(size - i3, 1073741824), i2);
                } else {
                    a(viewBase, i, i2);
                }
                i3 += viewBase.af();
            }
        }
        d(k(mode, size), l(mode2, size2));
        if (z) {
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getComMeasuredHeight(), 1073741824);
            int size4 = this.f9381a.size();
            for (int i5 = 0; i5 < size4; i5++) {
                ViewBase viewBase2 = (ViewBase) this.f9381a.get(i5);
                if (!viewBase2.I() && -1 == viewBase2.ae().b) {
                    a(viewBase2, i, makeMeasureSpec);
                }
            }
        }
    }

    private int k(int i, int i2) {
        int af;
        if (Integer.MIN_VALUE == i) {
            int i3 = 0;
            if (1 == this.ah) {
                int size = this.f9381a.size();
                int i4 = 0;
                while (i3 < size) {
                    ViewBase viewBase = (ViewBase) this.f9381a.get(i3);
                    if (!viewBase.I()) {
                        i4 += viewBase.af();
                    }
                    i3++;
                }
                this.ai = i4;
                i3 = i4 + this.J + this.K + (this.o << 1);
            } else if (this.ah == 0) {
                int size2 = this.f9381a.size();
                int i5 = 0;
                while (i3 < size2) {
                    ViewBase viewBase2 = (ViewBase) this.f9381a.get(i3);
                    if (!viewBase2.I() && (af = viewBase2.af()) > i5) {
                        i5 = af;
                    }
                    i3++;
                }
                this.ai = i5;
                i3 = i5 + this.J + this.K + (this.o << 1);
            }
            return Math.min(i2, i3);
        } else if (1073741824 == i) {
            return i2;
        } else {
            Log.e(ak, "getRealWidth error mode:" + i);
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
                i3 = i4 + this.L + this.M + (this.o << 1);
            } else if (this.ah == 0) {
                int size2 = this.f9381a.size();
                int i5 = 0;
                while (i3 < size2) {
                    ViewBase viewBase2 = (ViewBase) this.f9381a.get(i3);
                    if (!viewBase2.I()) {
                        i5 += viewBase2.ag();
                    }
                    i3++;
                }
                this.aj = i5;
                i3 = i5 + this.L + this.M + (this.o << 1);
            }
            return Math.min(i2, i3);
        } else if (1073741824 == i) {
            return i2;
        } else {
            if (1 == this.ah) {
                int size3 = this.f9381a.size();
                int i6 = 0;
                while (i3 < size3) {
                    ViewBase viewBase3 = (ViewBase) this.f9381a.get(i3);
                    if (!viewBase3.I() && (ag = viewBase3.ag()) > i6) {
                        i6 = ag;
                    }
                    i3++;
                }
                this.aj = i6;
                return i6 + this.L + this.M + (this.o << 1);
            } else if (this.ah != 0) {
                return 0;
            } else {
                int size4 = this.f9381a.size();
                int i7 = 0;
                while (i3 < size4) {
                    ViewBase viewBase4 = (ViewBase) this.f9381a.get(i3);
                    if (!viewBase4.I()) {
                        i7 += viewBase4.ag();
                    }
                    i3++;
                }
                this.aj = i7;
                return i7 + this.L + this.M + (this.o << 1);
            }
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
        public int m;

        public boolean c(int i, int i2) {
            boolean c = super.c(i, i2);
            if (c) {
                return c;
            }
            if (i != 516361156) {
                return false;
            }
            this.m = i2;
            return true;
        }
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new VHLayout(vafContext, viewCache);
        }
    }
}
