package com.xiaomi.smarthome.fastvideo;

import android.graphics.Bitmap;

public class Photo {

    /* renamed from: a  reason: collision with root package name */
    private int f15886a = -1;
    private int b;
    private int c;

    public static Photo a(Bitmap bitmap) {
        if (bitmap != null) {
            return new Photo(RendererUtils.a(bitmap), bitmap.getWidth(), bitmap.getHeight());
        }
        return null;
    }

    public static Photo a(int i, int i2) {
        return new Photo(RendererUtils.a(), i, i2);
    }

    public Photo(int i, int i2, int i3) {
        this.f15886a = i;
        this.b = i2;
        this.c = i3;
    }

    public void b(Bitmap bitmap) {
        this.f15886a = RendererUtils.a(this.f15886a, bitmap);
        this.b = bitmap.getWidth();
        this.c = bitmap.getHeight();
    }

    public int a() {
        return this.f15886a;
    }

    public void a(int i) {
        RendererUtils.a(this.f15886a);
        this.f15886a = i;
    }

    public boolean a(Photo photo) {
        return photo.b == this.b && photo.c == this.c;
    }

    public void b(int i, int i2) {
        this.b = i;
        this.c = i2;
        RendererUtils.a(this.f15886a);
        this.f15886a = RendererUtils.a();
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public Bitmap d() {
        return RendererUtils.a(this.f15886a, this.b, this.c);
    }

    public void e() {
        RendererUtils.a(this.f15886a);
        this.f15886a = -1;
    }

    public void c(int i, int i2) {
        this.b = i;
        this.c = i2;
    }

    public void b(Photo photo) {
        int i = this.f15886a;
        this.f15886a = photo.f15886a;
        photo.f15886a = i;
    }
}
