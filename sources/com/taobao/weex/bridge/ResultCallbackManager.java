package com.taobao.weex.bridge;

import android.util.SparseArray;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

class ResultCallbackManager {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private static SparseArray<ResultCallback> mResultCallbacks = new SparseArray<>();
    private static long sCallbackId = 0;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(8978562244608087189L, "com/taobao/weex/bridge/ResultCallbackManager", 9);
        $jacocoData = a2;
        return a2;
    }

    ResultCallbackManager() {
        $jacocoInit()[0] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[7] = true;
        $jacocoInit[8] = true;
    }

    static long generateCallbackId(ResultCallback resultCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sCallbackId < 2147483647L) {
            $jacocoInit[1] = true;
        } else {
            sCallbackId = 0;
            $jacocoInit[2] = true;
        }
        long j = sCallbackId;
        sCallbackId = 1 + j;
        int i = (int) j;
        $jacocoInit[3] = true;
        mResultCallbacks.put(i, resultCallback);
        long j2 = (long) i;
        $jacocoInit[4] = true;
        return j2;
    }

    static ResultCallback removeCallbackById(long j) {
        boolean[] $jacocoInit = $jacocoInit();
        int i = (int) j;
        ResultCallback resultCallback = mResultCallbacks.get(i);
        $jacocoInit[5] = true;
        mResultCallbacks.remove(i);
        $jacocoInit[6] = true;
        return resultCallback;
    }
}
