package com.scwang.smartrefresh.layout.util;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.ViewGroup;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.CoordinatorLayoutListener;

public class DesignUtil {
    public static void a(View view, RefreshKernel refreshKernel, CoordinatorLayoutListener coordinatorLayoutListener) {
        try {
            if (view instanceof CoordinatorLayout) {
                refreshKernel.a().setEnableNestedScroll(false);
                a((ViewGroup) view, refreshKernel.a(), coordinatorLayoutListener);
            }
        } catch (Throwable unused) {
        }
    }

    private static void a(ViewGroup viewGroup, final RefreshLayout refreshLayout, final CoordinatorLayoutListener coordinatorLayoutListener) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (childAt instanceof AppBarLayout) {
                ((AppBarLayout) childAt).addOnOffsetChangedListener((AppBarLayout.OnOffsetChangedListener) new AppBarLayout.OnOffsetChangedListener() {
                    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                        CoordinatorLayoutListener coordinatorLayoutListener = coordinatorLayoutListener;
                        boolean z = false;
                        boolean z2 = i >= 0;
                        if (refreshLayout.isEnableLoadMore() && appBarLayout.getTotalScrollRange() + i <= 0) {
                            z = true;
                        }
                        coordinatorLayoutListener.a(z2, z);
                    }
                });
            }
        }
    }
}
