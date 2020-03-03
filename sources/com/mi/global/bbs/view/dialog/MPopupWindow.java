package com.mi.global.bbs.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class MPopupWindow implements PopupWindow.OnDismissListener {
    private static final float DEFAULT_ALPHA = 0.7f;
    /* access modifiers changed from: private */
    public int mAnimationStyle;
    /* access modifiers changed from: private */
    public float mBackgroundDarkValue;
    /* access modifiers changed from: private */
    public boolean mClippEnable;
    /* access modifiers changed from: private */
    public View mContentView;
    private Context mContext;
    /* access modifiers changed from: private */
    public int mHeight;
    /* access modifiers changed from: private */
    public boolean mIgnoreCheekPress;
    /* access modifiers changed from: private */
    public int mInputMode;
    /* access modifiers changed from: private */
    public boolean mIsBackgroundDark;
    /* access modifiers changed from: private */
    public boolean mIsFocusable;
    /* access modifiers changed from: private */
    public boolean mIsOutside;
    /* access modifiers changed from: private */
    public PopupWindow.OnDismissListener mOnDismissListener;
    /* access modifiers changed from: private */
    public View.OnTouchListener mOnTouchListener;
    private PopupWindow mPopupWindow;
    /* access modifiers changed from: private */
    public int mResLayoutId;
    /* access modifiers changed from: private */
    public int mSoftInputMode;
    /* access modifiers changed from: private */
    public boolean mTouchable;
    /* access modifiers changed from: private */
    public int mWidth;
    private Window mWindow;

    private MPopupWindow(Context context) {
        this.mIsFocusable = true;
        this.mIsOutside = true;
        this.mResLayoutId = -1;
        this.mAnimationStyle = -1;
        this.mClippEnable = true;
        this.mIgnoreCheekPress = false;
        this.mInputMode = -1;
        this.mSoftInputMode = -1;
        this.mTouchable = true;
        this.mIsBackgroundDark = false;
        this.mBackgroundDarkValue = 0.0f;
        this.mContext = context;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public MPopupWindow showAsDropDown(View view, int i, int i2) {
        if (this.mPopupWindow != null) {
            this.mPopupWindow.showAsDropDown(view, i, i2);
        }
        return this;
    }

    public MPopupWindow showAsDropDown(View view) {
        if (this.mPopupWindow != null) {
            this.mPopupWindow.showAsDropDown(view);
        }
        return this;
    }

    @RequiresApi(api = 19)
    public MPopupWindow showAsDropDown(View view, int i, int i2, int i3) {
        if (this.mPopupWindow != null) {
            this.mPopupWindow.showAsDropDown(view, i, i2, i3);
        }
        return this;
    }

    public MPopupWindow showAtLocation(View view, int i, int i2, int i3) {
        if (this.mPopupWindow != null) {
            this.mPopupWindow.showAtLocation(view, i, i2, i3);
        }
        return this;
    }

    private void apply(PopupWindow popupWindow) {
        popupWindow.setClippingEnabled(this.mClippEnable);
        if (this.mIgnoreCheekPress) {
            popupWindow.setIgnoreCheekPress();
        }
        if (this.mInputMode != -1) {
            popupWindow.setInputMethodMode(this.mInputMode);
        }
        if (this.mSoftInputMode != -1) {
            popupWindow.setSoftInputMode(this.mSoftInputMode);
        }
        if (this.mOnDismissListener != null) {
            popupWindow.setOnDismissListener(this.mOnDismissListener);
        }
        if (this.mOnTouchListener != null) {
            popupWindow.setTouchInterceptor(this.mOnTouchListener);
        }
        popupWindow.setTouchable(this.mTouchable);
    }

    /* access modifiers changed from: private */
    public PopupWindow build() {
        if (this.mContentView == null) {
            this.mContentView = LayoutInflater.from(this.mContext).inflate(this.mResLayoutId, (ViewGroup) null);
        }
        Activity activity = (Activity) this.mContext;
        if (activity != null && this.mIsBackgroundDark) {
            float f = (this.mBackgroundDarkValue <= 0.0f || this.mBackgroundDarkValue >= 1.0f) ? 0.7f : this.mBackgroundDarkValue;
            this.mWindow = activity.getWindow();
            WindowManager.LayoutParams attributes = this.mWindow.getAttributes();
            attributes.alpha = f;
            this.mWindow.setAttributes(attributes);
        }
        if (this.mWidth == 0 || this.mHeight == 0) {
            this.mPopupWindow = new PopupWindow(this.mContentView, -2, -2);
        } else {
            this.mPopupWindow = new PopupWindow(this.mContentView, this.mWidth, this.mHeight);
        }
        if (this.mAnimationStyle != -1) {
            this.mPopupWindow.setAnimationStyle(this.mAnimationStyle);
        }
        apply(this.mPopupWindow);
        this.mPopupWindow.setFocusable(this.mIsFocusable);
        this.mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.mPopupWindow.setOutsideTouchable(this.mIsOutside);
        if (this.mWidth == 0 || this.mHeight == 0) {
            this.mPopupWindow.getContentView().measure(0, 0);
            this.mWidth = this.mPopupWindow.getContentView().getMeasuredWidth();
            this.mHeight = this.mPopupWindow.getContentView().getMeasuredHeight();
        }
        this.mPopupWindow.setOnDismissListener(this);
        this.mPopupWindow.update();
        return this.mPopupWindow;
    }

    public void onDismiss() {
        dismiss();
    }

    public void dismiss() {
        if (this.mWindow != null) {
            WindowManager.LayoutParams attributes = this.mWindow.getAttributes();
            attributes.alpha = 1.0f;
            this.mWindow.setAttributes(attributes);
        }
        if (this.mPopupWindow != null && this.mPopupWindow.isShowing()) {
            this.mPopupWindow.dismiss();
        }
    }

    public static class Builder {
        private MPopupWindow mCustomPopWindow;

        public Builder(Context context) {
            this.mCustomPopWindow = new MPopupWindow(context);
        }

        public Builder size(int i, int i2) {
            int unused = this.mCustomPopWindow.mWidth = i;
            int unused2 = this.mCustomPopWindow.mHeight = i2;
            return this;
        }

        public Builder setFocusable(boolean z) {
            boolean unused = this.mCustomPopWindow.mIsFocusable = z;
            return this;
        }

        public Builder setView(int i) {
            int unused = this.mCustomPopWindow.mResLayoutId = i;
            View unused2 = this.mCustomPopWindow.mContentView = null;
            return this;
        }

        public Builder setView(View view) {
            View unused = this.mCustomPopWindow.mContentView = view;
            int unused2 = this.mCustomPopWindow.mResLayoutId = -1;
            return this;
        }

        public Builder setOutsideTouchable(boolean z) {
            boolean unused = this.mCustomPopWindow.mIsOutside = z;
            return this;
        }

        public Builder setAnimationStyle(int i) {
            int unused = this.mCustomPopWindow.mAnimationStyle = i;
            return this;
        }

        public Builder setClippingEnable(boolean z) {
            boolean unused = this.mCustomPopWindow.mClippEnable = z;
            return this;
        }

        public Builder setIgnoreCheekPress(boolean z) {
            boolean unused = this.mCustomPopWindow.mIgnoreCheekPress = z;
            return this;
        }

        public Builder setInputMethodMode(int i) {
            int unused = this.mCustomPopWindow.mInputMode = i;
            return this;
        }

        public Builder setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
            PopupWindow.OnDismissListener unused = this.mCustomPopWindow.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setSoftInputMode(int i) {
            int unused = this.mCustomPopWindow.mSoftInputMode = i;
            return this;
        }

        public Builder setTouchable(boolean z) {
            boolean unused = this.mCustomPopWindow.mTouchable = z;
            return this;
        }

        public Builder setTouchIntercepter(View.OnTouchListener onTouchListener) {
            View.OnTouchListener unused = this.mCustomPopWindow.mOnTouchListener = onTouchListener;
            return this;
        }

        public Builder enableBackgroundDark(boolean z) {
            boolean unused = this.mCustomPopWindow.mIsBackgroundDark = z;
            return this;
        }

        public Builder setDimAlpha(float f) {
            float unused = this.mCustomPopWindow.mBackgroundDarkValue = f;
            return this;
        }

        public MPopupWindow create() {
            PopupWindow unused = this.mCustomPopWindow.build();
            return this.mCustomPopWindow;
        }
    }
}
