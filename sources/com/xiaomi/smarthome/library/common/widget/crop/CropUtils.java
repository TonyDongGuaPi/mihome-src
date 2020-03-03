package com.xiaomi.smarthome.library.common.widget.crop;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import com.xiaomi.smarthome.application.SHApplication;
import java.io.Closeable;
import java.io.File;

public class CropUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final int f19003a = 720;
    public static final int b = 720;
    public static String c = new File(SHApplication.getApplication().getExternalFilesDir(".temp"), "cropTemp.jpg").getAbsolutePath();

    public static void a(Activity activity, String str, String str2, Runnable runnable, Handler handler) {
        new Thread(new BackgroundJob(activity, runnable, ProgressDialog.show(activity, str, str2, true, false), handler)).start();
    }

    private static class BackgroundJob implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private final Activity f19004a;
        /* access modifiers changed from: private */
        public final ProgressDialog b;
        private final Runnable c;
        private final Handler d;
        private final Runnable e = new Runnable() {
            public void run() {
                if (BackgroundJob.this.b.getWindow() != null) {
                    BackgroundJob.this.b.dismiss();
                }
            }
        };

        public BackgroundJob(Activity activity, Runnable runnable, ProgressDialog progressDialog, Handler handler) {
            this.f19004a = activity;
            this.b = progressDialog;
            this.c = runnable;
            this.d = handler;
        }

        public void run() {
            try {
                this.c.run();
            } finally {
                this.d.post(this.e);
            }
        }

        public void a(Activity activity) {
            this.e.run();
            this.d.removeCallbacks(this.e);
        }

        public void b(Activity activity) {
            this.b.hide();
        }

        public void c(Activity activity) {
            this.b.show();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00c9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap a(android.graphics.Matrix r13, android.graphics.Bitmap r14, int r15, int r16, boolean r17) {
        /*
            r0 = r13
            r7 = r14
            r8 = r15
            r9 = r16
            int r1 = r14.getWidth()
            int r1 = r1 - r8
            int r2 = r14.getHeight()
            int r2 = r2 - r9
            r3 = 0
            r10 = 0
            if (r17 != 0) goto L_0x0060
            if (r1 < 0) goto L_0x0017
            if (r2 >= 0) goto L_0x0060
        L_0x0017:
            android.graphics.Bitmap$Config r0 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r15, r9, r0)
            android.graphics.Canvas r4 = new android.graphics.Canvas
            r4.<init>(r0)
            int r1 = r1 / 2
            int r1 = java.lang.Math.max(r10, r1)
            int r2 = r2 / 2
            int r2 = java.lang.Math.max(r10, r2)
            android.graphics.Rect r5 = new android.graphics.Rect
            int r6 = r14.getWidth()
            int r6 = java.lang.Math.min(r15, r6)
            int r6 = r6 + r1
            int r10 = r14.getHeight()
            int r10 = java.lang.Math.min(r9, r10)
            int r10 = r10 + r2
            r5.<init>(r1, r2, r6, r10)
            int r1 = r5.width()
            int r1 = r8 - r1
            int r1 = r1 / 2
            int r2 = r5.height()
            int r2 = r9 - r2
            int r2 = r2 / 2
            android.graphics.Rect r6 = new android.graphics.Rect
            int r8 = r8 - r1
            int r9 = r9 - r2
            r6.<init>(r1, r2, r8, r9)
            r4.drawBitmap(r14, r5, r6, r3)
            return r0
        L_0x0060:
            int r1 = r14.getWidth()
            float r1 = (float) r1
            int r2 = r14.getHeight()
            float r2 = (float) r2
            float r4 = r1 / r2
            float r5 = (float) r8
            float r6 = (float) r9
            float r11 = r5 / r6
            int r4 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            r11 = 1065353216(0x3f800000, float:1.0)
            r12 = 1063675494(0x3f666666, float:0.9)
            if (r4 <= 0) goto L_0x0089
            float r6 = r6 / r2
            int r1 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r1 < 0) goto L_0x0085
            int r1 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r1 <= 0) goto L_0x0083
            goto L_0x0085
        L_0x0083:
            r0 = r3
            goto L_0x0098
        L_0x0085:
            r13.setScale(r6, r6)
            goto L_0x0098
        L_0x0089:
            float r5 = r5 / r1
            int r1 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r1 < 0) goto L_0x0095
            int r1 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r1 <= 0) goto L_0x0093
            goto L_0x0095
        L_0x0093:
            r5 = r3
            goto L_0x0099
        L_0x0095:
            r13.setScale(r5, r5)
        L_0x0098:
            r5 = r0
        L_0x0099:
            if (r5 == 0) goto L_0x00ac
            r1 = 0
            r2 = 0
            int r3 = r14.getWidth()
            int r4 = r14.getHeight()
            r6 = 1
            r0 = r14
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r0, r1, r2, r3, r4, r5, r6)
            goto L_0x00ad
        L_0x00ac:
            r0 = r7
        L_0x00ad:
            int r1 = r0.getWidth()
            int r1 = r1 - r8
            int r1 = java.lang.Math.max(r10, r1)
            int r2 = r0.getHeight()
            int r2 = r2 - r9
            int r2 = java.lang.Math.max(r10, r2)
            int r1 = r1 / 2
            int r2 = r2 / 2
            android.graphics.Bitmap r1 = android.graphics.Bitmap.createBitmap(r0, r1, r2, r15, r9)
            if (r0 == r7) goto L_0x00cc
            r0.recycle()
        L_0x00cc:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.widget.crop.CropUtils.a(android.graphics.Matrix, android.graphics.Bitmap, int, int, boolean):android.graphics.Bitmap");
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }
}
