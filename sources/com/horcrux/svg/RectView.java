package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

@SuppressLint({"ViewConstructor"})
class RectView extends RenderableView {

    /* renamed from: a  reason: collision with root package name */
    private SVGLength f5820a;
    private SVGLength b;
    private SVGLength c;
    private SVGLength d;
    private SVGLength e;
    private SVGLength f;

    public RectView(ReactContext reactContext) {
        super(reactContext);
    }

    @ReactProp(name = "x")
    public void setX(Dynamic dynamic) {
        this.f5820a = SVGLength.a(dynamic);
        invalidate();
    }

    @ReactProp(name = "y")
    public void setY(Dynamic dynamic) {
        this.b = SVGLength.a(dynamic);
        invalidate();
    }

    @ReactProp(name = "width")
    public void setWidth(Dynamic dynamic) {
        this.c = SVGLength.a(dynamic);
        invalidate();
    }

    @ReactProp(name = "height")
    public void setHeight(Dynamic dynamic) {
        this.d = SVGLength.a(dynamic);
        invalidate();
    }

    @ReactProp(name = "rx")
    public void setRx(Dynamic dynamic) {
        this.e = SVGLength.a(dynamic);
        invalidate();
    }

    @ReactProp(name = "ry")
    public void setRy(Dynamic dynamic) {
        this.f = SVGLength.a(dynamic);
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = new Path();
        double relativeOnWidth = relativeOnWidth(this.f5820a);
        double relativeOnHeight = relativeOnHeight(this.b);
        double relativeOnWidth2 = relativeOnWidth(this.c);
        double relativeOnHeight2 = relativeOnHeight(this.d);
        double relativeOnWidth3 = relativeOnWidth(this.e);
        double relativeOnHeight3 = relativeOnHeight(this.f);
        if (relativeOnWidth3 == 0.0d && relativeOnHeight3 == 0.0d) {
            path.addRect((float) relativeOnWidth, (float) relativeOnHeight, (float) (relativeOnWidth + relativeOnWidth2), (float) (relativeOnHeight + relativeOnHeight2), Path.Direction.CW);
        } else {
            if (relativeOnWidth3 == 0.0d) {
                relativeOnWidth3 = relativeOnHeight3;
            } else if (relativeOnHeight3 == 0.0d) {
                relativeOnHeight3 = relativeOnWidth3;
            }
            double d2 = relativeOnWidth2 / 2.0d;
            if (relativeOnWidth3 > d2) {
                relativeOnWidth3 = d2;
            }
            double d3 = relativeOnHeight2 / 2.0d;
            if (relativeOnHeight3 <= d3) {
                d3 = relativeOnHeight3;
            }
            path.addRoundRect(new RectF((float) relativeOnWidth, (float) relativeOnHeight, (float) (relativeOnWidth + relativeOnWidth2), (float) (relativeOnHeight + relativeOnHeight2)), (float) relativeOnWidth3, (float) d3, Path.Direction.CW);
        }
        return path;
    }
}
