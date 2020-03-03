package com.mi.global.bbs.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.inter.OnCommentClickListener;
import com.mi.global.bbs.view.praise.OnAnimationEndListener;
import com.mi.global.bbs.view.praise.OnPraiseListener;
import com.mi.global.bbs.view.praise.PraiseButton;
import com.mi.util.Coder;
import com.mi.util.ScreenInfo;

public class LikeAndCommentView extends LinearLayout implements Animator.AnimatorListener {
    /* access modifiers changed from: private */
    public ValueAnimator animationFlex;
    private CommentNumView btnComment;
    /* access modifiers changed from: private */
    public PraiseButton btnLike;
    /* access modifiers changed from: private */
    public OnCommentClickListener commentClickListener;
    private FrameLayout flComment;
    private ImageView ivCommentSend;
    private LinearLayout likeCommentLy;
    /* access modifiers changed from: private */
    public LinearLayout lyCommentBottom;
    private View.OnClickListener onCommentClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (LikeAndCommentView.this.commentClickListener != null) {
                LikeAndCommentView.this.commentClickListener.onCommentClick();
            }
        }
    };
    private OnPraiseListener onPriaseClickListener;
    /* access modifiers changed from: private */
    public boolean show = true;
    private TextView tvComment;

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
    }

    public LikeAndCommentView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.ly_like_comment_view, this);
        this.btnLike = (PraiseButton) findViewById(R.id.view_like_btn);
        this.likeCommentLy = (LinearLayout) findViewById(R.id.view_like_comment_ly);
        this.btnComment = (CommentNumView) findViewById(R.id.view_comment_btn);
        this.tvComment = (TextView) findViewById(R.id.tv_comments_edit_text);
        this.ivCommentSend = (ImageView) findViewById(R.id.iv_comment_send);
        this.flComment = (FrameLayout) findViewById(R.id.fl_home_item_comment);
        this.lyCommentBottom = (LinearLayout) findViewById(R.id.ly_comment_bottom);
        final int b = ScreenInfo.a().b() - Coder.a(90.0f);
        this.animationFlex = ValueAnimator.ofInt(new int[]{Coder.a(25.0f), b});
        this.animationFlex.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) LikeAndCommentView.this.lyCommentBottom.getLayoutParams();
                layoutParams.width = intValue;
                if (intValue <= b) {
                    LikeAndCommentView.this.lyCommentBottom.setLayoutParams(layoutParams);
                }
                LikeAndCommentView.this.invalidate();
            }
        });
        this.animationFlex.setInterpolator(new AccelerateDecelerateInterpolator());
        this.animationFlex.setDuration(700);
        this.btnLike.setOnAnimationEndListener(new OnAnimationEndListener() {
            public void onAnimationEnd(PraiseButton praiseButton) {
                if (LikeAndCommentView.this.show) {
                    LikeAndCommentView.this.lyCommentBottom.setVisibility(0);
                    LikeAndCommentView.this.animationFlex.start();
                    LikeAndCommentView.this.showCommentSend();
                    LikeAndCommentView.this.showCommentTip();
                    LikeAndCommentView.this.btnLike.postDelayed(new Runnable() {
                        public void run() {
                            LikeAndCommentView.this.resetView();
                        }
                    }, 4000);
                }
            }
        });
        this.lyCommentBottom.setOnClickListener(this.onCommentClickListener);
        this.flComment.setOnClickListener(this.onCommentClickListener);
        this.btnLike.setOnLikeListener(new OnPraiseListener() {
            public void onNotLogin() {
                LikeAndCommentView.this.notLogin();
            }

            public void praised() {
                LikeAndCommentView.this.doPraiseLick(true);
            }

            public void unpraised() {
                LikeAndCommentView.this.doPraiseLick(false);
            }
        });
    }

    public void setOnPriaseClickListener(OnPraiseListener onPraiseListener) {
        this.onPriaseClickListener = onPraiseListener;
    }

    /* access modifiers changed from: private */
    public void notLogin() {
        if (this.onPriaseClickListener != null) {
            this.onPriaseClickListener.onNotLogin();
        }
    }

    /* access modifiers changed from: private */
    public void doPraiseLick(boolean z) {
        if (this.onPriaseClickListener == null) {
            return;
        }
        if (z) {
            this.onPriaseClickListener.praised();
        } else {
            this.onPriaseClickListener.unpraised();
        }
    }

    public void setCommentClickListener(OnCommentClickListener onCommentClickListener2) {
        this.commentClickListener = onCommentClickListener2;
    }

    /* access modifiers changed from: private */
    public void showCommentSend() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(500);
        this.btnComment.setVisibility(8);
        this.ivCommentSend.setVisibility(0);
        this.ivCommentSend.startAnimation(alphaAnimation);
    }

    /* access modifiers changed from: private */
    public void showCommentTip() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setStartOffset(300);
        alphaAnimation.setDuration(400);
        this.tvComment.setText(R.string.home_write_a_comment);
        this.tvComment.setVisibility(0);
        this.tvComment.startAnimation(alphaAnimation);
    }

    public void resetView() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.lyCommentBottom.getLayoutParams();
        layoutParams.width = Coder.a(15.0f);
        this.lyCommentBottom.setLayoutParams(layoutParams);
        invalidate();
        this.tvComment.setText("");
        this.lyCommentBottom.setVisibility(4);
        this.ivCommentSend.setVisibility(4);
        this.btnComment.setVisibility(0);
    }

    public LikeAndCommentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    public LikeAndCommentView(Context context) {
        super(context);
        initView(context);
    }

    public void setLikeAndComment(boolean z, int i, int i2) {
        this.btnLike.setLiked(Boolean.valueOf(z));
        this.btnLike.setLikeCount(i);
        this.btnComment.setMesCount(i2);
    }

    public void setLikeAndCommentPreview(boolean z, int i, int i2) {
        this.btnLike.setLiked(Boolean.valueOf(z), i);
        this.btnLike.setLikeCount(i);
        this.btnComment.setMesCount(i2);
    }

    public void setBackgroundBlack() {
        this.likeCommentLy.setBackground((Drawable) null);
        this.btnLike.setTextViewBackgroundNull();
        this.btnComment.setTextViewBackgroundNull();
    }

    public int getCommentCount() {
        return this.btnComment.getMesCount();
    }

    public void setLikeAnimationShow(boolean z) {
        this.show = z;
    }
}
