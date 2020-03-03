package com.taobao.weex.base;

import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class FloatUtil {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final float EPSILON = 1.0E-5f;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-252052588117287789L, "com/taobao/weex/base/FloatUtil", 11);
        $jacocoData = a2;
        return a2;
    }

    public FloatUtil() {
        $jacocoInit()[0] = true;
    }

    public static boolean floatsEqual(float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = false;
        if (Float.isNaN(f)) {
            $jacocoInit[1] = true;
        } else if (Float.isNaN(f2)) {
            $jacocoInit[2] = true;
        } else {
            if (Math.abs(f2 - f) < EPSILON) {
                $jacocoInit[8] = true;
                z = true;
            } else {
                $jacocoInit[9] = true;
            }
            $jacocoInit[10] = true;
            return z;
        }
        if (!Float.isNaN(f)) {
            $jacocoInit[3] = true;
        } else if (!Float.isNaN(f2)) {
            $jacocoInit[4] = true;
        } else {
            $jacocoInit[5] = true;
            z = true;
            $jacocoInit[7] = true;
            return z;
        }
        $jacocoInit[6] = true;
        $jacocoInit[7] = true;
        return z;
    }
}
