package com.xiaomi.qrcode2.camera;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import com.xiaomi.qrcode2.camera.open.OpenCamera;
import com.xiaomi.zxing.PlanarYUVLuminanceSource;

public final class CameraManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f13032a = "CameraManager";
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
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x004e */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x007d */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(android.view.SurfaceHolder r8) throws java.io.IOException {
        /*
            r7 = this;
            monitor-enter(r7)
            com.xiaomi.qrcode2.camera.open.OpenCamera r0 = r7.h     // Catch:{ all -> 0x0089 }
            if (r0 != 0) goto L_0x0018
            int r0 = r7.n     // Catch:{ all -> 0x0089 }
            com.xiaomi.qrcode2.camera.open.OpenCamera r0 = com.xiaomi.qrcode2.camera.open.OpenCameraInterface.a(r0)     // Catch:{ all -> 0x0089 }
            if (r0 == 0) goto L_0x0010
            r7.h = r0     // Catch:{ all -> 0x0089 }
            goto L_0x0018
        L_0x0010:
            java.io.IOException r8 = new java.io.IOException     // Catch:{ all -> 0x0089 }
            java.lang.String r0 = "Camera.open() failed to return object from driver"
            r8.<init>(r0)     // Catch:{ all -> 0x0089 }
            throw r8     // Catch:{ all -> 0x0089 }
        L_0x0018:
            boolean r1 = r7.l     // Catch:{ all -> 0x0089 }
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x0038
            r7.l = r2     // Catch:{ all -> 0x0089 }
            com.xiaomi.qrcode2.camera.CameraConfigurationManager r1 = r7.g     // Catch:{ all -> 0x0089 }
            r1.a((com.xiaomi.qrcode2.camera.open.OpenCamera) r0)     // Catch:{ all -> 0x0089 }
            int r1 = r7.o     // Catch:{ all -> 0x0089 }
            if (r1 <= 0) goto L_0x0038
            int r1 = r7.p     // Catch:{ all -> 0x0089 }
            if (r1 <= 0) goto L_0x0038
            int r1 = r7.o     // Catch:{ all -> 0x0089 }
            int r4 = r7.p     // Catch:{ all -> 0x0089 }
            r7.a((int) r1, (int) r4)     // Catch:{ all -> 0x0089 }
            r7.o = r3     // Catch:{ all -> 0x0089 }
            r7.p = r3     // Catch:{ all -> 0x0089 }
        L_0x0038:
            android.hardware.Camera r1 = r0.a()     // Catch:{ all -> 0x0089 }
            android.hardware.Camera$Parameters r4 = r1.getParameters()     // Catch:{ all -> 0x0089 }
            if (r4 != 0) goto L_0x0044
            r4 = 0
            goto L_0x0048
        L_0x0044:
            java.lang.String r4 = r4.flatten()     // Catch:{ all -> 0x0089 }
        L_0x0048:
            com.xiaomi.qrcode2.camera.CameraConfigurationManager r5 = r7.g     // Catch:{ RuntimeException -> 0x004e }
            r5.a((com.xiaomi.qrcode2.camera.open.OpenCamera) r0, (boolean) r3)     // Catch:{ RuntimeException -> 0x004e }
            goto L_0x0084
        L_0x004e:
            java.lang.String r3 = f13032a     // Catch:{ all -> 0x0089 }
            java.lang.String r5 = "Camera rejected parameters. Setting only minimal safe-mode parameters"
            android.util.Log.w(r3, r5)     // Catch:{ all -> 0x0089 }
            java.lang.String r3 = f13032a     // Catch:{ all -> 0x0089 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0089 }
            r5.<init>()     // Catch:{ all -> 0x0089 }
            java.lang.String r6 = "Resetting to saved camera params: "
            r5.append(r6)     // Catch:{ all -> 0x0089 }
            r5.append(r4)     // Catch:{ all -> 0x0089 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0089 }
            android.util.Log.i(r3, r5)     // Catch:{ all -> 0x0089 }
            if (r4 == 0) goto L_0x0084
            android.hardware.Camera$Parameters r3 = r1.getParameters()     // Catch:{ all -> 0x0089 }
            r3.unflatten(r4)     // Catch:{ all -> 0x0089 }
            r1.setParameters(r3)     // Catch:{ RuntimeException -> 0x007d }
            com.xiaomi.qrcode2.camera.CameraConfigurationManager r3 = r7.g     // Catch:{ RuntimeException -> 0x007d }
            r3.a((com.xiaomi.qrcode2.camera.open.OpenCamera) r0, (boolean) r2)     // Catch:{ RuntimeException -> 0x007d }
            goto L_0x0084
        L_0x007d:
            java.lang.String r0 = f13032a     // Catch:{ all -> 0x0089 }
            java.lang.String r2 = "Camera rejected even safe-mode parameters! No configuration"
            android.util.Log.w(r0, r2)     // Catch:{ all -> 0x0089 }
        L_0x0084:
            r1.setPreviewDisplay(r8)     // Catch:{ all -> 0x0089 }
            monitor-exit(r7)
            return
        L_0x0089:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.qrcode2.camera.CameraManager.a(android.view.SurfaceHolder):void");
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

    public synchronized Rect e() {
        if (this.j == null) {
            if (this.h == null) {
                return null;
            }
            Point d2 = this.g.d();
            if (d2 == null) {
                return null;
            }
            int e2 = this.g.e();
            int i2 = (d2.x - e2) / 2;
            int f2 = this.g.f();
            this.j = new Rect(i2, f2, i2 + e2, e2 + f2);
            String str = f13032a;
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

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x008e, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.graphics.Rect f() {
        /*
            r6 = this;
            monitor-enter(r6)
            android.graphics.Rect r0 = r6.k     // Catch:{ all -> 0x0093 }
            if (r0 != 0) goto L_0x008f
            android.graphics.Rect r0 = r6.e()     // Catch:{ all -> 0x0093 }
            r1 = 0
            if (r0 != 0) goto L_0x000e
            monitor-exit(r6)
            return r1
        L_0x000e:
            android.graphics.Rect r2 = new android.graphics.Rect     // Catch:{ all -> 0x0093 }
            r2.<init>(r0)     // Catch:{ all -> 0x0093 }
            com.xiaomi.qrcode2.camera.CameraConfigurationManager r0 = r6.g     // Catch:{ all -> 0x0093 }
            android.graphics.Point r0 = r0.c()     // Catch:{ all -> 0x0093 }
            com.xiaomi.qrcode2.camera.CameraConfigurationManager r3 = r6.g     // Catch:{ all -> 0x0093 }
            android.graphics.Point r3 = r3.d()     // Catch:{ all -> 0x0093 }
            if (r0 == 0) goto L_0x008d
            if (r3 != 0) goto L_0x0024
            goto L_0x008d
        L_0x0024:
            android.graphics.Rect r1 = new android.graphics.Rect     // Catch:{ all -> 0x0093 }
            r1.<init>()     // Catch:{ all -> 0x0093 }
            r6.k = r1     // Catch:{ all -> 0x0093 }
            android.graphics.Rect r1 = r6.k     // Catch:{ all -> 0x0093 }
            int r4 = r2.top     // Catch:{ all -> 0x0093 }
            int r5 = r0.x     // Catch:{ all -> 0x0093 }
            int r4 = r4 * r5
            int r5 = r3.y     // Catch:{ all -> 0x0093 }
            int r4 = r4 / r5
            r1.left = r4     // Catch:{ all -> 0x0093 }
            android.graphics.Rect r1 = r6.k     // Catch:{ all -> 0x0093 }
            int r4 = r3.x     // Catch:{ all -> 0x0093 }
            int r5 = r2.right     // Catch:{ all -> 0x0093 }
            int r4 = r4 - r5
            int r5 = r0.y     // Catch:{ all -> 0x0093 }
            int r4 = r4 * r5
            int r5 = r3.x     // Catch:{ all -> 0x0093 }
            int r4 = r4 / r5
            r1.top = r4     // Catch:{ all -> 0x0093 }
            int r1 = r2.height()     // Catch:{ all -> 0x0093 }
            int r4 = r0.x     // Catch:{ all -> 0x0093 }
            int r1 = r1 * r4
            int r4 = r3.y     // Catch:{ all -> 0x0093 }
            int r1 = r1 / r4
            int r2 = r2.width()     // Catch:{ all -> 0x0093 }
            int r0 = r0.y     // Catch:{ all -> 0x0093 }
            int r2 = r2 * r0
            int r0 = r3.x     // Catch:{ all -> 0x0093 }
            int r2 = r2 / r0
            int r0 = java.lang.Math.max(r1, r2)     // Catch:{ all -> 0x0093 }
            android.graphics.Rect r1 = r6.k     // Catch:{ all -> 0x0093 }
            android.graphics.Rect r2 = r6.k     // Catch:{ all -> 0x0093 }
            int r2 = r2.left     // Catch:{ all -> 0x0093 }
            int r2 = r2 + r0
            r1.right = r2     // Catch:{ all -> 0x0093 }
            android.graphics.Rect r1 = r6.k     // Catch:{ all -> 0x0093 }
            android.graphics.Rect r2 = r6.k     // Catch:{ all -> 0x0093 }
            int r2 = r2.top     // Catch:{ all -> 0x0093 }
            int r2 = r2 + r0
            r1.bottom = r2     // Catch:{ all -> 0x0093 }
            java.lang.String r0 = f13032a     // Catch:{ all -> 0x0093 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0093 }
            r1.<init>()     // Catch:{ all -> 0x0093 }
            java.lang.String r2 = "getFramingRectInPreview:"
            r1.append(r2)     // Catch:{ all -> 0x0093 }
            android.graphics.Rect r2 = r6.k     // Catch:{ all -> 0x0093 }
            r1.append(r2)     // Catch:{ all -> 0x0093 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0093 }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x0093 }
            goto L_0x008f
        L_0x008d:
            monitor-exit(r6)
            return r1
        L_0x008f:
            android.graphics.Rect r0 = r6.k     // Catch:{ all -> 0x0093 }
            monitor-exit(r6)
            return r0
        L_0x0093:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.qrcode2.camera.CameraManager.f():android.graphics.Rect");
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
            String str = f13032a;
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
