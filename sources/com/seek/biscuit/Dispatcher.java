package com.seek.biscuit;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class Dispatcher {
    private static final int b = 1;
    private static final int c = 2;

    /* renamed from: a  reason: collision with root package name */
    private final Handler f8808a = new DispatchHandler(Looper.getMainLooper());

    /* access modifiers changed from: package-private */
    public void a(ImageCompressor imageCompressor) {
        this.f8808a.obtainMessage(1, imageCompressor).sendToTarget();
    }

    /* access modifiers changed from: package-private */
    public void b(ImageCompressor imageCompressor) {
        this.f8808a.obtainMessage(2, imageCompressor).sendToTarget();
    }

    private static class DispatchHandler extends Handler {
        public DispatchHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            ImageCompressor imageCompressor = (ImageCompressor) message.obj;
            switch (message.what) {
                case 1:
                    imageCompressor.c.d(imageCompressor.f8810a);
                    return;
                case 2:
                    imageCompressor.c.a(imageCompressor.b);
                    return;
                default:
                    return;
            }
        }
    }
}
