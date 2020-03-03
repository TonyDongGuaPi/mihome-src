package com.mi.global.shop.widget.ptr;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ScrollView;

public abstract class PtrDefaultHandler implements PtrHandler {
    public boolean a(PtrFrameLayout ptrFrameLayout, View view, View view2) {
        return b(ptrFrameLayout, view, view2);
    }

    public static boolean b(PtrFrameLayout ptrFrameLayout, View view, View view2) {
        if (!(view instanceof ViewGroup)) {
            return true;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        if (viewGroup.getChildCount() == 0) {
            return true;
        }
        boolean z = viewGroup instanceof AbsListView;
        if (z && ((AbsListView) viewGroup).getFirstVisiblePosition() > 0) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 14) {
            return !view.canScrollVertically(-1);
        }
        if (!(viewGroup instanceof ScrollView) && !z) {
            View childAt = viewGroup.getChildAt(0);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            int top = childAt.getTop();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                if (top == ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + viewGroup.getPaddingTop()) {
                    return true;
                }
                return false;
            } else if (top == viewGroup.getPaddingTop()) {
                return true;
            } else {
                return false;
            }
        } else if (viewGroup.getScrollY() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
