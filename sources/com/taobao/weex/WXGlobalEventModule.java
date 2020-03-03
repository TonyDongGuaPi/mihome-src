package com.taobao.weex;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXGlobalEventModule extends WXModule {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(36993361384459581L, "com/taobao/weex/WXGlobalEventModule", 6);
        $jacocoData = a2;
        return a2;
    }

    public WXGlobalEventModule() {
        $jacocoInit()[0] = true;
    }

    @JSMethod
    public void addEventListener(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWXSDKInstance.addEventListener(str, str2);
        $jacocoInit[1] = true;
    }

    public void removeEventListener(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWXSDKInstance.removeEventListener(str, str2);
        $jacocoInit[2] = true;
    }

    @JSMethod
    public void removeEventListener(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWXSDKInstance.removeEventListener(str);
        $jacocoInit[3] = true;
    }

    public void addEventListener(String str, String str2, Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        super.addEventListener(str, str2, map);
        $jacocoInit[4] = true;
        addEventListener(str, str2);
        $jacocoInit[5] = true;
    }
}
