package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.xiaomi.smarthome.R;

public class SwitchButtonLocked extends SwitchButton {
    public SwitchButtonLocked(Context context) {
        super(context);
        init();
    }

    public SwitchButtonLocked(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public SwitchButtonLocked(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.mFrame_uncheck = getResources().getDrawable(R.drawable.lock_switch_bg);
        this.mSliderOn = getResources().getDrawable(R.drawable.lock_switch_point_on_normal);
        this.mSliderOff = getResources().getDrawable(R.drawable.lock_switch_point_off_normal);
        setBackgroundResource(R.drawable.sliding_btn_bg_light);
        this.mWidth = this.mFrame_uncheck.getIntrinsicWidth();
        this.mHeight = this.mFrame_uncheck.getIntrinsicHeight();
        this.mSliderWidth = Math.min(this.mWidth, this.mSliderOn.getIntrinsicWidth());
        this.mSliderPositionStart = 0;
        this.mSliderPositionEnd = this.mWidth - this.mSliderWidth;
        this.mSliderOffset = this.mSliderPositionStart;
        this.mBarOff = Bitmap.createScaledBitmap(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.sliding_btn_bar_off_light)).getBitmap(), (this.mWidth * 2) - this.mSliderWidth, this.mHeight, true);
        this.mBarOn = Bitmap.createScaledBitmap(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.sliding_btn_bar_on_light)).getBitmap(), (this.mWidth * 2) - this.mSliderWidth, this.mHeight, true);
        this.mFrame_uncheck.setBounds(0, 0, this.mWidth, this.mHeight);
        Drawable drawable = getResources().getDrawable(R.drawable.sliding_btn_mask_light);
        drawable.setBounds(0, 0, this.mWidth, this.mHeight);
        this.mMask = convertToAlphaMask(drawable);
        this.mBarOffPaint = new Paint();
        this.mBarOnPaint = new Paint();
        this.mBarOnPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        this.mBarOffPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    }
}
