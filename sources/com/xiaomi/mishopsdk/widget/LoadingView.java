package com.xiaomi.mishopsdk.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.mishopsdk.R;
import com.xiaomi.mishopsdk.util.ToastUtil;
import com.xiaomi.mishopsdk.widget.pulltorefresh.IPullToRefresh;
import java.lang.ref.WeakReference;

public class LoadingView extends RelativeLayout {
    protected CommonButton mButton;
    protected View mCustomErrorView;
    protected TextView mError;
    protected View mErrorContainer;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    protected View mProgressContainer;
    protected WeakReference<IPullToRefresh<?>> mPullToRefreshRef;

    public LoadingView(Context context) {
        super(context);
        init(context, (AttributeSet) null);
    }

    public LoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        LayoutInflater.from(context).inflate(R.layout.mishopsdk_loading_view, this, true);
        this.mProgressContainer = findViewById(R.id.mishopsdk_loadingview_progress_container);
        this.mErrorContainer = findViewById(R.id.mishopsdk_loadingview_txtcontainer);
        this.mError = (TextView) findViewById(R.id.mishopsdk_loadingview_txterror);
        this.mButton = (CommonButton) findViewById(R.id.mishopsdk_loadingview_button);
        this.mProgressContainer.setVisibility(8);
        this.mErrorContainer.setVisibility(8);
        if (isInEditMode()) {
            setVisibility(8);
        }
    }

    public void bindWithPullToRefreshLayout(IPullToRefresh<?> iPullToRefresh) {
        this.mPullToRefreshRef = new WeakReference<>(iPullToRefresh);
    }

    public void startLoading(boolean z, boolean z2) {
        this.mProgressContainer.setVisibility(z ? 0 : 8);
        if (z) {
            updateProgressStyle(z2);
        }
    }

    public void stopLoading() {
        IPullToRefresh iPullToRefresh;
        this.mProgressContainer.setVisibility(8);
        if (this.mPullToRefreshRef != null && (iPullToRefresh = (IPullToRefresh) this.mPullToRefreshRef.get()) != null) {
            iPullToRefresh.onRefreshComplete();
        }
    }

    public void loadingSuccess() {
        this.mErrorContainer.setVisibility(8);
        if (this.mCustomErrorView != null) {
            this.mCustomErrorView.setVisibility(8);
        }
    }

    public void showErrorAsToast(int i) {
        if (getContext() != null) {
            showErrorAsToast(getContext().getString(i));
        }
    }

    public void showErrorAsToast(String str) {
        showError(str, true, (String) null, (Handler.Callback) null);
    }

    public void showErrorFormally(int i) {
        if (getContext() != null) {
            showError(getContext().getString(i), false, (String) null, (Handler.Callback) null);
        }
    }

    public void showErrorFormally(String str) {
        showError(str, false, (String) null, (Handler.Callback) null);
    }

    public void showErrorWithActionButton(int i, int i2, Handler.Callback callback) {
        if (getContext() != null) {
            showErrorWithActionButton(getContext().getString(i), getContext().getString(i2), callback);
        }
    }

    public void showErrorWithActionButton(String str, String str2, Handler.Callback callback) {
        if (getContext() != null) {
            if (TextUtils.isEmpty(str2)) {
                str2 = getContext().getString(R.string.mishopsdk_try_again);
            }
            showError(str, false, str2, callback);
        }
    }

    private void showError(String str, boolean z, String str2, final Handler.Callback callback) {
        this.mProgressContainer.setVisibility(8);
        if (z) {
            this.mErrorContainer.setVisibility(8);
            ToastUtil.show(str);
            return;
        }
        this.mErrorContainer.setVisibility(0);
        if (this.mCustomErrorView != null) {
            this.mErrorContainer.setVisibility(8);
        }
        this.mError.setText(str);
        if (callback != null) {
            this.mButton.setVisibility(0);
            this.mButton.setText(str2);
            this.mButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    callback.handleMessage((Message) null);
                }
            });
            return;
        }
        this.mButton.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void updateProgressStyle(final boolean z) {
        ((RelativeLayout.LayoutParams) this.mProgressContainer.getLayoutParams()).addRule(z ? 13 : 12);
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (LoadingView.this.mProgressContainer == null) {
                    return;
                }
                if (z) {
                    LoadingView.this.mProgressContainer.setBackgroundDrawable((Drawable) null);
                } else {
                    LoadingView.this.mProgressContainer.setBackgroundResource(R.drawable.mishopsdk_loading_view_bg);
                }
            }
        }, 1000);
    }

    public void setErrorView(View view) {
        if (view != null) {
            if (this.mCustomErrorView != null) {
                this.mCustomErrorView = null;
            }
            this.mCustomErrorView = view;
            this.mProgressContainer.setVisibility(8);
            this.mErrorContainer.setVisibility(8);
            this.mButton.setVisibility(8);
            addView(view, 0);
        }
    }
}
