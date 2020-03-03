package com.taobao.weex.ui.action;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.dom.WXEvent;
import com.taobao.weex.tracing.Stopwatch;
import com.taobao.weex.ui.component.WXComponent;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class GraphicActionRemoveEvent extends BasicGraphicAction {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private final String mEvent;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(245246960997758762L, "com/taobao/weex/ui/action/GraphicActionRemoveEvent", 6);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphicActionRemoveEvent(WXSDKInstance wXSDKInstance, String str, Object obj) {
        super(wXSDKInstance, str);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.mEvent = WXEvent.getEventName(obj);
        $jacocoInit[1] = true;
    }

    public void executeAction() {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent wXComponent = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(getPageId(), getRef());
        if (wXComponent == null) {
            $jacocoInit[2] = true;
            return;
        }
        Stopwatch.tick();
        $jacocoInit[3] = true;
        wXComponent.removeEvent(this.mEvent);
        $jacocoInit[4] = true;
        Stopwatch.split("removeEventFromComponent");
        $jacocoInit[5] = true;
    }
}
