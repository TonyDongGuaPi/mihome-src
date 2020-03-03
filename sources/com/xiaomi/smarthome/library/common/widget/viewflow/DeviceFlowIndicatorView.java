package com.xiaomi.smarthome.library.common.widget.viewflow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.xiaomi.smarthome.R;

public class DeviceFlowIndicatorView extends LinearLayout {
    int mCellMargin = getResources().getDimensionPixelSize(R.dimen.margin_medium);
    int mCurrentPos;
    Drawable mDrawable = getResources().getDrawable(R.drawable.sel_icon);
    int mHorizScroll;
    int mParentsWidth;
    Rect mRect = new Rect();

    public DeviceFlowIndicatorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int getCurrentPos() {
        return this.mCurrentPos;
    }

    public void updateScroll(int i) {
        this.mHorizScroll = i;
        postInvalidate();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mParentsWidth = getResources().getDisplayMetrics().widthPixels;
    }

    public void setPosAndSize(int i, int i2) {
        this.mCurrentPos = i;
        if (i2 < 2) {
            removeAllViews();
        } else if (getChildCount() != i2) {
            removeAllViews();
            for (int i3 = 0; i3 < i2; i3++) {
                ImageView imageView = new ImageView(getContext());
                imageView.setBackgroundResource(R.drawable.unsel_icon);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 1.0f);
                layoutParams.leftMargin = this.mCellMargin;
                layoutParams.rightMargin = this.mCellMargin;
                addView(imageView, layoutParams);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (getChildCount() >= 2) {
            getChildAt(0).getHitRect(this.mRect);
            this.mRect.offsetTo(((getWidth() * this.mHorizScroll) / (this.mParentsWidth * getChildCount())) + this.mCellMargin, 0);
            this.mDrawable.setBounds(this.mRect);
            this.mDrawable.draw(canvas);
        }
    }
}
