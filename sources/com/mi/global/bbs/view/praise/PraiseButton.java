package com.mi.global.bbs.view.praise;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.react.uimanager.ViewProps;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;

public class PraiseButton extends FrameLayout implements View.OnClickListener {
    /* access modifiers changed from: private */
    public OnAnimationEndListener animationEndListener;
    private float animationScaleFactor;
    private AnimatorSet animatorSet;
    private int circleColor;
    /* access modifiers changed from: private */
    public CircleView circleView;
    /* access modifiers changed from: private */
    public FireworkView firewokView;
    private int fireworkColor;
    /* access modifiers changed from: private */
    public ImageView icon;
    private int iconSize;
    private boolean isChecked;
    private boolean isEnabled;
    private int likeCount;
    private Drawable likeDrawable;
    private OnPraiseListener likeListener;
    private TextView tvCount;
    private Drawable unLikeDrawable;
    private Drawable unLikeDrawableWithCount;

    public PraiseButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public PraiseButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PraiseButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (!isInEditMode()) {
            init(context, attributeSet, i);
        }
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        LayoutInflater.from(getContext()).inflate(R.layout.praiseview, this, true);
        this.icon = (ImageView) findViewById(R.id.icon);
        this.firewokView = (FireworkView) findViewById(R.id.fireworks);
        this.circleView = (CircleView) findViewById(R.id.circle);
        this.tvCount = (TextView) findViewById(R.id.tv_like_view_count);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PraiseButton, i, 0);
        this.iconSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.PraiseButton_icon_size, 60);
        setLikeDrawableRes(R.drawable.ic_action_thumped_new);
        setUnlikeDrawableRes(R.drawable.ic_action_thump);
        setUnlikeDrawableResWithCount(R.drawable.ic_action_thump_with_count);
        this.circleColor = obtainStyledAttributes.getColor(R.styleable.PraiseButton_circle_color, 0);
        if (this.circleColor != 0) {
            this.circleView.setColor(this.circleColor);
        }
        this.fireworkColor = obtainStyledAttributes.getColor(R.styleable.PraiseButton_firework_color, 0);
        if (this.fireworkColor != 0) {
            this.firewokView.setColor(this.fireworkColor);
        }
        setEnabled(obtainStyledAttributes.getBoolean(R.styleable.PraiseButton_is_enabled, true));
        Boolean valueOf = Boolean.valueOf(obtainStyledAttributes.getBoolean(R.styleable.PraiseButton_liked, false));
        setAnimationScaleFactor(obtainStyledAttributes.getFloat(R.styleable.PraiseButton_anim_scale_factor, 1.2f));
        setLiked(valueOf);
        setOnClickListener(this);
        obtainStyledAttributes.recycle();
    }

    private Drawable getDrawableFromResource(TypedArray typedArray, int i) {
        int resourceId = typedArray.getResourceId(i, -1);
        if (-1 != resourceId) {
            return ContextCompat.getDrawable(getContext(), resourceId);
        }
        return null;
    }

    public void onClick(View view) {
        if (this.isEnabled) {
            this.isChecked = !this.isChecked;
            if (this.likeListener != null) {
                if (!LoginManager.getInstance().hasLogin()) {
                    this.likeListener.onNotLogin();
                    return;
                } else if (this.isChecked) {
                    this.likeListener.praised();
                } else {
                    this.likeListener.unpraised();
                }
            }
            if (this.isChecked) {
                this.likeCount++;
            } else if (this.likeCount > 0) {
                this.likeCount--;
            }
            if (this.isChecked) {
                this.icon.setImageDrawable(this.likeDrawable);
            } else if (this.likeCount > 0) {
                this.icon.setImageDrawable(this.unLikeDrawableWithCount);
            } else {
                this.icon.setImageDrawable(this.unLikeDrawable);
            }
            setLikeCount(this.likeCount);
            if (this.animatorSet != null) {
                this.animatorSet.cancel();
            }
            if (this.isChecked) {
                this.icon.animate().cancel();
                this.firewokView.setVisibility(0);
                this.firewokView.setCurrentProgress(0.0f);
                this.circleView.setCircleRadiusProgress(0.0f);
                this.animatorSet = new AnimatorSet();
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.icon, ImageView.SCALE_Y, new float[]{1.0f, 1.3f, 1.0f});
                ofFloat.setDuration(500);
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.icon, ImageView.SCALE_X, new float[]{1.0f, 1.3f, 1.0f});
                ofFloat2.setDuration(500);
                ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.icon, "translationY", new float[]{0.0f, -10.0f, 0.0f});
                ofFloat3.setDuration(500);
                ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.icon, ViewProps.ROTATION, new float[]{0.0f, 10.0f, 0.0f, -10.0f, 0.0f});
                ofFloat4.setDuration(500);
                ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.firewokView, FireworkView.FIREWORK_PROGRESS, new float[]{0.0f, 1.0f});
                ofFloat5.setDuration(250);
                ofFloat4.setStartDelay(250);
                ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(this.circleView, CircleView.CIRCLE_RADIUS_PROGRESS, new float[]{0.0f, 1.0f});
                ofFloat6.setDuration(250);
                ofFloat4.setStartDelay(250);
                this.animatorSet.playTogether(new Animator[]{ofFloat6, ofFloat, ofFloat2, ofFloat3, ofFloat4, ofFloat5});
                this.animatorSet.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationCancel(Animator animator) {
                        PraiseButton.this.firewokView.setCurrentProgress(0.0f);
                        PraiseButton.this.firewokView.setVisibility(4);
                        PraiseButton.this.circleView.setCircleRadiusProgress(0.0f);
                        PraiseButton.this.icon.setRotation(0.0f);
                        PraiseButton.this.icon.setTranslationY(0.0f);
                        PraiseButton.this.icon.setScaleX(1.0f);
                        PraiseButton.this.icon.setScaleY(1.0f);
                    }

                    public void onAnimationEnd(Animator animator) {
                        if (PraiseButton.this.animationEndListener != null) {
                            PraiseButton.this.animationEndListener.onAnimationEnd(PraiseButton.this);
                        }
                    }
                });
                this.animatorSet.start();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.isEnabled) {
            return true;
        }
        boolean z = false;
        switch (motionEvent.getAction()) {
            case 0:
                setPressed(true);
                break;
            case 1:
                if (isPressed()) {
                    performClick();
                    setPressed(false);
                    break;
                }
                break;
            case 2:
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (x > 0.0f && x < ((float) getWidth()) && y > 0.0f && y < ((float) getHeight())) {
                    z = true;
                }
                if (isPressed() != z) {
                    setPressed(z);
                    break;
                }
                break;
            case 3:
                setPressed(false);
                break;
        }
        return true;
    }

    public void setLikeCount(int i) {
        this.likeCount = i;
        if (this.tvCount == null) {
            return;
        }
        if (i > 0) {
            this.tvCount.setVisibility(0);
            this.tvCount.setText(String.valueOf(i));
            return;
        }
        this.tvCount.setVisibility(8);
    }

    public int getLikeCount() {
        return this.likeCount;
    }

    public void setLikeDrawableRes(@DrawableRes int i) {
        this.likeDrawable = ContextCompat.getDrawable(getContext(), i);
        if (this.iconSize != 0) {
            this.likeDrawable = PraiseUtils.resizeDrawable(getContext(), this.likeDrawable, this.iconSize, this.iconSize);
        }
        if (this.isChecked) {
            this.icon.setImageDrawable(this.likeDrawable);
        }
    }

    public void setLikeDrawable(Drawable drawable) {
        this.likeDrawable = drawable;
        if (this.iconSize != 0) {
            this.likeDrawable = PraiseUtils.resizeDrawable(getContext(), drawable, this.iconSize, this.iconSize);
        }
        if (this.isChecked) {
            this.icon.setImageDrawable(this.likeDrawable);
        }
    }

    public void setUnlikeDrawableRes(@DrawableRes int i) {
        this.unLikeDrawable = ContextCompat.getDrawable(getContext(), i);
        if (this.iconSize != 0) {
            this.unLikeDrawable = PraiseUtils.resizeDrawable(getContext(), this.unLikeDrawable, this.iconSize, this.iconSize);
        }
        if (!this.isChecked) {
            this.icon.setImageDrawable(this.unLikeDrawable);
        }
    }

    public void setUnlikeDrawableResWithCount(@DrawableRes int i) {
        this.unLikeDrawableWithCount = ContextCompat.getDrawable(getContext(), i);
        if (this.iconSize != 0) {
            this.unLikeDrawableWithCount = PraiseUtils.resizeDrawable(getContext(), this.unLikeDrawableWithCount, this.iconSize, this.iconSize);
        }
        if (!this.isChecked) {
            this.icon.setImageDrawable(this.unLikeDrawableWithCount);
        }
    }

    public void setIconSizeDp(int i) {
        setIconSizePx((int) PraiseUtils.dipToPixels(getContext(), (float) i));
    }

    public void setIconSizePx(int i) {
        this.iconSize = i;
        setEffectsViewSize();
        this.unLikeDrawable = PraiseUtils.resizeDrawable(getContext(), this.unLikeDrawable, i, i);
        this.likeDrawable = PraiseUtils.resizeDrawable(getContext(), this.likeDrawable, i, i);
        this.unLikeDrawableWithCount = PraiseUtils.resizeDrawable(getContext(), this.unLikeDrawableWithCount, i, i);
    }

    public void setOnLikeListener(OnPraiseListener onPraiseListener) {
        this.likeListener = onPraiseListener;
    }

    public void setOnAnimationEndListener(OnAnimationEndListener onAnimationEndListener) {
        this.animationEndListener = onAnimationEndListener;
    }

    public void setFireworkColorInt(@ColorInt int i) {
        this.fireworkColor = i;
        this.firewokView.setColor(this.fireworkColor);
    }

    public void setFireworkColorRes(@ColorRes int i) {
        this.fireworkColor = ContextCompat.getColor(getContext(), i);
        this.firewokView.setColor(this.fireworkColor);
    }

    public void setCircleColorInt(@ColorInt int i) {
        this.circleColor = i;
        this.circleView.setColor(i);
    }

    public void setCircleColorRes(@ColorRes int i) {
        this.circleColor = ContextCompat.getColor(getContext(), i);
        this.circleView.setColor(this.circleColor);
    }

    private void setEffectsViewSize() {
        if (this.iconSize != 0) {
            this.firewokView.setSize((int) (((float) this.iconSize) * this.animationScaleFactor), (int) (((float) this.iconSize) * this.animationScaleFactor));
            this.circleView.setSize((int) (((float) this.iconSize) * this.animationScaleFactor), (int) (((float) this.iconSize) * this.animationScaleFactor));
        }
    }

    public void setLiked(Boolean bool) {
        if (bool.booleanValue()) {
            this.isChecked = true;
            this.icon.setImageDrawable(this.likeDrawable);
            return;
        }
        this.isChecked = false;
        this.icon.setImageDrawable(this.unLikeDrawable);
    }

    public void setLiked(Boolean bool, int i) {
        if (bool.booleanValue()) {
            this.isChecked = true;
            this.icon.setImageDrawable(this.likeDrawable);
            return;
        }
        this.isChecked = false;
        if (i > 0) {
            this.icon.setImageDrawable(this.unLikeDrawableWithCount);
        } else {
            this.icon.setImageDrawable(this.unLikeDrawable);
        }
    }

    public boolean isLiked() {
        return this.isChecked;
    }

    public void setEnabled(boolean z) {
        this.isEnabled = z;
    }

    public void setAnimationScaleFactor(float f) {
        this.animationScaleFactor = f;
        setEffectsViewSize();
    }

    public void setTextViewBackgroundNull() {
        this.tvCount.setBackground((Drawable) null);
    }
}
