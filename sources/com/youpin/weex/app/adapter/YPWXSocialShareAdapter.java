package com.youpin.weex.app.adapter;

import android.app.Activity;
import android.text.TextUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.bridge.JSCallback;
import com.xiaomi.miot.store.api.ICallback;
import com.xiaomi.youpin.share.config.YouPinShareApi;
import com.youpin.weex.app.common.ModuleUtils;
import com.youpin.weex.app.common.WXAppStoreApiManager;
import com.youpin.weex.app.module.share.IWXSocialShareAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class YPWXSocialShareAdapter implements IWXSocialShareAdapter {
    public void share(final Map<String, Object> map, final JSCallback jSCallback) throws JSONException {
        Activity f;
        if (map != null && (f = WXAppStoreApiManager.b().f()) != null) {
            WXAppStoreApiManager.b().c().a(f, map, (ICallback) new ICallback() {
                public void callback(Map map) {
                    if (map != null) {
                        try {
                            if (map.size() != 0) {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("channel", (Object) (String) map.get("channel"));
                                int intValue = ((Integer) map.get("result")).intValue();
                                jSONObject.put("result", (Object) Integer.valueOf(intValue));
                                if (intValue == 0) {
                                    ModuleUtils.a(jSONObject, jSCallback);
                                    return;
                                } else {
                                    ModuleUtils.c(jSONObject, jSCallback);
                                    return;
                                }
                            }
                        } catch (Exception unused) {
                            return;
                        }
                    }
                    ModuleUtils.b("not implement", jSCallback);
                }
            });
        }
    }

    public void shareCustom(Map<String, Object> map, final JSCallback jSCallback) throws JSONException {
        if (TextUtils.isEmpty((String) map.get("url"))) {
            ModuleUtils.b("failed", jSCallback);
            return;
        }
        YouPinShareApi.a(map, new YouPinShareApi.Callback() {
            public void a(int i, String str) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("result", (Object) Integer.valueOf(i));
                    jSONObject.put("channel", (Object) str);
                    if (i == 0) {
                        ModuleUtils.a(jSONObject, jSCallback);
                    } else {
                        ModuleUtils.c(jSONObject, jSCallback);
                    }
                } catch (Exception unused) {
                }
            }
        }, WXAppStoreApiManager.b().f());
    }

    public void getSupportShareList(final JSCallback jSCallback) {
        WXAppStoreApiManager.b().c().a((ICallback) new ICallback() {
            public void callback(Map map) {
                ModuleUtils.a(new JSONArray((List<Object>) new ArrayList((Set) map.get("result"))), jSCallback);
            }
        });
    }
}
