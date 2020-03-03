package com.xiaomi.mishopsdk.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.xiaomi.mishopsdk.R;

public class CommonAlertDialog extends Dialog {
    public static final int BUTTON_NEGATIVE = R.id.mishopsdk_basedialog_bottombar_btnnegative;
    public static final int BUTTON_POSITIVE = R.id.mishopsdk_basedialog_bottombar_btnpositive;
    private static final int DURATION = 300;
    private AnimatorSet mAnimatorSet = new AnimatorSet();
    private View mBottomBar;
    private View mBottomBar1;
    private View.OnClickListener mCloseListener = new View.OnClickListener() {
        public void onClick(View view) {
            CommonAlertDialog.this.dismiss();
        }
    };
    private Context mContext;
    private ImageView mIcon;
    private TextView mMessage;
    private CommonButton mNegativeBtn;
    private CommonButton mNeutralBtn;
    private CommonButton mPositiveBtn;
    /* access modifiers changed from: private */
    public RelativeLayout mRootLayout;
    private TextView mTitle;

    public CommonAlertDialog(Context context) {
        super(context, R.style.mishopsdk_Widget_Dialog);
        this.mContext = context;
        setContentView(R.layout.mishopsdk_base_dialog);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        this.mTitle = (TextView) findViewById(R.id.mishopsdk_basedialog_titlebar_title);
        this.mIcon = (ImageView) findViewById(R.id.mishopsdk_basedialog_titlebar_icon);
        this.mMessage = (TextView) findViewById(R.id.mishopsdk_basedialog_message);
        this.mPositiveBtn = (CommonButton) findViewById(R.id.mishopsdk_basedialog_bottombar_btnpositive);
        this.mNegativeBtn = (CommonButton) findViewById(R.id.mishopsdk_basedialog_bottombar_btnnegative);
        this.mNeutralBtn = (CommonButton) findViewById(R.id.mishopsdk_basedialog_bottombar1_btnNeutral);
        this.mBottomBar = findViewById(R.id.mishopsdk_basedialog_bottombar);
        this.mBottomBar1 = findViewById(R.id.mishopsdk_basedialog_bottombar1);
        this.mRootLayout = (RelativeLayout) findViewById(R.id.mishopsdk_basedialog_parent);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Window window = getWindow();
        layoutParams.copyFrom(window.getAttributes());
        layoutParams.width = -1;
        layoutParams.height = -2;
        layoutParams.gravity = 80;
        window.setAttributes(layoutParams);
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                CommonAlertDialog.this.mRootLayout.setVisibility(0);
                CommonAlertDialog.this.start();
            }
        });
    }

    /* access modifiers changed from: private */
    public void start() {
        ViewHelper.setPivotX(this.mRootLayout, ((float) this.mRootLayout.getMeasuredWidth()) / 2.0f);
        ViewHelper.setPivotY(this.mRootLayout, ((float) this.mRootLayout.getMeasuredHeight()) / 2.0f);
        this.mAnimatorSet.playTogether(ObjectAnimator.ofFloat((Object) this.mRootLayout, "translationY", 300.0f, 0.0f).setDuration(300), ObjectAnimator.ofFloat((Object) this.mRootLayout, "alpha", 0.0f, 1.0f).setDuration(450));
        this.mAnimatorSet.setDuration((long) Math.abs(300));
        this.mAnimatorSet.start();
    }

    public void setTitle(int i) {
        this.mTitle.setVisibility(0);
        this.mTitle.setText(i);
    }

    public void setIcon(int i) {
        this.mIcon.setVisibility(0);
        this.mIcon.setImageResource(i);
    }

    public void setMessage(int i) {
        setMessage((CharSequence) getContext().getString(i));
    }

    public void setMessage(CharSequence charSequence) {
        this.mMessage.setVisibility(0);
        this.mMessage.setText(charSequence);
    }

    public void setView(View view) {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.mishopsdk_basedialog_content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
    }

    public void setPositiveButton(int i, View.OnClickListener onClickListener) {
        this.mBottomBar.setVisibility(0);
        this.mPositiveBtn.setVisibility(0);
        if (i > 0) {
            this.mPositiveBtn.setText(i);
        }
        if (onClickListener != null) {
            this.mPositiveBtn.setOnClickListener(new OnClickListenerWrapper(onClickListener));
        } else {
            this.mPositiveBtn.setOnClickListener(this.mCloseListener);
        }
    }

    public void setPositiveButton(int i, View.OnClickListener onClickListener, boolean z) {
        this.mBottomBar.setVisibility(0);
        this.mPositiveBtn.setVisibility(0);
        if (i > 0) {
            this.mPositiveBtn.setText(i);
        }
        if (onClickListener == null) {
            this.mPositiveBtn.setOnClickListener(this.mCloseListener);
        } else if (z) {
            this.mPositiveBtn.setOnClickListener(new OnClickListenerWrapper(onClickListener));
        } else {
            this.mPositiveBtn.setOnClickListener(onClickListener);
        }
    }

    public void setPositiveButton(int i, int i2, View.OnClickListener onClickListener) {
        this.mPositiveBtn.setVisibility(0);
        this.mBottomBar.setVisibility(0);
        if (i > 0) {
            this.mPositiveBtn.setText(i);
        }
        if (i2 > 0) {
            this.mPositiveBtn.setTextColor(this.mContext.getResources().getColor(i2));
        }
        if (onClickListener != null) {
            this.mPositiveBtn.setOnClickListener(new OnClickListenerWrapper(onClickListener));
        } else {
            this.mPositiveBtn.setOnClickListener(this.mCloseListener);
        }
    }

    public void setNegativeButton(int i, View.OnClickListener onClickListener) {
        this.mNegativeBtn.setVisibility(0);
        this.mBottomBar.setVisibility(0);
        if (i > 0) {
            this.mNegativeBtn.setText(i);
        }
        if (onClickListener != null) {
            this.mNegativeBtn.setOnClickListener(new OnClickListenerWrapper(onClickListener));
        } else {
            this.mNegativeBtn.setOnClickListener(this.mCloseListener);
        }
    }

    public void setNeutralButton(int i, View.OnClickListener onClickListener) {
        this.mBottomBar1.setVisibility(0);
        this.mBottomBar.setVisibility(8);
        if (i > 0) {
            this.mNeutralBtn.setText(i);
        }
        if (onClickListener != null) {
            this.mNeutralBtn.setOnClickListener(new OnClickListenerWrapper(onClickListener));
        } else {
            this.mNeutralBtn.setOnClickListener(this.mCloseListener);
        }
    }

    private class OnClickListenerWrapper implements View.OnClickListener {
        private View.OnClickListener mListener;

        public OnClickListenerWrapper(View.OnClickListener onClickListener) {
            this.mListener = onClickListener;
        }

        public void onClick(View view) {
            if (this.mListener != null) {
                this.mListener.onClick(view);
            }
            CommonAlertDialog.this.dismiss();
        }
    }
}
