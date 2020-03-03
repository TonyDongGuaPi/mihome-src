package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.graphics.RectF;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.horcrux.svg.Brush;
import javax.annotation.Nullable;

@SuppressLint({"ViewConstructor"})
class PatternView extends GroupView {
    private static final float[] n = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    String b;
    int c;
    private SVGLength d;
    private SVGLength e;
    private SVGLength f;
    private SVGLength g;
    private Brush.BrushUnits h;
    private Brush.BrushUnits i;
    private float j;
    private float k;
    private float l;
    private float m;
    private Matrix o = null;

    public PatternView(ReactContext reactContext) {
        super(reactContext);
    }

    @ReactProp(name = "x")
    public void setX(Dynamic dynamic) {
        this.d = SVGLength.a(dynamic);
        invalidate();
    }

    @ReactProp(name = "y")
    public void setY(Dynamic dynamic) {
        this.e = SVGLength.a(dynamic);
        invalidate();
    }

    @ReactProp(name = "width")
    public void setWidth(Dynamic dynamic) {
        this.f = SVGLength.a(dynamic);
        invalidate();
    }

    @ReactProp(name = "height")
    public void setHeight(Dynamic dynamic) {
        this.g = SVGLength.a(dynamic);
        invalidate();
    }

    @ReactProp(name = "patternUnits")
    public void setPatternUnits(int i2) {
        switch (i2) {
            case 0:
                this.h = Brush.BrushUnits.OBJECT_BOUNDING_BOX;
                break;
            case 1:
                this.h = Brush.BrushUnits.USER_SPACE_ON_USE;
                break;
        }
        invalidate();
    }

    @ReactProp(name = "patternContentUnits")
    public void setPatternContentUnits(int i2) {
        switch (i2) {
            case 0:
                this.i = Brush.BrushUnits.OBJECT_BOUNDING_BOX;
                break;
            case 1:
                this.i = Brush.BrushUnits.USER_SPACE_ON_USE;
                break;
        }
        invalidate();
    }

    @ReactProp(name = "patternTransform")
    public void setPatternTransform(@Nullable ReadableArray readableArray) {
        if (readableArray != null) {
            int a2 = PropHelper.a(readableArray, n, this.mScale);
            if (a2 == 6) {
                if (this.o == null) {
                    this.o = new Matrix();
                }
                this.o.setValues(n);
            } else if (a2 != -1) {
                FLog.w(ReactConstants.TAG, "RNSVG: Transform matrices must be of size 6");
            }
        } else {
            this.o = null;
        }
        invalidate();
    }

    @ReactProp(name = "minX")
    public void setMinX(float f2) {
        this.j = f2;
        invalidate();
    }

    @ReactProp(name = "minY")
    public void setMinY(float f2) {
        this.k = f2;
        invalidate();
    }

    @ReactProp(name = "vbWidth")
    public void setVbWidth(float f2) {
        this.l = f2;
        invalidate();
    }

    @ReactProp(name = "vbHeight")
    public void setVbHeight(float f2) {
        this.m = f2;
        invalidate();
    }

    @ReactProp(name = "align")
    public void setAlign(String str) {
        this.b = str;
        invalidate();
    }

    @ReactProp(name = "meetOrSlice")
    public void setMeetOrSlice(int i2) {
        this.c = i2;
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public RectF e() {
        return new RectF(this.j * this.mScale, this.k * this.mScale, (this.j + this.l) * this.mScale, (this.k + this.m) * this.mScale);
    }

    /* access modifiers changed from: package-private */
    public void saveDefinition() {
        if (this.mName != null) {
            Brush brush = new Brush(Brush.BrushType.PATTERN, new SVGLength[]{this.d, this.e, this.f, this.g}, this.h);
            brush.a(this.i);
            brush.a(this);
            if (this.o != null) {
                brush.a(this.o);
            }
            SvgView svgView = getSvgView();
            if (this.h == Brush.BrushUnits.USER_SPACE_ON_USE || this.i == Brush.BrushUnits.USER_SPACE_ON_USE) {
                brush.a(svgView.getCanvasBounds());
            }
            svgView.defineBrush(brush, this.mName);
        }
    }
}
