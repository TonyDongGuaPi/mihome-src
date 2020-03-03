package com.mijia.model.alarm;

import android.text.TextUtils;
import com.coloros.mcssdk.mode.CommandMessage;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.payment.data.MibiConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AlarmItem implements Comparable<AlarmItem> {

    /* renamed from: a  reason: collision with root package name */
    public String f7968a;
    public long b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k = "";
    public boolean l;
    public long m;
    public long n;
    public long o;
    public int p;
    public String q;
    public String r;
    public String s;
    public String t;
    public String u;
    public String v;
    public boolean w;

    public String a() {
        if (TextUtils.isEmpty(this.h)) {
            return null;
        }
        if (TextUtils.isEmpty(this.i)) {
            return "fds://" + this.h;
        }
        return "fds://" + this.h + "?" + this.i;
    }

    public static AlarmItem a(JSONObject jSONObject) {
        JSONArray jSONArray;
        AlarmItem alarmItem = new AlarmItem();
        alarmItem.f7968a = jSONObject.optString("uid");
        alarmItem.b = jSONObject.optLong("time") * 1000;
        alarmItem.c = jSONObject.optString("did");
        alarmItem.d = jSONObject.optString("type");
        alarmItem.e = jSONObject.optString("key");
        try {
            jSONArray = new JSONArray(jSONObject.optString("value"));
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONArray = null;
        }
        if (jSONArray != null) {
            alarmItem.f = jSONArray.optString(0);
            alarmItem.g = jSONArray.optString(1);
            alarmItem.h = jSONArray.optString(2);
            alarmItem.i = jSONArray.optString(3);
            alarmItem.j = jSONArray.optString(4);
        }
        return alarmItem;
    }

    public static AlarmItem b(JSONObject jSONObject) {
        AlarmItem alarmItem = new AlarmItem();
        alarmItem.f = jSONObject.optString("0");
        alarmItem.g = jSONObject.optString("1");
        alarmItem.h = jSONObject.optString("2");
        alarmItem.i = jSONObject.optString("3");
        alarmItem.j = jSONObject.optString("4");
        return alarmItem;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof AlarmItem)) {
            return false;
        }
        AlarmItem alarmItem = (AlarmItem) obj;
        if (this.b != alarmItem.b || !this.c.equals(alarmItem.c) || !this.e.equals(alarmItem.e) || !this.d.equals(alarmItem.d)) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public int compareTo(AlarmItem alarmItem) {
        return (int) (this.b - alarmItem.b);
    }

    public String b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("duration", this.m);
            jSONObject.put(MibiConstants.eP, this.n);
            jSONObject.put("createTime", this.o);
            jSONObject.put("offset", this.p);
            jSONObject.put("imgStoreId", this.q);
            jSONObject.put("imgStoreUrl", this.r);
            jSONObject.put("fileId", this.s);
            jSONObject.put("videoStoreId", this.t);
            jSONObject.put("desc", this.u);
            jSONObject.put(CommandMessage.TYPE_TAGS, this.v);
            jSONObject.put("title", this.k);
            jSONObject.put("isLocalCached", this.w);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    public void a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.m = jSONObject.optLong("duration");
            this.n = jSONObject.optLong(MibiConstants.eP);
            this.o = jSONObject.optLong("createTime");
            this.p = jSONObject.optInt("offset");
            this.q = jSONObject.optString("imgStoreId");
            this.r = jSONObject.optString("imgStoreUrl");
            this.s = jSONObject.optString("fileId");
            this.t = jSONObject.optString("videoStoreId");
            this.u = jSONObject.optString("desc");
            this.v = jSONObject.optString(CommandMessage.TYPE_TAGS);
            this.k = jSONObject.optString("title");
            this.w = jSONObject.optBoolean("isLocalCached");
        } catch (JSONException unused) {
        }
    }

    public AlarmItem() {
    }

    public AlarmItem(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.m = jSONObject.optLong("duration");
            this.n = jSONObject.optLong(MibiConstants.eP);
            this.o = jSONObject.optLong("createTime");
            this.p = jSONObject.optInt("offset");
            this.q = jSONObject.optString("imgStoreId");
            this.r = jSONObject.optString("imgStoreUrl");
            this.s = jSONObject.optString("fileId");
            this.t = jSONObject.optString("videoStoreId");
            this.u = jSONObject.optString("desc");
            this.v = jSONObject.optString(CommandMessage.TYPE_TAGS);
            this.k = jSONObject.optString("title");
            this.w = jSONObject.optBoolean("isLocalCached");
        } catch (JSONException unused) {
        }
    }

    public String toString() {
        return "AlarmItem{uid='" + this.f7968a + Operators.SINGLE_QUOTE + ", time=" + this.b + ", did='" + this.c + Operators.SINGLE_QUOTE + ", type='" + this.d + Operators.SINGLE_QUOTE + ", key='" + this.e + Operators.SINGLE_QUOTE + ", videoObject='" + this.f + Operators.SINGLE_QUOTE + ", videoSecret='" + this.g + Operators.SINGLE_QUOTE + ", picObject='" + this.h + Operators.SINGLE_QUOTE + ", picSecret='" + this.i + Operators.SINGLE_QUOTE + ", triggle='" + this.j + Operators.SINGLE_QUOTE + ", title='" + this.k + Operators.SINGLE_QUOTE + ", isV2=" + this.l + ", duration=" + this.m + ", expireTime=" + this.n + ", createTime=" + this.o + ", offset=" + this.p + ", imgStoreId='" + this.q + Operators.SINGLE_QUOTE + ", imgStoreUrl='" + this.r + Operators.SINGLE_QUOTE + ", fileId='" + this.s + Operators.SINGLE_QUOTE + ", videoStoreId='" + this.t + Operators.SINGLE_QUOTE + ", desc='" + this.u + Operators.SINGLE_QUOTE + ", tags='" + this.v + Operators.SINGLE_QUOTE + ", isLocalCached=" + this.w + Operators.BLOCK_END;
    }
}
