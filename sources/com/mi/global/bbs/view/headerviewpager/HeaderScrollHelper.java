package com.mi.global.bbs.view.headerviewpager;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ScrollView;

public class HeaderScrollHelper {
    private ScrollableContainer mCurrentScrollableContainer;
    private int sysVersion = Build.VERSION.SDK_INT;

    public interface ScrollableContainer {
        View getScrollableView();
    }

    public void setCurrentScrollableContainer(ScrollableContainer scrollableContainer) {
        this.mCurrentScrollableContainer = scrollableContainer;
    }

    private View getScrollableView() {
        if (this.mCurrentScrollableContainer == null) {
            return null;
        }
        return this.mCurrentScrollableContainer.getScrollableView();
    }

    public boolean isTop() {
        View scrollableView = getScrollableView();
        if (scrollableView == null) {
            throw new NullPointerException("You should call ScrollableHelper.setCurrentScrollableContainer() to set ScrollableContainer.");
        } else if (scrollableView instanceof AdapterView) {
            return isAdapterViewTop((AdapterView) scrollableView);
        } else {
            if (scrollableView instanceof ScrollView) {
                return isScrollViewTop((ScrollView) scrollableView);
            }
            if (scrollableView instanceof RecyclerView) {
                return isRecyclerViewTop((RecyclerView) scrollableView);
            }
            if (scrollableView instanceof WebView) {
                return isWebViewTop((WebView) scrollableView);
            }
            throw new IllegalStateException("scrollableView must be a instance of AdapterView|ScrollView|RecyclerView");
        }
    }

    private boolean isRecyclerViewTop(RecyclerView recyclerView) {
        if (recyclerView != null) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                int findFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                View childAt = recyclerView.getChildAt(0);
                if (childAt == null) {
                    return true;
                }
                if (findFirstVisibleItemPosition == 0 && childAt.getTop() == 0) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    private boolean isAdapterViewTop(AdapterView adapterView) {
        if (adapterView != null) {
            int firstVisiblePosition = adapterView.getFirstVisiblePosition();
            View childAt = adapterView.getChildAt(0);
            if (childAt == null) {
                return true;
            }
            if (firstVisiblePosition == 0 && childAt.getTop() == 0) {
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean isScrollViewTop(ScrollView scrollView) {
        return scrollView != null && scrollView.getScrollY() <= 0;
    }

    private boolean isWebViewTop(WebView webView) {
        return webView != null && webView.getScrollY() <= 0;
    }

    @SuppressLint({"NewApi"})
    public void smoothScrollBy(int i, int i2, int i3) {
        View scrollableView = getScrollableView();
        if (scrollableView instanceof AbsListView) {
            AbsListView absListView = (AbsListView) scrollableView;
            if (this.sysVersion >= 21) {
                absListView.fling(i);
            } else {
                absListView.smoothScrollBy(i2, i3);
            }
        } else if (scrollableView instanceof ScrollView) {
            ((ScrollView) scrollableView).fling(i);
        } else if (scrollableView instanceof RecyclerView) {
            ((RecyclerView) scrollableView).fling(0, i);
        } else if (scrollableView instanceof WebView) {
            ((WebView) scrollableView).flingScroll(0, i);
        }
    }
}
