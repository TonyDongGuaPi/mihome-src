package com.ximalaya.ting.android.opensdk.model.banner;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;

public class VehicleBanners extends XimalayaResponse {
    @SerializedName("id")

    /* renamed from: a  reason: collision with root package name */
    private long f2047a;
    @SerializedName("banner_title")
    private String b;
    @SerializedName("banner_url")
    private String c;
    @SerializedName("track_id")
    private String d;

    public long a() {
        return this.f2047a;
    }

    public void a(long j) {
        this.f2047a = j;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public String d() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public String toString() {
        return "VehicleBanners [bannerId=" + this.f2047a + ", bannerTitle=" + this.b + ", bannerUrl=" + this.c + ", trackId=" + this.d + Operators.ARRAY_END_STR;
    }
}
