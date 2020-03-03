package com.youpin.weex.app.module.bundlemanager;

import com.taobao.weex.bridge.JSCallback;

public interface IWXBundleManagerAdapter {
    void precacheURL(String str, boolean z, JSCallback jSCallback);
}
