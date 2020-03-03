package com.mi.global.shop.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.mi.global.shop.R;
import com.mi.global.shop.loader.BaseResult;
import com.mi.global.shop.loader.ProgressNotifiable;
import com.mi.log.LogUtil;
import com.mi.util.MiToast;

public class EmptyLoadingView extends RelativeLayout implements ProgressNotifiable {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7157a = "EmptyLoadingView";
    private Handler b;
    public boolean dontShowBg;
    public boolean dontShowEmptyMsg;
    protected CommonButton mButton;
    protected int mEmptyResId;
    protected CharSequence mEmptyText;
    protected ImageView mErrorTipIcon;
    protected RelativeLayout mProgressContainer;
    protected CustomTextView mTextView;
    public OnErrorReloadButtonClick onErrorReloadButtonClick;

    public interface OnErrorReloadButtonClick {
        void a();
    }

    public void onFinish() {
    }

    public EmptyLoadingView(Context context) {
        this(context, (AttributeSet) null);
    }

    public EmptyLoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new Handler(Looper.getMainLooper());
        LayoutInflater.from(context).inflate(R.layout.shop_empty_loading, this, true);
        this.mProgressContainer = (RelativeLayout) findViewById(R.id.empty_progress_container);
        this.mTextView = (CustomTextView) findViewById(R.id.empty_text);
        this.mErrorTipIcon = (ImageView) findViewById(R.id.empty_tip_icon);
        this.mButton = (CommonButton) findViewById(R.id.button);
    }

    public void setOnErrorReloadButtonClick(OnErrorReloadButtonClick onErrorReloadButtonClick2) {
        this.onErrorReloadButtonClick = onErrorReloadButtonClick2;
    }

    /* access modifiers changed from: protected */
    public void updateStyle(boolean z) {
        if (z) {
            ((RelativeLayout.LayoutParams) this.mProgressContainer.getLayoutParams()).addRule(12);
        } else {
            ((RelativeLayout.LayoutParams) this.mProgressContainer.getLayoutParams()).addRule(13);
        }
    }

    public void updateBackground(final boolean z) {
        this.b.postDelayed(new Runnable() {
            public void run() {
                if (z) {
                    if (!EmptyLoadingView.this.dontShowBg && EmptyLoadingView.this.mProgressContainer != null) {
                        EmptyLoadingView.this.mProgressContainer.setBackgroundResource(R.drawable.shop_loading_view_bg);
                    }
                } else if (EmptyLoadingView.this.mProgressContainer != null) {
                    EmptyLoadingView.this.mProgressContainer.setBackground((Drawable) null);
                }
            }
        }, 1000);
    }

    public void startLoading(boolean z) {
        this.mProgressContainer.setVisibility(0);
        this.mTextView.setVisibility(8);
        this.mErrorTipIcon.setVisibility(8);
        this.mButton.setVisibility(8);
        showView(this);
    }

    public void stopLoading(boolean z) {
        if (z || this.dontShowEmptyMsg) {
            hideView(this);
            return;
        }
        showView(this);
        this.mProgressContainer.setVisibility(8);
        this.mTextView.setVisibility(0);
        this.mErrorTipIcon.setVisibility(8);
        if (this.mEmptyResId != 0) {
            this.mTextView.setText(this.mEmptyResId);
        } else if (!TextUtils.isEmpty(this.mEmptyText)) {
            this.mTextView.setText(this.mEmptyText);
        }
        this.mButton.setVisibility(8);
    }

    public void init(boolean z, boolean z2) {
        if (z2) {
            setVisibility(0);
            this.mProgressContainer.setVisibility(0);
            this.mTextView.setVisibility(8);
            this.mErrorTipIcon.setVisibility(8);
            this.mButton.setVisibility(8);
        } else if (z) {
            setVisibility(8);
        } else {
            setVisibility(0);
            this.mProgressContainer.setVisibility(8);
            this.mTextView.setVisibility(0);
            this.mErrorTipIcon.setVisibility(8);
            this.mButton.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void showView(View view) {
        if (view != null) {
            if (view.getVisibility() != 0) {
                view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.appear));
                view.setVisibility(0);
            }
            view.setBackgroundColor(Color.parseColor("#efeff0"));
            view.setAlpha(0.8f);
        }
    }

    /* access modifiers changed from: protected */
    public void hideView(View view) {
        if (view != null && view.getVisibility() == 0) {
            if (view.isShown()) {
                view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.disappear));
            }
            view.setVisibility(8);
        }
    }

    public void setEmptyText(int i) {
        this.mEmptyResId = i;
    }

    public void setEmptyText(CharSequence charSequence) {
        this.mEmptyText = charSequence;
    }

    public void onError(boolean z, BaseResult.ResultStatus resultStatus, Handler.Callback callback) {
        LogUtil.b(f7157a, "onError, hasData:" + z + ",status:" + resultStatus);
        ((RelativeLayout.LayoutParams) this.mProgressContainer.getLayoutParams()).addRule(13);
        if (z) {
            hideView(this);
            MiToast.a(getContext(), BaseResult.a(resultStatus), 0);
            return;
        }
        showView(this);
        this.mProgressContainer.setVisibility(8);
        this.mErrorTipIcon.setVisibility(0);
        this.mTextView.setVisibility(0);
        this.mTextView.setText(BaseResult.a(resultStatus));
        this.mButton.setVisibility(0);
        if (resultStatus == BaseResult.ResultStatus.NETWROK_ERROR) {
            this.mButton.setText(R.string.shop_check_network);
        }
        this.mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (EmptyLoadingView.this.onErrorReloadButtonClick != null) {
                    EmptyLoadingView.this.onErrorReloadButtonClick.a();
                }
            }
        });
    }
}
