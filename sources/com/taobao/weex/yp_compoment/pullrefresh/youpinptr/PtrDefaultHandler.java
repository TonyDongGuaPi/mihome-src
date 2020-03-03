package com.taobao.weex.yp_compoment.pullrefresh.youpinptr;

import android.os.Build;
import android.view.View;
import android.widget.AbsListView;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public abstract class PtrDefaultHandler implements PtrHandler {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-1238153898761114717L, "com/taobao/weex/yp_compoment/pullrefresh/youpinptr/PtrDefaultHandler", 19);
        $jacocoData = a2;
        return a2;
    }

    public PtrDefaultHandler() {
        $jacocoInit()[0] = true;
    }

    public static boolean canChildScrollUp(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        if (Build.VERSION.SDK_INT < 14) {
            boolean z = false;
            if (view instanceof AbsListView) {
                AbsListView absListView = (AbsListView) view;
                $jacocoInit[1] = true;
                if (absListView.getChildCount() <= 0) {
                    $jacocoInit[2] = true;
                } else {
                    $jacocoInit[3] = true;
                    if (absListView.getFirstVisiblePosition() > 0) {
                        $jacocoInit[4] = true;
                    } else {
                        View childAt = absListView.getChildAt(0);
                        $jacocoInit[5] = true;
                        if (childAt.getTop() >= absListView.getPaddingTop()) {
                            $jacocoInit[6] = true;
                        } else {
                            $jacocoInit[7] = true;
                        }
                    }
                    $jacocoInit[8] = true;
                    z = true;
                    $jacocoInit[10] = true;
                    return z;
                }
                $jacocoInit[9] = true;
                $jacocoInit[10] = true;
                return z;
            }
            if (view.getScrollY() > 0) {
                $jacocoInit[11] = true;
                z = true;
            } else {
                $jacocoInit[12] = true;
            }
            $jacocoInit[13] = true;
            return z;
        }
        boolean canScrollVertically = view.canScrollVertically(-1);
        $jacocoInit[14] = true;
        return canScrollVertically;
    }

    public static boolean checkContentCanBePulledDown(PtrFrameLayout ptrFrameLayout, View view, View view2) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (!canChildScrollUp(view)) {
            $jacocoInit[15] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[16] = true;
        }
        $jacocoInit[17] = true;
        return z;
    }

    public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean checkContentCanBePulledDown = checkContentCanBePulledDown(ptrFrameLayout, view, view2);
        $jacocoInit[18] = true;
        return checkContentCanBePulledDown;
    }
}
