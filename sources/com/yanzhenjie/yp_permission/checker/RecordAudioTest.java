package com.yanzhenjie.yp_permission.checker;

import android.content.Context;

class RecordAudioTest implements PermissionTest {

    /* renamed from: a  reason: collision with root package name */
    private Context f2447a;

    RecordAudioTest(Context context) {
        this.f2447a = context;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|(1:13)|14) */
    /* JADX WARNING: Can't wrap try/catch for region: R(11:18|17|20|21|22|23|24|25|26|(1:31)|32) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x003f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x004f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0061 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0029 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a() throws java.lang.Throwable {
        /*
            r5 = this;
            android.media.MediaRecorder r0 = new android.media.MediaRecorder
            r0.<init>()
            r1 = 1
            r2 = 0
            java.lang.String r3 = "permission"
            java.lang.String r4 = "test"
            java.io.File r3 = java.io.File.createTempFile(r3, r4)     // Catch:{ Throwable -> 0x003f }
            r0.setAudioSource(r1)     // Catch:{ Throwable -> 0x003b, all -> 0x0038 }
            r2 = 3
            r0.setOutputFormat(r2)     // Catch:{ Throwable -> 0x003b, all -> 0x0038 }
            r0.setAudioEncoder(r1)     // Catch:{ Throwable -> 0x003b, all -> 0x0038 }
            java.lang.String r2 = r3.getAbsolutePath()     // Catch:{ Throwable -> 0x003b, all -> 0x0038 }
            r0.setOutputFile(r2)     // Catch:{ Throwable -> 0x003b, all -> 0x0038 }
            r0.prepare()     // Catch:{ Throwable -> 0x003b, all -> 0x0038 }
            r0.start()     // Catch:{ Throwable -> 0x003b, all -> 0x0038 }
            r0.stop()     // Catch:{ Exception -> 0x0029 }
        L_0x0029:
            r0.release()     // Catch:{ Exception -> 0x002c }
        L_0x002c:
            if (r3 == 0) goto L_0x0037
            boolean r0 = r3.exists()
            if (r0 == 0) goto L_0x0037
            r3.delete()
        L_0x0037:
            return r1
        L_0x0038:
            r1 = move-exception
            r2 = r3
            goto L_0x005e
        L_0x003b:
            r2 = r3
            goto L_0x003f
        L_0x003d:
            r1 = move-exception
            goto L_0x005e
        L_0x003f:
            android.content.Context r3 = r5.f2447a     // Catch:{ all -> 0x003d }
            android.content.pm.PackageManager r3 = r3.getPackageManager()     // Catch:{ all -> 0x003d }
            java.lang.String r4 = "android.hardware.microphone"
            boolean r3 = r3.hasSystemFeature(r4)     // Catch:{ all -> 0x003d }
            r1 = r1 ^ r3
            r0.stop()     // Catch:{ Exception -> 0x004f }
        L_0x004f:
            r0.release()     // Catch:{ Exception -> 0x0052 }
        L_0x0052:
            if (r2 == 0) goto L_0x005d
            boolean r0 = r2.exists()
            if (r0 == 0) goto L_0x005d
            r2.delete()
        L_0x005d:
            return r1
        L_0x005e:
            r0.stop()     // Catch:{ Exception -> 0x0061 }
        L_0x0061:
            r0.release()     // Catch:{ Exception -> 0x0064 }
        L_0x0064:
            if (r2 == 0) goto L_0x006f
            boolean r0 = r2.exists()
            if (r0 == 0) goto L_0x006f
            r2.delete()
        L_0x006f:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yanzhenjie.yp_permission.checker.RecordAudioTest.a():boolean");
    }
}
