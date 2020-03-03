package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.horcrux.svg.Brush;
import javax.annotation.Nullable;

@SuppressLint({"ViewConstructor"})
class RadialGradientView extends DefinitionView {
    private static final float[] i = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};

    /* renamed from: a  reason: collision with root package name */
    private SVGLength f5819a;
    private SVGLength b;
    private SVGLength c;
    private SVGLength d;
    private SVGLength e;
    private SVGLength f;
    private ReadableArray g;
    private Brush.BrushUnits h;
    private Matrix j = null;

    public RadialGradientView(ReactContext reactContext) {
        super(reactContext);
    }

    @ReactProp(name = "fx")
    public void setFx(Dynamic dynamic) {
        this.f5819a = SVGLength.a(dynamic);
        invalidate();
    }

    @ReactProp(name = "fy")
    public void setFy(Dynamic dynamic) {
        this.b = SVGLength.a(dynamic);
        invalidate();
    }

    @ReactProp(name = "rx")
    public void setRx(Dynamic dynamic) {
        this.c = SVGLength.a(dynamic);
        invalidate();
    }

    @ReactProp(name = "ry")
    public void setRy(Dynamic dynamic) {
        this.d = SVGLength.a(dynamic);
        invalidate();
    }

    @ReactProp(name = "cx")
    public void setCx(Dynamic dynamic) {
        this.e = SVGLength.a(dynamic);
        invalidate();
    }

    @ReactProp(name = "cy")
    public void setCy(Dynamic dynamic) {
        this.f = SVGLength.a(dynamic);
        invalidate();
    }

    @ReactProp(name = "gradient")
    public void setGradient(ReadableArray readableArray) {
        this.g = readableArray;
        invalidate();
    }

    @ReactProp(name = "gradientUnits")
    public void setGradientUnits(int i2) {
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

    @ReactProp(name = "gradientTransform")
    public void setGradientTransform(@Nullable ReadableArray readableArray) {
        if (readableArray != null) {
            int a2 = PropHelper.a(readableArray, i, this.mScale);
            if (a2 == 6) {
                if (this.j == null) {
                    this.j = new Matrix();
                }
                this.j.setValues(i);
            } else if (a2 != -1) {
                FLog.w(ReactConstants.TAG, "RNSVG: Transform matrices must be of size 6");
            }
        } else {
            this.j = null;
        }
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public void saveDefinition() {
        if (this.mName != null) {
            Brush brush = new Brush(Brush.BrushType.RADIAL_GRADIENT, new SVGLength[]{this.f5819a, this.b, this.c, this.d, this.e, this.f}, this.h);
            brush.a(this.g);
            if (this.j != null) {
                brush.a(this.j);
            }
            SvgView svgView = getSvgView();
            if (this.h == Brush.BrushUnits.USER_SPACE_ON_USE) {
                brush.a(svgView.getCanvasBounds());
            }
            svgView.defineBrush(brush, this.mName);
        }
    }
}
