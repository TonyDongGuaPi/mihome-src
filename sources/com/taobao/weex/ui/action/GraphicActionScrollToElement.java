package com.taobao.weex.ui.action;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.ui.component.Scrollable;
import com.taobao.weex.ui.component.WXComponent;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class GraphicActionScrollToElement extends BasicGraphicAction {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private final JSONObject mOptions;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-3586427898011960489L, "com/taobao/weex/ui/action/GraphicActionScrollToElement", 4);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphicActionScrollToElement(WXSDKInstance wXSDKInstance, String str, JSONObject jSONObject) {
        super(wXSDKInstance, str);
        boolean[] $jacocoInit = $jacocoInit();
        this.mOptions = jSONObject;
        $jacocoInit[0] = true;
    }

    public void executeAction() {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent wXComponent = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(getPageId(), getRef());
        if (wXComponent == null) {
            $jacocoInit[1] = true;
            return;
        }
        Scrollable parentScroller = wXComponent.getParentScroller();
        if (parentScroller == null) {
            $jacocoInit[2] = true;
            return;
        }
        parentScroller.scrollTo(wXComponent, this.mOptions);
        $jacocoInit[3] = true;
    }
}
