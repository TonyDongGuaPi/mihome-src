package com.xiaomi.miio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MiioLocalRpcResult {

    /* renamed from: a  reason: collision with root package name */
    public MiioLocalErrorCode f11150a;
    public String b;
    public long c;
    public int d;
    public String e;
    public String f;

    public MiioLocalRpcResult(MiioLocalErrorCode miioLocalErrorCode) {
        this.f11150a = miioLocalErrorCode;
    }

    public MiioLocalRpcResult(MiioLocalErrorCode miioLocalErrorCode, String str, long j, int i, String str2, String str3) {
        this.f11150a = miioLocalErrorCode;
        this.b = str;
        this.c = j;
        this.d = i;
        this.e = str2;
        this.f = str3;
    }

    public String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", this.f11150a.getCode());
            jSONObject.put("message", this.f11150a.getMessage());
            if (this.f11150a != MiioLocalErrorCode.SUCCESS) {
                return jSONObject.toString();
            }
            JSONObject jSONObject2 = new JSONObject(this.b);
            try {
                if (jSONObject2.getInt("result") == 0) {
                    return jSONObject.toString();
                }
            } catch (JSONException unused) {
            }
            JSONObject optJSONObject = jSONObject2.optJSONObject("result");
            JSONArray jSONArray = null;
            if (optJSONObject == null) {
                jSONArray = jSONObject2.optJSONArray("result");
            }
            int i = jSONObject2.getInt("id");
            if (optJSONObject != null) {
                jSONObject.put("result", optJSONObject);
                jSONObject.put("id", i);
                return jSONObject.toString();
            } else if (jSONArray == null) {
                return jSONObject2.toString();
            } else {
                jSONObject.put("result", jSONArray);
                jSONObject.put("id", i);
                return jSONObject.toString();
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            return jSONObject.toString();
        }
    }
}
