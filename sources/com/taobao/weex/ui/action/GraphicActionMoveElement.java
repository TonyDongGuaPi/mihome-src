package com.taobao.weex.ui.action;

import android.text.TextUtils;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXVContainer;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class GraphicActionMoveElement extends BasicGraphicAction {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private int mIndex;
    private String mParentref;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-4731584385325297561L, "com/taobao/weex/ui/action/GraphicActionMoveElement", 23);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphicActionMoveElement(WXSDKInstance wXSDKInstance, String str, String str2, int i) {
        super(wXSDKInstance, str);
        boolean[] $jacocoInit = $jacocoInit();
        this.mParentref = str2;
        this.mIndex = i;
        $jacocoInit[0] = true;
    }

    public void executeAction() {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent wXComponent = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(getPageId(), getRef());
        if (wXComponent == null) {
            $jacocoInit[1] = true;
            return;
        }
        WXVContainer parent = wXComponent.getParent();
        $jacocoInit[2] = true;
        WXComponent wXComponent2 = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(getPageId(), this.mParentref);
        if (parent == null) {
            $jacocoInit[3] = true;
        } else if (wXComponent2 == null) {
            $jacocoInit[4] = true;
        } else if (!(wXComponent2 instanceof WXVContainer)) {
            $jacocoInit[5] = true;
        } else {
            if (wXComponent.getHostView() == null) {
                $jacocoInit[7] = true;
            } else if (TextUtils.equals(wXComponent.getComponentType(), "video")) {
                $jacocoInit[8] = true;
            } else if (TextUtils.equals(wXComponent.getComponentType(), "videoplus")) {
                $jacocoInit[9] = true;
            } else {
                $jacocoInit[10] = true;
                wXComponent.getHostView().getLocationInWindow(new int[2]);
                $jacocoInit[11] = true;
            }
            parent.remove(wXComponent, false);
            $jacocoInit[12] = true;
            WXVContainer wXVContainer = (WXVContainer) wXComponent2;
            wXVContainer.addChild(wXComponent, this.mIndex);
            $jacocoInit[13] = true;
            if (wXComponent.getHostView() == null) {
                $jacocoInit[14] = true;
            } else if (TextUtils.equals(wXComponent.getComponentType(), "video")) {
                $jacocoInit[15] = true;
            } else if (TextUtils.equals(wXComponent.getComponentType(), "videoplus")) {
                $jacocoInit[16] = true;
            } else {
                $jacocoInit[17] = true;
                wXComponent.getHostView().getLocationInWindow(new int[2]);
                $jacocoInit[18] = true;
            }
            if (wXComponent.isVirtualComponent()) {
                $jacocoInit[19] = true;
            } else {
                $jacocoInit[20] = true;
                wXVContainer.addSubView(wXComponent.getHostView(), this.mIndex);
                $jacocoInit[21] = true;
            }
            $jacocoInit[22] = true;
            return;
        }
        $jacocoInit[6] = true;
    }
}
