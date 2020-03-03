package com.xiaomi.smarthome.camera.v4.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.XMBaseListView;
import java.lang.ref.WeakReference;

public class CustomPullDownRefreshListView extends XMBaseListView {
    private static final int MSG_BUNCING_BACK = 0;
    private final int FRAME_DURATION = 16;
    private final float MAXIMUM_VELOCITY = 1.5f;
    private boolean isDown = false;
    private Animation mAnimRotate;
    private Animation mAnimRotateBack;
    private ImageView mBkgImgView = null;
    private boolean mCanPullDown = true;
    private boolean mCanRefresh = false;
    private View mContainer = null;
    private int mCurOffsetY = 0;
    private BuncingHandler mHandler = new BuncingHandler(this);
    private View mHeader = null;
    private OnInterceptListener mInterceptListener;
    private boolean mIsRefreshing = false;
    /* access modifiers changed from: private */
    public boolean mLastItemVisible = false;
    private int mMaxHeaderHeight = Integer.MAX_VALUE;
    private float mMaximumVelocity;
    /* access modifiers changed from: private */
    public OnLastItemVisibleListener mOnLastItemVisibleListener = null;
    private int mOriHeight = 0;
    private boolean mPullDownEnabled = true;
    private CharSequence mPullDownToRefreshText;
    private OnRefreshListener mRefreshListener;
    private CharSequence mReleaseText;
    private boolean mShowRefreshProgress = true;
    private float mStartY = 0.0f;
    private int mTriggerRefreshThreshold;

    public interface OnInterceptListener {
        boolean needInterceptAnimation();

        void onInterceptAnimation();
    }

    public interface OnLastItemVisibleListener {
        void onLastItemVisible();
    }

    public interface OnRefreshListener {
        void startRefresh();
    }

    public CustomPullDownRefreshListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public CustomPullDownRefreshListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CustomPullDownRefreshListView(Context context) {
        super(context);
        init();
    }

    public void setOnLastItemVisibleListener(OnLastItemVisibleListener onLastItemVisibleListener) {
        this.mOnLastItemVisibleListener = onLastItemVisibleListener;
    }

    public void setRefreshListener(OnRefreshListener onRefreshListener) {
        this.mRefreshListener = onRefreshListener;
    }

    public void setInterceptListener(OnInterceptListener onInterceptListener) {
        this.mInterceptListener = onInterceptListener;
    }

    public void setShowRefreshProgress(boolean z) {
        this.mShowRefreshProgress = z;
    }

    public boolean isRefreshing() {
        return this.mIsRefreshing;
    }

    public void doRefresh() {
        if (!this.mIsRefreshing && this.mRefreshListener != null) {
            preRefresh();
            this.mRefreshListener.startRefresh();
        }
    }

    private void preRefresh() {
        this.mIsRefreshing = true;
        ((TextView) findViewById(R.id.pull_header_txt)).setText(R.string.refreshing);
        if (this.mShowRefreshProgress) {
            findViewById(R.id.pull_header_prog).setVisibility(0);
        }
        View findViewById = findViewById(R.id.pull_header_indc);
        findViewById.clearAnimation();
        findViewById.setVisibility(8);
        ViewGroup.LayoutParams layoutParams = this.mContainer.getLayoutParams();
        if (this.mCurOffsetY == 0) {
            this.mCurOffsetY = getContext().getResources().getDimensionPixelSize(R.dimen.pull_down_header_height);
        }
        layoutParams.height = this.mCurOffsetY + this.mOriHeight;
        this.mContainer.setLayoutParams(layoutParams);
    }

    public void setProgressDrawable(Drawable drawable) {
        ((ProgressBar) findViewById(R.id.pull_header_prog)).setIndeterminateDrawable(drawable);
    }

    public void postRefresh() {
        this.mIsRefreshing = false;
        ((TextView) findViewById(R.id.pull_header_txt)).setText(this.mPullDownToRefreshText);
        findViewById(R.id.pull_header_prog).setVisibility(8);
        View findViewById = findViewById(R.id.pull_header_indc);
        findViewById.clearAnimation();
        findViewById.setVisibility(0);
        ViewGroup.LayoutParams layoutParams = this.mContainer.getLayoutParams();
        layoutParams.height = this.mOriHeight + this.mCurOffsetY;
        this.mContainer.setLayoutParams(layoutParams);
        this.mHandler.sendEmptyMessageDelayed(0, 16);
    }

