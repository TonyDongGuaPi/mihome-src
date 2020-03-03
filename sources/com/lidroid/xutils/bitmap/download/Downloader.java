package com.lidroid.xutils.bitmap.download;

import android.content.Context;
import com.lidroid.xutils.BitmapUtils;
import java.io.OutputStream;

public abstract class Downloader {

    /* renamed from: a  reason: collision with root package name */
    private Context f6303a;
    private long b;
    private int c;
    private int d;

    public abstract long a(String str, OutputStream outputStream, BitmapUtils.BitmapLoadTask<?> bitmapLoadTask);

    public Context a() {
        return this.f6303a;
    }

    public void a(Context context) {
        this.f6303a = context;
    }

    public void a(long j) {
        this.b = j;
    }

    public long b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public int d() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }
}
