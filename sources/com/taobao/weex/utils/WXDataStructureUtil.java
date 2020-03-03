package com.taobao.weex.utils;

import java.util.HashMap;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXDataStructureUtil {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final int MAX_POWER_OF_TWO = 1073741824;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-912866801563288613L, "com/taobao/weex/utils/WXDataStructureUtil", 9);
        $jacocoData = a2;
        return a2;
    }

    public WXDataStructureUtil() {
        $jacocoInit()[0] = true;
    }

    public static <K, V> HashMap<K, V> newHashMapWithExpectedSize(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        HashMap<K, V> hashMap = new HashMap<>(capacity(i));
        $jacocoInit[1] = true;
        return hashMap;
    }

    private static int capacity(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (i < 3) {
            $jacocoInit[2] = true;
            checkNonnegative(i, "expectedSize");
            int i2 = i + 1;
            $jacocoInit[3] = true;
            return i2;
        } else if (i < 1073741824) {
            int i3 = (int) ((((float) i) / 0.75f) + 1.0f);
            $jacocoInit[4] = true;
            return i3;
        } else {
            $jacocoInit[5] = true;
            return Integer.MAX_VALUE;
        }
    }

    private static int checkNonnegative(int i, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (i >= 0) {
            $jacocoInit[8] = true;
            return i;
        }
        $jacocoInit[6] = true;
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException(str + " cannot be negative but was: " + i);
        $jacocoInit[7] = true;
        throw illegalArgumentException;
    }
}
