package com.tmall.wireless.vaf.virtualview.view.line;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;

public class VirtualLine extends LineBase {
    private static final String an = "VirtualLine_TMTEST";
    protected ViewBase.VirtualViewImp al = new ViewBase.VirtualViewImp();
    protected Path am;

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    public VirtualLine(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
        this.al.a(true);
        this.al.a((ViewBase) this);
        this.j.setColor(-16777216);
    }

    public void f() {
        super.f();
        this.j.setStrokeWidth((float) this.ai);
        this.j.setColor(this.ah);
        switch (this.aj) {
            case 1:
                this.j.setStyle(Paint.Style.FILL);
                return;
            case 2:
                if (this.am == null) {
                    this.am = new Path();
                }
                this.am.reset();
                this.j.setStyle(Paint.Style.STROKE);
                this.j.setPathEffect(new DashPathEffect(this.ak, 1.0f));
                return;
            default:
                return;
        }
    }

    public void n(int i) {
        this.ah = i;
        this.j.setColor(this.ah);
        W();
    }

    /* access modifiers changed from: protected */
    public void b(Canvas canvas) {
        int i;
        int i2;
        super.b(canvas);
        int strokeWidth = (int) this.j.getStrokeWidth();
        if (this.f9406a) {
            if ((this.N & 32) != 0) {
                i2 = this.P >> 1;
            } else {
                i2 = (this.N & 16) != 0 ? this.P - (strokeWidth >> 1) : strokeWidth >> 1;
            }
            if (this.aj == 1) {
                float f = (float) i2;
                canvas.drawLine((float) this.J, f, (float) (this.O - this.K), f, this.j);
            } else if (this.aj == 2) {
                float f2 = (float) i2;
                this.am.moveTo((float) this.J, f2);
                this.am.lineTo((float) (this.O - this.K), f2);
                canvas.drawPath(this.am, this.j);
            }
        } else {
            if ((this.N & 4) != 0) {
                i = this.O >> 1;
            } else {
                i = (this.N & 2) != 0 ? this.O - (strokeWidth >> 1) : strokeWidth >> 1;
            }
            if (this.aj == 1) {
                float f3 = (float) i;
                canvas.drawLine(f3, (float) this.L, f3, (float) (this.P - this.M), this.j);
            } else if (this.aj == 2) {
                float f4 = (float) i;
                this.am.moveTo(f4, (float) this.L);
                this.am.lineTo(f4, (float) (this.P - this.M));
                canvas.drawPath(this.am, this.j);
            }
        }
    }

    public void onComMeasure(int i, int i2) {
        this.al.onComMeasure(i, i2);
    }

    public void measureComponent(int i, int i2) {
        this.al.measureComponent(i, i2);
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new VirtualLine(vafContext, viewCache);
        }
    }
}
