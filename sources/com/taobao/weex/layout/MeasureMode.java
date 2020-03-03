package com.taobao.weex.layout;

import java.io.Serializable;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class MeasureMode implements Serializable {
    private static transient /* synthetic */ boolean[] $jacocoData;
    public static int EXACTLY = 1;
    public static int UNSPECIFIED = 0;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(5714654692802371285L, "com/taobao/weex/layout/MeasureMode", 2);
        $jacocoData = a2;
        return a2;
    }

    public MeasureMode() {
        $jacocoInit()[0] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
    }
}
