package com.taobao.weex.ui.action;

import com.taobao.weex.WXSDKInstance;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class GraphicActionBatchEnd extends BasicGraphicAction {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-8418515630948521289L, "com/taobao/weex/ui/action/GraphicActionBatchEnd", 2);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphicActionBatchEnd(WXSDKInstance wXSDKInstance, String str) {
        super(wXSDKInstance, str);
        boolean[] $jacocoInit = $jacocoInit();
        this.mActionType = 2;
        $jacocoInit[0] = true;
    }

    public void executeAction() {
        $jacocoInit()[1] = true;
    }
}
