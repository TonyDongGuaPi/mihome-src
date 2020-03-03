package net.qiujuer.genius.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import net.qiujuer.genius.ui.R;
import net.qiujuer.genius.ui.Ui;
import net.qiujuer.genius.ui.compat.UiCompat;
import net.qiujuer.genius.ui.drawable.TouchEffectDrawable;
import net.qiujuer.genius.ui.drawable.effect.FloatEffect;

public class FloatActionButton extends ImageView implements TouchEffectDrawable.PerformClicker, TouchEffectDrawable.PerformLongClicker {
    private ColorStateList mBackgroundColor;
    private int mShadowRadius;
    private TouchEffectDrawable mTouchDrawable;

    public FloatActionButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public FloatActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.gFloatActionButtonStyle);
    }

    public FloatActionButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i, R.style.Genius_Widget_FloatActionButton);
    }

    @TargetApi(21)
    public FloatActionButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet, i, i2);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(FloatActionButton.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(FloatActionButton.class.getName());
    }

    private void init(AttributeSet attributeSet, int i, int i2) {
        if (attributeSet != null) {
            Context context = getContext();
            Resources resources = getResources();
            float f = resources.getDisplayMetrics().density;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FloatActionButton, i, i2);
            ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.FloatActionButton_gBackgroundColor);
            int color = obtainStyledAttributes.getColor(R.styleable.FloatActionButton_gTouchColor, Ui.b);
            boolean z = obtainStyledAttributes.getBoolean(R.styleable.FloatActionButton_android_enabled, true);
            int i3 = obtainStyledAttributes.getInt(R.styleable.FloatActionButton_shadowColor, -16777216);
            float dimension = obtainStyledAttributes.getDimension(R.styleable.FloatActionButton_shadowDx, 0.0f * f);
            float dimension2 = obtainStyledAttributes.getDimension(R.styleable.FloatActionButton_shadowDy, 1.8f * f);
            float dimension3 = obtainStyledAttributes.getDimension(R.styleable.FloatActionButton_shadowRadius, f * 3.75f);
            int i4 = obtainStyledAttributes.getInt(R.styleable.FloatActionButton_shadowAlpha, 32);
            float f2 = obtainStyledAttributes.getFloat(R.styleable.ImageView_gTouchDurationRate, 1.0f);
            int i5 = obtainStyledAttributes.getInt(R.styleable.FloatActionButton_gInterceptEvent, 1);
            obtainStyledAttributes.recycle();
            setEnabled(z);
            if (colorStateList == null) {
                colorStateList = UiCompat.b(resources, R.color.g_default_float_action_bg);
            }
            float max = Math.max(dimension, dimension2);
            double d = (double) dimension3;
            Double.isNaN(d);
            this.mShadowRadius = (int) (d + 0.5d);
            this.mShadowRadius = (int) (((float) this.mShadowRadius) + max);
            ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShadowShape(this.mShadowRadius, Ui.c(i3, 112)));
            Paint paint = shapeDrawable.getPaint();
            if (!isInEditMode()) {
                paint.setShadowLayer(((float) this.mShadowRadius) - max, dimension, dimension2, Ui.c(i3, i4));
            }
            UiCompat.a((View) this, (Drawable) shapeDrawable);
            setBackgroundColor(colorStateList);
            this.mTouchDrawable = new TouchEffectDrawable(new FloatEffect(), ColorStateList.valueOf(color));
            this.mTouchDrawable.setCallback(this);
            this.mTouchDrawable.b(i5);
            this.mTouchDrawable.a(f2);
            this.mTouchDrawable.b(f2);
            setLayerType(1, paint);
            int i6 = this.mShadowRadius;
            setPadding(Math.max(i6, getPaddingLeft()), Math.max(i6, getPaddingTop()), Math.max(i6, getPaddingRight()), Math.max(i6, getPaddingBottom()));
        }
    }

    public void setLayerType(int i, Paint paint) {
        super.setLayerType(1, paint);
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mBackgroundColor != null) {
            setBackgroundColor(this.mBackgroundColor.getColorForState(getDrawableState(), this.mBackgroundColor.getDefaultColor()));
        }
    }

    public void setBackgroundColor(ColorStateList colorStateList) {
        if (colorStateList != null && this.mBackgroundColor != colorStateList) {
            this.mBackgroundColor = colorStateList;
            setBackgroundColor(this.mBackgroundColor.getColorForState(getDrawableState(), this.mBackgroundColor.getDefaultColor()));
        }
    }

    public void setBackgroundColorRes(int i) {
        setBackgroundColor(UiCompat.a(getResources(), i));
    }

    public void setBackgroundColor(int i) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(Ui.c(i, 255));
        }
    }

    public void setTouchColor(int i) {
        this.mTouchDrawable.c(i);
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(Math.max(this.mShadowRadius, i), Math.max(this.mShadowRadius, i2), Math.max(this.mShadowRadius, i3), Math.max(this.mShadowRadius, i4));
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(getMeasuredWidth() + (this.mShadowRadius * 2), getMeasuredHeight() + (this.mShadowRadius * 2));
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        TouchEffectDrawable touchEffectDrawable = this.mTouchDrawable;
        if (touchEffectDrawable != null) {
            touchEffectDrawable.setBounds(this.mShadowRadius, this.mShadowRadius, getWidth() - this.mShadowRadius, getHeight() - this.mShadowRadius);
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return (this.mTouchDrawable != null && drawable == this.mTouchDrawable) || super.verifyDrawable(drawable);
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
        TouchEffectDrawable touchEffectDrawable = this.mTouchDrawable;
        if (touchEffectDrawable != null) {
            touchEffectDrawable.draw(canvas);
        }
        super.onDraw(canvas);
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
                FloatActionButton.this.performClick();
            }
        })) {
            performClick();
        }
    }

    public void postPerformLongClick() {
        if (!post(new Runnable() {
            public void run() {
                FloatActionButton.this.performLongClick();
            }
        })) {
            performLongClick();
        }
    }

    public TouchEffectDrawable getTouchDrawable() {
        return this.mTouchDrawable;
    }

    private static class OvalShadowShape extends Shape {

        /* renamed from: a  reason: collision with root package name */
        private Paint f3157a;
        private float b;
        private float c;
        private float d;
        private int e;
        private int f;
        private RectF g = new RectF();

        /* access modifiers changed from: protected */
        public final RectF a() {
            return this.g;
        }

        OvalShadowShape(int i, int i2) {
            this.e = i;
            this.f = i2;
            this.f3157a = new Paint(1);
            this.f3157a.setStyle(Paint.Style.FILL);
            this.f3157a.setAntiAlias(true);
            this.f3157a.setDither(true);
        }

        /* access modifiers changed from: protected */
        public void onResize(float f2, float f3) {
            super.onResize(f2, f3);
            this.g.set(0.0f, 0.0f, f2, f3);
            this.b = f2 / 2.0f;
            this.c = f3 / 2.0f;
            this.d = Math.min(this.b, this.c);
            this.f3157a.setShader(new RadialGradient(this.b, this.c, this.d, new int[]{this.f, 16777215}, new float[]{0.65f, 1.0f}, Shader.TileMode.CLAMP));
        }

        public void draw(Canvas canvas, Paint paint) {
            canvas.drawCircle(this.b, this.c, this.d, this.f3157a);
            canvas.drawCircle(this.b, this.c, this.d - ((float) this.e), paint);
        }
    }
}
