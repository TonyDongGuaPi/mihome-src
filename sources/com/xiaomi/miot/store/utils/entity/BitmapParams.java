package com.xiaomi.miot.store.utils.entity;

import android.content.Context;
import android.graphics.Bitmap;
import com.facebook.react.bridge.ReadableMap;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.viewshot.ViewShot;
import com.xiaomi.youpin.common.util.ConvertUtils;

public class BitmapParams {

    /* renamed from: a  reason: collision with root package name */
    private int f11426a;
    private int b;
    private int c;
    private int d;
    private int e;
    private String f;
    private String g;
    private String h;
    private String i;
    private boolean j = false;
    private boolean k = true;

    public BitmapParams(ReadableMap readableMap) {
        a(readableMap);
    }

    private void a(ReadableMap readableMap) {
        this.h = a(readableMap, "url", (String) null);
        this.i = a(readableMap, ViewShot.Results.BASE_64, (String) null);
        this.f = a(readableMap, "ext", "png");
        this.g = a(readableMap, "tolocalpath", (String) null);
        this.e = a(readableMap, Constants.Name.QUALITY, 80);
        this.f11426a = a(readableMap, "x", -1);
        this.b = a(readableMap, Constants.Name.Y, -1);
        this.c = a(readableMap, "width", -1);
        this.d = a(readableMap, "height", -1);
        boolean z = true;
        this.j = readableMap.hasKey("toUserAlbum") && readableMap.getBoolean("toUserAlbum");
        if (!(this.f11426a == -1 && this.b == -1 && this.c == -1 && this.d == -1)) {
            z = false;
        }
        this.k = z;
    }

    public void a(Bitmap bitmap) {
        if (!this.k) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (this.f11426a < 0 || this.f11426a >= width) {
                this.f11426a = 0;
            }
            if (this.c + this.f11426a > width || this.c == -1) {
                this.c = width - this.f11426a;
            }
            if (this.b < 0 || this.b >= height) {
                this.b = 0;
            }
            if (this.d + this.b > height || this.d == -1) {
                this.d = height - this.b;
            }
        }
    }

    private int a(Context context, int i2) {
        return ConvertUtils.a((float) i2);
    }

    private int a(ReadableMap readableMap, String str, int i2) {
        return readableMap.hasKey(str) ? readableMap.getInt(str) : i2;
    }

    private String a(ReadableMap readableMap, String str, String str2) {
        return readableMap.hasKey(str) ? readableMap.getString(str) : str2;
    }

    public boolean a() {
        return this.k;
    }

    public void a(boolean z) {
        this.k = z;
    }

    public int b() {
        return this.f11426a;
    }

    public void a(int i2) {
        this.f11426a = i2;
    }

    public int c() {
        return this.b;
    }

    public void b(int i2) {
        this.b = i2;
    }

    public int d() {
        return this.c;
    }

    public void c(int i2) {
        this.c = i2;
    }

    public int e() {
        return this.d;
    }

    public void d(int i2) {
        this.d = i2;
    }

    public String f() {
        return this.f == null ? "" : this.f;
    }

    public void a(String str) {
        this.f = str;
    }

    public String g() {
        return this.g == null ? "" : this.g;
    }

    public void b(String str) {
        this.g = str;
    }

    public String h() {
        return this.h == null ? "" : this.h;
    }

    public void c(String str) {
        this.h = str;
    }

    public String i() {
        return this.i == null ? "" : this.i;
    }

    public void d(String str) {
        this.i = str;
    }

    public int j() {
        return this.e;
    }

    public void e(int i2) {
        this.e = i2;
    }

    public boolean k() {
        return this.j;
    }

    public void b(boolean z) {
        this.j = z;
    }
}
