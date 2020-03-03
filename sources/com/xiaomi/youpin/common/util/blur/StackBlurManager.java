package com.xiaomi.youpin.common.util.blur;

import android.content.Context;
import android.graphics.Bitmap;
import java.io.FileOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StackBlurManager {

    /* renamed from: a  reason: collision with root package name */
    static final int f23291a = Runtime.getRuntime().availableProcessors();
    static final ExecutorService b = Executors.newFixedThreadPool(f23291a);
    private static volatile boolean c = true;
    private final Bitmap d;
    private final BlurProcess e = new JavaBlurProcess();
    private Bitmap f;

    public Bitmap a(Context context, float f2) {
        return null;
    }

    public StackBlurManager(Bitmap bitmap) {
        this.d = bitmap;
    }

    public Bitmap a(int i) {
        this.f = this.e.a(this.d, (float) i);
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
        return this.d;
    }
}
