package com.xiaomi.smarthome.wificonfig;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.Paint;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import com.xiaomi.smarthome.R;

public class GifView extends View {

    /* renamed from: a  reason: collision with root package name */
    private static final int f22895a = 1000;
    private int b;
    private Movie c;
    private long d;
    private int e;
    private float f;
    private float g;
    private float h;
    private int i;
    private int j;
    private boolean k;
    private volatile boolean l;

    public GifView(Context context) {
        this(context, (AttributeSet) null);
    }

    public GifView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GifView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.e = 0;
        this.k = true;
        this.l = false;
        a(context, attributeSet, i2);
    }

    @SuppressLint({"NewApi"})
    private void a(Context context, AttributeSet attributeSet, int i2) {
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(1, (Paint) null);
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.GifView, i2, -1);
        this.b = obtainStyledAttributes.getResourceId(1, -1);
        this.l = obtainStyledAttributes.getBoolean(3, false);
        obtainStyledAttributes.recycle();
        if (this.b != -1) {
            this.c = Movie.decodeStream(getResources().openRawResource(this.b));
        }
    }

    public void setMovieResource(int i2) {
        this.b = i2;
        this.c = Movie.decodeStream(getResources().openRawResource(this.b));
        requestLayout();
    }

    public void setMovie(Movie movie) {
        this.c = movie;
        requestLayout();
    }

    public Movie getMovie() {
        return this.c;
    }

    public void setMovieTime(int i2) {
        this.e = i2;
        invalidate();
    }

    public void setPaused(boolean z) {
        this.l = z;
        if (!z) {
            this.d = SystemClock.uptimeMillis() - ((long) this.e);
        }
        invalidate();
    }

    public boolean isPaused() {
        return this.l;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        if (this.c != null) {
            int width = this.c.width();
            int height = this.c.height();
            int size = View.MeasureSpec.getSize(i2);
            this.h = 1.0f / (((float) width) / ((float) size));
            this.i = size;
            this.j = (int) (((float) height) * this.h);
            setMeasuredDimension(this.i, this.j);
            return;
        }
        setMeasuredDimension(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        this.f = ((float) (getWidth() - this.i)) / 2.0f;
        this.g = ((float) (getHeight() - this.j)) / 2.0f;
        this.k = getVisibility() == 0;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.c == null) {
            return;
        }
        if (!this.l) {
            b();
            a(canvas);
            a();
            return;
        }
        a(canvas);
    }

    @SuppressLint({"NewApi"})
    private void a() {
        if (!this.k) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            postInvalidateOnAnimation();
        } else {
            invalidate();
        }
    }

    private void b() {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (this.d == 0) {
            this.d = uptimeMillis;
        }
        int duration = this.c.duration();
        if (duration == 0) {
            duration = 1000;
        }
        this.e = (int) ((uptimeMillis - this.d) % ((long) duration));
    }

    private void a(Canvas canvas) {
        this.c.setTime(this.e);
        canvas.save();
        canvas.scale(this.h, this.h);
        this.c.draw(canvas, this.f / this.h, this.g / this.h);
        canvas.restore();
    }

    @SuppressLint({"NewApi"})
    public void onScreenStateChanged(int i2) {
        super.onScreenStateChanged(i2);
        boolean z = true;
        if (i2 != 1) {
            z = false;
        }
        this.k = z;
        a();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"NewApi"})
    public void onVisibilityChanged(View view, int i2) {
        super.onVisibilityChanged(view, i2);
        this.k = i2 == 0;
        a();
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        this.k = i2 == 0;
        a();
    }
}
