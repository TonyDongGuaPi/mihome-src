package com.taobao.weex.ui.action;

import android.support.annotation.NonNull;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.ui.component.WXComponent;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class GraphicActionRenderSuccess extends BasicGraphicAction {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-7413366057544770450L, "com/taobao/weex/ui/action/GraphicActionRenderSuccess", 10);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphicActionRenderSuccess(@NonNull WXSDKInstance wXSDKInstance) {
        super(wXSDKInstance, "");
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public void executeAction() {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance wXSDKIntance = getWXSDKIntance();
        $jacocoInit[1] = true;
        if (wXSDKIntance == null) {
            $jacocoInit[2] = true;
        } else if (wXSDKIntance.getContext() == null) {
            $jacocoInit[3] = true;
        } else {
            WXComponent rootComponent = wXSDKIntance.getRootComponent();
            int i2 = 0;
            if (rootComponent == null) {
                $jacocoInit[5] = true;
                i = 0;
            } else {
                $jacocoInit[6] = true;
                i2 = (int) rootComponent.getLayoutWidth();
                $jacocoInit[7] = true;
                i = (int) rootComponent.getLayoutHeight();
                $jacocoInit[8] = true;
            }
            wXSDKIntance.onRenderSuccess(i2, i);
            $jacocoInit[9] = true;
            return;
        }
        $jacocoInit[4] = true;
    }
}
