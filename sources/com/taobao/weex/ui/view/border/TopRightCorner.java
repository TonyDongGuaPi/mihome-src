package com.taobao.weex.ui.view.border;

import android.graphics.RectF;
import android.support.annotation.NonNull;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

class TopRightCorner extends BorderCorner {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(5793520896440907360L, "com/taobao/weex/ui/view/border/TopRightCorner", 24);
        $jacocoData = a2;
        return a2;
    }

    TopRightCorner() {
        $jacocoInit()[0] = true;
    }

    /* access modifiers changed from: package-private */
    public void set(float f, float f2, float f3, @NonNull RectF rectF) {
        boolean[] $jacocoInit = $jacocoInit();
        set(f, f2, f3, rectF, 315.0f);
        $jacocoInit[1] = true;
    }

    /* access modifiers changed from: protected */
    public void prepareOval() {
        boolean[] $jacocoInit = $jacocoInit();
        if (hasInnerCorner()) {
            $jacocoInit[2] = true;
            setOvalLeft(getBorderBox().width() - ((getOuterCornerRadius() * 2.0f) - (getPostBorderWidth() / 2.0f)));
            $jacocoInit[3] = true;
            setOvalTop(getPreBorderWidth() / 2.0f);
            $jacocoInit[4] = true;
            setOvalRight(getBorderBox().width() - (getPostBorderWidth() / 2.0f));
            $jacocoInit[5] = true;
            setOvalBottom((getOuterCornerRadius() * 2.0f) - (getPreBorderWidth() / 2.0f));
            $jacocoInit[6] = true;
        } else {
            setOvalLeft(getBorderBox().width() - (getOuterCornerRadius() * 1.5f));
            $jacocoInit[7] = true;
            setOvalTop(getOuterCornerRadius() / 2.0f);
            $jacocoInit[8] = true;
            setOvalRight(getBorderBox().width() - (getOuterCornerRadius() / 2.0f));
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
            setRoundCornerStartX(getBorderBox().width() - getOuterCornerRadius());
            $jacocoInit[13] = true;
            setRoundCornerStartY(getPreBorderWidth() / 2.0f);
            $jacocoInit[14] = true;
            setRoundCornerEndX(getBorderBox().width() - (getPostBorderWidth() / 2.0f));
            $jacocoInit[15] = true;
            setRoundCornerEndY(getOuterCornerRadius());
            $jacocoInit[16] = true;
        } else {
            float width = getBorderBox().width() - (getPostBorderWidth() / 2.0f);
            $jacocoInit[17] = true;
            float preBorderWidth = getPreBorderWidth() / 2.0f;
            $jacocoInit[18] = true;
            setRoundCornerStartX(width);
            $jacocoInit[19] = true;
            setRoundCornerStartY(preBorderWidth);
            $jacocoInit[20] = true;
            setRoundCornerEndX(width);
            $jacocoInit[21] = true;
            setRoundCornerEndY(preBorderWidth);
            $jacocoInit[22] = true;
        }
        $jacocoInit[23] = true;
    }
}