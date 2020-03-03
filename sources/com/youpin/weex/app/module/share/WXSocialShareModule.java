package com.youpin.weex.app.module.share;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.youpin.weex.app.common.WXAppStoreApiManager;
import java.util.Map;
import org.json.JSONException;

public class WXSocialShareModule extends WXModule implements IWXSocialShareAdapter {
    public static final String MODULE_NAME = "yp-share";

    private IWXSocialShareAdapter getAdapter() {
        return (IWXSocialShareAdapter) WXAppStoreApiManager.b().a(IWXSocialShareAdapter.class);
    }

    @JSMethod(uiThread = true)
    public void share(Map<String, Object> map, JSCallback jSCallback) throws JSONException {
        if (getAdapter() != null) {
            getAdapter().share(map, jSCallback);
        }
    }

    @JSMethod(uiThread = true)
    public void shareCustom(Map<String, Object> map, JSCallback jSCallback) throws JSONException {
        if (getAdapter() != null) {
            getAdapter().shareCustom(map, jSCallback);
        }
    }

    @JSMethod
    public void getSupportShareList(JSCallback jSCallback) {
        if (getAdapter() != null) {
            getAdapter().getSupportShareList(jSCallback);
        }
    }
}
