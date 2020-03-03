package com.youpin.weex.app.adapter;

import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.bridge.JSCallback;
import com.xiaomi.miot.store.api.ICallback;
import com.xiaomi.youpin.youpin_common.UserAgent;
import com.youpin.weex.app.common.ModuleUtils;
import com.youpin.weex.app.common.WXAppStoreApiManager;
import com.youpin.weex.app.module.account.IAccountAdapter;
import java.util.Map;

public class YPAccountAdapter implements IAccountAdapter {

    /* renamed from: a  reason: collision with root package name */
    private WXAppStoreApiManager f2482a = WXAppStoreApiManager.b();

    public void appendUserAgent(String str) {
    }

    public void setUserAgent(String str) {
    }

    public void updateLoginInfo(String str, String str2, boolean z) {
        this.f2482a.c().a(this.f2482a.f(), str, str2, z);
    }

    public void openLoginPage() {
        this.f2482a.c().a(this.f2482a.f());
    }

    public void getUserInfo(final JSCallback jSCallback) {
        this.f2482a.c().c(new ICallback() {
            public void callback(Map map) {
                if (map == null || map.size() <= 0) {
                    ModuleUtils.b("not implement", jSCallback);
                } else {
                    ModuleUtils.a(new JSONObject((Map<String, Object>) map), jSCallback);
                }
            }
        });
    }

    public void getUserAgent(JSCallback jSCallback) {
        String d = UserAgent.d();
        if (TextUtils.isEmpty(d)) {
            ModuleUtils.b("not implement", jSCallback);
        } else {
            ModuleUtils.a(d, jSCallback);
        }
    }
}
