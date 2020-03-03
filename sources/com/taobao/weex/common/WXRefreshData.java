package com.taobao.weex.common;

import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXRefreshData {
    private static transient /* synthetic */ boolean[] $jacocoData;
    public String data;
    public boolean isDirty;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(997531107550839680L, "com/taobao/weex/common/WXRefreshData", 1);
        $jacocoData = a2;
        return a2;
    }

    public WXRefreshData(String str, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.data = str;
        this.isDirty = z;
        $jacocoInit[0] = true;
    }
}
