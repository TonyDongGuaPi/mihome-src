package com.youpin.weex.app.module.event_monitor;

import com.alipay.android.phone.a.a.a;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.xiaomi.miot.store.api.ICallback;
import com.xiaomi.youpin.common.AppIdManager;
import com.xiaomi.youpin.common.util.AppInfo;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.youpin.weex.app.common.ModuleUtils;
import com.youpin.weex.app.common.TimeManager;
import com.youpin.weex.app.util.OpenUtils;
import java.util.Map;

public class EventMonitorModule extends WXModule {
    public static final String MODULE_NAME = "yp-event-monitor";

    @JSMethod(uiThread = false)
    public void raxEvent(Map<String, String> map, final JSCallback jSCallback) {
        map.put("app_key", StoreApiManager.a().b().d());
        map.put("app_version", AppInfo.f());
        map.put("weex_version", OpenUtils.e);
        map.put("platform", a.f813a);
        map.put("device_id", AppIdManager.a().c());
        map.put("channel", StoreApiManager.a().b().c());
        TimeManager.a().a((Map) map, (ICallback) new ICallback() {
            public void callback(Map map) {
                if (((Integer) map.get("result")).intValue() == 0) {
                    ModuleUtils.a("success", jSCallback);
                } else {
                    ModuleUtils.b((String) map.get("msg"), jSCallback);
                }
            }
        });
    }
}
