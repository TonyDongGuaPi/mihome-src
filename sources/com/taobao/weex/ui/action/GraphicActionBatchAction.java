package com.taobao.weex.ui.action;

import com.taobao.weex.WXSDKInstance;
import java.util.ArrayList;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class GraphicActionBatchAction extends BasicGraphicAction {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private List<BasicGraphicAction> mActions;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(3845174605951235390L, "com/taobao/weex/ui/action/GraphicActionBatchAction", 6);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphicActionBatchAction(WXSDKInstance wXSDKInstance, String str, List<BasicGraphicAction> list) {
        super(wXSDKInstance, str);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.mActions = new ArrayList(list);
        $jacocoInit[1] = true;
    }

    public void executeAction() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[2] = true;
        int i = 0;
        while (i < this.mActions.size()) {
            $jacocoInit[3] = true;
            this.mActions.get(i).executeAction();
            i++;
            $jacocoInit[4] = true;
        }
        $jacocoInit[5] = true;
    }
}
