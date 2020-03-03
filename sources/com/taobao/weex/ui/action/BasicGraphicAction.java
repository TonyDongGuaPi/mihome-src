package com.taobao.weex.ui.action;

import android.text.TextUtils;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.utils.WXLogUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public abstract class BasicGraphicAction implements IExecutable, Runnable {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final int ActionTypeBatchBegin = 1;
    public static final int ActionTypeBatchEnd = 2;
    public static final int ActionTypeNormal = 0;
    public int mActionType = 0;
    private WXSDKInstance mInstance;
    private final String mRef;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(7572808491505704590L, "com/taobao/weex/ui/action/BasicGraphicAction", 18);
        $jacocoData = a2;
        return a2;
    }

    public BasicGraphicAction(WXSDKInstance wXSDKInstance, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mInstance = wXSDKInstance;
        this.mRef = str;
        $jacocoInit[0] = true;
    }

    public final WXSDKInstance getWXSDKIntance() {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance wXSDKInstance = this.mInstance;
        $jacocoInit[1] = true;
        return wXSDKInstance;
    }

    public final String getPageId() {
        boolean[] $jacocoInit = $jacocoInit();
        String instanceId = this.mInstance.getInstanceId();
        $jacocoInit[2] = true;
        return instanceId;
    }

    public final String getRef() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mRef;
        $jacocoInit[3] = true;
        return str;
    }

    public void executeActionOnRender() {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(this.mInstance.getInstanceId())) {
            $jacocoInit[4] = true;
            WXLogUtils.e("[BasicGraphicAction] pageId can not be null");
            $jacocoInit[5] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[8] = true;
                return;
            }
            $jacocoInit[6] = true;
            RuntimeException runtimeException = new RuntimeException(Operators.ARRAY_START_STR + getClass().getName() + "] pageId can not be null");
            $jacocoInit[7] = true;
            throw runtimeException;
        }
        WXSDKManager.getInstance().getWXRenderManager().postGraphicAction(this.mInstance.getInstanceId(), this);
        $jacocoInit[9] = true;
    }

    public void run() {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            executeAction();
            $jacocoInit[10] = true;
        } catch (Throwable th) {
            $jacocoInit[11] = true;
            if (!WXEnvironment.isApkDebugable()) {
                WXLogUtils.w("BasicGraphicAction", th);
                $jacocoInit[16] = true;
            } else {
                $jacocoInit[12] = true;
                StringBuilder sb = new StringBuilder();
                sb.append("SafeRunnable run throw expection:");
                $jacocoInit[13] = true;
                sb.append(th.getMessage());
                String sb2 = sb.toString();
                $jacocoInit[14] = true;
                WXLogUtils.e("BasicGraphicAction", sb2);
                $jacocoInit[15] = true;
                throw th;
            }
        }
        $jacocoInit[17] = true;
    }
}
