package com.reactnative.camera.tasks;

import com.google.zxing.Result;

public interface BarCodeScannerAsyncTaskDelegate {
    void onBarCodeRead(Result result);

    void onBarCodeScanningTaskCompleted();
}
