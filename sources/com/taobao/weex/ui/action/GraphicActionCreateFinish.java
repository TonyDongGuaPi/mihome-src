package com.taobao.weex.ui.action;

import android.support.annotation.NonNull;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.performance.WXInstanceApm;
import com.taobao.weex.ui.component.WXComponent;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class GraphicActionCreateFinish extends BasicGraphicAction {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private int mLayoutHeight;
    private int mLayoutWidth;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(8409825732205451789L, "com/taobao/weex/ui/action/GraphicActionCreateFinish", 19);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphicActionCreateFinish(@NonNull WXSDKInstance wXSDKInstance) {
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
        wXSDKInstance.getApmForInstance().onStage(WXInstanceApm.KEY_PAGE_STAGES_CREATE_FINISH);
        $jacocoInit[5] = true;
        wXSDKInstance.getApmForInstance().extInfo.put(WXInstanceApm.KEY_PAGE_STAGES_CREATE_FINISH, true);
        $jacocoInit[6] = true;
    }

    public void executeAction() {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance wXSDKIntance = getWXSDKIntance();
        $jacocoInit[7] = true;
        if (wXSDKIntance == null) {
            $jacocoInit[8] = true;
        } else if (wXSDKIntance.getContext() == null) {
            $jacocoInit[9] = true;
        } else {
            wXSDKIntance.mHasCreateFinish = true;
            $jacocoInit[11] = true;
            if (wXSDKIntance.getRenderStrategy() != WXRenderStrategy.APPEND_ONCE) {
                $jacocoInit[12] = true;
            } else {
                $jacocoInit[13] = true;
                wXSDKIntance.onCreateFinish();
                $jacocoInit[14] = true;
            }
            if (wXSDKIntance.getWXPerformance() == null) {
                $jacocoInit[15] = true;
            } else {
                $jacocoInit[16] = true;
                wXSDKIntance.getWXPerformance().callCreateFinishTime = System.currentTimeMillis() - wXSDKIntance.getWXPerformance().renderTimeOrigin;
                $jacocoInit[17] = true;
            }
            wXSDKIntance.onOldFsRenderTimeLogic();
            $jacocoInit[18] = true;
            return;
        }
        $jacocoInit[10] = true;
    }
}
