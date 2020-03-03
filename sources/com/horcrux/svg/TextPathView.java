package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.horcrux.svg.TextProperties;
import javax.annotation.Nullable;

@SuppressLint({"ViewConstructor"})
class TextPathView extends TextView {
    private String b;
    private TextProperties.TextPathSide f;
    private TextProperties.TextPathMidLine g;
    @Nullable
    private SVGLength h;
    private TextProperties.TextPathMethod i = TextProperties.TextPathMethod.align;
    private TextProperties.TextPathSpacing j = TextProperties.TextPathSpacing.exact;

    /* access modifiers changed from: package-private */
    public void c() {
    }

    /* access modifiers changed from: package-private */
    public void d() {
    }

    public TextPathView(ReactContext reactContext) {
        super(reactContext);
    }

    @ReactProp(name = "href")
    public void setHref(String str) {
        this.b = str;
        invalidate();
    }

    @ReactProp(name = "startOffset")
    public void setStartOffset(Dynamic dynamic) {
        this.h = SVGLength.a(dynamic);
        invalidate();
    }

    @ReactProp(name = "method")
    public void setMethod(@Nullable String str) {
        this.i = TextProperties.TextPathMethod.valueOf(str);
        invalidate();
    }

    @ReactProp(name = "spacing")
    public void setSpacing(@Nullable String str) {
        this.j = TextProperties.TextPathSpacing.valueOf(str);
        invalidate();
    }

    @ReactProp(name = "side")
    public void setSide(@Nullable String str) {
        this.f = TextProperties.TextPathSide.valueOf(str);
        invalidate();
    }

    @ReactProp(name = "midLine")
    public void setSharp(@Nullable String str) {
        this.g = TextProperties.TextPathMidLine.valueOf(str);
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public TextProperties.TextPathMethod e() {
        return this.i;
    }

    /* access modifiers changed from: package-private */
    public TextProperties.TextPathSpacing f() {
        return this.j;
    }

    /* access modifiers changed from: package-private */
    public TextProperties.TextPathSide g() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public TextProperties.TextPathMidLine h() {
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public SVGLength i() {
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public void draw(Canvas canvas, Paint paint, float f2) {
        a(canvas, paint, f2);
    }

    /* access modifiers changed from: package-private */
    public Path a(Canvas canvas, Paint paint) {
        VirtualView definedTemplate = getSvgView().getDefinedTemplate(this.b);
        if (!(definedTemplate instanceof RenderableView)) {
            return null;
        }
        return ((RenderableView) definedTemplate).getPath(canvas, paint);
    }

    /* access modifiers changed from: package-private */
    public Path getPath(Canvas canvas, Paint paint) {
        return b(canvas, paint);
    }
}
