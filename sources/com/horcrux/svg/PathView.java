package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.horcrux.svg.PropHelper;

@SuppressLint({"ViewConstructor"})
class PathView extends RenderableView {

    /* renamed from: a  reason: collision with root package name */
    private Path f5815a;

    public PathView(ReactContext reactContext) {
        super(reactContext);
    }

    @ReactProp(name = "d")
    public void setD(String str) {
        this.f5815a = new PropHelper.PathParser(str, this.mScale).a();
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public Path getPath(Canvas canvas, Paint paint) {
        return this.f5815a;
    }
}
