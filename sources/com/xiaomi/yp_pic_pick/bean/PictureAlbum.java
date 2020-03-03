package com.xiaomi.yp_pic_pick.bean;

import android.net.Uri;
import java.util.ArrayList;

public class PictureAlbum {

    /* renamed from: a  reason: collision with root package name */
    private String f19522a;
    private String b;
    private Uri c;
    private boolean d;
    private ArrayList<PictureItem> e = new ArrayList<>();

    public ArrayList<PictureItem> a() {
        return this.e;
    }

    public void a(ArrayList<PictureItem> arrayList) {
        this.e = arrayList;
    }

    public void a(PictureItem pictureItem) {
        this.e.add(pictureItem);
    }

    public String b() {
        return this.f19522a == null ? "" : this.f19522a;
    }

    public void a(String str) {
        this.f19522a = str;
    }

    public String c() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public boolean d() {
        return this.d;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public Uri e() {
        return this.c;
    }

    public void a(Uri uri) {
        this.c = uri;
    }
}
