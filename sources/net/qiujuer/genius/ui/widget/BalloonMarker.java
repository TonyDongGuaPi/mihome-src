package net.qiujuer.genius.ui.widget;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.TextView;
import net.qiujuer.genius.ui.R;
import net.qiujuer.genius.ui.Ui;
import net.qiujuer.genius.ui.animation.AnimatorListener;
import net.qiujuer.genius.ui.compat.UiCompat;
import net.qiujuer.genius.ui.drawable.BalloonMarkerDrawable;

public class BalloonMarker extends ViewGroup implements BalloonMarkerDrawable.MarkerAnimationListener {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3147a = 8;
    private static final int b = 0;
    /* access modifiers changed from: private */
    public TextView c;
    private int d;
    private int e;
    BalloonMarkerDrawable mBalloonMarkerDrawable;

    public BalloonMarker(Context context) {
        this(context, (AttributeSet) null);
    }

    public BalloonMarker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.gBalloonMarkerStyle);
    }

    public BalloonMarker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBalloonMarkerDrawable = new BalloonMarkerDrawable(ColorStateList.valueOf(0), 0);
        init(context, attributeSet, i, R.style.Genius_Widget_BalloonMarker, "0");
    }

    @TargetApi(21)
    public BalloonMarker(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mBalloonMarkerDrawable = new BalloonMarkerDrawable(ColorStateList.valueOf(0), 0);
        init(context, attributeSet, i, i2, "0");
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(BalloonMarker.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(BalloonMarker.class.getName());
    }

    public void init(Context context, AttributeSet attributeSet, int i, int i2, String str) {
        Typeface a2;
        Resources resources = getResources();
        float f = resources.getDisplayMetrics().density;
        this.c = new TextView(context);
        this.c.setGravity(17);
        this.c.setText(str);
        this.c.setMaxLines(1);
        this.c.setSingleLine(true);
        UiCompat.a(this.c, 5);
        this.c.setVisibility(4);
        resetSizes(str);
        this.e = (int) (0.0f * f);
        this.mBalloonMarkerDrawable = new BalloonMarkerDrawable(ColorStateList.valueOf(0), 0);
        this.mBalloonMarkerDrawable.setCallback(this);
        this.mBalloonMarkerDrawable.a((BalloonMarkerDrawable.MarkerAnimationListener) this);
        this.mBalloonMarkerDrawable.b(getPaddingBottom());
        UiCompat.a((View) this, this.mBalloonMarkerDrawable);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BalloonMarker, i, i2);
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BalloonMarker_gMarkerTextPadding, resources.getDimensionPixelSize(R.dimen.g_balloonMarker_textPadding));
            int resourceId = obtainStyledAttributes.getResourceId(R.styleable.BalloonMarker_gMarkerTextAppearance, R.style.Genius_Widget_BalloonMarker_TextAppearance);
            ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.BalloonMarker_gMarkerBackgroundColor);
            String string = obtainStyledAttributes.getString(R.styleable.BalloonMarker_gFont);
            this.e = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BalloonMarker_gMarkerSeparation, resources.getDimensionPixelSize(R.dimen.g_balloonMarker_separation));
            obtainStyledAttributes.recycle();
            setTextPadding(dimensionPixelSize);
            setTextAppearance(resourceId);
            setBackgroundColor(colorStateList);
            if (Build.VERSION.SDK_INT >= 21) {
                setElevation(obtainStyledAttributes.getDimension(R.styleable.BalloonMarker_gMarkerElevation, f * 8.0f));
            }
            if (!isInEditMode() && string != null && string.length() > 0 && (a2 = Ui.a(getContext(), string)) != null) {
                setTypeface(a2);
            }
        }
    }

    public void setSeparation(int i) {
        this.e = i;
    }

    public void setTextPadding(int i) {
        this.c.setPadding(i, 0, i, 0);
    }

    public void setTypeface(Typeface typeface) {
        this.c.setTypeface(typeface);
    }

    public void setTextAppearance(int i) {
        this.c.setTextAppearance(getContext(), i);
    }

    public ColorStateList getBackgroundColor() {
        return this.mBalloonMarkerDrawable.d();
    }

    public void setBackgroundColor(ColorStateList colorStateList) {
        this.mBalloonMarkerDrawable.a(colorStateList);
    }

    public void setClosedSize(float f) {
        this.mBalloonMarkerDrawable.a(f);
    }

    public void resetSizes(String str) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.c.setText(String.format("-%s", new Object[]{str}));
        this.c.measure(View.MeasureSpec.makeMeasureSpec(displayMetrics.widthPixels, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(displayMetrics.heightPixels, Integer.MIN_VALUE));
        this.d = Math.max(this.c.getMeasuredWidth(), this.c.getMeasuredHeight());
        removeView(this.c);
        addView(this.c, new FrameLayout.LayoutParams(this.d, this.d, 51));
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
        if (this.mBalloonMarkerDrawable != null) {
            this.mBalloonMarkerDrawable.b(i4);
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        this.mBalloonMarkerDrawable.draw(canvas);
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        measureChildren(i, i2);
        setMeasuredDimension(this.d + getPaddingLeft() + getPaddingRight(), this.d + getPaddingTop() + getPaddingBottom() + (((int) ((((float) this.d) * 1.41f) - ((float) this.d))) / 2) + this.e);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int width = getWidth() - getPaddingRight();
        int height = getHeight() - getPaddingBottom();
        this.c.layout(paddingLeft, paddingTop, this.d + paddingLeft, this.d + paddingTop);
        this.mBalloonMarkerDrawable.setBounds(paddingLeft, paddingTop, width, height);
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return drawable == this.mBalloonMarkerDrawable || super.verifyDrawable(drawable);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        animateOpen();
    }

    public CharSequence getValue() {
        return this.c.getText();
    }

    public void setValue(CharSequence charSequence) {
        this.c.setText(charSequence);
    }

    public void animateOpen() {
        this.mBalloonMarkerDrawable.stop();
        this.mBalloonMarkerDrawable.b();
    }

    @TargetApi(16)
    public void animateClose() {
        this.mBalloonMarkerDrawable.stop();
        ViewPropertyAnimator animate = this.c.animate();
        animate.alpha(0.0f);
        animate.setDuration(100);
        if (Build.VERSION.SDK_INT >= 16) {
            animate.withEndAction(new Runnable() {
                public void run() {
                    BalloonMarker.this.c.setVisibility(4);
                    BalloonMarker.this.mBalloonMarkerDrawable.c();
                }
            });
        } else {
            animate.setListener(new AnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                    BalloonMarker.this.c.setVisibility(4);
                    BalloonMarker.this.mBalloonMarkerDrawable.c();
                }
            });
        }
        animate.start();
    }

    public void onOpeningComplete() {
        this.c.setVisibility(0);
        ViewPropertyAnimator animate = this.c.animate();
        animate.alpha(1.0f);
        animate.setDuration(100);
        animate.start();
        if (getParent() instanceof BalloonMarkerDrawable.MarkerAnimationListener) {
            ((BalloonMarkerDrawable.MarkerAnimationListener) getParent()).onOpeningComplete();
        }
    }

    public void onClosingComplete() {
        if (getParent() instanceof BalloonMarkerDrawable.MarkerAnimationListener) {
            ((BalloonMarkerDrawable.MarkerAnimationListener) getParent()).onClosingComplete();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mBalloonMarkerDrawable.stop();
    }

    public void setColors(int i, int i2) {
        this.mBalloonMarkerDrawable.a(i, i2);
    }
}
