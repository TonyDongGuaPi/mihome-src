package com.taobao.weex.bridge;

import com.alibaba.fastjson.JSON;
import com.taobao.weex.utils.WXWsonJSONSwitch;
import com.taobao.weex.wson.WsonUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXDebugJsBridge {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-7128266797833037353L, "com/taobao/weex/bridge/WXDebugJsBridge", 10);
        $jacocoData = a2;
        return a2;
    }

    public native void jsHandleCallAddElement(String str, String str2, byte[] bArr, String str3, boolean z);

    public native void jsHandleCallAddEvent(String str, String str2, String str3);

    public native void jsHandleCallCreateBody(String str, byte[] bArr, boolean z);

    public native void jsHandleCallCreateFinish(String str);

    public native void jsHandleCallGCanvasLinkNative(String str, int i, String str2);

    public native void jsHandleCallMoveElement(String str, String str2, String str3, String str4);

    public native void jsHandleCallNative(String str, byte[] bArr, String str2);

    public native void jsHandleCallNativeComponent(String str, String str2, String str3, byte[] bArr, byte[] bArr2, boolean z);

    public native void jsHandleCallNativeLog(byte[] bArr);

    public native void jsHandleCallNativeModule(String str, String str2, String str3, byte[] bArr, byte[] bArr2, boolean z);

    public native void jsHandleCallRefreshFinish(String str, byte[] bArr, String str2);

    public native void jsHandleCallRemoveElement(String str, String str2);

    public native void jsHandleCallRemoveEvent(String str, String str2, String str3);

    public native void jsHandleCallUpdateAttrs(String str, String str2, byte[] bArr, boolean z);

    public native void jsHandleCallUpdateFinish(String str, byte[] bArr, String str2);

    public native void jsHandleCallUpdateStyleNative(String str, String str2, byte[] bArr, boolean z);

    public native void jsHandleClearInterval(String str, String str2);

    public native void jsHandleReportException(String str, String str2, String str3);

    public native void jsHandleSetInterval(String str, String str2, String str3);

    public native void jsHandleSetJSVersion(String str);

    public native void jsHandleSetTimeout(String str, String str2);

    public native void resetWXBridge(Object obj, String str);

    public WXDebugJsBridge() {
        $jacocoInit()[0] = true;
    }

    public void jsHandleCallNativeModule(String str, String str2, String str3, byte[] bArr, byte[] bArr2) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
        byte[] convertJSONToWsonIfUseWson = WXWsonJSONSwitch.convertJSONToWsonIfUseWson(bArr);
        byte[] convertJSONToWsonIfUseWson2 = WXWsonJSONSwitch.convertJSONToWsonIfUseWson(bArr2);
        $jacocoInit[2] = true;
        jsHandleCallNativeModule(str, str2, str3, convertJSONToWsonIfUseWson, convertJSONToWsonIfUseWson2, true);
        $jacocoInit[3] = true;
    }

    public void jsHandleCallNativeComponent(String str, String str2, String str3, byte[] bArr, byte[] bArr2) {
        boolean[] $jacocoInit = $jacocoInit();
        jsHandleCallNativeComponent(str, str2, str3, WXWsonJSONSwitch.convertJSONToWsonIfUseWson(bArr), WXWsonJSONSwitch.convertJSONToWsonIfUseWson(bArr2), true);
        $jacocoInit[4] = true;
    }

    public void jsHandleCallAddElement(String str, String str2, String str3, String str4) {
        boolean[] $jacocoInit = $jacocoInit();
        jsHandleCallAddElement(str, str2, WsonUtils.toWson(JSON.parse(str3)), str4, true);
        $jacocoInit[5] = true;
    }

    public void jsHandleCallCreateBody(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        jsHandleCallCreateBody(str, WsonUtils.toWson(JSON.parse(str2)), true);
        $jacocoInit[6] = true;
    }

    public void jsHandleCallUpdateAttrs(String str, String str2, String str3) {
        boolean[] $jacocoInit = $jacocoInit();
        jsHandleCallUpdateAttrs(str, str2, WsonUtils.toWson(JSON.parseObject(str3)), true);
        $jacocoInit[7] = true;
    }

    public void jsHandleCallUpdateStyle(String str, String str2, String str3) {
        boolean[] $jacocoInit = $jacocoInit();
        byte[] wson = WsonUtils.toWson(JSON.parseObject(str3));
        $jacocoInit[8] = true;
        jsHandleCallUpdateStyleNative(str, str2, wson, true);
        $jacocoInit[9] = true;
    }
}
