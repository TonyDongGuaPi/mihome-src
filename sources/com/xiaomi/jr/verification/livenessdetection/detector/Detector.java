package com.xiaomi.jr.verification.livenessdetection.detector;

import android.app.Activity;
import android.util.Log;

public abstract class Detector {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f1454a;
    protected Activity b;
    protected int c;
    protected int d;
    protected DetectionListener e;

    public abstract String a(String str);

    public abstract void a(long j);

    public abstract void a(DetectionType detectionType);

    public abstract boolean a();

    public abstract boolean a(byte[] bArr, int i, int i2);

    public abstract void b();

    public abstract byte[] c();

    public abstract byte[] d();

    public void a(DetectionListener detectionListener) {
        this.e = detectionListener;
    }

    public void a(int i, int i2) {
        this.c = i;
        this.d = i2;
    }

    public static void b(String str) {
        if (f1454a) {
            Log.d("TestDetect", str);
        }
    }
}
