package com.taobao.weex.ui.view.border;

import android.graphics.RectF;
import android.support.annotation.NonNull;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

class TopLeftCorner extends BorderCorner {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(5793758446616285797L, "com/taobao/weex/ui/view/border/TopLeftCorner", 24);
        $jacocoData = a2;
        return a2;
    }

    TopLeftCorner() {
        $jacocoInit()[0] = true;
    }

    /* access modifiers changed from: package-private */
    public void set(float f, float f2, float f3, @NonNull RectF rectF) {
        boolean[] $jacocoInit = $jacocoInit();
        set(f, f2, f3, rectF, 225.0f);
        $jacocoInit[1] = true;
    }

    /* access modifiers changed from: protected */
    public void prepareOval() {
        boolean[] $jacocoInit = $jacocoInit();
        if (hasInnerCorner()) {
            $jacocoInit[2] = true;
            setOvalLeft(getPreBorderWidth() / 2.0f);
            $jacocoInit[3] = true;
            setOvalTop(getPostBorderWidth() / 2.0f);
            $jacocoInit[4] = true;
            setOvalRight((getOuterCornerRadius() * 2.0f) - (getPreBorderWidth() / 2.0f));
            $jacocoInit[5] = true;
            setOvalBottom((getOuterCornerRadius() * 2.0f) - (getPostBorderWidth() / 2.0f));
            $jacocoInit[6] = true;
        } else {
            setOvalLeft(getOuterCornerRadius() / 2.0f);
            $jacocoInit[7] = true;
            setOvalTop(getOuterCornerRadius() / 2.0f);
            $jacocoInit[8] = true;
            setOvalRight(getOuterCornerRadius() * 1.5f);
            $jacocoInit[9] = true;
            setOvalBottom(getOuterCornerRadius() * 1.5f);
            $jacocoInit[10] = true;
        }
        $jacocoInit[11] = true;
    }

    /* access modifiers changed from: protected */
    public void prepareRoundCorner() {
        boolean[] $jacocoInit = $jacocoInit();
        if (hasOuterCorner()) {
            $jacocoInit[12] = true;
            setRoundCornerStartX(getPreBorderWidth() / 2.0f);
            $jacocoInit[13] = true;
            setRoundCornerStartY(getOuterCornerRadius());
            $jacocoInit[14] = true;
            setRoundCornerEndX(getOuterCornerRadius());
            $jacocoInit[15] = true;
            setRoundCornerEndY(getPostBorderWidth() / 2.0f);
            $jacocoInit[16] = true;
        } else {
            float preBorderWidth = getPreBorderWidth() / 2.0f;
            $jacocoInit[17] = true;
            float postBorderWidth = getPostBorderWidth() / 2.0f;
            $jacocoInit[18] = true;
            setRoundCornerStartX(preBorderWidth);
            $jacocoInit[19] = true;
            setRoundCornerStartY(postBorderWidth);
            $jacocoInit[20] = true;
            setRoundCornerEndX(preBorderWidth);
            $jacocoInit[21] = true;
            setRoundCornerEndY(postBorderWidth);
            $jacocoInit[22] = true;
        }
        $jacocoInit[23] = true;
    }
}
