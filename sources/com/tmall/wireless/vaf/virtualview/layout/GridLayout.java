package com.tmall.wireless.vaf.virtualview.layout;

import android.util.Log;
import android.view.View;
import com.libra.Utils;
import com.libra.virtualview.common.StringBase;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.Helper.RtlHelper;
import com.tmall.wireless.vaf.virtualview.core.Layout;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;

public class GridLayout extends Layout {
    private static final String am = "GridLayout_TMTEST";
    protected int ah = 1;
    protected int ai;
    protected int aj = -1;
    protected int ak = 0;
    protected int al = 0;

    public GridLayout(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
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
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        this.ai = (size - ((this.J + this.K) + (this.ak * (this.ah - 1)))) / this.ah;
        int size3 = this.f9381a.size();
        for (int i3 = 0; i3 < size3; i3++) {
            ViewBase viewBase = (ViewBase) this.f9381a.get(i3);
            if (!viewBase.I()) {
                if (this.aj > 0) {
                    viewBase.measureComponent(View.MeasureSpec.makeMeasureSpec(this.ai, 1073741824), View.MeasureSpec.makeMeasureSpec(this.aj, 1073741824));
                } else {
                    Layout.Params ae = viewBase.ae();
                    viewBase.measureComponent(View.MeasureSpec.makeMeasureSpec(this.ai, 1073741824), Layout.a(i2, this.J + this.K + (this.o << 1) + ae.d + ae.f, ae.b));
                }
            }
        }
        d(i(mode, size), j(mode2, size2));
    }

    private int i(int i, int i2) {
        if (Integer.MIN_VALUE == i) {
            Layout.Params params = this.Z;
            int i3 = this.J + this.K;
            int size = this.f9381a.size();
            int i4 = 0;
            for (int i5 = 0; i5 < size; i5++) {
                i3 += ((ViewBase) this.f9381a.get(i5)).af();
                i4++;
                if (i4 >= this.ah) {
                    break;
                }
                i3 += this.ak;
            }
            return Math.min(i2, i3);
        } else if (1073741824 == i) {
            return i2;
        } else {
            Log.e(am, "getRealWidth error mode:" + i);
            return i2;
        }
    }

    private int j(int i, int i2) {
        int i3;
        if (i != Integer.MIN_VALUE && i != 0) {
            return i2;
        }
        if (this.f9381a.size() > 0) {
            int size = this.f9381a.size();
            int i4 = (size / this.ah) + (size % this.ah > 0 ? 1 : 0);
            i3 = this.aj > 0 ? (this.aj * i4) + this.L + this.M + ((i4 - 1) * this.al) : (((ViewBase) this.f9381a.get(0)).getComMeasuredHeight() * i4) + this.L + this.M + ((i4 - 1) * this.al);
        } else {
            i3 = 0;
        }
        return Integer.MIN_VALUE == i ? Math.min(i2, i3) : i3;
    }

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6 = i;
        if (this.f9381a.size() > 0) {
            Layout.Params params = this.Z;
            int i7 = this.J + i6;
            int i8 = i2 + this.L;
            int size = this.f9381a.size();
            ViewBase viewBase = (ViewBase) this.f9381a.get(0);
            getComMeasuredWidth();
            int comMeasuredWidth = viewBase.getComMeasuredWidth();
            int comMeasuredHeight = viewBase.getComMeasuredHeight();
            int i9 = (size / this.ah) + (size % this.ah > 0 ? 1 : 0);
            int i10 = i8;
            int i11 = 0;
            int i12 = 0;
            while (i11 < i9) {
                int i13 = i7;
                int i14 = i12;
                int i15 = 0;
                while (i15 < this.ah && i14 < size) {
                    int i16 = i14 + 1;
                    ViewBase viewBase2 = (ViewBase) this.f9381a.get(i14);
                    if (!viewBase2.I()) {
                        int a2 = RtlHelper.a(ak(), i6, L(), i13, comMeasuredWidth);
                        viewBase2.comLayout(a2, i10, a2 + comMeasuredWidth, i10 + comMeasuredHeight);
                        i13 += this.ak + comMeasuredWidth;
                    }
                    i15++;
                    i14 = i16;
                }
                if (this.aj > 0) {
                    i5 = this.aj + this.al;
                } else {
                    i5 = this.al + comMeasuredHeight;
                }
                i10 += i5;
                i11++;
                i12 = i14;
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, float f) {
        boolean b = super.b(i, f);
        if (b) {
            return b;
        }
        if (i == 196203191) {
            this.al = Utils.b((double) f);
            return true;
        } else if (i == 1671241242) {
            this.aj = Utils.b((double) Math.round(f));
            return true;
        } else if (i != 2129234981) {
            return false;
        } else {
            this.ak = Utils.b((double) f);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean c(int i, int i2) {
        boolean c = super.c(i, i2);
        if (c) {
            return c;
        }
        if (i == -669528209) {
            this.ah = i2;
            return true;
        } else if (i == 196203191) {
            this.al = Utils.b((double) i2);
            return true;
        } else if (i == 1671241242) {
            this.aj = Utils.b((double) i2);
            return true;
        } else if (i != 2129234981) {
            return false;
        } else {
            this.ak = Utils.b((double) i2);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, String str) {
        if (i == 196203191) {
            this.c.a(this, StringBase.aS, str, 1);
            return true;
        } else if (i != 2129234981) {
            return super.a(i, str);
        } else {
            this.c.a(this, StringBase.aR, str, 1);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, float f) {
        boolean a2 = super.a(i, f);
        if (a2) {
            return a2;
        }
        if (i == 196203191) {
            this.al = Utils.a((double) f);
            return true;
        } else if (i == 1671241242) {
            this.aj = Utils.a((double) f);
            return true;
        } else if (i != 2129234981) {
            return false;
        } else {
            this.ak = Utils.a((double) f);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, int i2) {
        boolean b = super.b(i, i2);
        if (b) {
            return b;
        }
        if (i == 196203191) {
            this.al = Utils.a((double) i2);
            return true;
        } else if (i == 1671241242) {
            this.aj = Utils.a((double) i2);
            return true;
        } else if (i != 2129234981) {
            return false;
        } else {
            this.ak = Utils.a((double) i2);
            return true;
        }
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new GridLayout(vafContext, viewCache);
        }
    }
}
