package com.taobao.weex.utils;

import android.os.Build;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class OsVersion {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private static boolean sIsAtLeastJB_MR2;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-1684726857398857267L, "com/taobao/weex/utils/OsVersion", 6);
        $jacocoData = a2;
        return a2;
    }

    public OsVersion() {
        $jacocoInit()[0] = true;
    }

    static {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (getApiVersion() >= 18) {
            $jacocoInit[3] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[4] = true;
        }
        sIsAtLeastJB_MR2 = z;
        $jacocoInit[5] = true;
    }

    public static boolean isAtLeastJB_MR2() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = sIsAtLeastJB_MR2;
        $jacocoInit[1] = true;
        return z;
    }

    public static int getApiVersion() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = Build.VERSION.SDK_INT;
        $jacocoInit[2] = true;
        return i;
    }
}
