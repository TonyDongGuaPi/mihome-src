package com.taobao.weex.ui.action;

import com.alibaba.fastjson.JSONArray;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.utils.WXLogUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class ActionInvokeMethod implements IExecutable {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final String TAG = "ActionInvokeMethod";
    private final JSONArray mArgs;
    private final String mMethod;
    private String mPageId;
    private String mRef;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(7696415745970376276L, "com/taobao/weex/ui/action/ActionInvokeMethod", 4);
        $jacocoData = a2;
        return a2;
    }

    public ActionInvokeMethod(String str, String str2, String str3, JSONArray jSONArray) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mPageId = str;
        this.mRef = str2;
        this.mMethod = str3;
        this.mArgs = jSONArray;
        $jacocoInit[0] = true;
    }

    public void executeAction() {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent wXComponent = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(this.mPageId, this.mRef);
        if (wXComponent == null) {
            $jacocoInit[1] = true;
            WXLogUtils.e(TAG, "target component not found.");
            $jacocoInit[2] = true;
            return;
        }
        wXComponent.invoke(this.mMethod, this.mArgs);
        $jacocoInit[3] = true;
    }
}
