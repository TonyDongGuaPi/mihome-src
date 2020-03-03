package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import com.xiaomi.smarthome.R;
import java.io.PrintStream;

public class LoadingView extends View {

    /* renamed from: a  reason: collision with root package name */
    private int f18880a = 3;
    private int b = 5;
    private int c = 1275068416;
    private int d = 419430400;
    private int e = 300;
    private int f;
    private RectF g = new RectF();
    private Paint h = new Paint();
    private int i = 5;
    private int j;

    public LoadingView(Context context) {
        super(context);
        a((AttributeSet) null);
    }

    public LoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
    }

    public LoadingView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(attributeSet);
    }

    @RequiresApi(api = 21)
    public LoadingView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        a(attributeSet);
    }

    private void a(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.LoadingView);
            this.c = obtainStyledAttributes.getColor(1, this.c);
            this.d = obtainStyledAttributes.getColor(0, this.d);
            this.b = obtainStyledAttributes.getDimensionPixelSize(3, this.b);
            this.i = obtainStyledAttributes.getDimensionPixelSize(4, this.i);
            this.f18880a = obtainStyledAttributes.getInteger(2, this.f18880a);
            this.e = obtainStyledAttributes.getInteger(5, this.e);
            obtainStyledAttributes.recycle();
        }
    }

    public static void main(String[] strArr) {
        PrintStream printStream = System.out;
        printStream.println(Integer.toHexString(25) + "");
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        if (mode == 1073741824) {
            if (mode2 == 1073741824) {
                setDotSize(Math.min(size2, (size - (this.b * (this.f18880a - 1))) / this.f18880a));
                setMeasuredDimension(size + getPaddingLeft() + getPaddingRight(), size2 + getPaddingTop() + getPaddingBottom());
                return;
            }
            setDotSize((size - (this.b * (this.f18880a - 1))) / this.f18880a);
            setMeasuredDimension(size + getPaddingLeft() + getPaddingRight(), this.f + getPaddingTop() + getPaddingBottom());
        } else if (mode2 == 1073741824) {
            setDotSize(size2);
            setMeasuredDimension((this.f * this.f18880a) + (this.b * (this.f18880a - 1)) + getPaddingLeft() + getPaddingRight(), size2 + getPaddingTop() + getPaddingBottom());
        } else {
            setDotSize(((int) getContext().getResources().getDisplayMetrics().density) * 5);
            setMeasuredDimension((this.f * this.f18880a) + (this.b * (this.f18880a - 1)) + getPaddingLeft() + getPaddingRight(), this.f + getPaddingTop() + getPaddingBottom());
        }
    }

    private void setDotSize(int i2) {
        if (this.i == 0) {
            this.f = i2;
        } else {
            this.f = this.i;
        }
    }

    public void setDotCount(int i2) {
        this.f18880a = i2;
    }

    public void setDotDistance(int i2) {
        this.b = i2;
    }

    public void setDotColor(int i2, int i3) {
        this.c = i2;
        this.d = i3;
    }

    public void setTimeInterval(int i2) {
        this.e = i2;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.f18880a != 0) {
            int alpha = Color.alpha(this.c);
            int red = Color.red(this.c);
            int green = Color.green(this.c);
            int blue = Color.blue(this.c);
            int alpha2 = Color.alpha(this.d);
            int red2 = Color.red(this.d);
            int green2 = Color.green(this.d);
            int blue2 = Color.blue(this.d);
            float f2 = ((float) (alpha2 - alpha)) / ((float) this.f18880a);
            float f3 = ((float) (red2 - red)) / ((float) this.f18880a);
            float f4 = ((float) (green2 - green)) / ((float) this.f18880a);
            float f5 = ((float) (blue2 - blue)) / ((float) this.f18880a);
            int width = (((((getWidth() - getPaddingLeft()) - getPaddingRight()) - (this.f18880a * this.f)) - (this.b * (this.f18880a - 1))) / 2) + getPaddingLeft();
            int height = ((((getHeight() - getPaddingTop()) - getPaddingBottom()) - this.f) / 2) + getPaddingTop();
            int i2 = this.j;
            this.j = i2 + 1;
            int i3 = 0;
            while (i3 < this.f18880a) {
                this.g.top = (float) height;
                int i4 = height;
                this.g.left = (float) ((((i2 + i3) % this.f18880a) * (this.f + this.b)) + width);
                this.g.bottom = this.g.top + ((float) this.f);
                this.g.right = this.g.left + ((float) this.f);
                float f6 = (float) i3;
                this.h.setColor(Color.argb(((int) (f2 * f6)) + alpha, ((int) (f3 * f6)) + red, ((int) (f4 * f6)) + green, ((int) (f6 * f5)) + blue));
                canvas.drawOval(this.g, this.h);
                i3++;
                height = i4;
            }
            postInvalidateDelayed((long) this.e);
        }
    }
}
