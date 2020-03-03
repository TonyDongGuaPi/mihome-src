package com.xiaomi.youpin;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.xiaomi.plugin.AsyncCallback;
import com.xiaomi.plugin.Error;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.pojo.YingkebaoData;

public class RnMaskManager {
    private static RnMaskManager b;

    /* renamed from: a  reason: collision with root package name */
    OnRnMaskListener f23177a;

    public interface OnRnMaskListener {
        void a(String str);
    }

    private RnMaskManager() {
    }

    public static RnMaskManager a() {
        if (b == null) {
            b = new RnMaskManager();
        }
        return b;
    }

    public void a(OnRnMaskListener onRnMaskListener) {
        this.f23177a = onRnMaskListener;
    }

    public void b() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "homepage_toast");
        XmPluginHostApi.instance().sendYouPinNewRequest("POST", "/api/yingkebao/resource/get", jsonObject.toString(), false, false, new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                try {
                    YingkebaoData yingkebaoData = (YingkebaoData) new Gson().fromJson(str, YingkebaoData.class);
                    LogUtils.d("RnMaskManager", String.valueOf(str));
                    if (yingkebaoData != null && yingkebaoData.getResource() != null && !TextUtils.isEmpty(yingkebaoData.getResource().getPic_url()) && RnMaskManager.this.f23177a != null) {
                        RnMaskManager.this.f23177a.a("");
                    }
                } catch (Exception e) {
                    LogUtils.d("RnMaskManager", e.getMessage());
                }
            }

            public void onFailure(Error error) {
                LogUtils.e("RnMaskManager", error.toString());
            }
        });
    }
}
