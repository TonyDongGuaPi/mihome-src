package com.swmansion.rnscreens;

import android.view.View;
import android.view.ViewParent;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.views.view.ReactViewGroup;

public class ScreenStackHeaderSubview extends ReactViewGroup {

    /* renamed from: a  reason: collision with root package name */
    private int f8960a;
    private int b;
    private final UIManagerModule c;
    private Type d = Type.RIGHT;

    public enum Type {
        LEFT,
        CENTER,
        TITLE,
        RIGHT
    }

    public class Measurements {

        /* renamed from: a  reason: collision with root package name */
        public int f8961a;
        public int b;

        public Measurements() {
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getMode(i) == 1073741824 && View.MeasureSpec.getMode(i2) == 1073741824) {
            this.f8960a = View.MeasureSpec.getSize(i);
            this.b = View.MeasureSpec.getSize(i2);
            ViewParent parent = getParent();
            if (parent != null) {
                forceLayout();
                ((View) parent).requestLayout();
            }
        }
        setMeasuredDimension(this.f8960a, this.b);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z && (this.d == Type.CENTER || this.d == Type.TITLE)) {
            Measurements measurements = new Measurements();
            measurements.f8961a = i3 - i;
            if (this.d == Type.CENTER) {
                int width = ((View) getParent()).getWidth();
                measurements.f8961a = Math.max(0, width - (Math.max(width - i3, i) * 2));
            }
            measurements.b = i4 - i2;
            this.c.setViewLocalData(getId(), measurements);
        }
        super.onLayout(z, i, i2, i3, i4);
    }

    public ScreenStackHeaderSubview(ReactContext reactContext) {
        super(reactContext);
        this.c = (UIManagerModule) reactContext.getNativeModule(UIManagerModule.class);
    }

    public void setType(Type type) {
        this.d = type;
    }

    public Type getType() {
        return this.d;
    }
}
