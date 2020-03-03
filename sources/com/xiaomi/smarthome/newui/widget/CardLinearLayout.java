package com.xiaomi.smarthome.newui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.xiaomi.smarthome.smoothcard.SketchSmoothDelegate;

public class CardLinearLayout extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private SketchSmoothDelegate f20823a;

    public CardLinearLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public CardLinearLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CardLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f20823a = new SketchSmoothDelegate(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        canvas.clipPath(this.f20823a.a());
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f20823a.a(i, i2, i3, i4);
    }
}
