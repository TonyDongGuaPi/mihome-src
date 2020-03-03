package com.xiaomi.payment.ui.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.xiaomi.payment.platform.R;

public class PaySuccessView extends View {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12468a = "PaySucessedIconView";
    private static int b = 0;
    private static final int c = 2;
    private static final float d = 1.0f;
    private static final float e = 0.2f;
    private static final float f = 0.02f;
    private static final float g = 0.05f;
    private static final float h = 0.1f;
    private static final float i = 0.2f;
    private static final float j = 5.0f;
    private static final float k = 7.0f;
    private float A;
    private float B;
    private Path C;
    private RectF D;
    private float l;
    private float m;
    private float n;
    private float o;
    private float p;
    private float q;
    private float r;
    private float s;
    private float t;
    private float u;
    private float v;
    private float w;
    private float x;
    private Paint y;
    private float z;

    public PaySuccessView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PaySuccessView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PaySuccessView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        b = (int) getResources().getDimension(R.dimen.mibi_result_sucess_default_size);
        this.C = new Path();
        this.y = new Paint();
        this.y.setAntiAlias(false);
        this.y.setColor(getResources().getColor(R.color.mibi_text_color_counter_pay_succeed));
        this.y.setStyle(Paint.Style.STROKE);
        this.z = 0.0f;
        this.A = 0.0f;
        this.B = 0.0f;
        this.D = new RectF();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int size = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode != 1073741824) {
            int i4 = b;
            size = mode == Integer.MIN_VALUE ? Math.min(size, i4) : i4;
        }
        if (mode2 != 1073741824) {
            int i5 = b;
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(size2, i5) : i5;
        }
        int min = Math.min(size, size2);
        setMeasuredDimension(min, min);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.v = (float) i2;
        this.l = (float) (i2 / 4);
        this.m = (float) (i3 / 2);
        this.n = (float) ((i2 * 5) / 12);
        this.o = ((float) i3) * 0.6666667f;
        this.p = (float) ((i2 * 3) / 4);
        this.q = (float) (i3 / 3);
        this.w = (float) Math.sqrt(Math.pow((double) (this.n - this.l), 2.0d) + Math.pow((double) (this.o - this.m), 2.0d));
        this.x = (float) Math.sqrt(Math.pow((double) (this.p - this.n), 2.0d) + Math.pow((double) (this.q - this.o), 2.0d));
        this.r = Math.abs(this.o - this.m) / this.w;
        this.s = Math.abs(this.n - this.l) / this.w;
        this.t = Math.abs(this.q - this.o) / this.x;
        this.u = Math.abs(this.p - this.n) / this.x;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.y.setStrokeWidth(j);
        this.D.set(2.0f, 2.0f, this.v - 2.0f, this.v - 2.0f);
        canvas.drawArc(this.D, 0.0f, this.z * 360.0f, false, this.y);
        if (this.A > 0.0f) {
            this.y.setStrokeWidth(k);
            this.C.reset();
            this.C.moveTo(this.l, this.m);
            this.C.lineTo(this.l + (this.w * this.A * this.s), this.m + (this.w * this.A * this.r));
            if (this.B > 0.0f) {
                this.C.quadTo(this.n + (this.x * (this.B - 0.2f) * this.u), this.o - ((this.x * (this.B - 0.2f)) * this.t), this.n + (this.x * this.B * this.u), this.o - ((this.x * this.B) * this.t));
            }
            canvas.drawPath(this.C, this.y);
        }
        a();
    }

    private void a() {
        if (this.z >= 1.0f && this.A >= 1.0f && this.B >= 1.0f) {
            return;
        }
        if (this.z < 1.0f) {
            if (this.z < 0.2f) {
                this.z += f;
            } else {
                this.z += 0.05f;
            }
            invalidate();
        } else if (this.A < 1.0f) {
            this.A += 0.1f;
            invalidate();
        } else {
            this.B += 0.2f;
            invalidate();
        }
    }
}
