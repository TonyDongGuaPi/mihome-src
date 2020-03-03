package org.reactnative.barcodedetector;

import android.content.Context;
import android.util.SparseArray;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import org.reactnative.camera.utils.ImageDimensions;
import org.reactnative.frame.RNFrame;

public class RNBarcodeDetector {

    /* renamed from: a  reason: collision with root package name */
    public static int f4120a = 0;
    public static int b = 1;
    public static int c = 2;
    public static int d;
    private BarcodeDetector e = null;
    private ImageDimensions f;
    private BarcodeDetector.Builder g;
    private int h = 0;

    public RNBarcodeDetector(Context context) {
        this.g = new BarcodeDetector.Builder(context).setBarcodeFormats(this.h);
    }

    public boolean a() {
        if (this.e == null) {
            d();
        }
        return this.e.isOperational();
    }

    public SparseArray<Barcode> a(RNFrame rNFrame) {
        if (!rNFrame.b().equals(this.f)) {
            c();
        }
        if (this.e == null) {
            d();
            this.f = rNFrame.b();
        }
        return this.e.detect(rNFrame.a());
    }

    public void a(int i) {
        if (i != this.h) {
            b();
            this.g.setBarcodeFormats(i);
            this.h = i;
        }
    }

    public void b() {
        c();
        this.f = null;
    }

    private void c() {
        if (this.e != null) {
            this.e.release();
            this.e = null;
        }
    }

    private void d() {
        this.e = this.g.build();
    }
}
