package com.tmall.wireless.vaf.virtualview.view.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Log;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.Helper.VirtualViewUtils;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;

public class VirtualText extends TextBase {
    private static final String ak = "VirtualText_TMTEST";

    /* renamed from: a  reason: collision with root package name */
    protected int f9428a = 0;
    protected int ah;
    protected String ai = "";
    protected ViewBase.VirtualViewImp aj = new ViewBase.VirtualViewImp();

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    public VirtualText(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
        this.aj.a(true);
        this.aj.a((ViewBase) this);
    }

    public void f() {
        super.f();
        if ((this.ap & 1) != 0) {
            this.j.setFakeBoldText(true);
        }
        if ((this.ap & 8) != 0) {
            this.j.setStrikeThruText(true);
        }
        if ((this.ap & 2) != 0) {
            this.j.setTypeface(Typeface.create(Typeface.DEFAULT, 2));
        }
        this.j.setTextSize((float) this.ao);
        this.j.setColor(this.an);
        Paint.FontMetricsInt fontMetricsInt = this.j.getFontMetricsInt();
        this.f9428a = fontMetricsInt.descent - fontMetricsInt.ascent;
        this.ah = fontMetricsInt.descent;
        this.ai = this.am;
        if (!TextUtils.isEmpty(this.am)) {
            f(this.am);
        } else {
            f("");
        }
    }

    public void e() {
        super.e();
        this.aj.a();
        this.ai = this.am;
    }

    public void f(String str) {
        this.ai = str;
        super.f(str);
    }

    public void c(Object obj) {
        super.c(obj);
        if (obj instanceof String) {
            this.ai = (String) obj;
            if (this.f) {
                W();
                return;
            }
            return;
        }
        Log.e(ak, "setData type error:" + obj);
    }

    public void o(int i) {
        if (this.ao != i) {
            this.ao = i;
            W();
        }
    }

    public int an() {
        return this.ao;
    }

    /* access modifiers changed from: protected */
    public void b(Canvas canvas) {
        int i;
        super.b(canvas);
        if (this.Y == null) {
            aj();
        }
        if (this.Y != null) {
            int i2 = this.J;
            if ((this.N & 2) != 0) {
                i2 = ((this.O - this.Y.width()) - this.J) - this.K;
            } else if ((this.N & 4) != 0) {
                i2 = (this.O - this.Y.width()) / 2;
            }
            if ((this.N & 16) != 0) {
                i = this.P - this.M;
            } else if ((this.N & 32) != 0) {
                Paint.FontMetricsInt fontMetricsInt = this.j.getFontMetricsInt();
                i = this.ah + (((this.P - fontMetricsInt.bottom) - fontMetricsInt.top) / 2);
            } else {
                i = this.Y.height() + this.L;
            }
            canvas.save();
            canvas.clipRect(0, 0, this.O, this.P);
            canvas.drawText(this.ai, (float) i2, (float) (i - this.ah), this.j);
            canvas.restore();
            VirtualViewUtils.a(canvas, this.p, this.O, this.P, this.o, this.r, this.s, this.t, this.u);
            return;
        }
        Log.w(ak, "skip draw text");
    }

    /* access modifiers changed from: protected */
    public void aj() {
        float measureText = this.j.measureText(this.ai);
        if (this.Y == null) {
            this.Y = new Rect(0, 0, (int) measureText, this.f9428a);
        } else {
            this.Y.set(0, 0, (int) measureText, this.f9428a);
        }
    }

    public void onComMeasure(int i, int i2) {
        this.aj.onComMeasure(i, i2);
    }

    public void measureComponent(int i, int i2) {
        this.aj.measureComponent(i, i2);
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new VirtualText(vafContext, viewCache);
        }
    }
}
