package com.alipay.zoloz.hardware.camera.a;

import android.hardware.Camera;
import com.alipay.mobile.security.bio.utils.BioLog;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class a {
    private static a b;

    /* renamed from: a  reason: collision with root package name */
    private C0032a f1198a = new C0032a();

    private a() {
    }

    public static synchronized a a() {
        synchronized (a.class) {
            if (b == null) {
                b = new a();
                a aVar = b;
                return aVar;
            }
            a aVar2 = b;
            return aVar2;
        }
    }

    public Camera.Size a(List<Camera.Size> list, float f, int i) {
        if (list == null) {
            return null;
        }
        Collections.sort(list, this.f1198a);
        int i2 = 0;
        Iterator<Camera.Size> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Camera.Size next = it.next();
            if (next.width >= i && a(next, f)) {
                BioLog.i("PreviewSize:w = " + next.width + "h = " + next.height);
                break;
            }
            i2++;
        }
        if (i2 == list.size()) {
            i2 = list.size() - 1;
        }
        return list.get(i2);
    }

    public boolean a(Camera.Size size, float f) {
        return ((double) Math.abs((((float) size.width) / ((float) size.height)) - f)) <= 0.03d;
    }

    /* renamed from: com.alipay.zoloz.hardware.camera.a.a$a  reason: collision with other inner class name */
    public class C0032a implements Comparator<Camera.Size> {
        public C0032a() {
        }

        /* renamed from: a */
        public int compare(Camera.Size size, Camera.Size size2) {
            if (size.width == size2.width) {
                return 0;
            }
            return size.width > size2.width ? 1 : -1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x001f A[SYNTHETIC, Splitter:B:9:0x001f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int c() {
        /*
            r0 = 0
            java.lang.String r1 = android.os.Build.VERSION.SDK     // Catch:{ Throwable -> 0x0012 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ Throwable -> 0x0012 }
            r2 = 8
            if (r1 <= r2) goto L_0x0010
            int r1 = android.hardware.Camera.getNumberOfCameras()     // Catch:{ Throwable -> 0x0012 }
            goto L_0x001d
        L_0x0010:
            r1 = 0
            goto L_0x001d
        L_0x0012:
            r1 = move-exception
            java.lang.String r2 = "face"
            java.lang.String r1 = r1.toString()
            android.util.Log.e(r2, r1)
            goto L_0x0010
        L_0x001d:
            if (r0 >= r1) goto L_0x0030
            android.hardware.Camera$CameraInfo r2 = new android.hardware.Camera$CameraInfo     // Catch:{ Throwable -> 0x002d }
            r2.<init>()     // Catch:{ Throwable -> 0x002d }
            android.hardware.Camera.getCameraInfo(r0, r2)     // Catch:{ Throwable -> 0x002d }
            int r2 = r2.facing     // Catch:{ Throwable -> 0x002d }
            r3 = 1
            if (r2 != r3) goto L_0x002d
            goto L_0x0031
        L_0x002d:
            int r0 = r0 + 1
            goto L_0x001d
        L_0x0030:
            r0 = -1
        L_0x0031:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.zoloz.hardware.camera.a.a.c():int");
    }

    public static int b() {
        return c();
    }
}
