package com.youpin.weex.app.adapter;

import android.app.Activity;
import cn.com.fmsh.communication.contants.Contants;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amap.location.common.model.AmapLoc;
import com.taobao.weex.bridge.JSCallback;
import com.xiaomi.miot.store.api.ICallback;
import com.youpin.weex.app.common.ModuleUtils;
import com.youpin.weex.app.common.WXAppStoreApiManager;
import com.youpin.weex.app.module.payment.IWXPayAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class YPWXPayAdapter implements IWXPayAdapter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2489a = "alipay";
    private static final String b = "resultStatus";
    private static final String c = "memo";
    private static final String d = "wxpay";
    private static final String e = "pay_result_code";
    private static final String f = "pay_result_msg";

    public void pay(final String str, Map<String, Object> map, final JSCallback jSCallback) {
        Activity f2 = WXAppStoreApiManager.b().f();
        if (f2 != null) {
            WXAppStoreApiManager.b().c().a(f2, str, (String) map.get("info"), (ICallback) new ICallback() {
                public void callback(Map map) {
                    if (jSCallback != null) {
                        if (map == null) {
                            ModuleUtils.b("error", jSCallback);
                        } else if (str.equals("alipay")) {
                            String str = (String) map.get("resultStatus");
                            if (str.equals("6001")) {
                                ModuleUtils.a("cancel", (String) map.get("memo"), jSCallback);
                            } else if (str.equals(Contants.Message.PACKET_CODE_DEFAULT)) {
                                ModuleUtils.a(YPWXPayAdapter.this.a(map), jSCallback);
                            } else {
                                ModuleUtils.b((String) map.get("memo"), jSCallback);
                            }
                        } else if (str.equals(YPWXPayAdapter.d)) {
                            String str2 = (String) map.get(YPWXPayAdapter.e);
                            if (str2.equals(AmapLoc.q)) {
                                ModuleUtils.a("cancel", (String) map.get(YPWXPayAdapter.f), jSCallback);
                            } else if (str2.equals("-1")) {
                                ModuleUtils.b((String) map.get(YPWXPayAdapter.f), jSCallback);
                            } else {
                                ModuleUtils.a(YPWXPayAdapter.this.a(map), jSCallback);
                            }
                        }
                    }
                }
            });
        }
    }

    public void getSupportPayList(final JSCallback jSCallback) {
        if (jSCallback != null) {
            WXAppStoreApiManager.b().c().b(new ICallback() {
                public void callback(Map map) {
                    if (map != null) {
                        ModuleUtils.a(new JSONArray((List<Object>) new ArrayList((Set) map.get("result"))), jSCallback);
                    } else {
                        ModuleUtils.b("error", jSCallback);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public JSONObject a(Map map) {
        JSONObject jSONObject = new JSONObject();
        for (String str : map.keySet()) {
            jSONObject.put(str, (Object) (map.get(str) == null ? "" : map.get(str)).toString());
        }
        return jSONObject;
    }
}
