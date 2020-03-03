package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.libra.Color;
import com.xiaomi.smarthome.library.R;

public class CustomCircleProgressBar extends View {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18796a = 100;
    private static final int b = 10;
    private static final int c = -1;
    private static final int d = 0;
    private CustomCircleAttribute e = new CustomCircleAttribute();
    private Drawable f = null;
    private int g = 100;
    private int h = 0;

    public CustomCircleProgressBar(Context context) {
        super(context);
    }

    public CustomCircleProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CustomCircleProgressBar);
        this.g = obtainStyledAttributes.getInteger(R.styleable.CustomCircleProgressBar_max, 100);
        this.e.a(obtainStyledAttributes.getInt(R.styleable.CustomCircleProgressBar_paint_width, 10));
        this.e.b(obtainStyledAttributes.getColor(R.styleable.CustomCircleProgressBar_paint_color, -1));
        this.e.b = obtainStyledAttributes.getInt(R.styleable.CustomCircleProgressBar_inside_indent, 0);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        this.f = getBackground();
        if (this.f != null) {
            size = this.f.getMinimumWidth();
            size2 = this.f.getMinimumHeight();
        }
        setMeasuredDimension(resolveSize(size, i), resolveSize(size2, i2));
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.e.a(i, i2);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f == null) {
            canvas.drawArc(this.e.f18797a, 0.0f, 360.0f, true, this.e.g);
        }
        Canvas canvas2 = canvas;
        canvas2.drawArc(this.e.f18797a, (float) this.e.e, (((float) this.h) / ((float) this.g)) * 360.0f, false, this.e.f);
    }

    public synchronized void setMax(int i) {
        this.g = i;
        if (this.g < 0) {
            this.g = 0;
        }
        if (this.g < this.h) {
            this.g = this.h;
        }
        invalidate();
    }

    public synchronized int getMax() {
        return this.g;
    }

    public synchronized void setProgress(int i) {
        this.h = i;
        if (this.h < 0) {
            this.h = 0;
        }
        if (this.h > this.g) {
            this.h = this.g;
        }
        invalidate();
    }

    public synchronized int getProgress() {
        return this.h;
    }

    class CustomCircleAttribute {

        /* renamed from: a  reason: collision with root package name */
        public RectF f18797a = new RectF();
        public int b = 0;
        public int c = 0;
        public int d = -1;
        public int e = -90;
        public Paint f = new Paint();
        public Paint g;

        public CustomCircleAttribute() {
            this.f.setAntiAlias(true);
            this.f.setStyle(Paint.Style.STROKE);
            this.f.setStrokeWidth((float) this.c);
            this.f.setColor(this.d);
            this.g = new Paint();
            this.g.setAntiAlias(true);
            this.g.setStyle(Paint.Style.STROKE);
            this.g.setStrokeWidth((float) this.c);
            this.g.setColor(Color.c);
        }

        public void a(int i) {
            this.c = i;
            float f2 = (float) i;
            this.f.setStrokeWidth(f2);
            this.g.setStrokeWidth(f2);
        }

        public void b(int i) {
            this.d = i;
            this.f.setColor(i);
        }

        public void a(int i, int i2) {
            if (this.b != 0) {
                this.f18797a.set((float) ((this.c / 2) + this.b), (float) ((this.c / 2) + this.b), (float) ((i - (this.c / 2)) - this.b), (float) ((i2 - (this.c / 2)) - this.b));
                return;
            }
            int paddingLeft = CustomCircleProgressBar.this.getPaddingLeft();
            int paddingRight = CustomCircleProgressBar.this.getPaddingRight();
            this.f18797a.set((float) (paddingLeft + (this.c / 2)), (float) (CustomCircleProgressBar.this.getPaddingTop() + (this.c / 2)), (float) ((i - paddingRight) - (this.c / 2)), (float) ((i2 - CustomCircleProgressBar.this.getPaddingBottom()) - (this.c / 2)));
        }
    }
}
