package com.taobao.weex.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXSmoothScroller extends Scroller {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private double mScrollFactor = 1.0d;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(1351936799764230054L, "com/taobao/weex/ui/view/WXSmoothScroller", 5);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXSmoothScroller(Context context) {
        super(context);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXSmoothScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @SuppressLint({"NewApi"})
    public WXSmoothScroller(Context context, Interpolator interpolator, boolean z) {
        super(context, interpolator, z);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[2] = true;
    }

    public void setScrollDurationFactor(double d) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mScrollFactor = d;
        $jacocoInit[3] = true;
    }

    public void startScroll(int i, int i2, int i3, int i4, int i5) {
        boolean[] $jacocoInit = $jacocoInit();
        double d = (double) i5;
        double d2 = this.mScrollFactor;
        Double.isNaN(d);
        super.startScroll(i, i2, i3, i4, (int) (d * d2));
        $jacocoInit[4] = true;
    }
}
