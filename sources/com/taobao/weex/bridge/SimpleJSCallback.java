package com.taobao.weex.bridge;

import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class SimpleJSCallback implements JSCallback {
    private static transient /* synthetic */ boolean[] $jacocoData;
    String mCallbackId;
    String mInstanceId;
    private InvokerCallback mInvokerCallback;

    interface InvokerCallback {
        void onInvokeSuccess();
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-7719720552929722194L, "com/taobao/weex/bridge/SimpleJSCallback", 8);
        $jacocoData = a2;
        return a2;
    }

    public void setInvokerCallback(InvokerCallback invokerCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mInvokerCallback = invokerCallback;
        $jacocoInit[0] = true;
    }

    public String getCallbackId() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mCallbackId;
        $jacocoInit[1] = true;
        return str;
    }

    public SimpleJSCallback(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mCallbackId = str2;
        this.mInstanceId = str;
        $jacocoInit[2] = true;
    }

    public void invoke(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        WXBridgeManager.getInstance().callbackJavascript(this.mInstanceId, this.mCallbackId, obj, false);
        if (this.mInvokerCallback == null) {
            $jacocoInit[3] = true;
        } else {
            $jacocoInit[4] = true;
            this.mInvokerCallback.onInvokeSuccess();
            $jacocoInit[5] = true;
        }
        $jacocoInit[6] = true;
    }

    public void invokeAndKeepAlive(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        WXBridgeManager.getInstance().callbackJavascript(this.mInstanceId, this.mCallbackId, obj, true);
        $jacocoInit[7] = true;
    }
}
