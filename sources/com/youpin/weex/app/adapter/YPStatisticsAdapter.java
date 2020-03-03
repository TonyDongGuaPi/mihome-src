package com.youpin.weex.app.adapter;

import com.taobao.weex.bridge.JSCallback;
import com.xiaomi.miot.store.api.ICallback;
import com.youpin.weex.app.common.ModuleUtils;
import com.youpin.weex.app.common.WXAppStoreApiManager;
import com.youpin.weex.app.module.statistics.IStatisticsAdapter;
import java.util.Map;

public class YPStatisticsAdapter implements IStatisticsAdapter {
    public void stat(String str, String str2, String str3, String str4, final JSCallback jSCallback) {
        WXAppStoreApiManager.b().c().a(str, str2, str3, str4, new ICallback() {
            public void callback(Map map) {
                String str = (String) map.get("result");
                if (!(str != null && str.equals("ok"))) {
                    ModuleUtils.b("not implement", jSCallback);
                } else {
                    ModuleUtils.a("success", jSCallback);
                }
            }
        });
    }

    public void reportCached(boolean z, final JSCallback jSCallback) {
        WXAppStoreApiManager.b().c().a(z, new ICallback() {
            public void callback(Map map) {
                String str = (String) map.get("result");
                if (!(str != null && str.equals("ok"))) {
                    ModuleUtils.b("not implement", jSCallback);
                } else {
                    ModuleUtils.a("success", jSCallback);
                }
            }
        });
    }

    public void stat3(String str, String str2, String str3, String str4, Map<String, String> map, final JSCallback jSCallback) {
        WXAppStoreApiManager.b().c().a(str, str2, str3, str4, map, new ICallback() {
            public void callback(Map map) {
                String str = (String) map.get("result");
                if (!(str != null && str.equals("ok"))) {
                    ModuleUtils.b("not implement", jSCallback);
                } else {
                    ModuleUtils.a("success", jSCallback);
                }
            }
        });
    }
}
