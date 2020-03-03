package com.miuipub.internal.view.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.miuipub.internal.view.menu.ActionMenuView;

public class TabletActionMenuView extends ActionMenuView {
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public TabletActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int getCollapsedHeight() {
        return getMeasuredHeight();
    }

    public boolean filterLeftoverView(int i) {
        ActionMenuView.LayoutParams layoutParams = (ActionMenuView.LayoutParams) getChildAt(i).getLayoutParams();
        if (layoutParams == null || !layoutParams.f8297a) {
            return super.filterLeftoverView(i);
        }
        return false;
    }
}
