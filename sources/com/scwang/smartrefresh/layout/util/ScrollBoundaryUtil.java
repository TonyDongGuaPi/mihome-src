package com.scwang.smartrefresh.layout.util;

import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

public class ScrollBoundaryUtil {
    public static boolean a(@NonNull View view, PointF pointF) {
        if (a(view) && view.getVisibility() == 0) {
            return false;
        }
        if (!(view instanceof ViewGroup) || pointF == null) {
            return true;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        PointF pointF2 = new PointF();
        for (int childCount = viewGroup.getChildCount(); childCount > 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount - 1);
            if (a(viewGroup, childAt, pointF.x, pointF.y, pointF2)) {
                pointF.offset(pointF2.x, pointF2.y);
                boolean a2 = a(childAt, pointF);
                pointF.offset(-pointF2.x, -pointF2.y);
                return a2;
            }
        }
        return true;
    }

    public static boolean a(@NonNull View view, PointF pointF, boolean z) {
        if (b(view) && view.getVisibility() == 0) {
            return false;
        }
        if ((view instanceof ViewGroup) && pointF != null && !SmartUtil.b(view)) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            PointF pointF2 = new PointF();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (a(viewGroup, childAt, pointF.x, pointF.y, pointF2)) {
                    pointF.offset(pointF2.x, pointF2.y);
                    boolean a2 = a(childAt, pointF, z);
                    pointF.offset(-pointF2.x, -pointF2.y);
                    return a2;
                }
            }
        }
        if (z || a(view)) {
            return true;
        }
        return false;
    }

    public static boolean a(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 14) {
            return view.canScrollVertically(-1);
        }
        if (view instanceof AbsListView) {
            ViewGroup viewGroup = (ViewGroup) view;
            AbsListView absListView = (AbsListView) view;
            if (viewGroup.getChildCount() <= 0 || (absListView.getFirstVisiblePosition() <= 0 && viewGroup.getChildAt(0).getTop() >= view.getPaddingTop())) {
                return false;
            }
            return true;
        } else if (view.getScrollY() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean b(@NonNull View view) {
        int i;
        if (Build.VERSION.SDK_INT >= 14) {
            return view.canScrollVertically(1);
        }
        if (view instanceof AbsListView) {
            ViewGroup viewGroup = (ViewGroup) view;
            AbsListView absListView = (AbsListView) view;
            int childCount = viewGroup.getChildCount();
            if (childCount <= 0 || (absListView.getLastVisiblePosition() >= (i = childCount - 1) && viewGroup.getChildAt(i).getBottom() <= view.getPaddingBottom())) {
                return false;
            }
            return true;
        } else if (view.getScrollY() < 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean a(@NonNull View view, @NonNull View view2, float f, float f2, PointF pointF) {
        if (view2.getVisibility() != 0) {
            return false;
        }
        float[] fArr = {f, f2};
        fArr[0] = fArr[0] + ((float) (view.getScrollX() - view2.getLeft()));
        fArr[1] = fArr[1] + ((float) (view.getScrollY() - view2.getTop()));
        boolean z = fArr[0] >= 0.0f && fArr[1] >= 0.0f && fArr[0] < ((float) view2.getWidth()) && fArr[1] < ((float) view2.getHeight());
        if (z && pointF != null) {
            pointF.set(fArr[0] - f, fArr[1] - f2);
        }
        return z;
    }
}
