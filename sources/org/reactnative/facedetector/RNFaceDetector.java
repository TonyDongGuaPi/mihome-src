package org.reactnative.facedetector;

import android.content.Context;
import android.util.SparseArray;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import org.reactnative.camera.utils.ImageDimensions;
import org.reactnative.frame.RNFrame;

public class RNFaceDetector {

    /* renamed from: a  reason: collision with root package name */
    public static int f4165a = 1;
    public static int b = 0;
    public static int c = 1;
    public static int d = 0;
    public static int e = 1;
    public static int f;
    private FaceDetector g = null;
    private ImageDimensions h;
    private FaceDetector.Builder i = null;
    private int j = b;
    private int k = d;
    private float l = 0.15f;
    private int m = f;

    public RNFaceDetector(Context context) {
        this.i = new FaceDetector.Builder(context);
        this.i.setMinFaceSize(this.l);
        this.i.setMode(this.m);
        this.i.setLandmarkType(this.k);
        this.i.setClassificationType(this.j);
    }

    public boolean a() {
        if (this.g == null) {
            d();
        }
        return this.g.isOperational();
    }

    public SparseArray<Face> a(RNFrame rNFrame) {
        if (!rNFrame.b().equals(this.h)) {
            c();
        }
        if (this.g == null) {
            d();
            this.h = rNFrame.b();
        }
        return this.g.detect(rNFrame.a());
    }

    public void a(boolean z) {
        b();
        this.i.setTrackingEnabled(z);
    }

    public void a(int i2) {
        if (i2 != this.j) {
            b();
            this.i.setClassificationType(i2);
            this.j = i2;
        }
    }

    public void b(int i2) {
        if (i2 != this.k) {
            b();
            this.i.setLandmarkType(i2);
            this.k = i2;
        }
    }

    public void c(int i2) {
        if (i2 != this.m) {
            b();
            this.i.setMode(i2);
            this.m = i2;
        }
    }

    public void b() {
        c();
        this.h = null;
    }

    private void c() {
        if (this.g != null) {
            this.g.release();
            this.g = null;
        }
    }

    private void d() {
        this.g = this.i.build();
    }
}
