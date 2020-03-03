package com.youpin.weex.app.module.bundlemanager;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.youpin.weex.app.common.WXAppStoreApiManager;

public class WXBundleManagerModule extends WXModule implements IWXBundleManagerAdapter {
    public static final String MODULE_NAME = "yp-bundle-manager";

    /* access modifiers changed from: package-private */
    public IWXBundleManagerAdapter getAdapter() {
        return (IWXBundleManagerAdapter) WXAppStoreApiManager.b().a(IWXBundleManagerAdapter.class);
    }

    @JSMethod(uiThread = false)
    public void precacheURL(String str, boolean z, JSCallback jSCallback) {
        if (getAdapter() != null) {
            getAdapter().precacheURL(str, z, jSCallback);
        }
    }
}
