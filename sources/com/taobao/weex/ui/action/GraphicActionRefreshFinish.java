package com.taobao.weex.ui.action;

import android.support.annotation.NonNull;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.ui.component.WXComponent;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class GraphicActionRefreshFinish extends BasicGraphicAction {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private int mLayoutHeight;
    private int mLayoutWidth;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(3728136353511469638L, "com/taobao/weex/ui/action/GraphicActionRefreshFinish", 11);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphicActionRefreshFinish(@NonNull WXSDKInstance wXSDKInstance) {
        super(wXSDKInstance, "");
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        WXComponent rootComponent = wXSDKInstance.getRootComponent();
        if (rootComponent == null) {
            $jacocoInit[1] = true;
        } else {
            $jacocoInit[2] = true;
            this.mLayoutWidth = (int) rootComponent.getLayoutWidth();
            $jacocoInit[3] = true;
            this.mLayoutHeight = (int) rootComponent.getLayoutHeight();
            $jacocoInit[4] = true;
        }
        $jacocoInit[5] = true;
    }

    public void executeAction() {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance wXSDKIntance = getWXSDKIntance();
        $jacocoInit[6] = true;
        if (wXSDKIntance == null) {
            $jacocoInit[7] = true;
        } else if (wXSDKIntance.getContext() == null) {
            $jacocoInit[8] = true;
        } else {
            wXSDKIntance.onRefreshSuccess(this.mLayoutWidth, this.mLayoutHeight);
            $jacocoInit[10] = true;
            return;
        }
        $jacocoInit[9] = true;
    }
}
