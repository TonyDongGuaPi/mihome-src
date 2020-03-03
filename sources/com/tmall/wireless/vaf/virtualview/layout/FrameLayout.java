package com.tmall.wireless.vaf.virtualview.layout;

import android.util.Log;
import android.view.View;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.Helper.RtlHelper;
import com.tmall.wireless.vaf.virtualview.core.Layout;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;
import java.util.ArrayList;
import java.util.List;

public class FrameLayout extends Layout {
    private static final String ah = "FrameLayout_TMTEST";
    private List<ViewBase> ai = new ArrayList();

    public FrameLayout(VafContext vafContext, ViewCache viewCache) {
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
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        this.ai.clear();
        int size3 = this.f9381a.size();
        for (int i3 = 0; i3 < size3; i3++) {
            ViewBase viewBase = (ViewBase) this.f9381a.get(i3);
            if (!viewBase.I()) {
                Layout.Params ae = viewBase.ae();
                if ((1073741824 != mode2 && -1 == ae.b) || (1073741824 != mode && -1 == ae.f9382a)) {
                    this.ai.add(viewBase);
                }
                a(viewBase, i, i2);
            }
        }
        d(i(mode, size), j(mode2, size2));
        if (this.ai.size() > 0) {
            int size4 = this.ai.size();
            for (int i4 = 0; i4 < size4; i4++) {
                a(this.ai.get(i4), View.MeasureSpec.makeMeasureSpec(this.O, 1073741824), View.MeasureSpec.makeMeasureSpec(this.P, 1073741824));
            }
        }
    }

    private int i(int i, int i2) {
        int af;
        if (Integer.MIN_VALUE == i) {
            int size = this.f9381a.size();
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                ViewBase viewBase = (ViewBase) this.f9381a.get(i4);
                if (!viewBase.I() && (af = viewBase.af()) > i3) {
                    i3 = af;
                }
            }
            return Math.min(i2, i3 + this.J + this.K + (this.o << 1));
        } else if (1073741824 == i) {
            return i2;
        } else {
            Log.e(ah, "getRealWidth error mode:" + i);
            return i2;
        }
    }

    private int j(int i, int i2) {
        int ag;
        int ag2;
        int i3 = 0;
        if (Integer.MIN_VALUE == i) {
            int size = this.f9381a.size();
            int i4 = 0;
            while (i3 < size) {
                ViewBase viewBase = (ViewBase) this.f9381a.get(i3);
                if (!viewBase.I() && (ag2 = viewBase.ag()) > i4) {
                    i4 = ag2;
                }
                i3++;
            }
            return Math.min(i2, i4 + this.L + this.M + (this.o << 1));
        } else if (1073741824 == i) {
            return i2;
        } else {
            int size2 = this.f9381a.size();
            int i5 = 0;
            while (i3 < size2) {
                ViewBase viewBase2 = (ViewBase) this.f9381a.get(i3);
                if (!viewBase2.I() && (ag = viewBase2.ag()) > i5) {
                    i5 = ag;
                }
                i3++;
            }
            return i5 + this.L + this.M + (this.o << 1);
        }
    }

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int size = this.f9381a.size();
        for (int i7 = 0; i7 < size; i7++) {
            ViewBase viewBase = (ViewBase) this.f9381a.get(i7);
            if (!viewBase.I()) {
                int comMeasuredWidth = viewBase.getComMeasuredWidth();
                int comMeasuredHeight = viewBase.getComMeasuredHeight();
                Params params = (Params) viewBase.ae();
                if ((params.l & 4) != 0) {
                    i5 = ((i3 + i) - comMeasuredWidth) >> 1;
                } else if ((params.l & 2) != 0) {
                    i5 = (((i3 - this.K) - params.f) - comMeasuredWidth) - this.o;
                } else {
                    i5 = this.J + i + params.d + this.o;
                }
                if ((params.l & 32) != 0) {
                    i6 = ((i4 + i2) - comMeasuredHeight) >> 1;
                } else if ((params.l & 16) != 0) {
                    i6 = (((i4 - comMeasuredHeight) - this.M) - params.j) - this.o;
                } else {
                    i6 = this.o + this.L + i2 + params.h;
                }
                int a2 = RtlHelper.a(ak(), i, L(), i5, comMeasuredWidth);
                viewBase.comLayout(a2, i6, comMeasuredWidth + a2, comMeasuredHeight + i6);
            }
        }
    }

    public static class Params extends Layout.Params {
        public int l;

        public boolean c(int i, int i2) {
            boolean c = super.c(i, i2);
            if (c) {
                return c;
            }
            if (i != 516361156) {
                return false;
            }
            this.l = i2;
            return true;
        }
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new FrameLayout(vafContext, viewCache);
        }
    }
}
