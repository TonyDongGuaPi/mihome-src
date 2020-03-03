package com.youpin.weex.app.adapter;

import android.net.Uri;
import android.text.TextUtils;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.bridge.JSCallback;
import com.youpin.weex.app.common.ModuleUtils;
import com.youpin.weex.app.common.WeexCacheManager;
import com.youpin.weex.app.module.bundlemanager.IWXBundleManagerAdapter;

public class YPWXBundleManagerAdapter implements IWXBundleManagerAdapter {
    public void precacheURL(final String str, boolean z, final JSCallback jSCallback) {
        if (TextUtils.isEmpty(str) || !str.contains("_rt=weex")) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("message", (Object) "地址无效：" + str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ModuleUtils.c(jSONObject, jSCallback);
            return;
        }
        WeexCacheManager.a().a(Uri.parse(str), z, (WeexCacheManager.LoadCallBackListener) new WeexCacheManager.LoadCallBackListener() {
            public void a() {
            }

            public void a(String str, String str2, String str3, String str4, String str5, String str6, boolean z) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("originResponse", (Object) str6);
                    jSONObject.put("bundleLocalPath", (Object) str2);
                    jSONObject.put("bundlrUrl", (Object) str4);
                    jSONObject.put("originUrl", (Object) str);
                    jSONObject.put("name", (Object) str);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ModuleUtils.a(jSONObject, jSCallback);
            }

            public void a(String str) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("message", (Object) "预加载失败:" + str);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ModuleUtils.c(jSONObject, jSCallback);
            }
        });
    }
}
