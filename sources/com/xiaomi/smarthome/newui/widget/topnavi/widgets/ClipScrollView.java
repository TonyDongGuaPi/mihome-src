package com.xiaomi.smarthome.newui.widget.topnavi.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ScrollView;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.UIUtil;

public class ClipScrollView extends ScrollView {

    /* renamed from: a  reason: collision with root package name */
    private int f20946a;
    private int b;
    private Path c;

    public ClipScrollView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ClipScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ClipScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        canvas.clipPath(this.c);
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.f20946a != i || this.b != i2) {
            this.f20946a = i;
            this.b = i2;
            float a2 = (float) UIUtil.a(getContext(), 10.0d);
            this.c = new Path();
            this.c.addRoundRect(new RectF(0.0f, 0.0f, (float) this.f20946a, (float) (this.b * 2)), new float[]{a2, a2, a2, a2, 0.0f, 0.0f, 0.0f, 0.0f}, Path.Direction.CW);
        }
    }
}
