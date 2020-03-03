package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public abstract class BaseSeekbar extends BaseProgressBar {
    private static final int b = 255;

    /* renamed from: a  reason: collision with root package name */
    private int f18774a = 1;
    private float c;
    boolean mIsUserSeekable = true;
    protected Drawable mThumb;
    protected int mThumbOffset;
    float mTouchProgressOffset;

    /* access modifiers changed from: package-private */
    public void onKeyChange() {
    }

    /* access modifiers changed from: package-private */
    public void onStartTrackingTouch() {
    }

    /* access modifiers changed from: package-private */
    public void onStopTrackingTouch() {
    }

    public BaseSeekbar(Context context) {
        super(context);
    }

    public BaseSeekbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int a2 = DisplayUtils.a(getContext(), 5.0f);
        setPadding(a2, a2, a2, a2);
        setThumb(getResources().getDrawable(R.drawable.color_seekbar_thum));
        setThumbOffset(DisplayUtils.a(getContext(), 5.0f));
        this.c = 0.5f;
    }

    public void setThumb(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(this);
        }
        this.mThumb = drawable;
        invalidate();
    }

    public int getThumbOffset() {
        return this.mThumbOffset;
    }

    public void setThumbOffset(int i) {
        this.mThumbOffset = i;
        invalidate();
    }

    public void setKeyProgressIncrement(int i) {
        if (i < 0) {
            i = -i;
        }
        this.f18774a = i;
    }

    public int getKeyProgressIncrement() {
        return this.f18774a;
    }

    public synchronized void setMax(int i) {
        super.setMax(i);
        if (this.f18774a == 0 || getMax() / this.f18774a > 20) {
            setKeyProgressIncrement(Math.max(1, Math.round(((float) getMax()) / 20.0f)));
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return drawable == this.mThumb || super.verifyDrawable(drawable);
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable != null) {
            progressDrawable.setAlpha(isEnabled() ? 255 : (int) (this.c * 255.0f));
        }
        if (this.mThumb != null && this.mThumb.isStateful()) {
            this.mThumb.setState(getDrawableState());
        }
    }

    /* access modifiers changed from: package-private */
    public void onProgressRefresh(float f, boolean z) {
        Drawable drawable = this.mThumb;
        if (drawable != null) {
            setThumbPos(getWidth(), drawable, f, Integer.MIN_VALUE);
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        int i5;
        Drawable currentDrawable = getCurrentDrawable();
        Drawable drawable = this.mThumb;
        if (drawable == null) {
            i5 = 0;
        } else {
            i5 = drawable.getIntrinsicHeight();
        }
        int min = Math.min(this.mMaxHeight, (i2 - getPaddingTop()) - getPaddingBottom());
        int max = getMax();
        float progress = max > 0 ? ((float) getProgress()) / ((float) max) : 0.0f;
        if (i5 > min) {
            if (drawable != null) {
                setThumbPos(i, drawable, progress, 0);
            }
            int i6 = (i5 - min) / 2;
            if (currentDrawable != null) {
                currentDrawable.setBounds(0, i6, (i - getPaddingRight()) - getPaddingLeft(), ((i2 - getPaddingBottom()) - i6) - getPaddingTop());
                return;
            }
            return;
        }
        if (currentDrawable != null) {
            currentDrawable.setBounds(0, 0, (i - getPaddingRight()) - getPaddingLeft(), (i2 - getPaddingBottom()) - getPaddingTop());
        }
        int i7 = (min - i5) / 2;
        if (drawable != null) {
            setThumbPos(i, drawable, progress, i7);
        }
    }

    /* access modifiers changed from: protected */
    public void setThumbPos(int i, Drawable drawable, float f, int i2) {
        int i3;
        int paddingLeft = (i - getPaddingLeft()) - getPaddingRight();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int i4 = (int) (f * ((float) ((paddingLeft - intrinsicWidth) + (this.mThumbOffset * 2))));
        if (i2 == Integer.MIN_VALUE) {
            Rect bounds = drawable.getBounds();
            i2 = bounds.top;
            i3 = bounds.bottom;
        } else {
            i3 = i2 + intrinsicHeight;
        }
        drawable.setBounds(i4, i2, intrinsicWidth + i4, i3);
    }

    /* access modifiers changed from: protected */
    public synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mThumb != null) {
            canvas.save();
            canvas.translate((float) (getPaddingLeft() - this.mThumbOffset), (float) getPaddingTop());
            this.mThumb.draw(canvas);
            canvas.restore();
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void onMeasure(int i, int i2) {
        int i3;
        Drawable currentDrawable = getCurrentDrawable();
        int i4 = 0;
        int intrinsicHeight = this.mThumb == null ? 0 : this.mThumb.getIntrinsicHeight();
        if (currentDrawable != null) {
            i4 = Math.max(this.mMinWidth, Math.min(this.mMaxWidth, currentDrawable.getIntrinsicWidth()));
            i3 = Math.max(intrinsicHeight, Math.max(this.mMinHeight, Math.min(this.mMaxHeight, currentDrawable.getIntrinsicHeight())));
        } else {
            i3 = 0;
        }
        setMeasuredDimension(resolveSize(i4 + getPaddingLeft() + getPaddingRight(), i), resolveSize(i3 + getPaddingTop() + getPaddingBottom(), i2));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mIsUserSeekable || !isEnabled()) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                setPressed(true);
                onStartTrackingTouch();
                a(motionEvent);
                break;
            case 1:
                a(motionEvent);
                onStopTrackingTouch();
                setPressed(false);
                break;
            case 2:
                a(motionEvent);
                a();
                break;
            case 3:
                onStopTrackingTouch();
                setPressed(false);
                break;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.view.MotionEvent r5) {
        /*
            r4 = this;
            int r0 = r4.getWidth()
            int r1 = r4.getPaddingLeft()
            int r1 = r0 - r1
            int r2 = r4.getPaddingRight()
            int r1 = r1 - r2
            float r5 = r5.getX()
            int r5 = (int) r5
            int r2 = r4.getPaddingLeft()
            r3 = 0
            if (r5 >= r2) goto L_0x001e
            r5 = 0
        L_0x001c:
            r0 = 0
            goto L_0x0032
        L_0x001e:
            int r2 = r4.getPaddingRight()
            int r0 = r0 - r2
            if (r5 <= r0) goto L_0x0028
            r5 = 1065353216(0x3f800000, float:1.0)
            goto L_0x001c
        L_0x0028:
            int r0 = r4.getPaddingLeft()
            int r5 = r5 - r0
            float r5 = (float) r5
            float r0 = (float) r1
            float r5 = r5 / r0
            float r0 = r4.mTouchProgressOffset
        L_0x0032:
            int r1 = r4.getMax()
            float r1 = (float) r1
            float r5 = r5 * r1
            float r5 = r5 + r0
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x0040
            r5 = 0
            goto L_0x0045
        L_0x0040:
            int r0 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x0045
            r5 = r1
        L_0x0045:
            int r5 = (int) r5
            r0 = 1
            r4.setProgress(r5, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.widget.BaseSeekbar.a(android.view.MotionEvent):void");
    }

    private void a() {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        int progress = getProgress();
        switch (i) {
            case 21:
                if (progress > 0) {
                    setProgress(progress - this.f18774a, true);
                    onKeyChange();
                    return true;
                }
                break;
            case 22:
                if (progress < getMax()) {
                    setProgress(progress + this.f18774a, true);
                    onKeyChange();
                    return true;
                }
                break;
        }
        return super.onKeyDown(i, keyEvent);
    }
}
