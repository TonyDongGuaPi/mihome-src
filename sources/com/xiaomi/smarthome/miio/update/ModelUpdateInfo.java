package com.xiaomi.smarthome.miio.update;

import android.text.TextUtils;
import com.mi.global.bbs.utils.Constants;
import com.xiaomi.smarthome.frame.ErrorCode;
import org.json.JSONObject;

public class ModelUpdateInfo {

    /* renamed from: a  reason: collision with root package name */
    public ModelUpdateState f19974a = ModelUpdateState.Idle;
    public String b;
    public int c;
    public String d;
    public boolean e;
    public String f;
    public String g;
    public boolean h;
    public String i;
    public int j;
    public String k;
    public ErrorCode l;
    public int m;
    public String n;
    public int o;
    public int p;

    public void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.e = jSONObject.optBoolean("updating");
            this.f = jSONObject.optString("curr");
            this.g = jSONObject.optString(Constants.PageFragment.PAGE_LATEST);
            this.i = jSONObject.optString("description");
            this.h = jSONObject.optBoolean("isLatest");
            this.j = jSONObject.optInt("ota_progress");
            this.k = jSONObject.optString("ota_status");
            this.m = jSONObject.optInt("ota_failed_code");
            this.n = jSONObject.optString("ota_failed_reason");
            this.o = jSONObject.optInt("timeout_time");
            this.p = jSONObject.optInt("force");
        }
    }

    public String a() {
        return "itemState" + this.f19974a + ",did:" + this.b + ",pid:" + this.c + ",updating:" + this.e + ",curr:" + this.f + ",latest:" + this.g + ",isLatest:" + this.h + ",desc:" + this.i + ",progress:" + this.j + ",status:" + this.k + ",errcode:" + this.l;
    }

    public String toString() {
        String str = this.i;
        if (!TextUtils.isEmpty(this.i) && this.i.length() > 6) {
            str = this.i.substring(5) + "...";
        }
        return this.f19974a + "," + this.b + "," + this.c + "," + this.e + "," + this.f + "," + this.g + "," + this.h + "," + str + "," + this.j + "," + this.k + "," + this.l;
    }
}
