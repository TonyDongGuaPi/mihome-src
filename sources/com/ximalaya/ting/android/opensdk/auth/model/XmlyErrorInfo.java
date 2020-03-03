package com.ximalaya.ting.android.opensdk.auth.model;

import android.os.Bundle;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import org.json.JSONException;
import org.json.JSONObject;

public class XmlyErrorInfo {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1836a = "error_no";
    public static final String b = "error_code";
    public static final String c = "error_desc";
    private String d = "";
    private String e = "";
    private String f = "";

    public String a() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public String b() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public String c() {
        return this.f;
    }

    public void c(String str) {
        this.f = str;
    }

    public static XmlyErrorInfo d(String str) {
        if (TextUtils.isEmpty(str) || str.indexOf(Operators.BLOCK_START_STR) < 0) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            XmlyErrorInfo xmlyErrorInfo = new XmlyErrorInfo();
            xmlyErrorInfo.a(jSONObject.optString("error_no"));
            xmlyErrorInfo.b(jSONObject.optString("error_code"));
            xmlyErrorInfo.c(jSONObject.optString("error_desc"));
            return xmlyErrorInfo;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public Bundle d() {
        Bundle bundle = new Bundle();
        bundle.putString("error_no", this.d);
        bundle.putString("error_code", this.e);
        bundle.putString("error_desc", this.f);
        return bundle;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r0 = r0.getString(r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(android.os.Bundle r0, java.lang.String r1, java.lang.String r2) {
        /*
            if (r0 == 0) goto L_0x000a
            java.lang.String r0 = r0.getString(r1)
            if (r0 == 0) goto L_0x0009
            return r0
        L_0x0009:
            return r2
        L_0x000a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.auth.model.XmlyErrorInfo.a(android.os.Bundle, java.lang.String, java.lang.String):java.lang.String");
    }
}
