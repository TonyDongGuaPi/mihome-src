package com.yanzhenjie.permission.checker;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class CameraTest implements PermissionTest {
    private static final Camera.PreviewCallback b = new Camera.PreviewCallback() {
        public void onPreviewFrame(byte[] bArr, Camera camera) {
        }
    };
    private static final SurfaceHolder.Callback c = new SurfaceHolder.Callback() {
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private SurfaceHolder f2411a;

    CameraTest(Context context) {
        this.f2411a = new SurfaceView(context).getHolder();
        this.f2411a.addCallback(c);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a() throws java.lang.Throwable {
        /*
            r3 = this;
            r0 = 0
            android.hardware.Camera r1 = android.hardware.Camera.open()     // Catch:{ all -> 0x002b }
            android.hardware.Camera$Parameters r2 = r1.getParameters()     // Catch:{ all -> 0x0029 }
            r1.setParameters(r2)     // Catch:{ all -> 0x0029 }
            android.view.SurfaceHolder r2 = r3.f2411a     // Catch:{ all -> 0x0029 }
            r1.setPreviewDisplay(r2)     // Catch:{ all -> 0x0029 }
            android.hardware.Camera$PreviewCallback r2 = b     // Catch:{ all -> 0x0029 }
            r1.setPreviewCallback(r2)     // Catch:{ all -> 0x0029 }
            r1.startPreview()     // Catch:{ all -> 0x0029 }
            r2 = 1
            if (r1 == 0) goto L_0x0028
            r1.stopPreview()
            r1.setPreviewDisplay(r0)
            r1.setPreviewCallback(r0)
            r1.release()
        L_0x0028:
            return r2
        L_0x0029:
            r2 = move-exception
            goto L_0x002d
        L_0x002b:
            r2 = move-exception
            r1 = r0
        L_0x002d:
            if (r1 == 0) goto L_0x003b
            r1.stopPreview()
            r1.setPreviewDisplay(r0)
            r1.setPreviewCallback(r0)
            r1.release()
        L_0x003b:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yanzhenjie.permission.checker.CameraTest.a():boolean");
    }
}
