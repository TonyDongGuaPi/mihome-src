package com.xiaomi.smarthome.newui.widget.topnavi.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import com.xiaomi.smarthome.smoothcard.SketchSmoothDelegate;

public class CardConstraintLayout extends ConstraintLayout {

    /* renamed from: a  reason: collision with root package name */
    private SketchSmoothDelegate f20945a;

    public CardConstraintLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public CardConstraintLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CardConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f20945a = new SketchSmoothDelegate(context, attributeSet);
        setWillNotDraw(false);
    }

    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.f20945a.a(canvas);
        super.onDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f20945a.a(i, i2, i3, i4);
    }

    public void updateReactRadius(boolean z, boolean z2, boolean z3, boolean z4) {
        this.f20945a.a(z, z2, z3, z4);
        invalidate();
    }

    public void updateBgColor(int i) {
        this.f20945a.a(i);
        invalidate();
    }

    /* access modifiers changed from: protected */
    public Path getOutline() {
        return this.f20945a.a();
    }
}
