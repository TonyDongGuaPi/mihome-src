package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

@SuppressLint({"ViewConstructor"})
class SymbolView extends GroupView {
    private float b;
    private float c;
    private float d;
    private float e;
    private String f;
    private int g;

    public SymbolView(ReactContext reactContext) {
        super(reactContext);
    }

    @ReactProp(name = "minX")
    public void setMinX(float f2) {
        this.b = f2;
        invalidate();
    }

    @ReactProp(name = "minY")
    public void setMinY(float f2) {
        this.c = f2;
        invalidate();
    }

    @ReactProp(name = "vbWidth")
    public void setVbWidth(float f2) {
        this.d = f2;
        invalidate();
    }

    @ReactProp(name = "vbHeight")
    public void setVbHeight(float f2) {
        this.e = f2;
        invalidate();
    }

    @ReactProp(name = "align")
    public void setAlign(String str) {
        this.f = str;
        invalidate();
    }

    @ReactProp(name = "meetOrSlice")
    public void setMeetOrSlice(int i) {
        this.g = i;
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public void draw(Canvas canvas, Paint paint, float f2) {
        saveDefinition();
    }

    /* access modifiers changed from: package-private */
    public void a(Canvas canvas, Paint paint, float f2, float f3, float f4) {
        if (this.f != null) {
            canvas.concat(ViewBox.a(new RectF(this.b * this.mScale, this.c * this.mScale, (this.b + this.d) * this.mScale, (this.c + this.e) * this.mScale), new RectF(0.0f, 0.0f, f3, f4), this.f, this.g));
            super.draw(canvas, paint, f2);
        }
    }
}
