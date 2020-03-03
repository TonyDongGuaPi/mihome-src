package net.qiujuer.genius.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.mi.mistatistic.sdk.data.EventData;
import net.qiujuer.genius.ui.R;
import net.qiujuer.genius.ui.compat.UiCompat;
import net.qiujuer.genius.ui.drawable.LoadingCircleDrawable;
import net.qiujuer.genius.ui.drawable.LoadingDrawable;
import net.qiujuer.genius.ui.drawable.LoadingLineDrawable;

public class Loading extends View {
    public static int STYLE_CIRCLE = 1;
    public static int STYLE_LINE = 2;

    /* renamed from: a  reason: collision with root package name */
    private LoadingDrawable f3160a;
    private boolean b;
    private boolean c;

    public Loading(Context context) {
        super(context);
        a((AttributeSet) null, R.attr.gLoadingStyle, R.style.Genius_Widget_Loading);
    }

    public Loading(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, R.attr.gLoadingStyle, R.style.Genius_Widget_Loading);
    }

    public Loading(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet, i, R.style.Genius_Widget_Loading);
    }

    @TargetApi(21)
    public Loading(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(attributeSet, i, i2);
    }

    private void a(AttributeSet attributeSet, int i, int i2) {
        Context context = getContext();
        Resources resources = getResources();
        if (attributeSet == null) {
            setProgressStyle(STYLE_CIRCLE);
            return;
        }
        int i3 = (int) (resources.getDisplayMetrics().density * 2.0f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Loading, i, i2);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.Loading_gBackgroundLineSize, i3);
        int dimensionPixelOffset2 = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.Loading_gForegroundLineSize, i3);
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.Loading_gBackgroundColor);
        int defaultColor = colorStateList != null ? colorStateList.getDefaultColor() : 0;
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.Loading_gForegroundColor, R.array.g_default_loading_fg);
        int i4 = obtainStyledAttributes.getInt(R.styleable.Loading_gProgressStyle, 1);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.Loading_gAutoRun, true);
        float f = obtainStyledAttributes.getFloat(R.styleable.Loading_gProgressFloat, 0.0f);
        obtainStyledAttributes.recycle();
        setProgressStyle(i4);
        setAutoRun(z);
        setProgress(f);
        setBackgroundLineSize(dimensionPixelOffset);
        setForegroundLineSize(dimensionPixelOffset2);
        setBackgroundColor(defaultColor);
        if (!isInEditMode()) {
            String resourceTypeName = resources.getResourceTypeName(resourceId);
            char c2 = 65535;
            try {
                int hashCode = resourceTypeName.hashCode();
                if (hashCode != 93090393) {
                    if (hashCode == 94842723) {
                        if (resourceTypeName.equals("color")) {
                            c2 = 0;
                        }
                    }
                } else if (resourceTypeName.equals(EventData.d)) {
                    c2 = 1;
                }
                switch (c2) {
                    case 0:
                        setForegroundColor(resources.getColor(resourceId));
                        return;
                    case 1:
                        setForegroundColor(resources.getIntArray(resourceId));
                        return;
                    default:
                        setForegroundColor(resources.getIntArray(R.array.g_default_loading_fg));
                        return;
                }
            } catch (Exception unused) {
                setForegroundColor(resources.getIntArray(R.array.g_default_loading_fg));
            }
        }
    }

    public void start() {
        this.f3160a.start();
        this.c = false;
    }

    public void stop() {
        this.f3160a.stop();
        this.c = false;
    }

    public boolean isRunning() {
        return this.f3160a.isRunning();
    }

    public void setBackgroundLineSize(int i) {
        this.f3160a.b((float) i);
        invalidate();
        requestLayout();
    }

    public void setForegroundLineSize(int i) {
        this.f3160a.c((float) i);
        invalidate();
        requestLayout();
    }

    public float getBackgroundLineSize() {
        return this.f3160a.b();
    }

    public float getForegroundLineSize() {
        return this.f3160a.c();
    }

    public void setBackgroundColor(int i) {
        this.f3160a.a(i);
        invalidate();
    }

    public void setBackgroundColorRes(int i) {
        ColorStateList b2 = UiCompat.b(getResources(), i);
        if (b2 == null) {
            setBackgroundColor(0);
        } else {
            setBackgroundColor(b2.getDefaultColor());
        }
    }

    public int getBackgroundColor() {
        return this.f3160a.d();
    }

    public void setForegroundColor(int i) {
        setForegroundColor(new int[]{i});
    }

    public void setForegroundColor(int[] iArr) {
        this.f3160a.a(iArr);
        invalidate();
    }

    public int[] getForegroundColor() {
        return this.f3160a.e();
    }

    public float getProgress() {
        return this.f3160a.g();
    }

    public void setProgress(float f) {
        this.f3160a.d(f);
        invalidate();
    }

    public void setAutoRun(boolean z) {
        this.b = z;
    }

    public boolean isAutoRun() {
        return this.b;
    }

    public void setProgressStyle(int i) {
        LoadingDrawable loadingDrawable;
        if (i == STYLE_CIRCLE) {
            Resources resources = getResources();
            loadingDrawable = new LoadingCircleDrawable(resources.getDimensionPixelOffset(R.dimen.g_loading_minSize), resources.getDimensionPixelOffset(R.dimen.g_loading_maxSize));
        } else {
            loadingDrawable = i == STYLE_LINE ? new LoadingLineDrawable() : null;
        }
        if (loadingDrawable != null) {
            loadingDrawable.setCallback(this);
            this.f3160a = loadingDrawable;
            invalidate();
            requestLayout();
            return;
        }
        throw new NullPointerException("LoadingDrawable is null, You can only set the STYLE_CIRCLE and STYLE_LINE parameters.");
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int intrinsicHeight = this.f3160a.getIntrinsicHeight() + getPaddingTop() + getPaddingBottom();
        int intrinsicWidth = this.f3160a.getIntrinsicWidth() + getPaddingLeft() + getPaddingRight();
        if (mode != 1073741824) {
            size = mode == Integer.MIN_VALUE ? Math.min(size, intrinsicWidth) : intrinsicWidth;
        }
        if (mode2 != 1073741824) {
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(size2, intrinsicHeight) : intrinsicHeight;
        }
        setMeasuredDimension(size, size2);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f3160a.setBounds(getPaddingLeft(), getPaddingTop(), i - getPaddingRight(), i2 - getPaddingBottom());
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return drawable == this.f3160a || super.verifyDrawable(drawable);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f3160a.draw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        a(i);
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        a(i);
    }

    private void a(int i) {
        if (this.f3160a != null) {
            if (i == 0) {
                if (this.c) {
                    start();
                }
            } else if (this.f3160a.isRunning()) {
                this.c = true;
                this.f3160a.stop();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.b && this.f3160a.g() == 0.0f) {
            if (getVisibility() == 0) {
                this.f3160a.start();
            } else {
                this.c = true;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f3160a.stop();
    }
}
