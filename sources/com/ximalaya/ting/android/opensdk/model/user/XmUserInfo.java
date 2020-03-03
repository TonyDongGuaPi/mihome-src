package com.ximalaya.ting.android.opensdk.model.user;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class XmUserInfo extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private int f2131a;
    private String b;
    private int c;
    @SerializedName("birth_year")
    private String d;
    @SerializedName("birth_month")
    private String e;
    @SerializedName("birth_day")
    private String f;
    @SerializedName("interested_album_ids")
    private List<Long> g;
    @SerializedName("interested_category_ids")
    private List<Integer> h;
    @SerializedName("login_historys")
    private List<LoginHistory> i;
    @SerializedName("last_play_at")
    private long j;

    public int a() {
        return this.f2131a;
    }

    public void a(int i2) {
        this.f2131a = i2;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public int c() {
        return this.c;
    }

    public void b(int i2) {
        this.c = i2;
    }

    public String d() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public String e() {
        return this.e;
    }

    public void c(String str) {
        this.e = str;
    }

    public String f() {
        return this.f;
    }

    public void d(String str) {
        this.f = str;
    }

    public List<Long> g() {
        return this.g;
    }

    public void a(List<Long> list) {
        this.g = list;
    }

    public List<Integer> h() {
        return this.h;
    }

    public void b(List<Integer> list) {
        this.h = list;
    }

    public List<LoginHistory> i() {
        return this.i;
    }

    public void c(List<LoginHistory> list) {
        this.i = list;
    }

    public long j() {
        return this.j;
    }

    public void a(long j2) {
        this.j = j2;
    }
}
