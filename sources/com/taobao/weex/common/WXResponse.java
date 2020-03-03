package com.taobao.weex.common;

import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXResponse {
    private static transient /* synthetic */ boolean[] $jacocoData;
    public String data;
    public String errorCode;
    public String errorMsg;
    public Map<String, Object> extendParams;
    public byte[] originalData;
    public String statusCode;
    public String toastMsg;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-3562283216974323799L, "com/taobao/weex/common/WXResponse", 1);
        $jacocoData = a2;
        return a2;
    }

    public WXResponse() {
        $jacocoInit()[0] = true;
    }
}
