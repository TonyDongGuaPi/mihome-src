package com.xiaomi.smarthome.framework.update.api.entity;

import com.mi.global.bbs.utils.Constants;
import com.taobao.weex.el.parse.Operators;
import org.json.JSONObject;

public class MiioUpdateInfo {

    /* renamed from: a  reason: collision with root package name */
    public boolean f17780a;
    public String b;
    public String c;
    public String d;
    public boolean e;
    public int f;
    public String g;
    public int h;
    public String i;
    public int j;
    public long k;
    public int l;

    public static MiioUpdateInfo a(JSONObject jSONObject) {
        MiioUpdateInfo miioUpdateInfo = new MiioUpdateInfo();
        miioUpdateInfo.f17780a = jSONObject.optBoolean("updating");
        miioUpdateInfo.b = jSONObject.optString("curr");
        miioUpdateInfo.c = jSONObject.optString(Constants.PageFragment.PAGE_LATEST);
        miioUpdateInfo.d = jSONObject.optString("description");
        miioUpdateInfo.e = jSONObject.optBoolean("isLatest");
        miioUpdateInfo.f = jSONObject.optInt("ota_progress");
        miioUpdateInfo.g = jSONObject.optString("ota_status");
        miioUpdateInfo.h = jSONObject.optInt("ota_failed_code");
        miioUpdateInfo.i = jSONObject.optString("ota_failed_reason");
        miioUpdateInfo.j = jSONObject.optInt("timeout_time");
        miioUpdateInfo.k = jSONObject.optLong("ota_start_time");
        miioUpdateInfo.l = jSONObject.optInt("force");
        return miioUpdateInfo;
    }

    public String toString() {
        return "MiioUpdateInfo{updating=" + this.f17780a + ", curr='" + this.b + Operators.SINGLE_QUOTE + ", latest='" + this.c + Operators.SINGLE_QUOTE + ", description='" + this.d + Operators.SINGLE_QUOTE + ", isLatest=" + this.e + ", ota_progress=" + this.f + ", ota_status='" + this.g + Operators.SINGLE_QUOTE + ", ota_failed_code=" + this.h + ", ota_failed_reason='" + this.i + Operators.SINGLE_QUOTE + ", timeout_time=" + this.j + ", ota_start_time=" + this.k + ", force=" + this.l + Operators.BLOCK_END;
    }
}
