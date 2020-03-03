package com.xiaomi.qrcode2.camera;

import android.graphics.Point;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;

final class PreviewCallback implements Camera.PreviewCallback {

    /* renamed from: a  reason: collision with root package name */
    private static final String f13033a = "PreviewCallback";
    private final CameraConfigurationManager b;
    private Handler c;
    private int d;

    PreviewCallback(CameraConfigurationManager cameraConfigurationManager) {
        this.b = cameraConfigurationManager;
    }

    /* access modifiers changed from: package-private */
    public void a(Handler handler, int i) {
        this.c = handler;
        this.d = i;
    }

    public void onPreviewFrame(byte[] bArr, Camera camera) {
        Point c2 = this.b.c();
        Handler handler = this.c;
        if (c2 == null || handler == null) {
            Log.d(f13033a, "Got preview callback, but no handler or resolution available");
            return;
        }
        handler.obtainMessage(this.d, c2.x, c2.y, bArr).sendToTarget();
        this.c = null;
    }
}
