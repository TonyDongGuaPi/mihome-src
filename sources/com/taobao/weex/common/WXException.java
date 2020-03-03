package com.taobao.weex.common;

import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXException extends Exception {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final long serialVersionUID = 7265837506862157379L;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(8767541213724800477L, "com/taobao/weex/common/WXException", 1);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXException(String str) {
        super(str);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }
}
