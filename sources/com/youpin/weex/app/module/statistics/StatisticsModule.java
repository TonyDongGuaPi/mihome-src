package com.youpin.weex.app.module.statistics;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.youpin.weex.app.common.WXAppStoreApiManager;
import java.util.Map;

public class StatisticsModule extends WXModule implements IStatisticsAdapter {
    public static final String MODULE_NAME = "yp-statistics";

    private IStatisticsAdapter getAdapter() {
        return (IStatisticsAdapter) WXAppStoreApiManager.b().a(IStatisticsAdapter.class);
    }

    @JSMethod(uiThread = false)
    public void stat(String str, String str2, String str3, String str4, JSCallback jSCallback) {
        if (getAdapter() != null) {
            getAdapter().stat(str, str2, str3, str4, jSCallback);
        }
    }

    @JSMethod(uiThread = false)
    public void reportCached(boolean z, JSCallback jSCallback) {
        if (getAdapter() != null) {
            getAdapter().reportCached(z, jSCallback);
        }
    }

    @JSMethod(uiThread = false)
    public void stat3(String str, String str2, String str3, String str4, Map<String, String> map, JSCallback jSCallback) {
        if (getAdapter() != null) {
            getAdapter().stat3(str, str2, str3, str4, map, jSCallback);
        }
    }
}
