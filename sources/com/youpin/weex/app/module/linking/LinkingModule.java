package com.youpin.weex.app.module.linking;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.youpin.weex.app.common.WXAppStoreApiManager;

public class LinkingModule extends WXModule implements ILinkingAdapter {
    public static final String MODULE_NAME = "yp-linking";

    private ILinkingAdapter getAdapter() {
        return (ILinkingAdapter) WXAppStoreApiManager.b().a(ILinkingAdapter.class);
    }

    @JSMethod
    public void openURL(String str, JSCallback jSCallback) {
        if (getAdapter() != null) {
            getAdapter().openURL(str, jSCallback);
        }
    }

    @JSMethod
    public void canOpenURL(String str, JSCallback jSCallback) {
        if (getAdapter() != null) {
            getAdapter().canOpenURL(str, jSCallback);
        }
    }
}
