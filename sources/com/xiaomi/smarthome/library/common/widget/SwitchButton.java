package com.xiaomi.smarthome.library.common.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.ApiHelper;

public class SwitchButton extends CheckBox {
    protected static final int ANIMATION_DURATION = 180;
    protected static final int FULL_ALPHA = 255;
    protected static final int SCALE_ANIMATION_DELAY = 100;
    protected Animator mAnimator;
    protected Animator.AnimatorListener mAnimatorListener;
    protected Bitmap mBarOff;
    protected Paint mBarOffPaint;
    protected Bitmap mBarOn;
    protected Paint mBarOnPaint;
    protected Drawable mFrame_check;
    protected Drawable mFrame_uncheck;
    protected int mHeight;
    protected int mLastX;
    protected Bitmap mMask;
    protected CompoundButton.OnCheckedChangeListener mOnPerformCheckedChangeListener;
    protected boolean mOnTouchEventEnable;
    protected int mOriginalTouchPointX;
    protected boolean mSliderMoved;
    protected Drawable mSliderOff;
    protected int mSliderOffset;
    protected Drawable mSliderOn;
    protected int mSliderOnAlpha;
    protected int mSliderPositionEnd;
    protected int mSliderPositionStart;
    protected int mSliderWidth;
    protected int mTapThreshold;
    protected Rect mTmpRect;
    protected boolean mTracking;
    protected int mWidth;

    public void setButtonDrawable(Drawable drawable) {
    }

