package com.xiaomi.qrcode.camera;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.xiaomi.qrcode.camera.open.OpenCamera;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public final class CameraManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12990a = "CameraManager";
    private static final int b = 240;
    private static final int c = 240;
    private static final int d = 1200;
    private static final int e = 675;
    private final Context f;
    private final CameraConfigurationManager g;
    private OpenCamera h;
    private AutoFocusManager i;
    private Rect j;
    private Rect k;
    private boolean l;
    private boolean m;
    private int n = -1;
    private int o;
    private int p;
    private final PreviewCallback q;

    public CameraManager(Context context) {
        this.f = context;
        this.g = new CameraConfigurationManager(context);
        this.q = new PreviewCallback(this.g);
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0053 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x0082 */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0072  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(android.view.SurfaceHolder r6, int r7, int r8) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            com.xiaomi.qrcode.camera.open.OpenCamera r0 = r5.h     // Catch:{ all -> 0x008e }
            if (r0 != 0) goto L_0x001d
            int r0 = r5.n     // Catch:{ all -> 0x008e }
            com.xiaomi.qrcode.camera.open.OpenCamera r0 = com.xiaomi.qrcode.camera.open.OpenCameraInterface.a(r0)     // Catch:{ all -> 0x008e }
            if (r0 == 0) goto L_0x0015
            r5.h = r0     // Catch:{ all -> 0x008e }
            com.xiaomi.qrcode.camera.open.OpenCamera r1 = r5.h     // Catch:{ all -> 0x008e }
            r1.a(r7, r8)     // Catch:{ all -> 0x008e }
            goto L_0x001d
        L_0x0015:
            java.io.IOException r6 = new java.io.IOException     // Catch:{ all -> 0x008e }
            java.lang.String r7 = "Camera.open() failed to return object from driver"
            r6.<init>(r7)     // Catch:{ all -> 0x008e }
            throw r6     // Catch:{ all -> 0x008e }
        L_0x001d:
            boolean r7 = r5.l     // Catch:{ all -> 0x008e }
            r8 = 1
            r1 = 0
            if (r7 != 0) goto L_0x003d
            r5.l = r8     // Catch:{ all -> 0x008e }
            com.xiaomi.qrcode.camera.CameraConfigurationManager r7 = r5.g     // Catch:{ all -> 0x008e }
            r7.a((com.xiaomi.qrcode.camera.open.OpenCamera) r0)     // Catch:{ all -> 0x008e }
            int r7 = r5.o     // Catch:{ all -> 0x008e }
            if (r7 <= 0) goto L_0x003d
            int r7 = r5.p     // Catch:{ all -> 0x008e }
            if (r7 <= 0) goto L_0x003d
            int r7 = r5.o     // Catch:{ all -> 0x008e }
            int r2 = r5.p     // Catch:{ all -> 0x008e }
            r5.a((int) r7, (int) r2)     // Catch:{ all -> 0x008e }
            r5.o = r1     // Catch:{ all -> 0x008e }
            r5.p = r1     // Catch:{ all -> 0x008e }
        L_0x003d:
            android.hardware.Camera r7 = r0.a()     // Catch:{ all -> 0x008e }
            android.hardware.Camera$Parameters r2 = r7.getParameters()     // Catch:{ all -> 0x008e }
            if (r2 != 0) goto L_0x0049
            r2 = 0
            goto L_0x004d
        L_0x0049:
            java.lang.String r2 = r2.flatten()     // Catch:{ all -> 0x008e }
        L_0x004d:
            com.xiaomi.qrcode.camera.CameraConfigurationManager r3 = r5.g     // Catch:{ RuntimeException -> 0x0053 }
            r3.a((com.xiaomi.qrcode.camera.open.OpenCamera) r0, (boolean) r1)     // Catch:{ RuntimeException -> 0x0053 }
            goto L_0x0089
        L_0x0053:
            java.lang.String r1 = f12990a     // Catch:{ all -> 0x008e }
            java.lang.String r3 = "Camera rejected parameters. Setting only minimal safe-mode parameters"
            android.util.Log.w(r1, r3)     // Catch:{ all -> 0x008e }
            java.lang.String r1 = f12990a     // Catch:{ all -> 0x008e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x008e }
            r3.<init>()     // Catch:{ all -> 0x008e }
            java.lang.String r4 = "Resetting to saved camera params: "
            r3.append(r4)     // Catch:{ all -> 0x008e }
            r3.append(r2)     // Catch:{ all -> 0x008e }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x008e }
            android.util.Log.i(r1, r3)     // Catch:{ all -> 0x008e }
            if (r2 == 0) goto L_0x0089
            android.hardware.Camera$Parameters r1 = r7.getParameters()     // Catch:{ all -> 0x008e }
            r1.unflatten(r2)     // Catch:{ all -> 0x008e }
            r7.setParameters(r1)     // Catch:{ RuntimeException -> 0x0082 }
            com.xiaomi.qrcode.camera.CameraConfigurationManager r1 = r5.g     // Catch:{ RuntimeException -> 0x0082 }
            r1.a((com.xiaomi.qrcode.camera.open.OpenCamera) r0, (boolean) r8)     // Catch:{ RuntimeException -> 0x0082 }
            goto L_0x0089
        L_0x0082:
            java.lang.String r8 = f12990a     // Catch:{ all -> 0x008e }
            java.lang.String r0 = "Camera rejected even safe-mode parameters! No configuration"
            android.util.Log.w(r8, r0)     // Catch:{ all -> 0x008e }
        L_0x0089:
            r7.setPreviewDisplay(r6)     // Catch:{ all -> 0x008e }
            monitor-exit(r5)
            return
        L_0x008e:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.qrcode.camera.CameraManager.a(android.view.SurfaceHolder, int, int):void");
    }

    public synchronized boolean a() {
        return this.h != null;
    }

    public synchronized void b() {
        if (this.h != null) {
            this.h.a().release();
            this.h = null;
            this.j = null;
            this.k = null;
        }
    }

    public synchronized void c() {
        OpenCamera openCamera = this.h;
        if (openCamera != null && !this.m) {
            openCamera.a().startPreview();
            this.m = true;
            this.i = new AutoFocusManager(this.f, openCamera.a());
        }
    }

    public synchronized void d() {
        if (this.i != null) {
            this.i.b();
            this.i = null;
        }
        if (this.h != null && this.m) {
            this.h.a().stopPreview();
            this.q.a((Handler) null, 0);
            this.m = false;
        }
    }

    public synchronized void a(boolean z) {
        OpenCamera openCamera = this.h;
        if (!(openCamera == null || z == this.g.a(openCamera.a()))) {
            boolean z2 = this.i != null;
            if (z2) {
                this.i.b();
                this.i = null;
            }
            this.g.a(openCamera.a(), z);
            if (z2) {
                this.i = new AutoFocusManager(this.f, openCamera.a());
                this.i.a();
            }
        }
    }

    public synchronized void a(Handler handler, int i2) {
        OpenCamera openCamera = this.h;
        if (openCamera != null && this.m) {
            this.q.a(handler, i2);
            openCamera.a().setOneShotPreviewCallback(this.q);
        }
    }

    public void a(Rect rect) {
        this.j = rect;
    }

    public synchronized Rect e() {
        if (this.j == null) {
            if (this.h == null) {
                return null;
            }
            Point d2 = this.g.d();
            if (d2 == null) {
                return null;
            }
            int a2 = DisplayUtils.a(194.0f);
            int a3 = DisplayUtils.a(194.0f);
            int i2 = (d2.x - a2) / 2;
            int a4 = DisplayUtils.a(164.0f);
            this.j = new Rect(i2, a4, a2 + i2, a3 + a4);
            String str = f12990a;
            Log.d(str, "Calculated framing rect: " + this.j);
        }
        return this.j;
    }

    private static int a(int i2, int i3, int i4) {
        int i5 = (i2 * 5) / 8;
        if (i5 < i3) {
            return i3;
        }
        return i5 > i4 ? i4 : i5;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0061, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.graphics.Rect f() {
        /*
            r6 = this;
            monitor-enter(r6)
            android.graphics.Rect r0 = r6.k     // Catch:{ all -> 0x0066 }
            if (r0 != 0) goto L_0x0062
            android.graphics.Rect r0 = r6.e()     // Catch:{ all -> 0x0066 }
            r1 = 0
            if (r0 != 0) goto L_0x000e
            monitor-exit(r6)
            return r1
        L_0x000e:
            android.graphics.Rect r2 = new android.graphics.Rect     // Catch:{ all -> 0x0066 }
            r2.<init>(r0)     // Catch:{ all -> 0x0066 }
            com.xiaomi.qrcode.camera.CameraConfigurationManager r0 = r6.g     // Catch:{ all -> 0x0066 }
            android.graphics.Point r0 = r0.c()     // Catch:{ all -> 0x0066 }
            com.xiaomi.qrcode.camera.CameraConfigurationManager r3 = r6.g     // Catch:{ all -> 0x0066 }
            android.graphics.Point r3 = r3.d()     // Catch:{ all -> 0x0066 }
            if (r0 == 0) goto L_0x0060
            if (r3 != 0) goto L_0x0024
            goto L_0x0060
        L_0x0024:
            android.graphics.Rect r1 = new android.graphics.Rect     // Catch:{ all -> 0x0066 }
            r1.<init>()     // Catch:{ all -> 0x0066 }
            r6.k = r1     // Catch:{ all -> 0x0066 }
            android.graphics.Rect r1 = r6.k     // Catch:{ all -> 0x0066 }
            int r4 = r2.top     // Catch:{ all -> 0x0066 }
            int r5 = r0.x     // Catch:{ all -> 0x0066 }
            int r4 = r4 * r5
            int r5 = r3.y     // Catch:{ all -> 0x0066 }
            int r4 = r4 / r5
            r1.left = r4     // Catch:{ all -> 0x0066 }
            android.graphics.Rect r1 = r6.k     // Catch:{ all -> 0x0066 }
            int r4 = r2.bottom     // Catch:{ all -> 0x0066 }
            int r5 = r0.x     // Catch:{ all -> 0x0066 }
            int r4 = r4 * r5
            int r5 = r3.y     // Catch:{ all -> 0x0066 }
            int r4 = r4 / r5
            r1.right = r4     // Catch:{ all -> 0x0066 }
            android.graphics.Rect r1 = r6.k     // Catch:{ all -> 0x0066 }
            int r4 = r2.left     // Catch:{ all -> 0x0066 }
            int r5 = r0.y     // Catch:{ all -> 0x0066 }
            int r4 = r4 * r5
            int r5 = r3.x     // Catch:{ all -> 0x0066 }
            int r4 = r4 / r5
            r1.top = r4     // Catch:{ all -> 0x0066 }
            android.graphics.Rect r1 = r6.k     // Catch:{ all -> 0x0066 }
            int r2 = r2.right     // Catch:{ all -> 0x0066 }
            int r0 = r0.y     // Catch:{ all -> 0x0066 }
            int r2 = r2 * r0
            int r0 = r3.x     // Catch:{ all -> 0x0066 }
            int r2 = r2 / r0
            r1.bottom = r2     // Catch:{ all -> 0x0066 }
            goto L_0x0062
        L_0x0060:
            monitor-exit(r6)
            return r1
        L_0x0062:
            android.graphics.Rect r0 = r6.k     // Catch:{ all -> 0x0066 }
            monitor-exit(r6)
            return r0
        L_0x0066:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.qrcode.camera.CameraManager.f():android.graphics.Rect");
    }

    public synchronized void a(int i2) {
        this.n = i2;
    }

    public synchronized void a(int i2, int i3) {
        if (this.l) {
            Point d2 = this.g.d();
            if (i2 > d2.x) {
                i2 = d2.x;
            }
            if (i3 > d2.y) {
                i3 = d2.y;
            }
            int i4 = (d2.x - i2) / 2;
            int i5 = (d2.y - i3) / 2;
            this.j = new Rect(i4, i5, i2 + i4, i3 + i5);
            String str = f12990a;
            Log.d(str, "Calculated manual framing rect: " + this.j);
            this.k = null;
        } else {
            this.o = i2;
            this.p = i3;
        }
    }

    public PlanarYUVLuminanceSource a(byte[] bArr, int i2, int i3) {
        Rect f2 = f();
        if (f2 == null) {
            return null;
        }
        return new PlanarYUVLuminanceSource(bArr, i2, i3, f2.left, f2.top, f2.width(), f2.height(), false);
    }
}
