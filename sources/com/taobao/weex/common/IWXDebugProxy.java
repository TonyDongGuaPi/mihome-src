package com.taobao.weex.common;

public interface IWXDebugProxy {
    public static final String ACTION_DEBUG_INSTANCE_REFRESH = "DEBUG_INSTANCE_REFRESH";
    public static final String ACTION_INSTANCE_RELOAD = "INSTANCE_RELOAD";

    IWXBridge getWXBridge();

    void start(IWXJsFunctions iWXJsFunctions);

    void stop(boolean z);
}
