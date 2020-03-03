package org.reactnative.camera.tasks;

import android.graphics.Rect;
import android.os.AsyncTask;
import android.util.SparseArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.vision.barcode.Barcode;
import com.taobao.weex.common.Constants;
import org.reactnative.barcodedetector.BarcodeFormatUtils;
import org.reactnative.barcodedetector.RNBarcodeDetector;
import org.reactnative.camera.utils.ImageDimensions;
import org.reactnative.frame.RNFrameFactory;

public class BarcodeDetectorAsyncTask extends AsyncTask<Void, Void, SparseArray<Barcode>> {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f4157a;
    private int b;
    private int c;
    private int d;
    private RNBarcodeDetector e;
    private BarcodeDetectorAsyncTaskDelegate f;
    private double g;
    private double h;
    private ImageDimensions i;
    private int j;
    private int k;

    public BarcodeDetectorAsyncTask(BarcodeDetectorAsyncTaskDelegate barcodeDetectorAsyncTaskDelegate, RNBarcodeDetector rNBarcodeDetector, byte[] bArr, int i2, int i3, int i4, float f2, int i5, int i6, int i7, int i8, int i9) {
        this.f4157a = bArr;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.f = barcodeDetectorAsyncTaskDelegate;
        this.e = rNBarcodeDetector;
        this.i = new ImageDimensions(i2, i3, i4, i5);
        double d2 = (double) i6;
        double b2 = (double) (((float) this.i.b()) * f2);
        Double.isNaN(d2);
        Double.isNaN(b2);
        this.g = d2 / b2;
        double d3 = (double) i7;
        double c2 = (double) (((float) this.i.c()) * f2);
        Double.isNaN(d3);
        Double.isNaN(c2);
        this.h = d3 / c2;
        this.j = i8;
        this.k = i9;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public SparseArray<Barcode> doInBackground(Void... voidArr) {
        if (isCancelled() || this.f == null || this.e == null || !this.e.a()) {
            return null;
        }
        return this.e.a(RNFrameFactory.a(this.f4157a, this.b, this.c, this.d));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(SparseArray<Barcode> sparseArray) {
        super.onPostExecute(sparseArray);
        if (sparseArray == null) {
            this.f.onBarcodeDetectionError(this.e);
            return;
        }
        if (sparseArray.size() > 0) {
            this.f.onBarcodesDetected(b(sparseArray));
        }
        this.f.onBarcodeDetectingTaskCompleted();
    }

    private WritableArray b(SparseArray<Barcode> sparseArray) {
        WritableArray createArray = Arguments.createArray();
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            Barcode valueAt = sparseArray.valueAt(i2);
            WritableMap createMap = Arguments.createMap();
            createMap.putString("data", valueAt.displayValue);
            createMap.putString("rawData", valueAt.rawValue);
            createMap.putString("type", BarcodeFormatUtils.a(valueAt.format));
            createMap.putMap("bounds", a(valueAt.getBoundingBox()));
            createArray.pushMap(createMap);
        }
        return createArray;
    }

    private WritableMap a(Rect rect) {
        WritableMap createMap = Arguments.createMap();
        int i2 = rect.left;
        int i3 = rect.top;
        if (rect.left < this.b / 2) {
            i2 += this.j / 2;
        } else if (rect.left > this.b / 2) {
            i2 -= this.j / 2;
        }
        if (rect.top < this.c / 2) {
            i3 += this.k / 2;
        } else if (rect.top > this.c / 2) {
            i3 -= this.k / 2;
        }
        double d2 = (double) i2;
        double d3 = this.g;
        Double.isNaN(d2);
        createMap.putDouble("x", d2 * d3);
        double d4 = (double) i3;
        double d5 = this.h;
        Double.isNaN(d4);
        createMap.putDouble(Constants.Name.Y, d4 * d5);
        WritableMap createMap2 = Arguments.createMap();
        double width = (double) rect.width();
        double d6 = this.g;
        Double.isNaN(width);
        createMap2.putDouble("width", width * d6);
        double height = (double) rect.height();
        double d7 = this.h;
        Double.isNaN(height);
        createMap2.putDouble("height", height * d7);
        WritableMap createMap3 = Arguments.createMap();
        createMap3.putMap("origin", createMap);
        createMap3.putMap("size", createMap2);
        return createMap3;
    }
}
