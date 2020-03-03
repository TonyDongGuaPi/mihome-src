package com.mi.global.bbs.view.pop;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.praise.OnPraiseListener;
import com.mi.global.bbs.view.praise.PraiseButton;
import com.mi.util.Coder;

public class ForumActionPopup extends PopupWindow {
    private final int LEFT_PADDING;
    /* access modifiers changed from: private */
    public PraiseButton btnPraise;
    /* access modifiers changed from: private */
    public ImageView ivComment;
    /* access modifiers changed from: private */
    public ImageView ivShare;
    private Context mContext;
    private OnDissmissListener mDissmissListener;
    private final int[] mLocation;
    /* access modifiers changed from: private */
    public OnPopItemOnClickListener mPopItemOnClickListener;
    private Rect mRect;
    private int popupGravity;

    public interface OnDissmissListener {
        void onDissmiss();
    }

    public interface OnPopItemOnClickListener {
        void onItemClick(int i, boolean z);
    }

    public ForumActionPopup(Context context) {
        this(context, -2, -2);
        this.mContext = context;
    }

    public ForumActionPopup(Context context, int i, int i2) {
        this.LEFT_PADDING = Coder.a(140.0f);
        this.mRect = new Rect();
        this.mLocation = new int[2];
        this.popupGravity = 0;
        this.mContext = context;
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setWidth(i);
        setHeight(i2);
        setBackgroundDrawable(new BitmapDrawable());
        setContentView(LayoutInflater.from(this.mContext).inflate(R.layout.bbs_forum_action_popup, (ViewGroup) null));
        initUI();
    }

    private void initUI() {
        this.ivShare = (ImageView) getContentView().findViewById(R.id.forum_action_share);
        this.ivComment = (ImageView) getContentView().findViewById(R.id.forum_action_comment);
        this.btnPraise = (PraiseButton) getContentView().findViewById(R.id.forum_action_like);
        this.ivShare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ForumActionPopup.this.mPopItemOnClickListener != null) {
                    ForumActionPopup.this.mPopItemOnClickListener.onItemClick(0, false);
                }
                ForumActionPopup.this.dismiss();
            }
        });
        this.ivComment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ForumActionPopup.this.mPopItemOnClickListener != null) {
                    ForumActionPopup.this.mPopItemOnClickListener.onItemClick(1, false);
                }
                ForumActionPopup.this.dismiss();
            }
        });
        this.btnPraise.setOnLikeListener(new OnPraiseListener() {
            public void onNotLogin() {
            }

            public void praised() {
                ForumActionPopup.this.doPraiseLick(true);
            }

            public void unpraised() {
                ForumActionPopup.this.doPraiseLick(false);
            }
        });
    }

    /* access modifiers changed from: private */
    public void doPraiseLick(boolean z) {
        if (this.mPopItemOnClickListener != null) {
            this.mPopItemOnClickListener.onItemClick(2, z);
        }
    }

    public void show(View view) {
        View view2 = view;
        view2.getLocationOnScreen(this.mLocation);
        this.ivShare.setVisibility(4);
        this.ivComment.setVisibility(4);
        this.btnPraise.setVisibility(4);
        this.mRect.set(this.mLocation[0], this.mLocation[1], this.mLocation[0] + view.getWidth(), this.mLocation[1] + ((view.getHeight() * 3) / 4));
        setAnimationStyle(R.style.PopupAnimation);
        showAtLocation(view2, this.popupGravity, (this.mLocation[0] - getWidth()) - this.LEFT_PADDING, this.mLocation[1]);
        final AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(300);
        this.ivShare.postDelayed(new Runnable() {
            public void run() {
                ForumActionPopup.this.ivShare.startAnimation(alphaAnimation);
            }
        }, 250);
        final AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation2.setDuration(300);
        alphaAnimation2.setFillAfter(true);
        this.ivComment.postDelayed(new Runnable() {
            public void run() {
                ForumActionPopup.this.ivComment.startAnimation(alphaAnimation2);
            }
        }, 200);
        AlphaAnimation alphaAnimation3 = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation3.setDuration(150);
        alphaAnimation3.setFillAfter(true);
        this.btnPraise.setAnimation(alphaAnimation3);
        this.btnPraise.postDelayed(new Runnable() {
            public void run() {
                ForumActionPopup.this.btnPraise.setVisibility(0);
            }
        }, 200);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(340);
        view2.startAnimation(scaleAnimation);
        update();
    }

    public void setLike(boolean z) {
        this.btnPraise.setLiked(Boolean.valueOf(z));
    }

    public void dismiss() {
        super.dismiss();
        if (this.mDissmissListener != null) {
            this.mDissmissListener.onDissmiss();
        }
    }

    public void setPopItemOnClickListener(OnPopItemOnClickListener onPopItemOnClickListener) {
        this.mPopItemOnClickListener = onPopItemOnClickListener;
    }

    public void setmDissmissListener(OnDissmissListener onDissmissListener) {
        this.mDissmissListener = onDissmissListener;
    }
}
