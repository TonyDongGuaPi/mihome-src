package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.widget.RemoteViews;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

@RemoteViews.RemoteView
public class BaseProgressBar extends View {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18771a = 10000;
    private static final int b = 200;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private Transformation h;
    private AlphaAnimation i;
    private Drawable j;
    private Drawable k;
    private Drawable l;
    private boolean m;
    int mMaxHeight;
    int mMaxWidth;
    int mMinHeight;
    int mMinWidth;
    private Interpolator n;
    /* access modifiers changed from: private */
    public RefreshProgressRunnable o;
    private long p;
    private boolean q;
    private long r;
    private boolean s;

    private Drawable a(Drawable drawable, boolean z) {
        return drawable;
    }

    /* access modifiers changed from: package-private */
    public void onProgressRefresh(float f2, boolean z) {
    }

    public BaseProgressBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public BaseProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
        a(this.g, this.mMinWidth, this.mMinHeight, this.mMaxWidth, this.mMaxHeight, this.f, this.e, this.c, getResources().getDrawable(R.drawable.base_seekbar_progress));
    }

    private void a(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, Drawable drawable) {
        this.p = Thread.currentThread().getId();
        this.m = true;
        if (drawable != null) {
            setProgressDrawable(a(drawable, false));
        }
        this.g = i2;
        this.mMinWidth = i3;
        this.mMaxWidth = i5;
        this.mMinHeight = i4;
        this.mMaxHeight = i6;
        this.f = i7;
        setMax(i8);
        setProgress(i9);
        this.m = false;
    }

    /* access modifiers changed from: package-private */
    public Shape getDrawableShape() {
        return new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, (RectF) null, (float[]) null);
    }

    private void a() {
        this.e = 1000;
        this.c = 0;
        this.d = 0;
        this.g = 4000;
        this.f = 1;
        this.mMinWidth = 24;
        this.mMaxWidth = 48;
        this.mMinHeight = DisplayUtils.a(getContext(), 2.0f);
        this.mMaxHeight = DisplayUtils.a(getContext(), 2.0f);
    }

    public Drawable getProgressDrawable() {
        return this.k;
    }

    public void setProgressDrawable(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(this);
        }
        this.k = drawable;
        this.l = drawable;
        postInvalidate();
    }

    /* access modifiers changed from: package-private */
    public Drawable getCurrentDrawable() {
        return this.l;
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return drawable == this.k || drawable == this.j || super.verifyDrawable(drawable);
    }

    public void postInvalidate() {
        if (!this.m) {
            super.postInvalidate();
        }
    }

    private class RefreshProgressRunnable implements Runnable {
        private int b;
        private int c;
        private boolean d;

        RefreshProgressRunnable(int i, int i2, boolean z) {
            this.b = i;
            this.c = i2;
            this.d = z;
        }

        public void run() {
            BaseProgressBar.this.a(this.b, this.c, this.d);
            RefreshProgressRunnable unused = BaseProgressBar.this.o = this;
        }

        public void a(int i, int i2, boolean z) {
            this.b = i;
            this.c = i2;
            this.d = z;
        }
    }

    /* access modifiers changed from: private */
    public synchronized void a(int i2, int i3, boolean z) {
        float f2 = this.e > 0 ? ((float) i3) / ((float) this.e) : 0.0f;
        Drawable drawable = this.l;
        if (drawable != null) {
            Drawable drawable2 = null;
            if (drawable instanceof LayerDrawable) {
                drawable2 = ((LayerDrawable) drawable).findDrawableByLayerId(i2);
            }
            int i4 = (int) (10000.0f * f2);
            if (drawable2 != null) {
                drawable = drawable2;
            }
            drawable.setLevel(i4);
        } else {
            invalidate();
        }
        if (i2 == 16908301) {
            onProgressRefresh(f2, z);
        }
    }

    private synchronized void b(int i2, int i3, boolean z) {
        RefreshProgressRunnable refreshProgressRunnable;
        if (this.p == Thread.currentThread().getId()) {
            a(i2, i3, z);
        } else {
            if (this.o != null) {
                refreshProgressRunnable = this.o;
                this.o = null;
                refreshProgressRunnable.a(i2, i3, z);
            } else {
                refreshProgressRunnable = new RefreshProgressRunnable(i2, i3, z);
            }
            post(refreshProgressRunnable);
        }
    }

    public synchronized void setProgress(int i2) {
        setProgress(i2, false);
    }

    /* access modifiers changed from: package-private */
    public synchronized void setProgress(int i2, boolean z) {
        if (i2 < 0) {
            i2 = 0;
        }
        if (i2 > this.e) {
            i2 = this.e;
        }
        if (i2 != this.c) {
            this.c = i2;
            b(16908301, this.c, z);
        }
    }

    public synchronized int getProgress() {
        return this.c;
    }

    public synchronized int getMax() {
        return this.e;
    }

    public synchronized void setMax(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        if (i2 != this.e) {
            this.e = i2;
            postInvalidate();
            if (this.c > i2) {
                this.c = i2;
            }
        }
    }

    public void setInterpolator(Interpolator interpolator) {
        this.n = interpolator;
    }

    public Interpolator getInterpolator() {
        return this.n;
    }

    public void setVisibility(int i2) {
        if (getVisibility() != i2) {
            super.setVisibility(i2);
        }
    }

    public void invalidateDrawable(Drawable drawable) {
        if (this.s) {
            return;
        }
        if (verifyDrawable(drawable)) {
            Rect bounds = drawable.getBounds();
            int scrollX = getScrollX() + getPaddingLeft();
            int scrollY = getScrollY() + getPaddingTop();
            invalidate(bounds.left + scrollX, bounds.top + scrollY, bounds.right + scrollX, bounds.bottom + scrollY);
            return;
        }
        super.invalidateDrawable(drawable);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        int paddingRight = (i2 - getPaddingRight()) - getPaddingLeft();
        int paddingBottom = (i3 - getPaddingBottom()) - getPaddingTop();
        if (this.j != null) {
            this.j.setBounds(0, 0, paddingRight, paddingBottom);
        }
        if (this.k != null) {
            this.k.setBounds(0, 0, paddingRight, paddingBottom);
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable drawable = this.l;
        if (drawable != null) {
            canvas.save();
            canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            long drawingTime = getDrawingTime();
            if (this.i != null) {
                this.i.getTransformation(drawingTime, this.h);
                float alpha = this.h.getAlpha();
                try {
                    this.s = true;
                    drawable.setLevel((int) (alpha * 10000.0f));
                    this.s = false;
                    if (SystemClock.uptimeMillis() - this.r >= 200) {
                        this.r = SystemClock.uptimeMillis();
                        postInvalidateDelayed(200);
                    }
                } catch (Throwable th) {
                    this.s = false;
                    throw th;
                }
            }
            drawable.draw(canvas);
            canvas.restore();
            if (this.q && (drawable instanceof AnimationDrawable)) {
                ((AnimationDrawable) drawable).start();
                this.q = false;
            }
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void onMeasure(int i2, int i3) {
        int i4;
        Drawable drawable = this.l;
        int i5 = 0;
        if (drawable != null) {
            i5 = Math.max(this.mMinWidth, Math.min(this.mMaxWidth, drawable.getIntrinsicWidth()));
            i4 = Math.max(this.mMinHeight, Math.min(this.mMaxHeight, drawable.getIntrinsicHeight()));
        } else {
            i4 = 0;
        }
        setMeasuredDimension(resolveSize(i5 + getPaddingLeft() + getPaddingRight(), i2), resolveSize(i4 + getPaddingTop() + getPaddingBottom(), i3));
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        if (this.k != null && this.k.isStateful()) {
            this.k.setState(drawableState);
        }
        if (this.j != null && this.j.isStateful()) {
            this.j.setState(drawableState);
        }
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        int f18773a;
        int b;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f18773a = parcel.readInt();
            this.b = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f18773a);
            parcel.writeInt(this.b);
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f18773a = this.c;
        savedState.b = this.d;
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setProgress(savedState.f18773a);
    }
}
