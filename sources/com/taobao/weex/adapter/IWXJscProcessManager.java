package com.taobao.weex.adapter;

import com.taobao.weex.WXSDKInstance;

public interface IWXJscProcessManager {
    long rebootTimeout();

    boolean shouldReboot();

    boolean withException(WXSDKInstance wXSDKInstance);
}
