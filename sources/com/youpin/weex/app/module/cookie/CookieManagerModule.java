package com.youpin.weex.app.module.cookie;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.youpin.weex.app.common.WXAppStoreApiManager;
import java.util.Map;

public class CookieManagerModule extends WXModule implements ICookieAdapter {
    public static final String MODULE_NAME = "yp-cookie";

    private ICookieAdapter getAdapter() {
        return (ICookieAdapter) WXAppStoreApiManager.b().a(ICookieAdapter.class);
    }

    @JSMethod(uiThread = false)
    public void set(Map<String, Object> map, JSCallback jSCallback) throws Exception {
        if (getAdapter() != null) {
            getAdapter().set(map, jSCallback);
        }
    }

    @JSMethod(uiThread = false)
    public void setFromResponse(String str, Map<String, Object> map, JSCallback jSCallback) throws Exception {
        if (getAdapter() != null) {
            getAdapter().setFromResponse(str, map, jSCallback);
        }
    }

    @JSMethod(uiThread = false)
    public void getFromResponse(String str, JSCallback jSCallback) {
        if (getAdapter() != null) {
            getAdapter().getFromResponse(str, jSCallback);
        }
    }

    @JSMethod(uiThread = false)
    public void getAll(JSCallback jSCallback) throws Exception {
        if (getAdapter() != null) {
            getAdapter().getAll(jSCallback);
        }
    }

    @JSMethod(uiThread = false)
    public void get(String str, JSCallback jSCallback) throws Exception {
        if (getAdapter() != null) {
            getAdapter().get(str, jSCallback);
        }
    }

    @JSMethod(uiThread = false)
    public void clearByName(String str, String str2, JSCallback jSCallback) throws Exception {
        if (getAdapter() != null) {
            getAdapter().clearByName(str, str2, jSCallback);
        }
    }

    @JSMethod(uiThread = false)
    public void clearAll(JSCallback jSCallback) throws Exception {
        if (getAdapter() != null) {
            getAdapter().clearAll(jSCallback);
        }
    }
}
