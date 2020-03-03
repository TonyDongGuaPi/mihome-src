package com.mi.global.shop.widget.slide;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.ScrollView;

public class SlideUtil {
    public static boolean a(View view) {
        View childAt;
        if (view instanceof AbsListView) {
            AbsListView absListView = (AbsListView) view;
            if (absListView.getFirstVisiblePosition() > 1 || (childAt = absListView.getChildAt(0)) == null || childAt.getTop() < absListView.getTop()) {
                return false;
            }
            return true;
        } else if (view.getScrollY() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean b(View view) {
        View childAt;
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup == null) {
            return false;
        }
        if (view instanceof AbsListView) {
            AbsListView absListView = (AbsListView) view;
            int lastVisiblePosition = absListView.getLastVisiblePosition();
            if (lastVisiblePosition < (absListView.getCount() - 1) - 1 || (childAt = absListView.getChildAt(lastVisiblePosition - absListView.getFirstVisiblePosition())) == null || childAt.getBottom() > absListView.getBottom()) {
                return false;
            }
            return true;
        } else if (view instanceof ScrollView) {
            ScrollView scrollView = (ScrollView) view;
            View childAt2 = scrollView.getChildAt(0);
            if (childAt2 == null || scrollView.getScrollY() < childAt2.getHeight() - viewGroup.getHeight()) {
                return false;
            }
            return true;
        } else if (!(view instanceof WebView)) {
            return true;
        } else {
            WebView webView = (WebView) view;
            if ((((float) Math.floor((double) (((float) webView.getContentHeight()) * webView.getScale()))) - ((float) webView.getScrollY())) - ((float) webView.getHeight()) <= ((float) a(webView.getContext(), 1.0f))) {
                return true;
            }
            return false;
        }
    }

    public static int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
