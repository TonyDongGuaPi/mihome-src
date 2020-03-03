package com.taobao.weex.common;

public interface IWXJsFunctions {
    void initWxBridge(Object obj, String str);

    void jsFunctionCallAddEvent(String str, String str2, String str3);

    void jsFunctionCallCreateBody(String str, String str2);

    void jsFunctionCallCreateFinish(String str);

    void jsFunctionCallMoveElement(String str, String str2, String str3, String str4);

    void jsFunctionCallRefreshFinish(String str, byte[] bArr, String str2);

    void jsFunctionCallRemoveElement(String str, String str2);

    void jsFunctionCallRemoveEvent(String str, String str2, String str3);

    void jsFunctionCallUpdateAttrs(String str, String str2, String str3);

    void jsFunctionCallUpdateFinish(String str, byte[] bArr, String str2);

    void jsFunctionCallUpdateStyle(String str, String str2, String str3);

    void jsHandleCallAddElement(String str, String str2, String str3, String str4);

    void jsHandleCallGCanvasLinkNative(String str, int i, String str2);

    void jsHandleCallNative(String str, byte[] bArr, String str2);

    void jsHandleCallNativeComponent(String str, String str2, String str3, byte[] bArr, byte[] bArr2);

    void jsHandleCallNativeLog(byte[] bArr);

    void jsHandleCallNativeModule(String str, String str2, String str3, byte[] bArr, byte[] bArr2);

    void jsHandleClearInterval(String str, String str2);

    void jsHandleReportException(String str, String str2, String str3);

    void jsHandleSetInterval(String str, String str2, String str3);

    void jsHandleSetJSVersion(String str);

    void jsHandleSetTimeout(String str, String str2);
}
