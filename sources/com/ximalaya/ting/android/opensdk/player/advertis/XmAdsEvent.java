package com.ximalaya.ting.android.opensdk.player.advertis;

public class XmAdsEvent {

    /* renamed from: a  reason: collision with root package name */
    private int f2165a;
    private long b;
    private String c = "AD";
    private XmAdsRecord d;

    public XmAdsRecord a() {
        return this.d;
    }

    public void a(XmAdsRecord xmAdsRecord) {
        this.d = xmAdsRecord;
    }

    public int b() {
        return this.f2165a;
    }

    public void a(int i) {
        this.f2165a = i;
    }

    public long c() {
        return this.b;
    }

    public void a(long j) {
        this.b = j;
    }

    public String d() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }
}
