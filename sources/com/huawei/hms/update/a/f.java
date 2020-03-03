package com.huawei.hms.update.a;

import android.content.Context;
import android.os.Environment;
import com.amap.api.services.core.AMapException;
import com.huawei.hms.update.a.a.a;
import com.huawei.hms.update.a.a.c;
import com.huawei.hms.update.b.b;
import com.huawei.hms.update.b.d;
import com.huawei.hms.update.provider.UpdateProvider;
import java.io.File;
import java.io.IOException;

public class f implements a {

    /* renamed from: a  reason: collision with root package name */
    private final Context f5907a;
    private final d b = new b();
    private com.huawei.hms.update.a.a.b c;
    private File d;
    /* access modifiers changed from: private */
    public final c e = new c();

    public f(Context context) {
        this.f5907a = context.getApplicationContext();
    }

    private synchronized void b(com.huawei.hms.update.a.a.b bVar) {
        this.c = bVar;
    }

    /* access modifiers changed from: private */
    public synchronized void a(int i, int i2, int i3) {
        if (this.c != null) {
            this.c.a(i, i2, i3, this.d);
        }
    }

    public Context a() {
        return this.f5907a;
    }

    public void b() {
        com.huawei.hms.support.log.a.b("OtaUpdateDownload", "Enter cancel.");
        b((com.huawei.hms.update.a.a.b) null);
        this.b.b();
    }

    public void a(com.huawei.hms.update.a.a.b bVar) {
        throw new IllegalStateException("Not supported.");
    }

    public void a(com.huawei.hms.update.a.a.b bVar, c cVar) {
        com.huawei.hms.c.a.a(bVar, "callback must not be null.");
        com.huawei.hms.support.log.a.b("OtaUpdateDownload", "Enter downloadPackage.");
        b(bVar);
        if (cVar == null || !cVar.a()) {
            com.huawei.hms.support.log.a.d("OtaUpdateDownload", "In downloadPackage, Invalid update info.");
            a(2201, 0, 0);
        } else if (!"mounted".equals(Environment.getExternalStorageState())) {
            com.huawei.hms.support.log.a.d("OtaUpdateDownload", "In downloadPackage, Invalid external storage for downloading file.");
            a(AMapException.CODE_AMAP_CLIENT_UPLOAD_LOCATION_ERROR, 0, 0);
        } else {
            this.d = UpdateProvider.getLocalFile(this.f5907a, "hms/HwMobileService.apk");
            if (this.d == null) {
                com.huawei.hms.support.log.a.d("OtaUpdateDownload", "In downloadPackage, Failed to get local file for downloading.");
                a(AMapException.CODE_AMAP_CLIENT_UPLOAD_LOCATION_ERROR, 0, 0);
                return;
            }
            File parentFile = this.d.getParentFile();
            if (parentFile == null || (!parentFile.mkdirs() && !parentFile.isDirectory())) {
                com.huawei.hms.support.log.a.d("OtaUpdateDownload", "In downloadPackage, Failed to create directory for downloading file.");
                a(2201, 0, 0);
            } else if (parentFile.getUsableSpace() < ((long) (cVar.c * 3))) {
                com.huawei.hms.support.log.a.d("OtaUpdateDownload", "In downloadPackage, No space for downloading file.");
                a(AMapException.CODE_AMAP_CLIENT_UPLOAD_TOO_FREQUENT, 0, 0);
            } else {
                try {
                    a(cVar);
                } catch (com.huawei.hms.update.b.a unused) {
                    com.huawei.hms.support.log.a.c("OtaUpdateDownload", "In downloadPackage, Canceled to download the update file.");
                    a(2101, 0, 0);
                }
            }
        }
    }

