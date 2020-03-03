package com.taobao.weex.bridge;

import com.alibaba.fastjson.JSON;
import com.taobao.weex.common.IWXJsFunctions;
import com.taobao.weex.utils.WXWsonJSONSwitch;
import com.taobao.weex.wson.WsonUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXJsFunctions implements IWXJsFunctions {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(649349765099910938L, "com/taobao/weex/bridge/WXJsFunctions", 9);
        $jacocoData = a2;
        return a2;
    }

    public native void initWxBridge(Object obj, String str);

    public native void jsFunctionCallAddEvent(String str, String str2, String str3);

    public native void jsFunctionCallCreateBody(String str, byte[] bArr, boolean z);

    public native void jsFunctionCallCreateFinish(String str);

    public native void jsFunctionCallMoveElement(String str, String str2, String str3, String str4);

    public native void jsFunctionCallRefreshFinish(String str, byte[] bArr, String str2);

    public native void jsFunctionCallRemoveElement(String str, String str2);

    public native void jsFunctionCallRemoveEvent(String str, String str2, String str3);

    public native void jsFunctionCallUpdateAttrs(String str, String str2, byte[] bArr, boolean z);

    public native void jsFunctionCallUpdateFinish(String str, byte[] bArr, String str2);

    public native void jsFunctionCallUpdateStyle(String str, String str2, byte[] bArr, boolean z);

    public native void jsHandleCallAddElement(String str, String str2, byte[] bArr, String str3, boolean z);

    public native void jsHandleCallGCanvasLinkNative(String str, int i, String str2);

    public native void jsHandleCallNative(String str, byte[] bArr, String str2);

    public native void jsHandleCallNativeComponent(String str, String str2, String str3, byte[] bArr, byte[] bArr2, boolean z);

    public native void jsHandleCallNativeLog(byte[] bArr);

    public native void jsHandleCallNativeModule(String str, String str2, String str3, byte[] bArr, byte[] bArr2, boolean z);

    public native void jsHandleClearInterval(String str, String str2);

    public native void jsHandleReportException(String str, String str2, String str3);

    public native void jsHandleSetInterval(String str, String str2, String str3);

    public native void jsHandleSetJSVersion(String str);

    public native void jsHandleSetTimeout(String str, String str2);

    public WXJsFunctions() {
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

    public void jsFunctionCallCreateBody(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        jsFunctionCallCreateBody(str, WsonUtils.toWson(JSON.parse(str2)), true);
        $jacocoInit[6] = true;
    }

    public void jsFunctionCallUpdateAttrs(String str, String str2, String str3) {
        boolean[] $jacocoInit = $jacocoInit();
        jsFunctionCallUpdateAttrs(str, str2, WsonUtils.toWson(JSON.parseObject(str3)), true);
        $jacocoInit[7] = true;
    }

    public void jsFunctionCallUpdateStyle(String str, String str2, String str3) {
        boolean[] $jacocoInit = $jacocoInit();
        jsFunctionCallUpdateStyle(str, str2, WsonUtils.toWson(JSON.parseObject(str3)), true);
        $jacocoInit[8] = true;
    }
}
