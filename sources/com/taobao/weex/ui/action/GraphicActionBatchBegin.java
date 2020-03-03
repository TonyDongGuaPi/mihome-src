package com.taobao.weex.ui.action;

import com.taobao.weex.WXSDKInstance;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class GraphicActionBatchBegin extends BasicGraphicAction {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-4447030797523635313L, "com/taobao/weex/ui/action/GraphicActionBatchBegin", 2);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphicActionBatchBegin(WXSDKInstance wXSDKInstance, String str) {
        super(wXSDKInstance, str);
        boolean[] $jacocoInit = $jacocoInit();
        this.mActionType = 1;
        $jacocoInit[0] = true;
    }

    public void executeAction() {
        $jacocoInit()[1] = true;
    }
}
