package com.xiaomi.smarthome.newui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.xiaomi.smarthome.miotcard.R;

public class RoundedGradientSeekBar extends AppCompatSeekBar {

    /* renamed from: a  reason: collision with root package name */
    private GradientProgressDrawable f20884a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private boolean j;

    public RoundedGradientSeekBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public RoundedGradientSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundedGradientSeekBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        setProgressDrawable((Drawable) null);
        a(context, attributeSet, i2);
        setLayerType(1, (Paint) null);
    }

    private void a(Context context, AttributeSet attributeSet, int i2) {
        this.f20884a = new GradientProgressDrawable();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundedGradientSeekBar, i2, 0);
        this.b = obtainStyledAttributes.getResourceId(R.styleable.RoundedGradientSeekBar_color_from, R.color.gradient_yellow_start);
        this.c = obtainStyledAttributes.getResourceId(R.styleable.RoundedGradientSeekBar_color_to, R.color.gradient_yellow_end);
        this.d = R.color.gradient_unable_start;
        this.e = R.color.gradient_unable_end;
        this.f20884a.setColors(new int[]{getResources().getColor(this.b), getResources().getColor(this.c)});
        this.f = obtainStyledAttributes.getResourceId(R.styleable.RoundedGradientSeekBar_thumb_max_cirlce_color, this.c);
        this.g = obtainStyledAttributes.getResourceId(R.styleable.RoundedGradientSeekBar_thumb_min_cirlce_color, this.b);
        this.h = this.c;
        this.i = this.d;
        this.f20884a.a(getResources().getColor(this.f), getResources().getColor(this.g));
        this.f20884a.a((int) obtainStyledAttributes.getDimension(R.styleable.RoundedGradientSeekBar_thumb_max_radius, 0.0f));
        this.f20884a.b((int) obtainStyledAttributes.getDimension(R.styleable.RoundedGradientSeekBar_thumb_max_radius, 10.0f));
        this.f20884a.b(0, getMax());
        obtainStyledAttributes.recycle();
    }

    public void setCanDrag(boolean z) {
        this.j = z;
    }

    public void setColorFrom(int i2) {
        this.b = i2;
        this.f20884a.setColors(new int[]{getResources().getColor(this.b), getResources().getColor(this.c)});
    }

    public void setColorTo(int i2) {
        this.c = i2;
        this.f20884a.setColors(new int[]{getResources().getColor(this.b), getResources().getColor(this.c)});
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.save();
        this.f20884a.c(getProgress());
        this.f20884a.draw(canvas);
        canvas.restore();
    }

    public synchronized int getProgress() {
        int progress = super.getProgress();
        int height = getHeight() / 2;
        int width = getWidth();
        if (width == 0) {
            return 0;
        }
        return progress - ((int) (((float) (height / width)) / ((float) getMax())));
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.f20884a.setBounds(new Rect(0, 0, i2, i3));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.j) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void updateEnableUI(boolean z) {
        if (z) {
            this.f20884a.setColors(new int[]{getResources().getColor(this.b), getResources().getColor(this.c)});
            this.f20884a.a(getResources().getColor(this.f), getResources().getColor(this.g));
        } else {
            this.f20884a.setColors(new int[]{getResources().getColor(this.d), getResources().getColor(this.e)});
            this.f20884a.a(getResources().getColor(this.h), getResources().getColor(this.i));
        }
        invalidate();
    }
}
