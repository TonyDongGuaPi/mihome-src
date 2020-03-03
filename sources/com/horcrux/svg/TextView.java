package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.view.View;
import android.view.ViewParent;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.horcrux.svg.TextProperties;
import java.util.ArrayList;
import javax.annotation.Nullable;

@SuppressLint({"ViewConstructor"})
class TextView extends GroupView {
    private String b = null;
    SVGLength c = null;
    TextProperties.TextLengthAdjust d = TextProperties.TextLengthAdjust.spacing;
    double e = Double.NaN;
    private TextProperties.AlignmentBaseline f;
    @Nullable
    private ArrayList<SVGLength> g;
    @Nullable
    private ArrayList<SVGLength> h;
    @Nullable
    private ArrayList<SVGLength> i;
    @Nullable
    private ArrayList<SVGLength> j;
    @Nullable
    private ArrayList<SVGLength> k;

    public TextView(ReactContext reactContext) {
        super(reactContext);
    }

    public void invalidate() {
        if (this.mPath != null) {
            super.invalidate();
            m().clearChildCache();
        }
    }

    /* access modifiers changed from: package-private */
    public void clearCache() {
        this.e = Double.NaN;
        super.clearCache();
    }

    @ReactProp(name = "textLength")
    public void setTextLength(Dynamic dynamic) {
        this.c = SVGLength.a(dynamic);
        invalidate();
    }

    @ReactProp(name = "lengthAdjust")
    public void setLengthAdjust(@Nullable String str) {
        this.d = TextProperties.TextLengthAdjust.valueOf(str);
        invalidate();
    }

    @ReactProp(name = "alignmentBaseline")
    public void setMethod(@Nullable String str) {
        this.f = TextProperties.AlignmentBaseline.getEnum(str);
        invalidate();
    }

    @ReactProp(name = "baselineShift")
    public void setBaselineShift(Dynamic dynamic) {
        this.b = SVGLength.b(dynamic);
        invalidate();
    }

    @ReactProp(name = "verticalAlign")
    public void setVerticalAlign(@Nullable String str) {
        if (str != null) {
            String trim = str.trim();
            int lastIndexOf = trim.lastIndexOf(32);
            try {
                this.f = TextProperties.AlignmentBaseline.getEnum(trim.substring(lastIndexOf));
            } catch (IllegalArgumentException unused) {
                this.f = TextProperties.AlignmentBaseline.baseline;
            }
            try {
                this.b = trim.substring(0, lastIndexOf);
            } catch (IndexOutOfBoundsException unused2) {
                this.b = null;
            }
        } else {
            this.f = TextProperties.AlignmentBaseline.baseline;
            this.b = null;
        }
        invalidate();
    }

    @ReactProp(name = "rotate")
    public void setRotate(Dynamic dynamic) {
        this.i = SVGLength.c(dynamic);
        invalidate();
    }

    @ReactProp(name = "dx")
    public void setDeltaX(Dynamic dynamic) {
        this.j = SVGLength.c(dynamic);
        invalidate();
    }

    @ReactProp(name = "dy")
    public void setDeltaY(Dynamic dynamic) {
        this.k = SVGLength.c(dynamic);
        invalidate();
    }

    @ReactProp(name = "x")
    public void setPositionX(Dynamic dynamic) {
        this.g = SVGLength.c(dynamic);
        invalidate();
    }

    @ReactProp(name = "y")
    public void setPositionY(Dynamic dynamic) {
        this.h = SVGLength.c(dynamic);
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public void draw(Canvas canvas, Paint paint, float f2) {
        if (f2 > 0.01f) {
            a(canvas);
            clip(canvas, paint);
            b(canvas, paint);
            a(canvas, paint, f2);
        }
    }

    /* access modifiers changed from: package-private */
    public Path getPath(Canvas canvas, Paint paint) {
        if (this.mPath != null) {
            return this.mPath;
        }
        a(canvas);
        return b(canvas, paint);
    }

    /* access modifiers changed from: package-private */
    public Path a(Canvas canvas, Paint paint, Region.Op op) {
        return getPath(canvas, paint);
    }

    /* access modifiers changed from: package-private */
    public TextProperties.AlignmentBaseline j() {
        TextProperties.AlignmentBaseline alignmentBaseline;
        if (this.f == null) {
            ViewParent parent = getParent();
            while (parent != null) {
                if (!(parent instanceof TextView) || (alignmentBaseline = ((TextView) parent).f) == null) {
                    parent = parent.getParent();
                } else {
                    this.f = alignmentBaseline;
                    return alignmentBaseline;
                }
            }
        }
        if (this.f == null) {
            this.f = TextProperties.AlignmentBaseline.baseline;
        }
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public String k() {
        String str;
        if (this.b == null) {
            ViewParent parent = getParent();
            while (parent != null) {
                if (!(parent instanceof TextView) || (str = ((TextView) parent).b) == null) {
                    parent = parent.getParent();
                } else {
                    this.b = str;
                    return str;
                }
            }
        }
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public Path b(Canvas canvas, Paint paint) {
        if (this.mPath != null) {
            return this.mPath;
        }
        c();
        this.mPath = super.getPath(canvas, paint);
        d();
        return this.mPath;
    }

    /* access modifiers changed from: package-private */
    public void c() {
        b().a(!(this instanceof TextPathView) && !(this instanceof TSpanView), this, this.f5811a, this.g, this.h, this.j, this.k, this.i);
    }

    /* access modifiers changed from: package-private */
    public TextView l() {
        ArrayList<FontData> arrayList = b().f5809a;
        ViewParent parent = getParent();
        int size = arrayList.size() - 1;
        TextView textView = this;
        ViewParent viewParent = parent;
        while (size >= 0 && (viewParent instanceof TextView) && arrayList.get(size).i != TextProperties.TextAnchor.start && textView.g == null) {
            textView = (TextView) viewParent;
            size--;
            viewParent = textView.getParent();
        }
        return textView;
    }

    /* access modifiers changed from: package-private */
    public double a(Paint paint) {
        if (!Double.isNaN(this.e)) {
            return this.e;
        }
        double d2 = 0.0d;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt instanceof TextView) {
                d2 += ((TextView) childAt).a(paint);
            }
        }
        this.e = d2;
        return d2;
    }

    /* access modifiers changed from: package-private */
    public TextView m() {
        TextView textView = this;
        ViewParent viewParent = getParent();
        while (viewParent instanceof TextView) {
            textView = (TextView) viewParent;
            viewParent = textView.getParent();
        }
        return textView;
    }
}
