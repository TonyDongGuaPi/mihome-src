package com.reactnative.camera.tasks;

import android.util.SparseArray;
import com.google.android.gms.vision.face.Face;
import com.reactnative.camera.facedetector.RNFaceDetector;

public interface FaceDetectorAsyncTaskDelegate {
    void onFaceDetectingTaskCompleted();

    void onFaceDetectionError(RNFaceDetector rNFaceDetector);

    void onFacesDetected(SparseArray<Face> sparseArray, int i, int i2, int i3);
}
