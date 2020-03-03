package miuipub.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import miuipub.v6.R;

public class SlidingButton extends CheckBox {
    private static final int ANIMATION_DURATION = 180;
    private static final int FULL_ALPHA = 255;
    private static final int SCALE_ANIMATION_DELAY = 100;
    /* access modifiers changed from: private */
    public Animator mAnimator;
    private Animator.AnimatorListener mAnimatorListener;
    private Bitmap mBarOff;
    private Paint mBarOffPaint;
    private Bitmap mBarOn;
    private Paint mBarOnPaint;
    private Drawable mFrame;
    private int mHeight;
    /* access modifiers changed from: private */
    public boolean mIsAnimating;
    private int mLastX;
    private Bitmap mMask;
    private Drawable mMaskChecked;
    private Drawable mMaskUnchecked;
    /* access modifiers changed from: private */
    public CompoundButton.OnCheckedChangeListener mOnPerformCheckedChangeListener;
    private int mOriginalTouchPointX;
    private boolean mSliderMoved;
    private Drawable mSliderOff;
    /* access modifiers changed from: private */
    public int mSliderOffset;
    private Drawable mSliderOn;
    private int mSliderOnAlpha;
    /* access modifiers changed from: private */
    public int mSliderPositionEnd;
    private int mSliderPositionStart;
    private int mSliderWidth;
    private int mTapThreshold;
    private Rect mTmpRect;
    private boolean mTracking;
    private int mWidth;

    public void setButtonDrawable(Drawable drawable) {
    }