    private static boolean a(String str, File file) {
        byte[] a2 = com.huawei.hms.c.f.a(file);
        if (a2 != null) {
            return com.huawei.hms.c.b.b(a2, true).equalsIgnoreCase(str);
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a6 A[Catch:{ IOException -> 0x00d8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00c1 A[Catch:{ IOException -> 0x00d8 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.huawei.hms.update.a.a.c r9) throws com.huawei.hms.update.b.a {
        /*
            r8 = this;
            java.lang.String r0 = "OtaUpdateDownload"
            java.lang.String r1 = "Enter downloadPackage."
            com.huawei.hms.support.log.a.b(r0, r1)
            r0 = 2201(0x899, float:3.084E-42)
            r1 = 0
            r2 = 0
            com.huawei.hms.update.a.c r3 = r8.e     // Catch:{ IOException -> 0x00d8 }
            android.content.Context r4 = r8.a()     // Catch:{ IOException -> 0x00d8 }
            r3.a(r4)     // Catch:{ IOException -> 0x00d8 }
            com.huawei.hms.update.a.c r3 = r8.e     // Catch:{ IOException -> 0x00d8 }
            java.lang.String r4 = r9.b     // Catch:{ IOException -> 0x00d8 }
            int r5 = r9.c     // Catch:{ IOException -> 0x00d8 }
            java.lang.String r6 = r9.d     // Catch:{ IOException -> 0x00d8 }
            boolean r3 = r3.b(r4, r5, r6)     // Catch:{ IOException -> 0x00d8 }
            r4 = 2000(0x7d0, float:2.803E-42)
            if (r3 == 0) goto L_0x0076
            com.huawei.hms.update.a.c r3 = r8.e     // Catch:{ IOException -> 0x00d8 }
            int r3 = r3.b()     // Catch:{ IOException -> 0x00d8 }
            com.huawei.hms.update.a.c r5 = r8.e     // Catch:{ IOException -> 0x00d8 }
            int r5 = r5.a()     // Catch:{ IOException -> 0x00d8 }
            if (r3 != r5) goto L_0x005c
            java.lang.String r3 = r9.d     // Catch:{ IOException -> 0x00d8 }
            java.io.File r5 = r8.d     // Catch:{ IOException -> 0x00d8 }
            boolean r3 = a((java.lang.String) r3, (java.io.File) r5)     // Catch:{ IOException -> 0x00d8 }
            if (r3 == 0) goto L_0x0048
            r8.a(r4, r1, r1)     // Catch:{ IOException -> 0x00d8 }
        L_0x003f:
            com.huawei.hms.update.b.d r9 = r8.b
            r9.a()
            com.huawei.hms.c.c.a((java.io.OutputStream) r2)
            return
        L_0x0048:
            com.huawei.hms.update.a.c r3 = r8.e     // Catch:{ IOException -> 0x00d8 }
            java.lang.String r5 = r9.b     // Catch:{ IOException -> 0x00d8 }
            int r6 = r9.c     // Catch:{ IOException -> 0x00d8 }
            java.lang.String r7 = r9.d     // Catch:{ IOException -> 0x00d8 }
            r3.a(r5, r6, r7)     // Catch:{ IOException -> 0x00d8 }
            java.io.File r3 = r8.d     // Catch:{ IOException -> 0x00d8 }
            int r5 = r9.c     // Catch:{ IOException -> 0x00d8 }
            com.huawei.hms.update.a.h r3 = r8.a((java.io.File) r3, (int) r5)     // Catch:{ IOException -> 0x00d8 }
            goto L_0x0089
        L_0x005c:
            java.io.File r3 = r8.d     // Catch:{ IOException -> 0x00d8 }
            int r5 = r9.c     // Catch:{ IOException -> 0x00d8 }
            com.huawei.hms.update.a.h r3 = r8.a((java.io.File) r3, (int) r5)     // Catch:{ IOException -> 0x00d8 }
            com.huawei.hms.update.a.c r2 = r8.e     // Catch:{ IOException -> 0x0073, all -> 0x006f }
            int r2 = r2.b()     // Catch:{ IOException -> 0x0073, all -> 0x006f }
            long r5 = (long) r2     // Catch:{ IOException -> 0x0073, all -> 0x006f }
            r3.a(r5)     // Catch:{ IOException -> 0x0073, all -> 0x006f }
            goto L_0x0089
        L_0x006f:
            r9 = move-exception
            r2 = r3
            goto L_0x00ff
        L_0x0073:
            r9 = move-exception
            r2 = r3
            goto L_0x00d9
        L_0x0076:
            com.huawei.hms.update.a.c r3 = r8.e     // Catch:{ IOException -> 0x00d8 }
            java.lang.String r5 = r9.b     // Catch:{ IOException -> 0x00d8 }
            int r6 = r9.c     // Catch:{ IOException -> 0x00d8 }
            java.lang.String r7 = r9.d     // Catch:{ IOException -> 0x00d8 }
            r3.a(r5, r6, r7)     // Catch:{ IOException -> 0x00d8 }
            java.io.File r3 = r8.d     // Catch:{ IOException -> 0x00d8 }
            int r5 = r9.c     // Catch:{ IOException -> 0x00d8 }
            com.huawei.hms.update.a.h r3 = r8.a((java.io.File) r3, (int) r5)     // Catch:{ IOException -> 0x00d8 }
        L_0x0089:
            r2 = r3
            com.huawei.hms.update.b.d r3 = r8.b     // Catch:{ IOException -> 0x00d8 }
            java.lang.String r5 = r9.b     // Catch:{ IOException -> 0x00d8 }
            com.huawei.hms.update.a.c r6 = r8.e     // Catch:{ IOException -> 0x00d8 }
            int r6 = r6.b()     // Catch:{ IOException -> 0x00d8 }
            com.huawei.hms.update.a.c r7 = r8.e     // Catch:{ IOException -> 0x00d8 }
            int r7 = r7.a()     // Catch:{ IOException -> 0x00d8 }
            int r3 = r3.a(r5, r2, r6, r7)     // Catch:{ IOException -> 0x00d8 }
            r5 = 200(0xc8, float:2.8E-43)
            if (r3 == r5) goto L_0x00c1
            r5 = 206(0xce, float:2.89E-43)
            if (r3 == r5) goto L_0x00c1
            java.lang.String r9 = "OtaUpdateDownload"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00d8 }
            r4.<init>()     // Catch:{ IOException -> 0x00d8 }
            java.lang.String r5 = "In DownloadHelper.downloadPackage, Download the package, HTTP code: "
            r4.append(r5)     // Catch:{ IOException -> 0x00d8 }
            r4.append(r3)     // Catch:{ IOException -> 0x00d8 }
            java.lang.String r3 = r4.toString()     // Catch:{ IOException -> 0x00d8 }
            com.huawei.hms.support.log.a.d(r9, r3)     // Catch:{ IOException -> 0x00d8 }
            r8.a(r0, r1, r1)     // Catch:{ IOException -> 0x00d8 }
            goto L_0x003f
        L_0x00c1:
            java.lang.String r9 = r9.d     // Catch:{ IOException -> 0x00d8 }
            java.io.File r3 = r8.d     // Catch:{ IOException -> 0x00d8 }
            boolean r9 = a((java.lang.String) r9, (java.io.File) r3)     // Catch:{ IOException -> 0x00d8 }
            if (r9 != 0) goto L_0x00d2
            r9 = 2202(0x89a, float:3.086E-42)
            r8.a(r9, r1, r1)     // Catch:{ IOException -> 0x00d8 }
            goto L_0x003f
        L_0x00d2:
            r8.a(r4, r1, r1)     // Catch:{ IOException -> 0x00d8 }
            goto L_0x00f6
        L_0x00d6:
            r9 = move-exception
            goto L_0x00ff
        L_0x00d8:
            r9 = move-exception
        L_0x00d9:
            java.lang.String r3 = "OtaUpdateDownload"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d6 }
            r4.<init>()     // Catch:{ all -> 0x00d6 }
            java.lang.String r5 = "In DownloadHelper.downloadPackage, Failed to download."
            r4.append(r5)     // Catch:{ all -> 0x00d6 }
            java.lang.String r9 = r9.getMessage()     // Catch:{ all -> 0x00d6 }
            r4.append(r9)     // Catch:{ all -> 0x00d6 }
            java.lang.String r9 = r4.toString()     // Catch:{ all -> 0x00d6 }
            com.huawei.hms.support.log.a.d(r3, r9)     // Catch:{ all -> 0x00d6 }
            r8.a(r0, r1, r1)     // Catch:{ all -> 0x00d6 }
        L_0x00f6:
            com.huawei.hms.update.b.d r9 = r8.b
            r9.a()
            com.huawei.hms.c.c.a((java.io.OutputStream) r2)
            return
        L_0x00ff:
            com.huawei.hms.update.b.d r0 = r8.b
            r0.a()
            com.huawei.hms.c.c.a((java.io.OutputStream) r2)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.update.a.f.a(com.huawei.hms.update.a.a.c):void");
    }

    private h a(File file, int i) throws IOException {
        return new g(this, file, i, i);
    }
}
