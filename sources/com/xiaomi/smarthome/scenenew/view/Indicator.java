package com.xiaomi.smarthome.scenenew.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class Indicator extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private Paint f22034a;
    private int b;
    private int c;
    private int d;
    private int e = 9;
    private int f;
    private int g;
    private int h;

    public Indicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBackgroundColor(0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.IndicatorStyle, 0, 0);
        this.f = obtainStyledAttributes.getColor(0, 255);
        obtainStyledAttributes.recycle();
        this.f22034a = new Paint();
        this.f22034a.setColor(this.f);
        this.f22034a.setAntiAlias(true);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.b = getMeasuredHeight();
        this.g = getPaddingTop();
        this.h = getPaddingBottom();
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight() + this.e + this.h + this.g;
        LogUtil.a("Indicator", "height" + measuredHeight + "mHeight" + this.e + " mTop" + this.b + "paddingTop " + this.g + "  paddingBottom" + this.h + "   width" + measuredWidth);
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    public void scroll(int i, float f2) {
        View childAt = getChildAt(i);
        if (childAt != null) {
            this.d = childAt.getMeasuredWidth();
        } else {
            this.d = DisplayUtils.a(62.0f);
        }
        this.c = 0;
        for (int i2 = 0; i2 < i; i2++) {
            this.c += getChildAt(i2).getMeasuredWidth();
        }
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.drawRect(new Rect(this.c, this.b + this.g, this.c + this.d, this.b + this.g + this.e), this.f22034a);
        super.onDraw(canvas);
    }
}
