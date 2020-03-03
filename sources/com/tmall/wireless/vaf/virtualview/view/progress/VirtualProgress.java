package com.tmall.wireless.vaf.virtualview.view.progress;

import android.graphics.Canvas;
import com.libra.Color;
import com.libra.Utils;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;
import com.tmall.wireless.vaf.virtualview.core.VirtualViewBase;

public class VirtualProgress extends VirtualViewBase {
    public static final int ah = 1;
    private static final String ai = "Progress_TMTEST";
    private int aj = 1;
    private int ak = 0;
    private int al = Color.h;
    private int am = 0;
    private int an = 0;

    public VirtualProgress(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
    }

    public void i(int i, int i2) {
        if (this.am != i) {
            this.am = i;
            this.an = i2;
            W();
        }
    }

    public void e() {
        super.e();
        this.ak = 0;
        this.am = 0;
        this.an = 0;
    }

    /* access modifiers changed from: protected */
    public void b(Canvas canvas) {
        super.b(canvas);
        int i = this.ak;
        if (this.am > 0) {
            i += ((((this.O - this.ak) - this.J) - this.K) * this.am) / this.an;
        }
        if (i > 0) {
            canvas.drawRect((float) this.J, (float) this.L, (float) (i + this.J), (float) (this.P - this.M), this.j);
        }
    }

    public void f() {
        super.f();
        int i = this.aj;
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, float f) {
        boolean b = super.b(i, f);
        if (b) {
            return b;
        }
        if (i != -266541503) {
            return false;
        }
        this.ak = Utils.b((double) f);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean c(int i, int i2) {
        boolean c = super.c(i, i2);
        if (c) {
            return c;
        }
        if (i == -266541503) {
            this.ak = Utils.b((double) i2);
            return true;
        } else if (i == 3575610) {
            this.aj = i2;
            return true;
        } else if (i != 94842723) {
            return false;
        } else {
            this.al = i2;
            this.j.setColor(this.al);
            return true;
        }
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new VirtualProgress(vafContext, viewCache);
        }
    }
}
