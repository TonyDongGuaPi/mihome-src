package com.reactnative.camera.tasks;

import android.os.AsyncTask;
import android.util.SparseArray;
import com.google.android.gms.vision.face.Face;
import com.reactnative.camera.facedetector.RNFaceDetector;
import com.reactnative.camera.facedetector.RNFrameFactory;

public class FaceDetectorAsyncTask extends AsyncTask<Void, Void, SparseArray<Face>> {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f8680a;
    private int b;
    private int c;
    private int d;
    private RNFaceDetector e;
    private FaceDetectorAsyncTaskDelegate f;

    public FaceDetectorAsyncTask(FaceDetectorAsyncTaskDelegate faceDetectorAsyncTaskDelegate, RNFaceDetector rNFaceDetector, byte[] bArr, int i, int i2, int i3) {
        this.f8680a = bArr;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.f = faceDetectorAsyncTaskDelegate;
        this.e = rNFaceDetector;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public SparseArray<Face> doInBackground(Void... voidArr) {
        if (isCancelled() || this.f == null || this.e == null || !this.e.a()) {
            return null;
        }
        return this.e.a(RNFrameFactory.a(this.f8680a, this.b, this.c, this.d));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(SparseArray<Face> sparseArray) {
        super.onPostExecute(sparseArray);
        if (sparseArray == null) {
            this.f.onFaceDetectionError(this.e);
            return;
        }
        this.f.onFacesDetected(sparseArray, this.b, this.c, this.d);
        this.f.onFaceDetectingTaskCompleted();
    }
}
