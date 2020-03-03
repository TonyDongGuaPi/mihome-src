package com.mi.global.bbs.view.main;

import android.animation.Animator;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.bbs.R;
import com.mi.global.bbs.utils.DefaultAnimatorListener;
import com.mi.global.bbs.view.Editor.FontTextView;

public class ActionOptionItem extends LinearLayout {
    @BindView(2131493478)
    ImageView mItemAction;
    @BindView(2131493491)
    FontTextView mItemHint;

    public ActionOptionItem(Context context) {
        super(context);
        inflateView(context);
    }

    public ActionOptionItem(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        inflateView(context);
    }

    public ActionOptionItem(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        inflateView(context);
    }

    private void inflateView(Context context) {
        setOrientation(0);
        LayoutInflater.from(context).inflate(R.layout.bbs_action_options_item, this, true);
        ButterKnife.bind((View) this);
        this.mItemHint.setVisibility(8);
        this.mItemAction.setClickable(false);
    }

    public void showHint() {
        this.mItemHint.setVisibility(0);
        ViewCompat.setAlpha(this.mItemHint, 0.2f);
        this.mItemHint.animate().alpha(1.0f).setDuration(200).start();
    }

    public void hideHint() {
        this.mItemHint.setVisibility(8);
    }

    public void setHint(CharSequence charSequence) {
        this.mItemHint.setText(charSequence);
    }

    public void setActionResource(@DrawableRes int i) {
        this.mItemAction.setImageResource(i);
    }

    public void setOnActionClickListener(@Nullable View.OnClickListener onClickListener) {
        this.mItemAction.setOnClickListener(onClickListener);
    }

    public void hide(long j, long j2) {
        this.mItemAction.setClickable(false);
        animate().translationY(0.0f).scaleY(0.0f).scaleX(0.0f).setDuration(j).setStartDelay(j2).setInterpolator(new AccelerateInterpolator()).setListener(new DefaultAnimatorListener() {
            public void onAnimationStart(Animator animator) {
                ActionOptionItem.this.hideHint();
            }

            public void onAnimationEnd(Animator animator) {
                ActionOptionItem.this.setVisibility(8);
            }
        }).start();
    }

    public void show(int i, long j, long j2) {
        setVisibility(0);
        animate().translationY((float) i).scaleY(1.0f).scaleX(1.0f).setDuration(j).setStartDelay(j2).setInterpolator(new OvershootInterpolator()).setListener(new DefaultAnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                ActionOptionItem.this.showHint();
                ActionOptionItem.this.mItemAction.setClickable(true);
            }
        }).start();
    }

    public void silent() {
        ViewCompat.setScaleX(this, 0.0f);
        ViewCompat.setScaleY(this, 0.0f);
        setVisibility(8);
    }
}
