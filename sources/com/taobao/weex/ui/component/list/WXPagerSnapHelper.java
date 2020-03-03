package com.taobao.weex.ui.component.list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXPagerSnapHelper extends PagerSnapHelper {
    private static transient /* synthetic */ boolean[] $jacocoData;
    @Nullable
    private OrientationHelper mHorizontalHelper;
    @Nullable
    private OrientationHelper mVerticalHelper;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(5702263928750672509L, "com/taobao/weex/ui/component/list/WXPagerSnapHelper", 18);
        $jacocoData = a2;
        return a2;
    }

    public WXPagerSnapHelper() {
        $jacocoInit()[0] = true;
    }

    @Nullable
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view) {
        boolean[] $jacocoInit = $jacocoInit();
        int[] iArr = new int[2];
        $jacocoInit[1] = true;
        if (layoutManager.canScrollHorizontally()) {
            $jacocoInit[2] = true;
            iArr[0] = distanceToStart(layoutManager, view, getHorizontalHelper(layoutManager));
            $jacocoInit[3] = true;
        } else {
            iArr[0] = 0;
            $jacocoInit[4] = true;
        }
        if (layoutManager.canScrollVertically()) {
            $jacocoInit[5] = true;
            iArr[1] = distanceToStart(layoutManager, view, getVerticalHelper(layoutManager));
            $jacocoInit[6] = true;
        } else {
            iArr[1] = 0;
            $jacocoInit[7] = true;
        }
        $jacocoInit[8] = true;
        return iArr;
    }

    @NonNull
    private OrientationHelper getVerticalHelper(@NonNull RecyclerView.LayoutManager layoutManager) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mVerticalHelper != null) {
            $jacocoInit[9] = true;
        } else {
            $jacocoInit[10] = true;
            this.mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
            $jacocoInit[11] = true;
        }
        OrientationHelper orientationHelper = this.mVerticalHelper;
        $jacocoInit[12] = true;
        return orientationHelper;
    }

    @NonNull
    private OrientationHelper getHorizontalHelper(@NonNull RecyclerView.LayoutManager layoutManager) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mHorizontalHelper != null) {
            $jacocoInit[13] = true;
        } else {
            $jacocoInit[14] = true;
            this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
            $jacocoInit[15] = true;
        }
        OrientationHelper orientationHelper = this.mHorizontalHelper;
        $jacocoInit[16] = true;
        return orientationHelper;
    }

    private int distanceToStart(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view, OrientationHelper orientationHelper) {
        boolean[] $jacocoInit = $jacocoInit();
        int decoratedStart = orientationHelper.getDecoratedStart(view) - orientationHelper.getStartAfterPadding();
        $jacocoInit[17] = true;
        return decoratedStart;
    }
}
