package com.taobao.weex.common;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXInstanceWrap extends WXModule {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(1895654819458961915L, "com/taobao/weex/common/WXInstanceWrap", 5);
        $jacocoData = a2;
        return a2;
    }

    public WXInstanceWrap() {
        $jacocoInit()[0] = true;
    }

    @JSMethod
    public void error(String str, String str2, String str3) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mWXSDKInstance == null) {
            $jacocoInit[1] = true;
        } else {
            $jacocoInit[2] = true;
            WXSDKInstance wXSDKInstance = this.mWXSDKInstance;
            wXSDKInstance.onRenderError(str + "|" + str2, str3);
            $jacocoInit[3] = true;
        }
        $jacocoInit[4] = true;
    }
}
