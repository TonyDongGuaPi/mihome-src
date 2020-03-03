package net.qiujuer.genius.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import net.qiujuer.genius.ui.R;
import net.qiujuer.genius.ui.Ui;
import net.qiujuer.genius.ui.drawable.TouchEffectDrawable;
import net.qiujuer.genius.ui.drawable.effect.EffectFactory;
import net.qiujuer.genius.ui.drawable.factory.ClipFilletFactory;

public class ImageView extends android.widget.ImageView implements TouchEffectDrawable.PerformClicker, TouchEffectDrawable.PerformLongClicker {
    private TouchEffectDrawable mTouchDrawable;

    public ImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.gImageViewStyle);
    }

    public ImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i, R.style.Genius_Widget_ImageView);
    }

    @TargetApi(21)
    public ImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet, i, i2);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(ImageView.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(ImageView.class.getName());
    }

    private void init(AttributeSet attributeSet, int i, int i2) {
        if (attributeSet != null) {
            Context context = getContext();
            Resources resources = getResources();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ImageView, i, i2);
            int i3 = obtainStyledAttributes.getInt(R.styleable.ImageView_gTouchEffect, 0);
            int color = obtainStyledAttributes.getColor(R.styleable.ImageView_gTouchColor, Ui.b);
            int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ImageView_gTouchCornerRadius, resources.getDimensionPixelOffset(R.dimen.g_imageView_touch_corners_radius));
            int dimensionPixelOffset2 = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ImageView_gTouchCornerRadiusTL, dimensionPixelOffset);
            int dimensionPixelOffset3 = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ImageView_gTouchCornerRadiusTR, dimensionPixelOffset);
            int dimensionPixelOffset4 = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ImageView_gTouchCornerRadiusBL, dimensionPixelOffset);
            int dimensionPixelOffset5 = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ImageView_gTouchCornerRadiusBR, dimensionPixelOffset);
            float f = obtainStyledAttributes.getFloat(R.styleable.ImageView_gTouchDurationRate, 1.0f);
            boolean z = obtainStyledAttributes.getBoolean(R.styleable.ImageView_android_enabled, i3 != 0);
            int i4 = obtainStyledAttributes.getInt(R.styleable.ImageView_gInterceptEvent, 1);
            obtainStyledAttributes.recycle();
            if (i3 != 0) {
                TouchEffectDrawable touchEffectDrawable = new TouchEffectDrawable();
                touchEffectDrawable.c(color);
                touchEffectDrawable.a(EffectFactory.a(i3));
                touchEffectDrawable.a(f);
                touchEffectDrawable.b(f);
                touchEffectDrawable.b(i4);
                if (!isInEditMode()) {
                    float f2 = (float) dimensionPixelOffset2;
                    float f3 = (float) dimensionPixelOffset3;
                    float f4 = (float) dimensionPixelOffset5;
                    float f5 = (float) dimensionPixelOffset4;
                    touchEffectDrawable.a((TouchEffectDrawable.ClipFactory) new ClipFilletFactory(new float[]{f2, f2, f3, f3, f4, f4, f5, f5}));
                }
                setTouchDrawable(touchEffectDrawable);
            }
            setEnabled(z);
        }
    }

    public TouchEffectDrawable getTouchDrawable() {
        return this.mTouchDrawable;
    }

    public void setTouchDrawable(TouchEffectDrawable touchEffectDrawable) {
        if (this.mTouchDrawable != touchEffectDrawable) {
            if (this.mTouchDrawable != null) {
                this.mTouchDrawable.setCallback((Drawable.Callback) null);
            }
            if (touchEffectDrawable != null) {
                touchEffectDrawable.setCallback(this);
                if (getLayerType() != 1) {
                    setLayerType(1, (Paint) null);
                }
            }
            this.mTouchDrawable = touchEffectDrawable;
        }
    }

    public void setLayerType(int i, Paint paint) {
        if (this.mTouchDrawable != null) {
            i = 1;
        }
        super.setLayerType(i, paint);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        TouchEffectDrawable touchEffectDrawable = this.mTouchDrawable;
        if (touchEffectDrawable != null) {
            touchEffectDrawable.setBounds(0, 0, getWidth(), getHeight());
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        TouchEffectDrawable touchEffectDrawable = this.mTouchDrawable;
        return (touchEffectDrawable != null && drawable == touchEffectDrawable) || super.verifyDrawable(drawable);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        TouchEffectDrawable touchEffectDrawable = this.mTouchDrawable;
        if (onTouchEvent && touchEffectDrawable != null && isEnabled()) {
            touchEffectDrawable.a(motionEvent);
        }
        return onTouchEvent;
    }

    /* access modifiers changed from: protected */
    public synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        TouchEffectDrawable touchEffectDrawable = this.mTouchDrawable;
        if (touchEffectDrawable != null) {
            touchEffectDrawable.draw(canvas);
        }
    }

    public boolean performClick() {
        TouchEffectDrawable touchEffectDrawable = this.mTouchDrawable;
        if (touchEffectDrawable != null) {
            return touchEffectDrawable.a((TouchEffectDrawable.PerformClicker) this) && super.performClick();
        }
        return super.performClick();
    }

    public boolean performLongClick() {
        TouchEffectDrawable touchEffectDrawable = this.mTouchDrawable;
        if (touchEffectDrawable != null) {
            return touchEffectDrawable.a((TouchEffectDrawable.PerformLongClicker) this) && super.performLongClick();
        }
        return super.performLongClick();
    }

    public void postPerformClick() {
        if (!post(new Runnable() {
            public void run() {
                ImageView.this.performClick();
            }
        })) {
            performClick();
        }
    }

    public void postPerformLongClick() {
        if (!post(new Runnable() {
            public void run() {
                ImageView.this.performLongClick();
            }
        })) {
            performLongClick();
        }
    }
}
