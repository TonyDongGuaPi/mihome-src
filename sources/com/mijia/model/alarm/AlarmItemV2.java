package com.mijia.model.alarm;

import com.coloros.mcssdk.mode.CommandMessage;
import com.xiaomi.payment.data.MibiConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class AlarmItemV2 {

    /* renamed from: a  reason: collision with root package name */
    public long f7969a;
    public long b;
    public long c;
    public int d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public boolean l;

    public String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("duration", this.f7969a);
            jSONObject.put(MibiConstants.eP, this.b);
            jSONObject.put("createTime", this.c);
            jSONObject.put("offset", this.d);
            jSONObject.put("imgStoreId", this.e);
            jSONObject.put("imgStoreUrl", this.f);
            jSONObject.put("fileId", this.g);
            jSONObject.put("videoStoreId", this.h);
            jSONObject.put("desc", this.i);
            jSONObject.put(CommandMessage.TYPE_TAGS, this.j);
            jSONObject.put("title", this.k);
            jSONObject.put("isLocalCached", this.l);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    public AlarmItemV2() {
    }

    public AlarmItemV2(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f7969a = jSONObject.optLong("duration");
            this.b = jSONObject.optLong(MibiConstants.eP);
            this.c = jSONObject.optLong("createTime");
            this.d = jSONObject.optInt("offset");
            this.e = jSONObject.optString("imgStoreId");
            this.f = jSONObject.optString("imgStoreUrl");
            this.g = jSONObject.optString("fileId");
            this.h = jSONObject.optString("videoStoreId");
            this.i = jSONObject.optString("desc");
            this.j = jSONObject.optString(CommandMessage.TYPE_TAGS);
            this.k = jSONObject.optString("title");
            this.l = jSONObject.optBoolean("isLocalCached");
        } catch (JSONException unused) {
        }
    }
}
