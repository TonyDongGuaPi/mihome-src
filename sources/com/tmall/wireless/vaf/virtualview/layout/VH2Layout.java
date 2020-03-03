package com.tmall.wireless.vaf.virtualview.layout;

import android.util.Log;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.Helper.RtlHelper;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;
import com.tmall.wireless.vaf.virtualview.layout.VHLayout;

public class VH2Layout extends VHLayout {
    private static final String ak = "VH2Layout_TMTEST";

    public VH2Layout(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
    }

    /* renamed from: am */
    public Params an() {
        return new Params();
    }

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = 0;
        switch (this.ah) {
            case 0:
                int i8 = i2 + this.L;
                int i9 = i4 - this.M;
                int size = this.f9381a.size();
                int i10 = 0;
                while (i7 < size) {
                    ViewBase viewBase = (ViewBase) this.f9381a.get(i7);
                    if (!viewBase.I()) {
                        Params params = (Params) viewBase.ae();
                        int comMeasuredWidth = viewBase.getComMeasuredWidth();
                        int comMeasuredHeight = viewBase.getComMeasuredHeight();
                        if ((params.l & 2) != 0) {
                            i10 = params.h + i8;
                            i8 = params.j + comMeasuredHeight + i10;
                        } else if ((params.l & 8) != 0) {
                            i10 = i9 - (params.j + comMeasuredHeight);
                            i9 = i10 - params.h;
                        } else {
                            Log.e(ak, "onComLayout VERTICAL direction invalidate:" + params.l);
                        }
                        if ((params.m & 4) != 0) {
                            i5 = ((i3 + i) - comMeasuredWidth) >> 1;
                        } else if ((params.m & 2) != 0) {
                            i5 = ((i3 - this.K) - params.f) - comMeasuredWidth;
                        } else {
                            i5 = params.d + this.J + i;
                        }
                        int a2 = RtlHelper.a(ak(), i, L(), i5, comMeasuredWidth);
                        viewBase.comLayout(a2, i10, comMeasuredWidth + a2, comMeasuredHeight + i10);
                    }
                    i7++;
                }
                return;
            case 1:
                int i11 = this.J + i;
                int i12 = i3 - this.K;
                int size2 = this.f9381a.size();
                int i13 = 0;
                while (i7 < size2) {
                    ViewBase viewBase2 = (ViewBase) this.f9381a.get(i7);
                    if (!viewBase2.I()) {
                        Params params2 = (Params) viewBase2.ae();
                        int comMeasuredWidth2 = viewBase2.getComMeasuredWidth();
                        int comMeasuredHeight2 = viewBase2.getComMeasuredHeight();
                        if ((params2.l & 1) != 0) {
                            i13 = params2.d + i11;
                            i11 = params2.f + comMeasuredWidth2 + i13;
                        } else if ((params2.l & 4) != 0) {
                            i13 = i12 - (params2.f + comMeasuredWidth2);
                            i12 = i13 - params2.d;
                        } else {
                            Log.e(ak, "onComLayout HORIZONTAL direction invalidate:" + params2.l);
                        }
                        if ((params2.m & 32) != 0) {
                            i6 = ((i4 + i2) - comMeasuredHeight2) >> 1;
                        } else if ((params2.m & 16) != 0) {
                            i6 = ((i4 - comMeasuredHeight2) - this.M) - params2.j;
                        } else {
                            i6 = params2.h + this.L + i2;
                        }
                        int a3 = RtlHelper.a(ak(), i, L(), i13, comMeasuredWidth2);
                        viewBase2.comLayout(a3, i6, comMeasuredWidth2 + a3, comMeasuredHeight2 + i6);
                    }
                    i7++;
                }
                return;
            default:
                return;
        }
    }

    public static class Params extends VHLayout.Params {
        public int l = 1;

        public boolean c(int i, int i2) {
            boolean c = super.c(i, i2);
            if (c) {
                return c;
            }
            if (i != -1955718283) {
                return false;
            }
            this.l = i2;
            return true;
        }
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new VH2Layout(vafContext, viewCache);
        }
    }
}
