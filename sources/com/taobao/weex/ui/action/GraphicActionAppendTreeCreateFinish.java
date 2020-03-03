package com.taobao.weex.ui.action;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXVContainer;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class GraphicActionAppendTreeCreateFinish extends BasicGraphicAction {
    private static transient /* synthetic */ boolean[] $jacocoData;
    WXComponent component;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(6520549594113363273L, "com/taobao/weex/ui/action/GraphicActionAppendTreeCreateFinish", 7);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphicActionAppendTreeCreateFinish(WXSDKInstance wXSDKInstance, String str) {
        super(wXSDKInstance, str);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.component = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(getPageId(), str);
        if (this.component == null) {
            $jacocoInit[1] = true;
        } else if (!(this.component instanceof WXVContainer)) {
            $jacocoInit[2] = true;
        } else {
            $jacocoInit[3] = true;
            ((WXVContainer) this.component).appendTreeCreateFinish();
            $jacocoInit[4] = true;
        }
        $jacocoInit[5] = true;
    }

    public void executeAction() {
        $jacocoInit()[6] = true;
    }
}
