package com.yanzhenjie.permission.checker;

import android.media.MediaRecorder;
import java.io.File;

class RecordAudioTest implements PermissionTest {

    /* renamed from: a  reason: collision with root package name */
    private File f2418a;
    private MediaRecorder b;

    RecordAudioTest() {
        this.f2418a = null;
        this.b = null;
        this.b = new MediaRecorder();
    }

    public boolean a() throws Throwable {
        try {
            this.f2418a = File.createTempFile("permission", "test");
            this.b.setAudioSource(1);
            this.b.setOutputFormat(3);
            this.b.setAudioEncoder(1);
            this.b.setOutputFile(this.f2418a.getAbsolutePath());
            this.b.prepare();
            this.b.start();
            return true;
        } finally {
            b();
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:2|3|4|5) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0009 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b() {
        /*
            r1 = this;
            android.media.MediaRecorder r0 = r1.b
            if (r0 == 0) goto L_0x000e
            android.media.MediaRecorder r0 = r1.b     // Catch:{ Exception -> 0x0009 }
            r0.stop()     // Catch:{ Exception -> 0x0009 }
        L_0x0009:
            android.media.MediaRecorder r0 = r1.b     // Catch:{ Exception -> 0x000e }
            r0.release()     // Catch:{ Exception -> 0x000e }
        L_0x000e:
            java.io.File r0 = r1.f2418a
            if (r0 == 0) goto L_0x001f
            java.io.File r0 = r1.f2418a
            boolean r0 = r0.exists()
            if (r0 == 0) goto L_0x001f
            java.io.File r0 = r1.f2418a
            r0.delete()
        L_0x001f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yanzhenjie.permission.checker.RecordAudioTest.b():void");
    }
}
