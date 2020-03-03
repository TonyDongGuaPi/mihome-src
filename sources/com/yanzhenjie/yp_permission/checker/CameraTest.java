package com.yanzhenjie.yp_permission.checker;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;

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
    private Context f2440a;

    CameraTest(Context context) {
        this.f2440a = context;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0049, code lost:
        r0 = !r5.f2440a.getPackageManager().hasSystemFeature("android.hardware.camera");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004a, code lost:
        if (r3 != null) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004c, code lost:
        r3.stopPreview();
        r3.setPreviewDisplay((android.view.SurfaceHolder) null);
        r3.setPreviewCallback((android.hardware.Camera.PreviewCallback) null);
        r3.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0058, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0037, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a() throws java.lang.Throwable {
        /*
            r5 = this;
            android.view.SurfaceView r0 = new android.view.SurfaceView
            android.content.Context r1 = r5.f2440a
            r0.<init>(r1)
            android.view.SurfaceHolder r0 = r0.getHolder()
            android.view.SurfaceHolder$Callback r1 = c
            r0.addCallback(r1)
            r1 = 1
            r2 = 0
            android.hardware.Camera r3 = android.hardware.Camera.open()     // Catch:{ Throwable -> 0x003c, all -> 0x0039 }
            android.hardware.Camera$Parameters r4 = r3.getParameters()     // Catch:{ Throwable -> 0x003d }
            r3.setParameters(r4)     // Catch:{ Throwable -> 0x003d }
            r3.setPreviewDisplay(r0)     // Catch:{ Throwable -> 0x003d }
            android.hardware.Camera$PreviewCallback r0 = b     // Catch:{ Throwable -> 0x003d }
            r3.setPreviewCallback(r0)     // Catch:{ Throwable -> 0x003d }
            r3.startPreview()     // Catch:{ Throwable -> 0x003d }
            if (r3 == 0) goto L_0x0036
            r3.stopPreview()
            r3.setPreviewDisplay(r2)
            r3.setPreviewCallback(r2)
            r3.release()
        L_0x0036:
            return r1
        L_0x0037:
            r0 = move-exception
            goto L_0x0059
        L_0x0039:
            r0 = move-exception
            r3 = r2
            goto L_0x0059
        L_0x003c:
            r3 = r2
        L_0x003d:
            android.content.Context r0 = r5.f2440a     // Catch:{ all -> 0x0037 }
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch:{ all -> 0x0037 }
            java.lang.String r4 = "android.hardware.camera"
            boolean r0 = r0.hasSystemFeature(r4)     // Catch:{ all -> 0x0037 }
            r0 = r0 ^ r1
            if (r3 == 0) goto L_0x0058
            r3.stopPreview()
            r3.setPreviewDisplay(r2)
            r3.setPreviewCallback(r2)
            r3.release()
        L_0x0058:
            return r0
        L_0x0059:
            if (r3 == 0) goto L_0x0067
            r3.stopPreview()
            r3.setPreviewDisplay(r2)
            r3.setPreviewCallback(r2)
            r3.release()
        L_0x0067:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yanzhenjie.yp_permission.checker.CameraTest.a():boolean");
    }
}
