package com.ximalaya.ting.android.opensdk.model.app;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;

public class AppModel extends XimalayaResponse {
    @SerializedName("id")

    /* renamed from: a  reason: collision with root package name */
    private long f2039a;
    @SerializedName("release_type")
    private int b;
    @SerializedName("sdk_or_app_name")
    private String c;
    @SerializedName("pack_id")
    private String d;
    @SerializedName("client_os_type")
    private int e;
    @SerializedName("version_code")
    private int f;
    @SerializedName("version_name")
    private String g;
    @SerializedName("release_info")
    private String h;
    @SerializedName("download_url")
    private String i;
    @SerializedName("updated_at")
    private long j;
    @SerializedName("created_at")
    private long k;
    @SerializedName("file_name")
    private String l;

    public long a() {
        return this.f2039a;
    }

    public void a(long j2) {
        this.f2039a = j2;
    }

    public int b() {
        return this.b;
    }

    public void a(int i2) {
        this.b = i2;
    }

    public String c() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public String d() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public int e() {
        return this.e;
    }

    public void b(int i2) {
        this.e = i2;
    }

    public int f() {
        return this.f;
    }

    public void c(int i2) {
        this.f = i2;
    }

    public String g() {
        return this.g;
    }

    public void c(String str) {
        this.g = str;
    }

    public String h() {
        return this.h;
    }

    public void d(String str) {
        this.h = str;
    }

    public String i() {
        return this.i;
    }

    public void e(String str) {
        this.i = str;
    }

    public long j() {
        return this.j;
    }

    public void b(long j2) {
        this.j = j2;
    }

    public long k() {
        return this.k;
    }

    public void c(long j2) {
        this.k = j2;
    }

    public String l() {
        return this.l;
    }

    public void f(String str) {
        this.l = str;
    }

    public String toString() {
        return "AppModel [dataId=" + this.f2039a + ", releaseType=" + this.b + ", name=" + this.c + ", packId=" + this.d + ", osType=" + this.e + ", versionCode=" + this.f + ", versionName=" + this.g + ", releaseInfo=" + this.h + ", downloadUrl=" + this.i + ", updatedAt=" + this.j + ", createdAt=" + this.k + ", filename=" + this.l + Operators.ARRAY_END_STR;
    }
}
