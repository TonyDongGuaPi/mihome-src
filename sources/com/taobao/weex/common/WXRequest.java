package com.taobao.weex.common;

import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXRequest {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final int DEFAULT_TIMEOUT_MS = 3000;
    public String body;
    public String instanceId;
    public String method;
    public Map<String, String> paramMap;
    public int timeoutMs = 3000;
    public String url;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-2058164802655877663L, "com/taobao/weex/common/WXRequest", 1);
        $jacocoData = a2;
        return a2;
    }

    public WXRequest() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }
}
