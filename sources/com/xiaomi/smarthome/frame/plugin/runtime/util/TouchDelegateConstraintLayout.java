package com.xiaomi.smarthome.frame.plugin.runtime.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.xiaomi.smarthome.sdk.R;

public class TouchDelegateConstraintLayout extends ConstraintLayout {
    private static final String TAG = "TouchDelegateConstraint";
    private boolean mIsShowing;
    private RectF mTitleBarArea;

    public TouchDelegateConstraintLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public TouchDelegateConstraintLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TouchDelegateConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIsShowing = true;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Resources resources = getResources();
        this.mTitleBarArea = new RectF(0.0f, 0.0f, (float) i, (float) (resources.getDimensionPixelSize(R.dimen.std_titlebar_height) + resources.getDimensionPixelOffset(R.dimen.title_bar_top_padding) + resources.getDimensionPixelOffset(R.dimen.title_bar_top_padding)));
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.mIsShowing) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (this.mTitleBarArea != null) {
                return !this.mTitleBarArea.contains(x, y);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setDialogIsShowing(boolean z) {
        this.mIsShowing = z;
    }
}
