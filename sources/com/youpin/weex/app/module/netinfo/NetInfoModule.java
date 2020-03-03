package com.youpin.weex.app.module.netinfo;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.youpin.weex.app.common.WXAppStoreApiManager;
import java.util.Map;

public class NetInfoModule extends WXModule implements INetInfoAdapter {
    public static final String MODULE_NAME = "yp-netinfo";

    private INetInfoAdapter getAdapter() {
        return (INetInfoAdapter) WXAppStoreApiManager.b().a(INetInfoAdapter.class);
    }

    @JSMethod
    public void fetch(Map<String, String> map, JSCallback jSCallback) {
        if (getAdapter() != null) {
            getAdapter().fetch(map, jSCallback);
        }
    }

    @JSMethod
    public void startMonitor(Map<String, String> map, JSCallback jSCallback) {
        if (getAdapter() != null) {
            getAdapter().startMonitor(map, jSCallback);
        }
    }

    @JSMethod
    public void stopMonitor(JSCallback jSCallback) {
        if (getAdapter() != null) {
            getAdapter().stopMonitor(jSCallback);
        }
    }
}
