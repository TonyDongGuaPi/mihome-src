package com.taobao.weex.bridge;

import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class EventResult {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private Object result;
    private boolean success = false;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(372183040898566528L, "com/taobao/weex/bridge/EventResult", 4);
        $jacocoData = a2;
        return a2;
    }

    public EventResult() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public void onCallback(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        this.success = true;
        this.result = obj;
        $jacocoInit[1] = true;
    }

    public boolean isSuccess() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.success;
        $jacocoInit[2] = true;
        return z;
    }

    public Object getResult() {
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = this.result;
        $jacocoInit[3] = true;
        return obj;
    }
}