    public void setHeaderBackground(Drawable drawable) {
        if (this.mBkgImgView != null) {
            this.mBkgImgView.setImageDrawable(drawable);
            int minimumWidth = drawable.getMinimumWidth();
            int minimumHeight = drawable.getMinimumHeight();
            if (minimumWidth > 0) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int i = (minimumHeight * displayMetrics.widthPixels) / minimumWidth;
                ViewGroup.LayoutParams layoutParams = this.mBkgImgView.getLayoutParams();
                layoutParams.height = i;
                this.mMaxHeaderHeight = i;
                this.mBkgImgView.setLayoutParams(layoutParams);
            }
        }
    }

    public void setOriHeight(int i) {
        this.mOriHeight = i;
        findViewById(R.id.pull_header).getLayoutParams().height = this.mOriHeight;
        this.mHeader.findViewById(R.id.empty_view).getLayoutParams().height = this.mOriHeight;
    }

    public void setPullDownEnabled(boolean z) {
        this.mPullDownEnabled = z;
    }

    public void setPullDownHeaderVisibility(int i) {
        findViewById(R.id.pull_header_container).setVisibility(i);
    }

    public void setPullDownTextColor(int i) {
        ((TextView) findViewById(R.id.pull_header_txt)).setTextColor(i);
    }

    public void setPullDownTextSize(int i) {
        ((TextView) findViewById(R.id.pull_header_txt)).setTextSize((float) i);
    }

    public void setPullDownText(CharSequence charSequence, CharSequence charSequence2) {
        this.mPullDownToRefreshText = charSequence;
        ((TextView) findViewById(R.id.pull_header_txt)).setText(this.mPullDownToRefreshText);
        this.mReleaseText = charSequence2;
    }

    public void setPullDownTextColorLine2(int i) {
        ((TextView) findViewById(R.id.pull_header_txt_line2)).setTextColor(i);
    }

    public void setPullDownLine2Text(CharSequence charSequence) {
        TextView textView = (TextView) findViewById(R.id.pull_header_txt_line2);
        if (!TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(0);
            textView.setText(charSequence);
            return;
        }
        textView.setVisibility(8);
    }

    public void setIndicatorDrawable(Drawable drawable) {
        ((ImageView) findViewById(R.id.pull_header_indc)).setImageDrawable(drawable);
    }

    public void setMaxPullDownFromRes(int i) {
        this.mMaxHeaderHeight = getResources().getDimensionPixelSize(i);
    }

    @SuppressLint({"NewApi"})
    private void init() {
        if (Build.VERSION.SDK_INT >= 14) {
            setOverScrollMode(2);
        }
        if (!isInEditMode()) {
            this.mMaximumVelocity = (getResources().getDisplayMetrics().density * 1.5f) + 0.5f;
            this.mPullDownToRefreshText = getContext().getString(R.string.pull_down_refresh);
            this.mReleaseText = getContext().getString(R.string.release_to_refresh);
            this.mTriggerRefreshThreshold = getResources().getDimensionPixelSize(R.dimen.pull_down_refresh_threshold);
            this.mHeader = LayoutInflater.from(getContext()).inflate(R.layout.pull_header, (ViewGroup) null);
            this.mContainer = this.mHeader.findViewById(R.id.pull_header);
            this.mBkgImgView = (ImageView) this.mHeader.findViewById(R.id.img_bkg);
            addHeaderView(this.mHeader);
            setOnScrollListener(new AbsListView.OnScrollListener() {
                public void onScrollStateChanged(AbsListView absListView, int i) {
                    if (i == 0 && CustomPullDownRefreshListView.this.mOnLastItemVisibleListener != null && CustomPullDownRefreshListView.this.mLastItemVisible) {
                        CustomPullDownRefreshListView.this.mOnLastItemVisibleListener.onLastItemVisible();
                    }
                }

                public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                    if (CustomPullDownRefreshListView.this.mOnLastItemVisibleListener != null) {
                        CustomPullDownRefreshListView customPullDownRefreshListView = CustomPullDownRefreshListView.this;
                        boolean z = true;
                        if (i3 <= 0 || i + i2 < i3 - 1) {
                            z = false;
                        }
                        boolean unused = customPullDownRefreshListView.mLastItemVisible = z;
                    }
                }
            });
        }
    }

    public void setCanPullDown(boolean z) {
        this.mCanPullDown = z;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.mCanPullDown) {
            return super.dispatchTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 0:
                if (this.mPullDownEnabled) {
                    this.mCanRefresh = false;
                    if (!this.mIsRefreshing && getFirstVisiblePosition() <= 0) {
                        this.isDown = true;
                        this.mStartY = motionEvent.getY();
                        break;
                    }
                }
                break;
            case 1:
                if (this.isDown) {
                    if (this.mInterceptListener != null && this.mInterceptListener.needInterceptAnimation()) {
                        if (!this.mCanRefresh) {
                            this.mHandler.sendEmptyMessage(0);
                            this.isDown = false;
                            break;
                        } else {
                            this.mInterceptListener.onInterceptAnimation();
                            break;
                        }
                    } else {
                        this.mHandler.sendEmptyMessage(0);
                        if (this.mCanRefresh) {
                            doRefresh();
                        }
                        this.isDown = false;
                        break;
                    }
                }
                break;
            case 2:
                if (!this.isDown) {
                    if (this.mPullDownEnabled && !this.isDown && !this.mIsRefreshing && getFirstVisiblePosition() <= 0 && this.mHeader.getTop() >= 0) {
                        this.isDown = true;
                        this.mStartY = motionEvent.getY();
                        this.mCanRefresh = false;
                        break;
                    }
                } else {
                    TextView textView = (TextView) findViewById(R.id.pull_header_txt);
                    ImageView imageView = (ImageView) findViewById(R.id.pull_header_indc);
                    float y = motionEvent.getY();
                    if (y - this.mStartY > 10.0f) {
                        ViewGroup.LayoutParams layoutParams = this.mContainer.getLayoutParams();
                        this.mCurOffsetY = (int) ((y - this.mStartY) / 2.0f);
                        if (this.mCurOffsetY + this.mOriHeight < this.mMaxHeaderHeight) {
                            layoutParams.height = this.mCurOffsetY + this.mOriHeight;
                            this.mContainer.setLayoutParams(layoutParams);
                            if (this.mCurOffsetY >= this.mTriggerRefreshThreshold) {
                                if (!this.mCanRefresh) {
                                    textView.setText(this.mReleaseText);
                                    if (this.mAnimRotate == null) {
                                        this.mAnimRotate = AnimationUtils.loadAnimation(getContext(), R.anim.v5_rotate_180);
                                        this.mAnimRotate.setFillAfter(true);
                                    }
                                    imageView.startAnimation(this.mAnimRotate);
                                    this.mCanRefresh = true;
                                }
                            } else if (this.mCanRefresh) {
                                textView.setText(this.mPullDownToRefreshText);
                                if (this.mAnimRotateBack == null) {
                                    this.mAnimRotateBack = AnimationUtils.loadAnimation(getContext(), R.anim.v5_rotate_back_180);
                                    this.mAnimRotateBack.setFillAfter(true);
                                }
                                imageView.startAnimation(this.mAnimRotateBack);
                                this.mCanRefresh = false;
                            }
                        } else {
                            this.mCurOffsetY = Math.max(0, this.mMaxHeaderHeight - this.mOriHeight);
                        }
                        motionEvent.setAction(3);
                        super.dispatchTouchEvent(motionEvent);
                        return true;
                    }
                }
                break;
            case 3:
                onViewHide();
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void onViewHide() {
        if (this.isDown) {
            this.mHandler.sendEmptyMessage(0);
        }
        this.isDown = false;
    }

    public boolean getIsDown() {
        if ((this.mIsRefreshing || getFirstVisiblePosition() > 0 || this.mHeader.getTop() < 0) && !this.mIsRefreshing) {
            return false;
        }
        return true;
    }

    public void resume() {
        onViewHide();
        doRefresh();
    }

    /* access modifiers changed from: private */
    public void doAnimation() {
        ViewGroup.LayoutParams layoutParams = this.mContainer.getLayoutParams();
        if (this.mCurOffsetY >= 0) {
            this.mCurOffsetY = (int) (((float) this.mCurOffsetY) - ((this.mIsRefreshing ? this.mMaximumVelocity : this.mMaximumVelocity / 2.0f) * 16.0f));
            if (this.mIsRefreshing && this.mCurOffsetY <= this.mTriggerRefreshThreshold) {
                this.mCurOffsetY = this.mTriggerRefreshThreshold;
                layoutParams.height = this.mCurOffsetY + this.mOriHeight;
                this.mContainer.setLayoutParams(layoutParams);
                this.mHandler.removeMessages(0);
                return;
            } else if (this.mCurOffsetY <= 0) {
                this.mCurOffsetY = 0;
                layoutParams.height = this.mCurOffsetY + this.mOriHeight;
                this.mContainer.setLayoutParams(layoutParams);
                this.mHandler.removeMessages(0);
                return;
            } else {
                layoutParams.height = this.mCurOffsetY + this.mOriHeight;
                this.mContainer.setLayoutParams(layoutParams);
            }
        }
        this.mHandler.sendEmptyMessageDelayed(0, 16);
    }

    private static final class BuncingHandler extends Handler {
        private WeakReference<CustomPullDownRefreshListView> mReference;

        public BuncingHandler(CustomPullDownRefreshListView customPullDownRefreshListView) {
            this.mReference = new WeakReference<>(customPullDownRefreshListView);
        }

        public void handleMessage(Message message) {
            CustomPullDownRefreshListView customPullDownRefreshListView = (CustomPullDownRefreshListView) this.mReference.get();
            if (customPullDownRefreshListView != null) {
                if (message.what == 0) {
                    customPullDownRefreshListView.doAnimation();
                }
                super.handleMessage(message);
            }
        }
    }
}
