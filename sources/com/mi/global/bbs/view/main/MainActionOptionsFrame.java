package com.mi.global.bbs.view.main;

import android.animation.Animator;
import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.ButterKnife;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.R;
import com.mi.global.bbs.utils.DefaultAnimatorListener;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import java.util.ArrayList;

public class MainActionOptionsFrame extends FrameLayout {
    private static final long DEFAULT_ANIMATION_DELAY = 50;
    private static final long DEFAULT_ANIMATION_TIME = 200;
    private static final long DEFAULT_OPTIONS_ANIMATION_TIME = 300;
    private String[] actionHints;
    private int[] actionResources = {R.drawable.action_options_thread_selector, R.drawable.action_options_question_selector, R.drawable.action_options_something_selector};
    /* access modifiers changed from: private */
    public boolean animating = false;
    private boolean expandOptions = false;
    /* access modifiers changed from: private */
    public int firstOptionMargin = 0;
    private ArrayList<ActionOptionItem> mActionOptionItems;
    ImageView mActionView;
    private boolean mActionVisible;
    FrameLayout mBlurringView;
    /* access modifiers changed from: private */
    public OnActionOptionsClickListener mOnActionOptionsClickListener;
    /* access modifiers changed from: private */
    public int[] margins;
    /* access modifiers changed from: private */
    public int optionsMargin = 0;
    private int optionsSize;

    public interface OnActionOptionsClickListener {
        void onActionItemClick(ActionOptionItem actionOptionItem, int i);
    }

    public MainActionOptionsFrame(@NonNull Context context) {
        super(context);
        inflateView(context);
    }

    public MainActionOptionsFrame(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        inflateView(context);
    }

    public MainActionOptionsFrame(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
        inflateView(context);
    }

