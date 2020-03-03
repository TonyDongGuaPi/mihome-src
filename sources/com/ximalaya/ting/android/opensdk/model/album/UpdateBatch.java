package com.ximalaya.ting.android.opensdk.model.album;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;

public class UpdateBatch {
    @SerializedName("id")

    /* renamed from: a  reason: collision with root package name */
    private long f2032a;
    @SerializedName("last_up_track_id")
    private long b;
    @SerializedName("last_up_track_title")
    private String c;
    @SerializedName("last_up_track_cover_path")
    private String d;
    @SerializedName("last_up_track_at")
    private long e;

    public long a() {
        return this.f2032a;
    }

    public void a(long j) {
        this.f2032a = j;
    }

    public long b() {
        return this.b;
    }

    public void b(long j) {
        this.b = j;
    }

    public String c() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public String d() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public long e() {
        return this.e;
    }

    public void c(long j) {
        this.e = j;
    }

    public String toString() {
        return "UpdateBatch [albumId=" + this.f2032a + ", trackId=" + this.b + ", trackTitle=" + this.c + ", coverUrl=" + this.d + ", updateAt=" + this.e + Operators.ARRAY_END_STR;
    }
}
