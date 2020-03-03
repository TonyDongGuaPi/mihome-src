package com.ximalaya.ting.android.player.cdn;

import com.taobao.weex.el.parse.Operators;

public class CdnEvent {

    /* renamed from: a  reason: collision with root package name */
    private String f2299a;
    private String b;
    private int c;
    private String d;
    private String e;
    private long f;
    private String g;
    private CdnCollectDataForPlay h;

    public String a() {
        return this.f2299a;
    }

    public void a(String str) {
        this.f2299a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public int c() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public String d() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public String e() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }

    public long f() {
        return this.f;
    }

    public void a(long j) {
        this.f = j;
    }

    public String g() {
        return this.g;
    }

    public void e(String str) {
        this.g = str;
    }

    public CdnCollectDataForPlay h() {
        return this.h;
    }

    public void a(CdnCollectDataForPlay cdnCollectDataForPlay) {
        this.h = cdnCollectDataForPlay;
    }

    public String toString() {
        return super.toString() + "CdnEvent [props=" + this.h + Operators.ARRAY_END_STR;
    }
}
