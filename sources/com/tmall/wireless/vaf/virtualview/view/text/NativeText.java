package com.tmall.wireless.vaf.virtualview.view.text;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.LineHeightSpan;
import android.view.View;
import com.libra.Utils;
import com.libra.virtualview.common.StringBase;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;

public class NativeText extends TextBase {
    private static final String at = "NativeText_TMTEST";

    /* renamed from: a  reason: collision with root package name */
    protected NativeTextImp f9424a;
    protected VVLineHeightSpannableStringBuilder ah;
    protected boolean ai = false;
    protected float aj = 1.0f;
    protected float ak = 0.0f;
    protected float al = Float.NaN;

    public NativeText(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
        this.f9424a = new NativeTextImp(vafContext.m());
    }

    public void f(String str) {
        if (!TextUtils.equals(str, this.am)) {
            this.am = str;
            g(this.am);
        }
    }

    public void n(int i) {
        if (this.an != i) {
            this.an = i;
            this.f9424a.setTextColor(this.an);
        }
    }

    public void e() {
        super.e();
    }

    public View g_() {
        return this.f9424a;
    }

    public void onComMeasure(int i, int i2) {
        this.f9424a.onComMeasure(i, i2);
    }

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
        this.f9424a.onComLayout(z, i, i2, i3, i4);
    }

    public void measureComponent(int i, int i2) {
        this.f9424a.measureComponent(i, i2);
    }

    public int getComMeasuredWidth() {
        return this.f9424a.getComMeasuredWidth();
    }

    public int getComMeasuredHeight() {
        return this.f9424a.getComMeasuredHeight();
    }

    public void comLayout(int i, int i2, int i3, int i4) {
        super.comLayout(i, i2, i3, i4);
        this.f9424a.comLayout(i, i2, i3, i4);
    }

    public void f() {
        super.f();
        int i = 0;
        this.f9424a.setTextSize(0, (float) this.ao);
        this.f9424a.setBorderColor(this.p);
        this.f9424a.setBorderWidth(this.o);
        this.f9424a.setBorderTopLeftRadius(this.r);
        this.f9424a.setBorderTopRightRadius(this.s);
        this.f9424a.setBorderBottomLeftRadius(this.t);
        this.f9424a.setBorderBottomRightRadius(this.u);
        this.f9424a.setBackgroundColor(this.k);
        this.f9424a.setTextColor(this.an);
        int i2 = (this.ap & 1) != 0 ? 33 : 1;
        if ((this.ap & 8) != 0) {
            i2 |= 16;
        }
        if ((this.ap & 4) != 0) {
            i2 |= 8;
        }
        this.f9424a.setPaintFlags(i2);
        if ((this.ap & 2) != 0) {
            this.f9424a.setTypeface((Typeface) null, 3);
        }
        if (this.ar > 0) {
            this.f9424a.setLines(this.ar);
        }
        if (this.as >= 0) {
            this.f9424a.setEllipsize(TextUtils.TruncateAt.values()[this.as]);
        }
        if ((this.N & 1) != 0) {
            i = 3;
        } else if ((this.N & 2) != 0) {
            i = 5;
        } else if ((this.N & 4) != 0) {
            i = 1;
        }
        if ((this.N & 8) != 0) {
            i |= 48;
        } else if ((this.N & 16) != 0) {
            i |= 80;
        } else if ((this.N & 32) != 0) {
            i |= 16;
        }
        this.f9424a.setGravity(i);
        this.f9424a.setLineSpacing(this.ak, this.aj);
        if (!TextUtils.isEmpty(this.am)) {
            g(this.am);
        } else {
            g("");
        }
    }

    public void c(Object obj) {
        super.c(obj);
        if (obj instanceof String) {
            g((String) obj);
        }
    }

    /* access modifiers changed from: protected */
    public void g(String str) {
        CharSequence charSequence = str;
        if (this.ai) {
            charSequence = Html.fromHtml(str);
        }
        if (!Float.isNaN(this.al)) {
            if (this.ah == null) {
                this.ah = new VVLineHeightSpannableStringBuilder();
            }
            this.ah.a(charSequence, this.al);
            this.f9424a.setText(this.ah);
            return;
        }
        this.f9424a.setText(charSequence);
    }

    /* access modifiers changed from: protected */
    public boolean c(int i, int i2) {
        boolean c = super.c(i, i2);
        boolean z = false;
        if (c) {
            return c;
        }
        switch (i) {
            case StringBase.bH:
                this.ak = (float) i2;
                break;
            case StringBase.bG:
                this.aj = (float) i2;
                break;
            case StringBase.ce:
                this.al = (float) Utils.b((double) i2);
                break;
            case StringBase.bK:
                this.f9424a.setMaxLines(i2);
                break;
            case StringBase.bF:
                if (i2 > 0) {
                    z = true;
                }
                this.ai = z;
                break;
            default:
                return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, float f) {
        boolean b = super.b(i, f);
        boolean z = false;
        if (b) {
            return b;
        }
        if (i == -1118334530) {
            this.ak = f;
        } else if (i == -667362093) {
            this.aj = f;
        } else if (i == -515807685) {
            this.al = (float) Utils.b((double) f);
        } else if (i != 506010071) {
            return false;
        } else {
            if (f > 0.0f) {
                z = true;
            }
            this.ai = z;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, int i2) {
        boolean b = super.b(i, i2);
        if (b) {
            return b;
        }
        if (i != -515807685) {
            return false;
        }
        this.al = (float) Utils.a((double) i2);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, float f) {
        boolean a2 = super.a(i, f);
        if (a2) {
            return a2;
        }
        if (i != -515807685) {
            return false;
        }
        this.al = (float) Utils.a((double) f);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, String str) {
        boolean a2 = super.a(i, str);
        if (a2) {
            return a2;
        }
        if (i != -515807685) {
            return false;
        }
        this.c.a(this, StringBase.ce, str, 1);
        return true;
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new NativeText(vafContext, viewCache);
        }
    }

    public static class VVLineHeightSpannableStringBuilder extends SpannableStringBuilder {

        /* renamed from: a  reason: collision with root package name */
        private VVLineHeightSpan f9426a;

        public void a(CharSequence charSequence, float f) {
            clear();
            clearSpans();
            if (this.f9426a == null) {
                this.f9426a = new VVLineHeightSpan(f);
            } else {
                this.f9426a.a(f);
            }
            append(charSequence);
            setSpan(this.f9426a, 0, charSequence.length(), 17);
        }
    }

    public static class VVLineHeightSpan implements LineHeightSpan {

        /* renamed from: a  reason: collision with root package name */
        private int f9425a;

        VVLineHeightSpan(float f) {
            this.f9425a = (int) Math.ceil((double) f);
        }

        public void a(float f) {
            this.f9425a = (int) Math.ceil((double) f);
        }

        public int a() {
            return this.f9425a;
        }

        public void chooseHeight(CharSequence charSequence, int i, int i2, int i3, int i4, Paint.FontMetricsInt fontMetricsInt) {
            if (fontMetricsInt.descent > this.f9425a) {
                int min = Math.min(this.f9425a, fontMetricsInt.descent);
                fontMetricsInt.descent = min;
                fontMetricsInt.bottom = min;
                fontMetricsInt.ascent = 0;
                fontMetricsInt.top = 0;
            } else if ((-fontMetricsInt.ascent) + fontMetricsInt.descent > this.f9425a) {
                fontMetricsInt.bottom = fontMetricsInt.descent;
                int i5 = (-this.f9425a) + fontMetricsInt.descent;
                fontMetricsInt.ascent = i5;
                fontMetricsInt.top = i5;
            } else if ((-fontMetricsInt.ascent) + fontMetricsInt.bottom > this.f9425a) {
                fontMetricsInt.top = fontMetricsInt.ascent;
                fontMetricsInt.bottom = fontMetricsInt.ascent + this.f9425a;
            } else if ((-fontMetricsInt.top) + fontMetricsInt.bottom > this.f9425a) {
                fontMetricsInt.top = fontMetricsInt.bottom - this.f9425a;
            } else {
                int i6 = this.f9425a - ((-fontMetricsInt.top) + fontMetricsInt.bottom);
                double d = (double) fontMetricsInt.top;
                double d2 = (double) (((float) i6) / 2.0f);
                double ceil = Math.ceil(d2);
                Double.isNaN(d);
                fontMetricsInt.top = (int) (d - ceil);
                double d3 = (double) fontMetricsInt.bottom;
                double floor = Math.floor(d2);
                Double.isNaN(d3);
                fontMetricsInt.bottom = (int) (d3 + floor);
                fontMetricsInt.ascent = fontMetricsInt.top;
                fontMetricsInt.descent = fontMetricsInt.bottom;
            }
        }
    }
}