    private void inflateView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.bbs_action_options_frame, this, true);
        this.mBlurringView = (FrameLayout) inflate.findViewById(R.id.blurring_view);
        this.mActionView = (ImageView) inflate.findViewById(R.id.action_view);
        this.firstOptionMargin = getResources().getDimensionPixelOffset(R.dimen.action_option_gap_first);
        this.optionsMargin = getResources().getDimensionPixelOffset(R.dimen.action_options_gap);
        ButterKnife.bind((View) this);
        this.mBlurringView.setVisibility(8);
        this.mBlurringView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActionOptionsFrame.this.expandOrHideOptions();
            }
        });
        this.mActionView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActionOptionsFrame.this.expandOrHideOptions();
            }
        });
        this.mActionOptionItems = new ArrayList<>();
        this.actionHints = BBSApplication.getInstance().getResources().getStringArray(R.array.action_hint_options);
        this.optionsSize = this.actionHints.length;
        this.margins = new int[this.optionsSize];
        inflateOptionsView(context);
    }

    private void inflateOptionsView(Context context) {
        for (final int i = this.optionsSize - 1; i >= 0; i--) {
            final ActionOptionItem actionOptionItem = new ActionOptionItem(context);
            actionOptionItem.setLayoutDirection(3);
            actionOptionItem.setHint(this.actionHints[i]);
            actionOptionItem.setActionResource(this.actionResources[i]);
            actionOptionItem.setOnActionClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MainActionOptionsFrame.this.expandOrHideOptions();
                    if (MainActionOptionsFrame.this.mOnActionOptionsClickListener != null) {
                        MainActionOptionsFrame.this.mOnActionOptionsClickListener.onActionItemClick(actionOptionItem, i);
                    }
                }
            });
            actionOptionItem.silent();
            this.mActionOptionItems.add(actionOptionItem);
            addView(actionOptionItem, this.mActionView.getLayoutParams());
        }
        calculateMargin();
    }

    private void calculateMargin() {
        this.mActionView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                MainActionOptionsFrame.this.mActionView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                MainActionOptionsFrame.this.margins[0] = MainActionOptionsFrame.this.firstOptionMargin + MainActionOptionsFrame.this.mActionView.getHeight();
                MainActionOptionsFrame.this.margins[1] = MainActionOptionsFrame.this.mActionView.getHeight() + MainActionOptionsFrame.this.margins[0] + MainActionOptionsFrame.this.optionsMargin;
            }
        });
    }

    public void hide() {
        this.mActionView.animate().rotation(0.0f).setInterpolator(new AccelerateInterpolator()).scaleX(1.0f).scaleY(1.0f).setDuration(200).start();
        this.mBlurringView.animate().alpha(0.2f).setDuration(200).setListener(new DefaultAnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                MainActionOptionsFrame.this.mBlurringView.setVisibility(8);
            }
        }).start();
        animateHideOptions();
        this.expandOptions = false;
    }

    /* access modifiers changed from: private */
    public void expandOrHideOptions() {
        if (!this.animating) {
            this.animating = true;
            if (this.expandOptions) {
                this.mActionView.animate().rotation(0.0f).setInterpolator(new AccelerateInterpolator()).scaleX(1.0f).scaleY(1.0f).setDuration(200).start();
                this.mBlurringView.animate().alpha(0.2f).setDuration(200).setListener(new DefaultAnimatorListener() {
                    public void onAnimationEnd(Animator animator) {
                        MainActionOptionsFrame.this.mBlurringView.setVisibility(8);
                        boolean unused = MainActionOptionsFrame.this.animating = false;
                    }

                    public void onAnimationCancel(Animator animator) {
                        boolean unused = MainActionOptionsFrame.this.animating = false;
                    }
                }).start();
                animateHideOptions();
            } else {
                this.mActionView.animate().rotation(225.0f).setInterpolator(new AccelerateInterpolator()).scaleX(0.8f).scaleY(0.8f).setDuration(200).start();
                this.mBlurringView.setVisibility(0);
                this.mBlurringView.setAlpha(0.2f);
                this.mBlurringView.animate().alpha(1.0f).setDuration(200).setListener(new DefaultAnimatorListener() {
                    public void onAnimationStart(Animator animator) {
                        MainActionOptionsFrame.this.mBlurringView.invalidate();
                    }

                    public void onAnimationEnd(Animator animator) {
                        MainActionOptionsFrame.this.animateExpandOptions();
                        boolean unused = MainActionOptionsFrame.this.animating = false;
                    }

                    public void onAnimationCancel(Animator animator) {
                        boolean unused = MainActionOptionsFrame.this.animating = false;
                    }
                }).start();
            }
            this.expandOptions = true ^ this.expandOptions;
        }
    }

    /* access modifiers changed from: private */
    public void animateExpandOptions() {
        for (int i = 0; i < this.optionsSize; i++) {
            this.mActionOptionItems.get(i).show(-this.margins[i], DEFAULT_OPTIONS_ANIMATION_TIME, DEFAULT_ANIMATION_DELAY * ((long) i));
        }
    }

    private void animateHideOptions() {
        for (int i = 0; i < this.optionsSize; i++) {
            this.mActionOptionItems.get(i).hide(DEFAULT_OPTIONS_ANIMATION_TIME, ((long) i) * DEFAULT_ANIMATION_DELAY);
        }
    }

    public void changeActionButtonMargin(int i) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mActionView.getLayoutParams();
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.fad_bottom_margin) + i;
        if (layoutParams.bottomMargin != dimensionPixelOffset) {
            layoutParams.bottomMargin = dimensionPixelOffset;
            this.mActionView.requestLayout();
        }
    }

    public void showActionView(boolean z) {
        this.mActionView.setClickable(true);
        if (!this.mActionVisible) {
            if (z) {
                ViewPropertyAnimator.animate(this.mActionView).cancel();
                ViewPropertyAnimator.animate(this.mActionView).scaleX(1.0f).scaleY(1.0f).setDuration(DEFAULT_OPTIONS_ANIMATION_TIME).start();
            } else {
                ViewHelper.setScaleX(this.mActionView, 1.0f);
                ViewHelper.setScaleY(this.mActionView, 1.0f);
            }
            this.mActionVisible = true;
            return;
        }
        ViewHelper.setScaleX(this.mActionView, 1.0f);
        ViewHelper.setScaleY(this.mActionView, 1.0f);
    }

    public void hideActionView(boolean z) {
        this.mActionView.setClickable(false);
        if (this.mActionVisible) {
            if (z) {
                ViewPropertyAnimator.animate(this.mActionView).cancel();
                ViewPropertyAnimator.animate(this.mActionView).scaleX(0.0f).scaleY(0.0f).setDuration(DEFAULT_OPTIONS_ANIMATION_TIME).start();
            } else {
                ViewHelper.setScaleX(this.mActionView, 0.0f);
                ViewHelper.setScaleY(this.mActionView, 0.0f);
            }
            this.mActionVisible = false;
            return;
        }
        ViewHelper.setScaleX(this.mActionView, 0.0f);
        ViewHelper.setScaleY(this.mActionView, 0.0f);
    }

    public void setOnActionOptionsClickListener(OnActionOptionsClickListener onActionOptionsClickListener) {
        this.mOnActionOptionsClickListener = onActionOptionsClickListener;
    }

    public boolean isExpandOptions() {
        return this.expandOptions;
    }

    public boolean isAnimating() {
        return this.animating;
    }

    public void refreshBlurView() {
        this.mBlurringView.invalidate();
    }
}
