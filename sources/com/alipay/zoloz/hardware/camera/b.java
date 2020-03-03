package com.alipay.zoloz.hardware.camera;

public interface b {
    void onError(int i);

    void onPreviewFrame(a aVar);

    void onSurfaceChanged(double d, double d2);

    void onSurfaceCreated();

    void onSurfaceDestroyed();
}
