package com.taobao.weex.ui.component;

import android.text.TextUtils;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXErrorCode;
import com.taobao.weex.ui.IFComponentHolder;
import com.taobao.weex.ui.WXComponentRegistry;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.utils.WXExceptionUtils;
import com.taobao.weex.utils.WXLogUtils;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXComponentFactory {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-4544083163084008111L, "com/taobao/weex/ui/component/WXComponentFactory", 16);
        $jacocoData = a2;
        return a2;
    }

    public WXComponentFactory() {
        $jacocoInit()[0] = true;
    }

    public static WXComponent newInstance(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXSDKInstance == null) {
            $jacocoInit[1] = true;
        } else if (TextUtils.isEmpty(basicComponentData.mComponentType)) {
            $jacocoInit[2] = true;
        } else {
            IFComponentHolder component = WXComponentRegistry.getComponent(basicComponentData.mComponentType);
            if (component != null) {
                $jacocoInit[4] = true;
            } else {
                $jacocoInit[5] = true;
                if (!WXEnvironment.isApkDebugable()) {
                    $jacocoInit[6] = true;
                } else {
                    $jacocoInit[7] = true;
                    $jacocoInit[8] = true;
                    WXLogUtils.e("WXComponentFactory error type:[" + basicComponentData.mComponentType + "] class not found");
                    $jacocoInit[9] = true;
                }
                component = WXComponentRegistry.getComponent(WXBasicComponentType.CONTAINER);
                if (component != null) {
                    $jacocoInit[10] = true;
                } else {
                    $jacocoInit[11] = true;
                    WXExceptionUtils.commitCriticalExceptionRT(wXSDKInstance.getInstanceId(), WXErrorCode.WX_RENDER_ERR_COMPONENT_NOT_REGISTER, "createComponent", basicComponentData.mComponentType + " not registered", (Map<String, String>) null);
                    $jacocoInit[12] = true;
                    return null;
                }
            }
            try {
                WXComponent createInstance = component.createInstance(wXSDKInstance, wXVContainer, basicComponentData);
                $jacocoInit[13] = true;
                return createInstance;
            } catch (Throwable th) {
                $jacocoInit[14] = true;
                WXLogUtils.e("WXComponentFactory Exception type:[" + basicComponentData.mComponentType + "] ", th);
                $jacocoInit[15] = true;
                return null;
            }
        }
        $jacocoInit[3] = true;
        return null;
    }
}
