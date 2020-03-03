package com.taobao.weex.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public final class WXWorkThreadManager {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(8480926240025075958L, "com/taobao/weex/common/WXWorkThreadManager", 10);
        $jacocoData = a2;
        return a2;
    }

    public WXWorkThreadManager() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
    }

    public void post(Runnable runnable) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.singleThreadExecutor == null) {
            $jacocoInit[2] = true;
        } else {
            $jacocoInit[3] = true;
            this.singleThreadExecutor.execute(runnable);
            $jacocoInit[4] = true;
        }
        $jacocoInit[5] = true;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.singleThreadExecutor == null) {
            $jacocoInit[6] = true;
        } else {
            $jacocoInit[7] = true;
            this.singleThreadExecutor.shutdown();
            $jacocoInit[8] = true;
        }
        this.singleThreadExecutor = null;
        $jacocoInit[9] = true;
    }
}
