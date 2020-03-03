package com.taobao.weex.utils;

import android.net.Uri;
import android.view.View;
import com.alibaba.fastjson.JSONArray;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.bridge.WXBridgeManager;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class ATagUtil {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-3448184477838372059L, "com/taobao/weex/utils/ATagUtil", 7);
        $jacocoData = a2;
        return a2;
    }

    public ATagUtil() {
        $jacocoInit()[0] = true;
    }

    public static void onClick(View view, String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
        if (sDKInstance == null) {
            $jacocoInit[1] = true;
            return;
        }
        String uri = sDKInstance.rewriteUri(Uri.parse(str2), "link").toString();
        $jacocoInit[2] = true;
        JSONArray jSONArray = new JSONArray();
        $jacocoInit[3] = true;
        jSONArray.add(uri);
        $jacocoInit[4] = true;
        WXBridgeManager wXBridgeManager = WXSDKManager.getInstance().getWXBridgeManager();
        $jacocoInit[5] = true;
        wXBridgeManager.callModuleMethod(str, "event", "openURL", jSONArray);
        $jacocoInit[6] = true;
    }
}
