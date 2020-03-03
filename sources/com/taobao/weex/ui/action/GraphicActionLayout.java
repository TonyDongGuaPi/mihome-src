package com.taobao.weex.ui.action;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.ui.component.WXComponent;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class GraphicActionLayout extends BasicGraphicAction {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private final boolean mIsLayoutRTL;
    private final GraphicPosition mLayoutPosition;
    private final GraphicSize mLayoutSize;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(6840036169542911213L, "com/taobao/weex/ui/action/GraphicActionLayout", 6);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphicActionLayout(WXSDKInstance wXSDKInstance, String str, GraphicPosition graphicPosition, GraphicSize graphicSize, boolean z) {
        super(wXSDKInstance, str);
        boolean[] $jacocoInit = $jacocoInit();
        this.mLayoutPosition = graphicPosition;
        this.mLayoutSize = graphicSize;
        this.mIsLayoutRTL = z;
        $jacocoInit[0] = true;
    }

    public void executeAction() {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent wXComponent = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(getPageId(), getRef());
        if (wXComponent == null) {
            $jacocoInit[1] = true;
            return;
        }
        wXComponent.setIsLayoutRTL(this.mIsLayoutRTL);
        $jacocoInit[2] = true;
        wXComponent.setDemission(this.mLayoutSize, this.mLayoutPosition);
        $jacocoInit[3] = true;
        wXComponent.setSafeLayout(wXComponent);
        $jacocoInit[4] = true;
        wXComponent.setPadding(wXComponent.getPadding(), wXComponent.getBorder());
        $jacocoInit[5] = true;
    }
}
