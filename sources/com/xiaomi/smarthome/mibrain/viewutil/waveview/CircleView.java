package com.xiaomi.smarthome.mibrain.viewutil.waveview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.R;

public class CircleView extends View {
    private static final int i = Color.rgb(0, 116, 193);

    /* renamed from: a  reason: collision with root package name */
    private float f10750a;
    private float b;
    private float c;
    private Paint d;
    private float e;
    private boolean f;
    private int g;
    private float h;

    public void receiveVoice(double d2) {
    }

    public void setmPaint(Paint paint) {
        this.d = paint;
    }

    public void setRadius(float f2) {
        Log.d("CircleView", "setRadius" + f2);
        if (this.e != f2) {
            this.e = f2;
            a();
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        this.f10750a = ((float) measuredWidth) * 0.5f;
        this.b = ((float) measuredHeight) * 0.5f;
        this.c = ((float) Math.max(measuredWidth, measuredHeight)) * 0.5f;
        this.e = this.c;
        setMeasuredDimension(measuredWidth, measuredHeight);
        Log.d("CircleView", "onMeasure" + measuredWidth + "*" + measuredHeight);
    }

    public void setUseRing(boolean z) {
        if (this.f != z) {
            this.f = z;
            a();
            invalidate();
        }
    }

    public void setColor(int i2) {
        if (this.g != i2) {
            this.g = i2;
            a();
            invalidate();
        }
    }

    public void setStrokeWidth(float f2) {
        if (this.h != f2) {
            this.h = f2;
            a();
            invalidate();
        }
    }

    private void a() {
        if (this.d != null) {
            this.d = null;
        }
    }

    public CircleView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CircleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public CircleView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f = false;
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircleView);
        this.g = obtainStyledAttributes.getColor(0, getResources().getColor(R.color.mi_brain_circle_view_green));
        this.e = (float) obtainStyledAttributes.getDimensionPixelSize(1, a(30.0f));
        this.h = (float) a(1.0f);
        this.f = false;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.d == null) {
            this.d = new Paint();
            this.d.setColor(this.g);
            this.d.setAntiAlias(true);
            this.d.setStrokeWidth(this.f ? this.h : 0.0f);
            this.d.setStyle(this.f ? Paint.Style.STROKE : Paint.Style.FILL);
        }
        Log.d("CircleView", "onDraw" + this.f10750a + Constants.Name.Y + this.b);
        if (this.e > ((float) a(50.0f))) {
            canvas.drawCircle(this.f10750a, this.b, (float) a(26.0f), this.d);
        } else {
            canvas.drawCircle(this.f10750a, this.b, this.e, this.d);
        }
    }

    private int a(float f2) {
        return (int) ((f2 * getResources().getDisplayMetrics().density) + 0.5f);
    }

    public float getRadius() {
        return this.e;
    }
}
