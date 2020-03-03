package com.taobao.weex.ui.action;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.bridge.SimpleJSCallback;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.list.template.jni.NativeRenderObjectUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.util.HashMap;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class ActionGetLayoutDirection extends BasicGraphicAction {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private final String mCallback;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-4206458086724169419L, "com/taobao/weex/ui/action/ActionGetLayoutDirection", 34);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ActionGetLayoutDirection(WXSDKInstance wXSDKInstance, String str, String str2) {
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
                String str = "ltr";
                if (wXComponent != null) {
                    $jacocoInit[16] = true;
                    switch (NativeRenderObjectUtils.nativeRenderObjectGetLayoutDirectionFromPathNode(wXComponent.getRenderObjectPtr())) {
                        case 0:
                            str = "inherit";
                            $jacocoInit[17] = true;
                            break;
                        case 1:
                            str = "ltr";
                            $jacocoInit[18] = true;
                            break;
                        case 2:
                            str = Constants.Name.RTL;
                            $jacocoInit[19] = true;
                            break;
                        default:
                            str = "ltr";
                            $jacocoInit[20] = true;
                            break;
                    }
                } else {
                    $jacocoInit[15] = true;
                }
                simpleJSCallback.invoke(str);
                $jacocoInit[21] = true;
            }
            $jacocoInit[22] = true;
            return;
        }
        $jacocoInit[4] = true;
    }

    private void callbackViewport(WXSDKInstance wXSDKInstance, JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXSDKInstance.getContainerView() != null) {
            $jacocoInit[23] = true;
            HashMap hashMap = new HashMap();
            $jacocoInit[24] = true;
            hashMap.put("direction", "ltr");
            $jacocoInit[25] = true;
            hashMap.put("result", true);
            $jacocoInit[26] = true;
            jSCallback.invoke(hashMap);
            $jacocoInit[27] = true;
        } else {
            HashMap hashMap2 = new HashMap();
            $jacocoInit[28] = true;
            hashMap2.put("result", false);
            $jacocoInit[29] = true;
            hashMap2.put("errMsg", "Component does not exist");
            $jacocoInit[30] = true;
            jSCallback.invoke(hashMap2);
            $jacocoInit[31] = true;
        }
        $jacocoInit[32] = true;
    }

    @NonNull
    private float getWebPxValue(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        float webPxByWidth = WXViewUtils.getWebPxByWidth((float) i, i2);
        $jacocoInit[33] = true;
        return webPxByWidth;
    }
}
