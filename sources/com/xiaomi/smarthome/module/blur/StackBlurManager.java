package com.xiaomi.smarthome.module.blur;

import android.graphics.Bitmap;
import java.io.FileOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StackBlurManager {

    /* renamed from: a  reason: collision with root package name */
    static boolean f20154a;
    static final int b = Runtime.getRuntime().availableProcessors();
    static final ExecutorService c = Executors.newFixedThreadPool(b);
    private static volatile boolean d = true;
    private Bitmap e;
    private Bitmap f;
    private final BlurProcess g;

    static {
        try {
            System.loadLibrary("native-lib");
            f20154a = true;
        } catch (Throwable unused) {
            f20154a = false;
        }
        f20154a = true;
    }

    public StackBlurManager(Bitmap bitmap) {
        this.e = bitmap;
        if (f20154a) {
            this.g = new NativeBlurProcess();
        } else {
            this.g = new JavaBlurProcess();
        }
    }

    public StackBlurManager() {
        if (f20154a) {
            this.g = new NativeBlurProcess();
        } else {
            this.g = new JavaBlurProcess();
        }
    }

    public Bitmap a(int i) {
        this.f = this.g.a(this.e, (float) i);
        return this.f;
    }

    public Bitmap a(Bitmap bitmap, int i) {
        this.f = this.g.a(bitmap, (float) i);
        return this.f;
    }

    public Bitmap a() {
        return this.f;
    }

    public void a(String str) {
        try {
            this.f.compress(Bitmap.CompressFormat.PNG, 90, new FileOutputStream(str));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public Bitmap b() {
        return this.e;
    }

    public Bitmap b(int i) {
        this.f = new NativeBlurProcess().a(this.e, (float) i);
        return this.f;
    }
}
