package com.taobao.weex.ui.action;

import android.text.TextUtils;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXVContainer;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class GraphicActionRemoveElement extends BasicGraphicAction {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(3948340382613568433L, "com/taobao/weex/ui/action/GraphicActionRemoveElement", 25);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphicActionRemoveElement(WXSDKInstance wXSDKInstance, String str) {
        super(wXSDKInstance, str);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public void executeAction() {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent wXComponent = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(getPageId(), getRef());
        $jacocoInit[1] = true;
        if (wXComponent == null) {
            $jacocoInit[2] = true;
        } else if (wXComponent.getParent() == null) {
            $jacocoInit[3] = true;
        } else if (wXComponent.getInstance() == null) {
            $jacocoInit[4] = true;
        } else {
            clearRegistryForComponent(wXComponent);
            $jacocoInit[6] = true;
            WXVContainer parent = wXComponent.getParent();
            $jacocoInit[7] = true;
            if (wXComponent.getHostView() == null) {
                $jacocoInit[8] = true;
            } else if (TextUtils.equals(wXComponent.getComponentType(), "video")) {
                $jacocoInit[9] = true;
            } else if (TextUtils.equals(wXComponent.getComponentType(), "videoplus")) {
                $jacocoInit[10] = true;
            } else {
                $jacocoInit[11] = true;
                wXComponent.getHostView().getLocationInWindow(new int[2]);
                $jacocoInit[12] = true;
            }
            parent.remove(wXComponent, true);
            $jacocoInit[13] = true;
            return;
        }
        $jacocoInit[5] = true;
    }

    private void clearRegistryForComponent(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent unregisterComponent = WXSDKManager.getInstance().getWXRenderManager().unregisterComponent(getPageId(), getRef());
        if (unregisterComponent == null) {
            $jacocoInit[14] = true;
        } else {
            $jacocoInit[15] = true;
            unregisterComponent.removeAllEvent();
            $jacocoInit[16] = true;
            unregisterComponent.removeStickyStyle();
            $jacocoInit[17] = true;
        }
        if (!(wXComponent instanceof WXVContainer)) {
            $jacocoInit[18] = true;
        } else {
            WXVContainer wXVContainer = (WXVContainer) wXComponent;
            $jacocoInit[19] = true;
            int childCount = wXVContainer.childCount() - 1;
            $jacocoInit[20] = true;
            while (childCount >= 0) {
                $jacocoInit[22] = true;
                clearRegistryForComponent(wXVContainer.getChild(childCount));
                childCount--;
                $jacocoInit[23] = true;
            }
            $jacocoInit[21] = true;
        }
        $jacocoInit[24] = true;
    }
}
