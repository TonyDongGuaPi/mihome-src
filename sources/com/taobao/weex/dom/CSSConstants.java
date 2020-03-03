package com.taobao.weex.dom;

import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class CSSConstants {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final float UNDEFINED = Float.NaN;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-7003069796325541137L, "com/taobao/weex/dom/CSSConstants", 4);
        $jacocoData = a2;
        return a2;
    }

    public CSSConstants() {
        $jacocoInit()[0] = true;
    }

    public static boolean isUndefined(float f) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (Float.compare(f, Float.NaN) == 0) {
            $jacocoInit[1] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[2] = true;
        }
        $jacocoInit[3] = true;
        return z;
    }
}
