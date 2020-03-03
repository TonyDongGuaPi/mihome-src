package com.xiaomi.ai.utils;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.media.ExifInterface;
import android.util.Log;
import com.xiaomi.ai.utils.o;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import miuipub.reflect.Field;

public final class i implements o.a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9953a = "MIUI/debug_log/%s";
    private static final String b = ".log";
    private String c;
    private o.a d;
    private File e;
    private int f;
    private int g;
    private long h;
    private FileOutputStream i;
    private boolean j;

    public i(Context context, File file, int i2, int i3, o.a aVar) {
        if (context.getApplicationContext() != context) {
            throw new IllegalArgumentException("appContext is not the application context. ");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("maxFileSizeInByte should >0. ");
        } else if (i3 > 0) {
            this.c = file.getName();
            this.d = aVar;
            this.f = i2;
            this.g = i3;
            if (!file.isDirectory()) {
                file.mkdirs();
            }
            if (file.isDirectory()) {
                this.e = file;
                b();
            } else if (this.d != null) {
                this.d.a(6, getClass().getName(), String.format("Construction failure. Failed to create folder %s. ", new Object[]{file.getAbsolutePath()}));
                Log.println(6, getClass().getName(), String.format("Construction failure. Failed to create folder %s. ", new Object[]{file.getAbsolutePath()}));
            }
        } else {
            throw new IllegalArgumentException("maxFileCount should >0. ");
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public i(android.content.Context r7, java.lang.String r8, int r9, int r10, com.xiaomi.ai.utils.o.a r11) {
        /*
            r6 = this;
            java.io.File r2 = new java.io.File
            java.io.File r0 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r1 = "MIUI/debug_log/%s"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r4 = 0
            r3[r4] = r8
            java.lang.String r8 = java.lang.String.format(r1, r3)
            r2.<init>(r0, r8)
            r0 = r6
            r1 = r7
            r3 = r9
            r4 = r10
            r5 = r11
            r0.<init>((android.content.Context) r1, (java.io.File) r2, (int) r3, (int) r4, (com.xiaomi.ai.utils.o.a) r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.ai.utils.i.<init>(android.content.Context, java.lang.String, int, int, com.xiaomi.ai.utils.o$a):void");
    }

    /* access modifiers changed from: private */
    public synchronized void a() {
        a(6, getClass().getName(), "========================== shut down ========================== ");
        if (this.i != null) {
            try {
                this.i.close();
            } catch (IOException e2) {
                Log.e("ContentValues", "IOException: ", e2);
            }
            this.i = null;
        }
        this.j = true;
    }

    private void a(Context context) {
        context.registerReceiver(new j(this), new IntentFilter("android.intent.action.ACTION_SHUTDOWN"), (String) null, (Handler) null);
    }

    private void b() {
        boolean z;
        File file;
        File file2 = null;
        if (this.i != null) {
            try {
                this.i.close();
            } catch (IOException e2) {
                Log.e("ContentValues", "IOException: ", e2);
            }
            this.i = null;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= this.g) {
                break;
            }
            File file3 = this.e;
            file = new File(file3, this.c + b + "." + i2);
            if (file.exists() && file.length() >= ((long) this.f)) {
                i2++;
            }
        }
        file2 = file;
        long j2 = 0;
        if (file2 == null) {
            File file4 = this.e;
            long lastModified = new File(file4, this.c + b + "." + 0).lastModified();
            long j3 = 0;
            for (int i3 = 1; i3 < this.g; i3++) {
                File file5 = this.e;
                long lastModified2 = new File(file5, this.c + b + "." + i3).lastModified();
                if (lastModified2 < lastModified) {
                    j3 = (long) i3;
                    lastModified = lastModified2;
                }
            }
            File file6 = this.e;
            file2 = new File(file6, this.c + b + "." + j3);
            z = false;
        } else {
            z = true;
        }
        try {
            this.i = new FileOutputStream(file2, z);
            if (z) {
                j2 = file2.length();
            }
            this.h = j2;
        } catch (FileNotFoundException e3) {
            if (this.d != null) {
                this.d.a(6, getClass().getName(), String.format("Failed to switch to file %s, error: %s. ", new Object[]{file2.getAbsolutePath(), e3}));
                Log.println(6, getClass().getName(), String.format("Failed to switch to file %s, error: %s. ", new Object[]{file2.getAbsolutePath(), e3}));
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0038, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00e6, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(int r6, java.lang.String r7, java.lang.String r8) {
        /*
            r5 = this;
            monitor-enter(r5)
            com.xiaomi.ai.utils.o$a r0 = r5.d     // Catch:{ all -> 0x00e7 }
            if (r0 == 0) goto L_0x000a
            com.xiaomi.ai.utils.o$a r0 = r5.d     // Catch:{ all -> 0x00e7 }
            r0.a(r6, r7, r8)     // Catch:{ all -> 0x00e7 }
        L_0x000a:
            boolean r0 = r5.a((int) r6)     // Catch:{ all -> 0x00e7 }
            if (r0 != 0) goto L_0x0012
            monitor-exit(r5)
            return
        L_0x0012:
            boolean r0 = r5.j     // Catch:{ all -> 0x00e7 }
            r1 = 6
            if (r0 == 0) goto L_0x0039
            com.xiaomi.ai.utils.o$a r6 = r5.d     // Catch:{ all -> 0x00e7 }
            if (r6 == 0) goto L_0x0037
            com.xiaomi.ai.utils.o$a r6 = r5.d     // Catch:{ all -> 0x00e7 }
            java.lang.Class r7 = r5.getClass()     // Catch:{ all -> 0x00e7 }
            java.lang.String r7 = r7.getName()     // Catch:{ all -> 0x00e7 }
            java.lang.String r8 = "Shutdown state. Skip outputing. "
            r6.a(r1, r7, r8)     // Catch:{ all -> 0x00e7 }
            java.lang.Class r6 = r5.getClass()     // Catch:{ all -> 0x00e7 }
            java.lang.String r6 = r6.getName()     // Catch:{ all -> 0x00e7 }
            java.lang.String r7 = "Shutdown state. Skip outputing. "
            android.util.Log.println(r1, r6, r7)     // Catch:{ all -> 0x00e7 }
        L_0x0037:
            monitor-exit(r5)
            return
        L_0x0039:
            java.lang.String r6 = r5.b(r6)     // Catch:{ all -> 0x00e7 }
            java.text.SimpleDateFormat r0 = new java.text.SimpleDateFormat     // Catch:{ all -> 0x00e7 }
            java.lang.String r2 = "yyyy-MM-dd HH:mm:ss"
            r0.<init>(r2)     // Catch:{ all -> 0x00e7 }
            java.lang.String r2 = "LV:%s, TM: %s, TAG: %s, TH: %s, MSG: %s\n"
            r3 = 5
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x00e7 }
            r4 = 0
            r3[r4] = r6     // Catch:{ all -> 0x00e7 }
            java.util.Date r6 = new java.util.Date     // Catch:{ all -> 0x00e7 }
            r6.<init>()     // Catch:{ all -> 0x00e7 }
            java.lang.String r6 = r0.format(r6)     // Catch:{ all -> 0x00e7 }
            r0 = 1
            r3[r0] = r6     // Catch:{ all -> 0x00e7 }
            r6 = 2
            r3[r6] = r7     // Catch:{ all -> 0x00e7 }
            r6 = 3
            java.lang.Thread r7 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x00e7 }
            java.lang.String r7 = r7.getName()     // Catch:{ all -> 0x00e7 }
            r3[r6] = r7     // Catch:{ all -> 0x00e7 }
            r6 = 4
            r3[r6] = r8     // Catch:{ all -> 0x00e7 }
            java.lang.String r6 = java.lang.String.format(r2, r3)     // Catch:{ all -> 0x00e7 }
            byte[] r6 = r6.getBytes()     // Catch:{ all -> 0x00e7 }
            long r7 = r5.h     // Catch:{ all -> 0x00e7 }
            int r2 = r6.length     // Catch:{ all -> 0x00e7 }
            long r2 = (long) r2     // Catch:{ all -> 0x00e7 }
            long r7 = r7 + r2
            r5.h = r7     // Catch:{ all -> 0x00e7 }
            java.io.FileOutputStream r7 = r5.i     // Catch:{ all -> 0x00e7 }
            if (r7 != 0) goto L_0x009d
            com.xiaomi.ai.utils.o$a r6 = r5.d     // Catch:{ all -> 0x00e7 }
            if (r6 == 0) goto L_0x00d9
            com.xiaomi.ai.utils.o$a r6 = r5.d     // Catch:{ all -> 0x00e7 }
            java.lang.Class r7 = r5.getClass()     // Catch:{ all -> 0x00e7 }
            java.lang.String r7 = r7.getName()     // Catch:{ all -> 0x00e7 }
            java.lang.String r8 = "Null output stream. Skip outputing. "
            r6.a(r1, r7, r8)     // Catch:{ all -> 0x00e7 }
            java.lang.Class r6 = r5.getClass()     // Catch:{ all -> 0x00e7 }
            java.lang.String r6 = r6.getName()     // Catch:{ all -> 0x00e7 }
            java.lang.String r7 = "Null output stream. Skip outputing. "
            android.util.Log.println(r1, r6, r7)     // Catch:{ all -> 0x00e7 }
            goto L_0x00d9
        L_0x009d:
            java.io.FileOutputStream r7 = r5.i     // Catch:{ IOException -> 0x00a8 }
            r7.write(r6)     // Catch:{ IOException -> 0x00a8 }
            java.io.FileOutputStream r6 = r5.i     // Catch:{ IOException -> 0x00a8 }
            r6.flush()     // Catch:{ IOException -> 0x00a8 }
            goto L_0x00d9
        L_0x00a8:
            r6 = move-exception
            com.xiaomi.ai.utils.o$a r7 = r5.d     // Catch:{ all -> 0x00e7 }
            if (r7 == 0) goto L_0x00d9
            com.xiaomi.ai.utils.o$a r7 = r5.d     // Catch:{ all -> 0x00e7 }
            java.lang.Class r8 = r5.getClass()     // Catch:{ all -> 0x00e7 }
            java.lang.String r8 = r8.getName()     // Catch:{ all -> 0x00e7 }
            java.lang.String r2 = "Failed to output log, IOException: %s"
            java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch:{ all -> 0x00e7 }
            r3[r4] = r6     // Catch:{ all -> 0x00e7 }
            java.lang.String r2 = java.lang.String.format(r2, r3)     // Catch:{ all -> 0x00e7 }
            r7.a(r1, r8, r2)     // Catch:{ all -> 0x00e7 }
            java.lang.Class r7 = r5.getClass()     // Catch:{ all -> 0x00e7 }
            java.lang.String r7 = r7.getName()     // Catch:{ all -> 0x00e7 }
            java.lang.String r8 = "Failed to output log, IOException: %s"
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x00e7 }
            r0[r4] = r6     // Catch:{ all -> 0x00e7 }
            java.lang.String r6 = java.lang.String.format(r8, r0)     // Catch:{ all -> 0x00e7 }
            android.util.Log.println(r1, r7, r6)     // Catch:{ all -> 0x00e7 }
        L_0x00d9:
            long r6 = r5.h     // Catch:{ all -> 0x00e7 }
            int r8 = r5.f     // Catch:{ all -> 0x00e7 }
            long r0 = (long) r8     // Catch:{ all -> 0x00e7 }
            int r8 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r8 < 0) goto L_0x00e5
            r5.b()     // Catch:{ all -> 0x00e7 }
        L_0x00e5:
            monitor-exit(r5)
            return
        L_0x00e7:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.ai.utils.i.a(int, java.lang.String, java.lang.String):void");
    }

    /* access modifiers changed from: protected */
    public boolean a(int i2) {
        return true;
    }

    /* access modifiers changed from: protected */
    public String b(int i2) {
        switch (i2) {
            case 2:
                return "V";
            case 3:
                return Field.h;
            case 4:
                return Field.e;
            case 5:
                return "W";
            case 6:
                return ExifInterface.LONGITUDE_EAST;
            case 7:
                return "A";
            default:
                return String.valueOf(i2);
        }
    }
}
