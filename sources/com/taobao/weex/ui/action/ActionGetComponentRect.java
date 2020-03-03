package com.taobao.weex.ui.action;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.bridge.SimpleJSCallback;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.utils.WXViewUtils;
import java.util.HashMap;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class ActionGetComponentRect extends BasicGraphicAction {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private final String mCallback;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-1791707975948100667L, "com/taobao/weex/ui/action/ActionGetComponentRect", 50);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ActionGetComponentRect(WXSDKInstance wXSDKInstance, String str, String str2) {
        super(wXSDKInstance, str);
        boolean[] $jacocoInit = $jacocoInit();
        this.mCallback = str2;
        $jacocoInit[0] = true;
    }

    public void executeAction() {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance wXSDKIntance = getWXSDKIntance();
        $jacocoInit[1] = true;
        if (wXSDKIntance == null) {
            $jacocoInit[2] = true;
        } else if (wXSDKIntance.isDestroy()) {
            $jacocoInit[3] = true;
        } else {
            SimpleJSCallback simpleJSCallback = new SimpleJSCallback(wXSDKIntance.getInstanceId(), this.mCallback);
            $jacocoInit[5] = true;
            if (TextUtils.isEmpty(getRef())) {
                $jacocoInit[6] = true;
                HashMap hashMap = new HashMap();
                $jacocoInit[7] = true;
                hashMap.put("result", false);
                $jacocoInit[8] = true;
                hashMap.put("errMsg", "Illegal parameter");
                $jacocoInit[9] = true;
                simpleJSCallback.invoke(hashMap);
                $jacocoInit[10] = true;
                $jacocoInit[11] = true;
            } else if ("viewport".equalsIgnoreCase(getRef())) {
                $jacocoInit[12] = true;
                callbackViewport(wXSDKIntance, simpleJSCallback);
                $jacocoInit[13] = true;
            } else {
                WXComponent wXComponent = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(getPageId(), getRef());
                if (wXComponent == null) {
                    $jacocoInit[14] = true;
                    return;
                }
                HashMap hashMap2 = new HashMap();
                if (wXComponent != null) {
                    $jacocoInit[15] = true;
                    int instanceViewPortWidth = wXSDKIntance.getInstanceViewPortWidth();
                    $jacocoInit[16] = true;
                    HashMap hashMap3 = new HashMap();
                    $jacocoInit[17] = true;
                    Rect componentSize = wXComponent.getComponentSize();
                    $jacocoInit[18] = true;
                    hashMap3.put("width", Float.valueOf(getWebPxValue(componentSize.width(), instanceViewPortWidth)));
                    $jacocoInit[19] = true;
                    hashMap3.put("height", Float.valueOf(getWebPxValue(componentSize.height(), instanceViewPortWidth)));
                    $jacocoInit[20] = true;
                    hashMap3.put("bottom", Float.valueOf(getWebPxValue(componentSize.bottom, instanceViewPortWidth)));
                    $jacocoInit[21] = true;
                    hashMap3.put("left", Float.valueOf(getWebPxValue(componentSize.left, instanceViewPortWidth)));
                    $jacocoInit[22] = true;
                    hashMap3.put("right", Float.valueOf(getWebPxValue(componentSize.right, instanceViewPortWidth)));
                    $jacocoInit[23] = true;
                    hashMap3.put("top", Float.valueOf(getWebPxValue(componentSize.top, instanceViewPortWidth)));
                    $jacocoInit[24] = true;
                    hashMap2.put("size", hashMap3);
                    $jacocoInit[25] = true;
                    hashMap2.put("result", true);
                    $jacocoInit[26] = true;
                } else {
                    hashMap2.put("errMsg", "Component does not exist");
                    $jacocoInit[27] = true;
                }
                simpleJSCallback.invoke(hashMap2);
                $jacocoInit[28] = true;
            }
            $jacocoInit[29] = true;
            return;
        }
        $jacocoInit[4] = true;
    }

    private void callbackViewport(WXSDKInstance wXSDKInstance, JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        View containerView = wXSDKInstance.getContainerView();
        if (containerView != null) {
            $jacocoInit[30] = true;
            HashMap hashMap = new HashMap();
            $jacocoInit[31] = true;
            HashMap hashMap2 = new HashMap();
            $jacocoInit[32] = true;
            wXSDKInstance.getContainerView().getLocationOnScreen(new int[2]);
            $jacocoInit[33] = true;
            int instanceViewPortWidth = wXSDKInstance.getInstanceViewPortWidth();
            $jacocoInit[34] = true;
            hashMap2.put("left", Float.valueOf(0.0f));
            $jacocoInit[35] = true;
            hashMap2.put("top", Float.valueOf(0.0f));
            $jacocoInit[36] = true;
            hashMap2.put("right", Float.valueOf(getWebPxValue(containerView.getWidth(), instanceViewPortWidth)));
            $jacocoInit[37] = true;
            hashMap2.put("bottom", Float.valueOf(getWebPxValue(containerView.getHeight(), instanceViewPortWidth)));
            $jacocoInit[38] = true;
            hashMap2.put("width", Float.valueOf(getWebPxValue(containerView.getWidth(), instanceViewPortWidth)));
            $jacocoInit[39] = true;
            hashMap2.put("height", Float.valueOf(getWebPxValue(containerView.getHeight(), instanceViewPortWidth)));
            $jacocoInit[40] = true;
            hashMap.put("size", hashMap2);
            $jacocoInit[41] = true;
            hashMap.put("result", true);
            $jacocoInit[42] = true;
            jSCallback.invoke(hashMap);
            $jacocoInit[43] = true;
        } else {
            HashMap hashMap3 = new HashMap();
            $jacocoInit[44] = true;
            hashMap3.put("result", false);
            $jacocoInit[45] = true;
            hashMap3.put("errMsg", "Component does not exist");
            $jacocoInit[46] = true;
            jSCallback.invoke(hashMap3);
            $jacocoInit[47] = true;
        }
        $jacocoInit[48] = true;
    }

    @NonNull
    private float getWebPxValue(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        float webPxByWidth = WXViewUtils.getWebPxByWidth((float) i, i2);
        $jacocoInit[49] = true;
        return webPxByWidth;
    }
}