    public SlidingButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public SlidingButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlidingButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTmpRect = new Rect();
        this.mIsAnimating = false;
        this.mAnimatorListener = new AnimatorListenerAdapter() {
            private boolean b;

            public void onAnimationStart(Animator animator) {
                this.b = false;
                boolean unused = SlidingButton.this.mIsAnimating = true;
            }

            public void onAnimationCancel(Animator animator) {
                this.b = true;
            }

            public void onAnimationEnd(Animator animator) {
                final boolean z = false;
                boolean unused = SlidingButton.this.mIsAnimating = false;
                if (!this.b) {
                    Animator unused2 = SlidingButton.this.mAnimator = null;
                    if (SlidingButton.this.mSliderOffset >= SlidingButton.this.mSliderPositionEnd) {
                        z = true;
                    }
                    if (z != SlidingButton.this.isChecked()) {
                        SlidingButton.this.setChecked(z);
                        if (SlidingButton.this.mOnPerformCheckedChangeListener != null) {
                            SlidingButton.this.post(new Runnable() {
                                public void run() {
                                    if (SlidingButton.this.mOnPerformCheckedChangeListener != null) {
                                        SlidingButton.this.mOnPerformCheckedChangeListener.onCheckedChanged(SlidingButton.this, z);
                                    }
                                }
                            });
                        }
                    }
                }
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.V6_SlidingButton, i, R.style.V6_Widget_SlidingButton);
        setDrawingCacheEnabled(false);
        this.mTapThreshold = ViewConfiguration.get(context).getScaledTouchSlop() / 2;
        this.mFrame = obtainStyledAttributes.getDrawable(R.styleable.V6_SlidingButton_v6_frame);
        this.mSliderOn = obtainStyledAttributes.getDrawable(R.styleable.V6_SlidingButton_v6_sliderOn);
        this.mSliderOff = obtainStyledAttributes.getDrawable(R.styleable.V6_SlidingButton_v6_sliderOff);
        setBackgroundDrawable(obtainStyledAttributes.getDrawable(R.styleable.V6_SlidingButton_android_background));
        this.mWidth = this.mFrame.getIntrinsicWidth();
        this.mHeight = this.mFrame.getIntrinsicHeight();
        this.mSliderWidth = Math.min(this.mWidth, this.mSliderOn.getIntrinsicWidth());
        this.mSliderPositionStart = 0;
        this.mSliderPositionEnd = this.mWidth - this.mSliderWidth;
        this.mSliderOffset = this.mSliderPositionStart;
        TypedValue typedValue = new TypedValue();
        obtainStyledAttributes.getValue(R.styleable.V6_SlidingButton_v6_barOff, typedValue);
        TypedValue typedValue2 = new TypedValue();
        obtainStyledAttributes.getValue(R.styleable.V6_SlidingButton_v6_barOn, typedValue2);
        this.mBarOff = Bitmap.createScaledBitmap(((BitmapDrawable) obtainStyledAttributes.getDrawable(R.styleable.V6_SlidingButton_v6_barOff)).getBitmap(), (this.mWidth * 2) - this.mSliderWidth, this.mHeight, true);
        if (typedValue.type == typedValue2.type && typedValue.data == typedValue2.data && typedValue.resourceId == typedValue2.resourceId) {
            this.mBarOn = this.mBarOff;
        } else {
            this.mBarOn = Bitmap.createScaledBitmap(((BitmapDrawable) obtainStyledAttributes.getDrawable(R.styleable.V6_SlidingButton_v6_barOn)).getBitmap(), (this.mWidth * 2) - this.mSliderWidth, this.mHeight, true);
        }
        this.mFrame.setBounds(0, 0, this.mWidth, this.mHeight);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.V6_SlidingButton_v6_mask);
        drawable.setBounds(0, 0, this.mWidth, this.mHeight);
        this.mMask = convertToAlphaMask(drawable);
        this.mBarOffPaint = new Paint();
        this.mBarOnPaint = new Paint();
        this.mBarOnPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        this.mBarOffPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        this.mMaskChecked = createMaskDrawable(true);
        this.mMaskUnchecked = createMaskDrawable(false);
        obtainStyledAttributes.recycle();
    }

    public void setOnPerformCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.mOnPerformCheckedChangeListener = onCheckedChangeListener;
    }

    private Bitmap convertToAlphaMask(Drawable drawable) {
        Rect bounds = drawable.getBounds();
        Bitmap createBitmap = Bitmap.createBitmap(bounds.width(), bounds.height(), Bitmap.Config.ALPHA_8);
        drawable.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    public void setChecked(boolean z) {
        if (isChecked() != z) {
            super.setChecked(z);
            this.mSliderOffset = z ? this.mSliderPositionEnd : this.mSliderPositionStart;
            int i = 0;
            this.mBarOnPaint.setAlpha(z ? 255 : 0);
            this.mBarOffPaint.setAlpha(!z ? 255 : 0);
            if (z) {
                i = 255;
            }
            this.mSliderOnAlpha = i;
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        this.mSliderOn.setState(getDrawableState());
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(this.mWidth, this.mHeight);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (!isEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        Rect rect = this.mTmpRect;
        rect.set(this.mSliderOffset, 0, this.mSliderOffset + this.mSliderWidth, this.mHeight);
        switch (action) {
            case 0:
                if (rect.contains(x, y)) {
                    this.mTracking = true;
                    setPressed(true);
                } else {
                    this.mTracking = false;
                }
                this.mLastX = x;
                this.mOriginalTouchPointX = x;
                this.mSliderMoved = false;
                break;
            case 1:
                if (!this.mTracking) {
                    animateToggle();
                } else if (!this.mSliderMoved) {
                    animateToggle();
                } else {
                    animateToState(this.mSliderOffset >= this.mSliderPositionEnd / 2);
                }
                this.mTracking = false;
                this.mSliderMoved = false;
                setPressed(false);
                break;
            case 2:
                if (this.mTracking) {
                    moveSlider(x - this.mLastX);
                    this.mLastX = x;
                    if (Math.abs(x - this.mOriginalTouchPointX) >= this.mTapThreshold) {
                        this.mSliderMoved = true;
                        getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    }
                }
                break;
            case 3:
                this.mTracking = false;
                this.mSliderMoved = false;
                setPressed(false);
                if (this.mSliderOffset >= this.mSliderPositionEnd / 2) {
                    z = true;
                }
                animateToState(z);
                break;
        }
        return true;
    }

    public void setPressed(boolean z) {
        super.setPressed(z);
        invalidate();
    }

    private void animateToggle() {
        animateToState(!isChecked());
    }

    private void animateToState(boolean z) {
        if (this.mAnimator != null) {
            this.mAnimator.cancel();
            this.mAnimator = null;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        int[] iArr = new int[1];
        iArr[0] = z ? this.mSliderPositionEnd : this.mSliderPositionStart;
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this, "SliderOffset", iArr);
        int[] iArr2 = new int[1];
        iArr2[0] = z ? 255 : 0;
        ObjectAnimator ofInt2 = ObjectAnimator.ofInt(this, "SliderOnAlpha", iArr2);
        ofInt2.setDuration(180);
        ofInt.setDuration(180);
        animatorSet.play(ofInt2).after(ofInt).after(100);
        this.mAnimator = animatorSet;
        this.mAnimator.addListener(this.mAnimatorListener);
        this.mAnimator.start();
    }

    private void moveSlider(int i) {
        this.mSliderOffset += i;
        if (this.mSliderOffset < this.mSliderPositionStart) {
            this.mSliderOffset = this.mSliderPositionStart;
        } else if (this.mSliderOffset > this.mSliderPositionEnd) {
            this.mSliderOffset = this.mSliderPositionEnd;
        }
        setSliderOffset(this.mSliderOffset);
    }

    public int getSliderOffset() {
        return this.mSliderOffset;
    }

    public void setSliderOffset(int i) {
        this.mSliderOffset = i;
        invalidate();
    }

    public int getSliderOnAlpha() {
        return this.mSliderOnAlpha;
    }

    public void setSliderOnAlpha(int i) {
        this.mSliderOnAlpha = i;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i = isEnabled() ? 255 : 127;
        if (this.mTracking || this.mIsAnimating) {
            canvas.saveLayerAlpha(0.0f, 0.0f, (float) this.mMask.getWidth(), (float) this.mMask.getHeight(), i, 4);
            canvas.drawBitmap(this.mMask, 0.0f, 0.0f, (Paint) null);
            drawBar(canvas);
            canvas.restore();
        } else if (isChecked()) {
            this.mMaskChecked.draw(canvas);
        } else {
            this.mMaskUnchecked.draw(canvas);
        }
        this.mFrame.draw(canvas);
        if (isChecked()) {
            this.mSliderOn.setAlpha(i);
            this.mSliderOn.setBounds(this.mSliderOffset, 0, this.mSliderWidth + this.mSliderOffset, this.mHeight);
            this.mSliderOn.draw(canvas);
            return;
        }
        this.mSliderOff.setAlpha(i);
        this.mSliderOff.setBounds(this.mSliderOffset, 0, this.mSliderWidth + this.mSliderOffset, this.mHeight);
        this.mSliderOff.draw(canvas);
    }

    private void drawBar(Canvas canvas) {
        if (this.mBarOnPaint.getAlpha() != 0) {
            canvas.drawBitmap(this.mBarOn, 0.0f, 0.0f, this.mBarOnPaint);
        }
        if (this.mBarOffPaint.getAlpha() != 0) {
            canvas.drawBitmap(this.mBarOff, 0.0f, 0.0f, this.mBarOffPaint);
        }
    }

    private Drawable createMaskDrawable(boolean z) {
        Bitmap createBitmap = Bitmap.createBitmap(this.mWidth, this.mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(this.mMask, 0.0f, 0.0f, (Paint) null);
        if (z) {
            canvas.drawBitmap(this.mBarOn, 0.0f, 0.0f, this.mBarOnPaint);
        } else {
            canvas.drawBitmap(this.mBarOff, 0.0f, 0.0f, this.mBarOffPaint);
        }
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getContext().getResources(), createBitmap);
        bitmapDrawable.setBounds(0, 0, this.mWidth, this.mHeight);
        return bitmapDrawable;
    }
}
