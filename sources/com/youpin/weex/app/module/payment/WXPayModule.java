package com.youpin.weex.app.module.payment;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.youpin.weex.app.common.WXAppStoreApiManager;
import java.util.Map;

public class WXPayModule extends WXModule implements IWXPayAdapter {
    public static final String MODULE_NAME = "yp-payment";

    private IWXPayAdapter getAdapter() {
        return (IWXPayAdapter) WXAppStoreApiManager.b().a(IWXPayAdapter.class);
    }

    @JSMethod(uiThread = false)
    public void pay(String str, Map<String, Object> map, JSCallback jSCallback) {
        if (getAdapter() != null) {
            getAdapter().pay(str, map, jSCallback);
        }
    }

    @JSMethod(uiThread = false)
    public void getSupportPayList(JSCallback jSCallback) {
        if (getAdapter() != null) {
            getAdapter().getSupportPayList(jSCallback);
        }
    }
}
