package org.reactnative.camera.tasks;

import com.xiaomi.zxing.Result;

public interface BarCodeScannerAsyncTaskDelegate {
    void onBarCodeRead(Result result, int i, int i2);

    void onBarCodeScanningTaskCompleted();
}
