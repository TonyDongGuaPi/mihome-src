package com.taobao.weex.ui.action;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.ui.component.WXComponent;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class GraphicActionUpdateAttr extends BasicGraphicAction {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private WXComponent component = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(getPageId(), getRef());
    private Map<String, String> mAttrs;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-3244212664396143570L, "com/taobao/weex/ui/action/GraphicActionUpdateAttr", 9);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphicActionUpdateAttr(WXSDKInstance wXSDKInstance, String str, Map<String, String> map) {
        super(wXSDKInstance, str);
        boolean[] $jacocoInit = $jacocoInit();
        this.mAttrs = map;
        $jacocoInit[0] = true;
        if (this.component == null) {
            $jacocoInit[1] = true;
            return;
        }
        if (this.mAttrs == null) {
            $jacocoInit[2] = true;
        } else {
            $jacocoInit[3] = true;
            this.component.addAttr(this.mAttrs);
            $jacocoInit[4] = true;
        }
        $jacocoInit[5] = true;
    }

    public void executeAction() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.component == null) {
            $jacocoInit[6] = true;
            return;
        }
        this.component.getAttrs().mergeAttr();
        $jacocoInit[7] = true;
        this.component.updateAttrs((Map<String, Object>) this.mAttrs);
        $jacocoInit[8] = true;
    }
}
