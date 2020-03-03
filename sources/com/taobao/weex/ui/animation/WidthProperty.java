package com.taobao.weex.ui.animation;

import android.view.View;
import android.view.ViewGroup;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WidthProperty extends LayoutParamsProperty {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(2299855430138091590L, "com/taobao/weex/ui/animation/WidthProperty", 5);
        $jacocoData = a2;
        return a2;
    }

    public WidthProperty() {
        $jacocoInit()[0] = true;
    }

    public /* synthetic */ Integer get(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        Integer num = super.get(view);
        $jacocoInit[4] = true;
        return num;
    }

    public /* synthetic */ void set(View view, Integer num) {
        boolean[] $jacocoInit = $jacocoInit();
        super.set(view, num);
        $jacocoInit[3] = true;
    }

    /* access modifiers changed from: protected */
    public Integer getProperty(ViewGroup.LayoutParams layoutParams) {
        boolean[] $jacocoInit = $jacocoInit();
        Integer valueOf = Integer.valueOf(layoutParams.width);
        $jacocoInit[1] = true;
        return valueOf;
    }

    /* access modifiers changed from: protected */
    public void setProperty(ViewGroup.LayoutParams layoutParams, Integer num) {
        boolean[] $jacocoInit = $jacocoInit();
        layoutParams.width = num.intValue();
        $jacocoInit[2] = true;
    }
}
