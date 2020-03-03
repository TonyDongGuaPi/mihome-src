package com.taobao.weex.utils;

import java.io.Serializable;
import java.util.HashMap;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXMap extends HashMap<String, String> implements Serializable {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(6087022141439612494L, "com/taobao/weex/utils/WXMap", 2);
        $jacocoData = a2;
        return a2;
    }

    public WXMap() {
        $jacocoInit()[0] = true;
    }

    public String put(String str, byte[] bArr) {
        boolean[] $jacocoInit = $jacocoInit();
        String str2 = (String) super.put(str, new String(bArr));
        $jacocoInit[1] = true;
        return str2;
    }
}
