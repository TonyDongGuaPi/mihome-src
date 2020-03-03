package com.youpin.weex.app.adapter;

import android.content.Context;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.bridge.JSCallback;
import com.xiaomi.miot.store.api.ICallback;
import com.xiaomi.youpin.log.LogUtils;
import com.youpin.weex.app.common.ModuleUtils;
import com.youpin.weex.app.common.NetInfoManager;
import com.youpin.weex.app.common.WXAppStoreApiManager;
import com.youpin.weex.app.module.netinfo.INetInfoAdapter;
import java.util.Map;

public class YPNetInfoAdapter implements INetInfoAdapter {
    public void fetch(Map<String, String> map, final JSCallback jSCallback) {
        NetInfoManager.a((Context) WXAppStoreApiManager.b().d()).a(map.get("url"), (ICallback) new ICallback() {
            public void callback(Map map) {
                if (map != null) {
                    try {
                        LogUtils.e("fetch", map.toString());
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("status", (Object) (String) map.get("status"));
                        ModuleUtils.a(jSONObject, jSCallback);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ModuleUtils.b("not implement", jSCallback);
                }
            }
        });
    }

    public void startMonitor(Map<String, String> map, JSCallback jSCallback) {
        NetInfoManager.a((Context) WXAppStoreApiManager.b().d()).a(map.get("url"), jSCallback);
    }

    public void stopMonitor(JSCallback jSCallback) {
        NetInfoManager.a((Context) WXAppStoreApiManager.b().d()).a(jSCallback);
    }
}
