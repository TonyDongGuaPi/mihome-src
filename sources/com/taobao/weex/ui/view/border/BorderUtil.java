package com.taobao.weex.ui.view.border;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import com.taobao.weex.dom.CSSShorthand;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

class BorderUtil {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(5676579738368073116L, "com/taobao/weex/ui/view/border/BorderUtil", 13);
        $jacocoData = a2;
        return a2;
    }

    BorderUtil() {
        $jacocoInit()[0] = true;
    }

    static int fetchFromSparseArray(@Nullable SparseIntArray sparseIntArray, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sparseIntArray == null) {
            $jacocoInit[1] = true;
        } else {
            CSSShorthand.EDGE edge = CSSShorthand.EDGE.ALL;
            $jacocoInit[2] = true;
            i2 = sparseIntArray.get(i, sparseIntArray.get(edge.ordinal()));
            $jacocoInit[3] = true;
        }
        $jacocoInit[4] = true;
        return i2;
    }

    static void updateSparseArray(@NonNull SparseIntArray sparseIntArray, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (i == CSSShorthand.EDGE.ALL.ordinal()) {
            $jacocoInit[5] = true;
            sparseIntArray.put(CSSShorthand.EDGE.ALL.ordinal(), i2);
            $jacocoInit[6] = true;
            sparseIntArray.put(CSSShorthand.EDGE.TOP.ordinal(), i2);
            $jacocoInit[7] = true;
            sparseIntArray.put(CSSShorthand.EDGE.LEFT.ordinal(), i2);
            $jacocoInit[8] = true;
            sparseIntArray.put(CSSShorthand.EDGE.RIGHT.ordinal(), i2);
            $jacocoInit[9] = true;
            sparseIntArray.put(CSSShorthand.EDGE.BOTTOM.ordinal(), i2);
            $jacocoInit[10] = true;
        } else {
            sparseIntArray.put(i, i2);
            $jacocoInit[11] = true;
        }
        $jacocoInit[12] = true;
    }
}
