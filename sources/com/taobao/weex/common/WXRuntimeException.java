package com.taobao.weex.common;

import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXRuntimeException extends RuntimeException {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final long serialVersionUID = 5732315311747521491L;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(3774036155497162780L, "com/taobao/weex/common/WXRuntimeException", 1);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXRuntimeException(String str) {
        super(str);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }
}
