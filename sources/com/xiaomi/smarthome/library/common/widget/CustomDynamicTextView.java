package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;
import com.xiaomi.smarthome.R;

public class CustomDynamicTextView extends TextView {
    /* access modifiers changed from: private */
    public int mCurrentNumber = 0;
    /* access modifiers changed from: private */
    public String mDynamicTextUnit;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    public int mMaxNumber;
    private Runnable mRunnable = new Runnable() {
        public void run() {
            String str = "";
            CustomDynamicTextView.access$008(CustomDynamicTextView.this);
            if (CustomDynamicTextView.this.mCurrentNumber > CustomDynamicTextView.this.mMaxNumber) {
                int unused = CustomDynamicTextView.this.mCurrentNumber = 0;
            }
            for (int i = 0; i < CustomDynamicTextView.this.mCurrentNumber; i++) {
                str = str.concat(CustomDynamicTextView.this.mDynamicTextUnit);
            }
            CustomDynamicTextView customDynamicTextView = CustomDynamicTextView.this;
            customDynamicTextView.setText(CustomDynamicTextView.this.mStaticText + str);
            CustomDynamicTextView.this.mHandler.postDelayed(this, 500);
        }
    };
    /* access modifiers changed from: private */
    public String mStaticText;

    static /* synthetic */ int access$008(CustomDynamicTextView customDynamicTextView) {
        int i = customDynamicTextView.mCurrentNumber;
        customDynamicTextView.mCurrentNumber = i + 1;
        return i;
    }

    public CustomDynamicTextView(Context context) {
        super(context);
    }

    public CustomDynamicTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CustomDynamicTextView);
        this.mStaticText = obtainStyledAttributes.getString(2);
        this.mDynamicTextUnit = obtainStyledAttributes.getString(1);
        this.mMaxNumber = obtainStyledAttributes.getInt(0, 3);
        obtainStyledAttributes.recycle();
    }

    public void show() {
        if (getVisibility() != 0) {
            setVisibility(0);
            this.mCurrentNumber = 0;
            setText(this.mStaticText);
            this.mHandler.post(this.mRunnable);
        }
    }

    public void hide() {
        if (8 != getVisibility()) {
            setVisibility(8);
            this.mHandler.removeCallbacks(this.mRunnable);
        }
    }

    public void onDestroy() {
        this.mHandler.removeCallbacks(this.mRunnable);
    }
}
