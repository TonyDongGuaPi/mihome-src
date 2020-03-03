package com.reactnative.camera.facedetector.tasks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.util.SparseArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.vision.face.Face;
import com.reactnative.camera.facedetector.FaceDetectorUtils;
import com.reactnative.camera.facedetector.RNFaceDetector;
import com.reactnative.camera.facedetector.RNFrameFactory;
import java.io.File;
import java.io.IOException;

public class FileFaceDetectionAsyncTask extends AsyncTask<Void, Void, SparseArray<Face>> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8678a = "E_FACE_DETECTION_FAILED";
    private static final String b = "mode";
    private static final String c = "detectLandmarks";
    private static final String d = "runClassifications";
    private String e;
    private String f;
    private Promise g;
    private int h = 0;
    private int i = 0;
    private Context j;
    private ReadableMap k;
    private int l = 0;
    private RNFaceDetector m;

    public FileFaceDetectionAsyncTask(Context context, ReadableMap readableMap, Promise promise) {
        this.e = readableMap.getString("uri");
        this.g = promise;
        this.k = readableMap;
        this.j = context;
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        if (this.e == null) {
            this.g.reject(f8678a, "You have to provide an URI of an image.");
            cancel(true);
            return;
        }
        this.f = Uri.parse(this.e).getPath();
        if (this.f == null) {
            Promise promise = this.g;
            promise.reject(f8678a, "Invalid URI provided: `" + this.e + "`.");
            cancel(true);
            return;
        }
        if (!(this.f.startsWith(this.j.getCacheDir().getPath()) || this.f.startsWith(this.j.getFilesDir().getPath()))) {
            this.g.reject(f8678a, "The image has to be in the local app's directories.");
            cancel(true);
        } else if (!new File(this.f).exists()) {
            Promise promise2 = this.g;
            promise2.reject(f8678a, "The file does not exist. Given path: `" + this.f + "`.");
            cancel(true);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public SparseArray<Face> doInBackground(Void... voidArr) {
        if (isCancelled()) {
            return null;
        }
        this.m = a(this.k, this.j);
        Bitmap decodeFile = BitmapFactory.decodeFile(this.f);
        this.h = decodeFile.getWidth();
        this.i = decodeFile.getHeight();
        try {
            this.l = new ExifInterface(this.f).getAttributeInt(android.support.media.ExifInterface.TAG_ORIENTATION, 0);
        } catch (IOException e2) {
            Log.e(f8678a, "Reading orientation from file `" + this.f + "` failed.", e2);
        }
        return this.m.a(RNFrameFactory.a(decodeFile));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(SparseArray<Face> sparseArray) {
        super.onPostExecute(sparseArray);
        WritableMap createMap = Arguments.createMap();
        WritableArray createArray = Arguments.createArray();
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            WritableMap a2 = FaceDetectorUtils.a(sparseArray.valueAt(i2));
            a2.putDouble("yawAngle", ((-a2.getDouble("yawAngle")) + 360.0d) % 360.0d);
            a2.putDouble("rollAngle", ((-a2.getDouble("rollAngle")) + 360.0d) % 360.0d);
            createArray.pushMap(a2);
        }
        createMap.putArray("faces", createArray);
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putInt("width", this.h);
        createMap2.putInt("height", this.i);
        createMap2.putInt("orientation", this.l);
        createMap2.putString("uri", this.e);
        createMap.putMap("image", createMap2);
        this.m.b();
        this.g.resolve(createMap);
    }

    private static RNFaceDetector a(ReadableMap readableMap, Context context) {
        RNFaceDetector rNFaceDetector = new RNFaceDetector(context);
        rNFaceDetector.b(false);
        if (readableMap.hasKey("mode")) {
            rNFaceDetector.c(readableMap.getInt("mode"));
        }
        if (readableMap.hasKey(d)) {
            rNFaceDetector.a(readableMap.getInt(d));
        }
        if (readableMap.hasKey(c)) {
            rNFaceDetector.b(readableMap.getInt(c));
        }
        return rNFaceDetector;
    }
}
