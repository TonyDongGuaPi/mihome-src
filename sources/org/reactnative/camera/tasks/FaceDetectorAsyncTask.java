package org.reactnative.camera.tasks;

import android.os.AsyncTask;
import android.util.SparseArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.vision.face.Face;
import org.reactnative.camera.utils.ImageDimensions;
import org.reactnative.facedetector.FaceDetectorUtils;
import org.reactnative.facedetector.RNFaceDetector;
import org.reactnative.frame.RNFrameFactory;

public class FaceDetectorAsyncTask extends AsyncTask<Void, Void, SparseArray<Face>> {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f4158a;
    private int b;
    private int c;
    private int d;
    private RNFaceDetector e;
    private FaceDetectorAsyncTaskDelegate f;
    private ImageDimensions g;
    private double h;
    private double i;
    private int j;
    private int k;

    public FaceDetectorAsyncTask(FaceDetectorAsyncTaskDelegate faceDetectorAsyncTaskDelegate, RNFaceDetector rNFaceDetector, byte[] bArr, int i2, int i3, int i4, float f2, int i5, int i6, int i7, int i8, int i9) {
        this.f4158a = bArr;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.f = faceDetectorAsyncTaskDelegate;
        this.e = rNFaceDetector;
        this.g = new ImageDimensions(i2, i3, i4, i5);
        double d2 = (double) i6;
        double b2 = (double) (((float) this.g.b()) * f2);
        Double.isNaN(d2);
        Double.isNaN(b2);
        this.h = d2 / b2;
        double d3 = (double) i7;
        double c2 = (double) (((float) this.g.c()) * f2);
        Double.isNaN(d3);
        Double.isNaN(c2);
        this.i = d3 / c2;
        this.j = i8;
        this.k = i9;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public SparseArray<Face> doInBackground(Void... voidArr) {
        if (isCancelled() || this.f == null || this.e == null || !this.e.a()) {
            return null;
        }
        return this.e.a(RNFrameFactory.a(this.f4158a, this.b, this.c, this.d));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(SparseArray<Face> sparseArray) {
        super.onPostExecute(sparseArray);
        if (sparseArray == null) {
            this.f.onFaceDetectionError(this.e);
            return;
        }
        if (sparseArray.size() > 0) {
            this.f.onFacesDetected(b(sparseArray));
        }
        this.f.onFaceDetectingTaskCompleted();
    }

    private WritableArray b(SparseArray<Face> sparseArray) {
        WritableMap writableMap;
        WritableArray createArray = Arguments.createArray();
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            WritableMap a2 = FaceDetectorUtils.a(sparseArray.valueAt(i2), this.h, this.i, this.b, this.c, this.j, this.k);
            if (this.g.e() == 1) {
                writableMap = FaceDetectorUtils.a(a2, this.g.b(), this.h);
            } else {
                writableMap = FaceDetectorUtils.a(a2);
            }
            createArray.pushMap(writableMap);
        }
        return createArray;
    }
}
