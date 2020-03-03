package com.sina.weibo.sdk.web;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class WebPicUploadResult {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8852a = "code";
    public static final String b = "data";
    public static final int c = 1;
    private int d = -2;
    private String e;

    private WebPicUploadResult() {
    }

    public int a() {
        return this.d;
    }

    public String b() {
        return this.e;
    }

    public static WebPicUploadResult a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        WebPicUploadResult webPicUploadResult = new WebPicUploadResult();
        try {
            JSONObject jSONObject = new JSONObject(str);
            webPicUploadResult.d = jSONObject.optInt("code", -2);
            webPicUploadResult.e = jSONObject.optString("data", "");
        } catch (JSONException unused) {
        }
        return webPicUploadResult;
    }
}
