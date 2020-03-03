package com.ximalaya.ting.android.sdkdownloader.model;

import com.ximalaya.ting.android.opensdk.model.track.Track;

public class XmDownloadAlbum {

    /* renamed from: a  reason: collision with root package name */
    private long f2365a;
    private String b;
    private String c;
    private String d;
    private String e;
    private int f;
    private long g;

    public long a() {
        return this.f2365a;
    }

    public void a(long j) {
        this.f2365a = j;
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

    public String e() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }

    public int f() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
    }

    public long g() {
        return this.g;
    }

    public void b(long j) {
        this.g = j;
    }

    public void h() {
        this.f++;
    }

    public static XmDownloadAlbum a(Track track) {
        if (track == null || track.ao() == null) {
            return null;
        }
        XmDownloadAlbum xmDownloadAlbum = new XmDownloadAlbum();
        xmDownloadAlbum.b(track.al());
        xmDownloadAlbum.a(1);
        xmDownloadAlbum.a(track.ao().d());
        xmDownloadAlbum.d(track.ao().h());
        xmDownloadAlbum.c(track.ao().g());
        xmDownloadAlbum.b(track.ao().f());
        xmDownloadAlbum.a(track.ao().e());
        return xmDownloadAlbum;
    }
}