    public SwitchButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public SwitchButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @SuppressLint({"NewApi"})
    public SwitchButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTmpRect = new Rect();
        this.mOnTouchEventEnable = true;
        if (!isInEditMode()) {
            try {
                if (ApiHelper.f18555a) {
                    this.mAnimatorListener = new AnimatorListenerAdapter() {
                        private boolean b;

                        public void onAnimationStart(Animator animator) {
                            this.b = false;
                        }

                        public void onAnimationCancel(Animator animator) {
                            this.b = true;
                        }

                        public void onAnimationEnd(Animator animator) {
                            if (!this.b) {
                                SwitchButton.this.mAnimator = null;
                                final boolean z = SwitchButton.this.mSliderOffset >= SwitchButton.this.mSliderPositionEnd;
                                if (z != SwitchButton.this.isChecked()) {
                                    SwitchButton.this.setChecked(z);
                                    if (SwitchButton.this.mOnPerformCheckedChangeListener != null) {
                                        SwitchButton.this.post(new Runnable() {
                                            public void run() {
                                                if (SwitchButton.this.mOnPerformCheckedChangeListener != null) {
                                                    SwitchButton.this.mOnPerformCheckedChangeListener.onCheckedChanged(SwitchButton.this, z);
                                                }
                                            }
                                        });
                                    }
                                }
                            }
                        }
                    };
                }
            } catch (Throwable unused) {
            }
        }
        setDrawingCacheEnabled(false);
        this.mTapThreshold = ViewConfiguration.get(context).getScaledTouchSlop() / 2;
        this.mFrame_uncheck = getResources().getDrawable(R.drawable.sliding_btn_frame_uncheck);
        this.mFrame_check = getResources().getDrawable(R.drawable.sliding_btn_frame_check);
        this.mSliderOn = getResources().getDrawable(R.drawable.sliding_btn_slider_on_light);
        this.mSliderOff = getResources().getDrawable(R.drawable.sliding_btn_slider_off_light);
        setBackgroundResource(R.drawable.sliding_btn_bg_light);
        this.mWidth = this.mFrame_uncheck.getIntrinsicWidth();
        this.mHeight = this.mFrame_uncheck.getIntrinsicHeight();
        this.mSliderWidth = Math.min(this.mWidth, this.mSliderOn.getIntrinsicWidth());
        this.mSliderPositionStart = 0;
        this.mSliderPositionEnd = this.mWidth - this.mSliderWidth;
        this.mSliderOffset = this.mSliderPositionStart;
        this.mBarOff = Bitmap.createScaledBitmap(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.sliding_btn_bar_off_light)).getBitmap(), (this.mWidth * 2) - this.mSliderWidth, this.mHeight, true);
        this.mBarOn = Bitmap.createScaledBitmap(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.sliding_btn_bar_on_light)).getBitmap(), (this.mWidth * 2) - this.mSliderWidth, this.mHeight, true);
        this.mFrame_uncheck.setBounds(0, 0, this.mWidth, this.mHeight);
        this.mFrame_check.setBounds(0, 0, this.mWidth, this.mHeight);
        Drawable drawable = getResources().getDrawable(R.drawable.sliding_btn_mask_light);
        drawable.setBounds(0, 0, this.mWidth, this.mHeight);
        this.mMask = convertToAlphaMask(drawable);
        this.mBarOffPaint = new Paint();
        this.mBarOnPaint = new Paint();
        this.mBarOnPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST));
        this.mBarOffPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST));
        setBackgroundColor(getResources().getColor(R.color.transparent));
    }

    public void setOnPerformCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.mOnPerformCheckedChangeListener = onCheckedChangeListener;
    }

    /* access modifiers changed from: protected */
    public Bitmap convertToAlphaMask(Drawable drawable) {
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
        int[] drawableState = getDrawableState();
        if (drawableState != null) {
            this.mSliderOn.setState(drawableState);
            this.mSliderOff.setState(drawableState);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(this.mWidth, this.mHeight);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (!isEnabled() || !this.mOnTouchEventEnable) {
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

    /* access modifiers changed from: protected */
    public void animateToggle() {
        animateToState(!isChecked());
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"NewApi"})
    public void animateToState(final boolean z) {
        if (ApiHelper.f18555a) {
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
        } else if (z != isChecked()) {
            setChecked(z);
            if (this.mOnPerformCheckedChangeListener != null) {
                post(new Runnable() {
                    public void run() {
                        if (SwitchButton.this.mOnPerformCheckedChangeListener != null) {
                            SwitchButton.this.mOnPerformCheckedChangeListener.onCheckedChanged(SwitchButton.this, z);
                        }
                    }
                });
            }
        }
    }

    /* access modifiers changed from: protected */
    public void moveSlider(int i) {
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

    public void setOnTouchEnable(boolean z) {
        this.mOnTouchEventEnable = z;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.saveLayerAlpha(0.0f, 0.0f, (float) this.mMask.getWidth(), (float) this.mMask.getHeight(), isEnabled() ? 255 : 127, 31);
        canvas.drawBitmap(this.mMask, 0.0f, 0.0f, (Paint) null);
        drawBar(canvas);
        if (this.mSliderOnAlpha < 255) {
            this.mSliderOff.setBounds(this.mSliderOffset, 0, this.mSliderWidth + this.mSliderOffset, this.mHeight);
            this.mFrame_uncheck.draw(canvas);
            this.mSliderOff.draw(canvas);
        } else {
            this.mSliderOn.setAlpha(this.mSliderOnAlpha);
            this.mSliderOn.setBounds(this.mSliderOffset, 0, this.mSliderWidth + this.mSliderOffset, this.mHeight);
            this.mFrame_check.draw(canvas);
            this.mSliderOn.draw(canvas);
        }
        canvas.restore();
    }

    /* access modifiers changed from: protected */
    public void drawBar(Canvas canvas) {
        if (this.mBarOnPaint.getAlpha() != 0) {
            canvas.drawBitmap(this.mBarOn, 0.0f, 0.0f, this.mBarOnPaint);
        }
        if (this.mBarOffPaint.getAlpha() != 0) {
            canvas.drawBitmap(this.mBarOff, 0.0f, 0.0f, this.mBarOffPaint);
        }
    }
}
