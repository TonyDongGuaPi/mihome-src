package com.amap.api.services.a;

import android.content.Context;
import android.os.storage.StorageManager;
import java.io.Closeable;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

public class e {
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006a, code lost:
        r7 = new java.lang.String(r4.toByteArray(), "UTF-8");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0079, code lost:
        if (android.text.TextUtils.isEmpty(r7) != false) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0081, code lost:
        if (r7.contains("#") == false) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0083, code lost:
        r7 = r7.split("#");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0089, code lost:
        if (r7 == null) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008d, code lost:
        if (r7.length != 2) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0095, code lost:
        if (android.text.TextUtils.equals(r8, r7[0]) == false) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0097, code lost:
        r7 = r7[1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        a(r4);
        a(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a1, code lost:
        return r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        a(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00a9, code lost:
        r7 = th;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.lang.String a(android.content.Context r7, java.lang.String r8) {
        /*
            java.lang.Class<com.amap.api.services.a.e> r0 = com.amap.api.services.a.e.class
            monitor-enter(r0)
            r1 = 0
            java.lang.String r7 = a((android.content.Context) r7, (boolean) r1)     // Catch:{ all -> 0x00c9 }
            boolean r2 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x00c9 }
            if (r2 == 0) goto L_0x0012
            java.lang.String r7 = ""
            monitor-exit(r0)
            return r7
        L_0x0012:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c9 }
            r2.<init>()     // Catch:{ all -> 0x00c9 }
            r2.append(r7)     // Catch:{ all -> 0x00c9 }
            java.lang.String r7 = java.io.File.separator     // Catch:{ all -> 0x00c9 }
            r2.append(r7)     // Catch:{ all -> 0x00c9 }
            java.lang.String r7 = "backups"
            r2.append(r7)     // Catch:{ all -> 0x00c9 }
            java.lang.String r7 = r2.toString()     // Catch:{ all -> 0x00c9 }
            java.io.File r2 = new java.io.File     // Catch:{ all -> 0x00c9 }
            java.lang.String r3 = ".adiu"
            r2.<init>(r7, r3)     // Catch:{ all -> 0x00c9 }
            boolean r7 = r2.exists()     // Catch:{ all -> 0x00c9 }
            if (r7 == 0) goto L_0x00c5
            boolean r7 = r2.canRead()     // Catch:{ all -> 0x00c9 }
            if (r7 != 0) goto L_0x003d
            goto L_0x00c5
        L_0x003d:
            long r3 = r2.length()     // Catch:{ all -> 0x00c9 }
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x004e
            r2.delete()     // Catch:{ all -> 0x00c9 }
            java.lang.String r7 = ""
            monitor-exit(r0)
            return r7
        L_0x004e:
            r7 = 0
            java.io.RandomAccessFile r3 = new java.io.RandomAccessFile     // Catch:{ Throwable -> 0x00bb, all -> 0x00b0 }
            java.lang.String r4 = "r"
            r3.<init>(r2, r4)     // Catch:{ Throwable -> 0x00bb, all -> 0x00b0 }
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch:{ Throwable -> 0x00ae, all -> 0x00ab }
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x00ae, all -> 0x00ab }
            r4.<init>()     // Catch:{ Throwable -> 0x00ae, all -> 0x00ab }
        L_0x005f:
            int r7 = r3.read(r2)     // Catch:{ Throwable -> 0x00bd, all -> 0x00a9 }
            r5 = -1
            if (r7 == r5) goto L_0x006a
            r4.write(r2, r1, r7)     // Catch:{ Throwable -> 0x00bd, all -> 0x00a9 }
            goto L_0x005f
        L_0x006a:
            java.lang.String r7 = new java.lang.String     // Catch:{ Throwable -> 0x00bd, all -> 0x00a9 }
            byte[] r2 = r4.toByteArray()     // Catch:{ Throwable -> 0x00bd, all -> 0x00a9 }
            java.lang.String r5 = "UTF-8"
            r7.<init>(r2, r5)     // Catch:{ Throwable -> 0x00bd, all -> 0x00a9 }
            boolean r2 = android.text.TextUtils.isEmpty(r7)     // Catch:{ Throwable -> 0x00bd, all -> 0x00a9 }
            if (r2 != 0) goto L_0x00a2
            java.lang.String r2 = "#"
            boolean r2 = r7.contains(r2)     // Catch:{ Throwable -> 0x00bd, all -> 0x00a9 }
            if (r2 == 0) goto L_0x00a2
            java.lang.String r2 = "#"
            java.lang.String[] r7 = r7.split(r2)     // Catch:{ Throwable -> 0x00bd, all -> 0x00a9 }
            if (r7 == 0) goto L_0x00a2
            int r2 = r7.length     // Catch:{ Throwable -> 0x00bd, all -> 0x00a9 }
            r5 = 2
            if (r2 != r5) goto L_0x00a2
            r1 = r7[r1]     // Catch:{ Throwable -> 0x00bd, all -> 0x00a9 }
            boolean r8 = android.text.TextUtils.equals(r8, r1)     // Catch:{ Throwable -> 0x00bd, all -> 0x00a9 }
            if (r8 == 0) goto L_0x00a2
            r8 = 1
            r7 = r7[r8]     // Catch:{ Throwable -> 0x00bd, all -> 0x00a9 }
            a(r4)     // Catch:{ all -> 0x00c9 }
            a(r3)     // Catch:{ all -> 0x00c9 }
            monitor-exit(r0)
            return r7
        L_0x00a2:
            a(r4)     // Catch:{ all -> 0x00c9 }
        L_0x00a5:
            a(r3)     // Catch:{ all -> 0x00c9 }
            goto L_0x00c1
        L_0x00a9:
            r7 = move-exception
            goto L_0x00b4
        L_0x00ab:
            r8 = move-exception
            r4 = r7
            goto L_0x00b3
        L_0x00ae:
            r4 = r7
            goto L_0x00bd
        L_0x00b0:
            r8 = move-exception
            r3 = r7
            r4 = r3
        L_0x00b3:
            r7 = r8
        L_0x00b4:
            a(r4)     // Catch:{ all -> 0x00c9 }
            a(r3)     // Catch:{ all -> 0x00c9 }
            throw r7     // Catch:{ all -> 0x00c9 }
        L_0x00bb:
            r3 = r7
            r4 = r3
        L_0x00bd:
            a(r4)     // Catch:{ all -> 0x00c9 }
            goto L_0x00a5
        L_0x00c1:
            java.lang.String r7 = ""
            monitor-exit(r0)
            return r7
        L_0x00c5:
            java.lang.String r7 = ""
            monitor-exit(r0)
            return r7
        L_0x00c9:
            r7 = move-exception
            monitor-exit(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.e.a(android.content.Context, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(12:8|9|10|11|12|(1:16)|17|(7:18|19|20|21|(3:23|24|25)|(2:31|32)|(2:35|36))|37|38|67|68) */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:26|27|40|(0)|(0)|54|55|56) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x0087 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:54:0x00a2 */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x009a A[SYNTHETIC, Splitter:B:48:0x009a] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x009f A[SYNTHETIC, Splitter:B:52:0x009f] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00aa A[SYNTHETIC, Splitter:B:61:0x00aa] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00af A[SYNTHETIC, Splitter:B:65:0x00af] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:54:0x00a2=Splitter:B:54:0x00a2, B:37:0x0087=Splitter:B:37:0x0087} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a(android.content.Context r4, java.lang.String r5, java.lang.String r6) {
        /*
            java.lang.Class<com.amap.api.services.a.e> r0 = com.amap.api.services.a.e.class
            monitor-enter(r0)
            r1 = 0
            java.lang.String r4 = a((android.content.Context) r4, (boolean) r1)     // Catch:{ all -> 0x00b5 }
            boolean r1 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x00b5 }
            if (r1 == 0) goto L_0x0010
            monitor-exit(r0)
            return
        L_0x0010:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b5 }
            r1.<init>()     // Catch:{ all -> 0x00b5 }
            r1.append(r5)     // Catch:{ all -> 0x00b5 }
            java.lang.String r5 = "#"
            r1.append(r5)     // Catch:{ all -> 0x00b5 }
            r1.append(r6)     // Catch:{ all -> 0x00b5 }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x00b5 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b5 }
            r6.<init>()     // Catch:{ all -> 0x00b5 }
            r6.append(r4)     // Catch:{ all -> 0x00b5 }
            java.lang.String r4 = java.io.File.separator     // Catch:{ all -> 0x00b5 }
            r6.append(r4)     // Catch:{ all -> 0x00b5 }
            java.lang.String r4 = "backups"
            r6.append(r4)     // Catch:{ all -> 0x00b5 }
            java.lang.String r4 = r6.toString()     // Catch:{ all -> 0x00b5 }
            java.io.File r6 = new java.io.File     // Catch:{ all -> 0x00b5 }
            r6.<init>(r4)     // Catch:{ all -> 0x00b5 }
            java.io.File r4 = new java.io.File     // Catch:{ all -> 0x00b5 }
            java.lang.String r1 = ".adiu"
            r4.<init>(r6, r1)     // Catch:{ all -> 0x00b5 }
            r1 = 0
            boolean r2 = r6.exists()     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            if (r2 == 0) goto L_0x0053
            boolean r2 = r6.isDirectory()     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            if (r2 == 0) goto L_0x0056
        L_0x0053:
            r6.mkdirs()     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
        L_0x0056:
            r4.createNewFile()     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            java.io.RandomAccessFile r6 = new java.io.RandomAccessFile     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            java.lang.String r2 = "rws"
            r6.<init>(r4, r2)     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            java.nio.channels.FileChannel r4 = r6.getChannel()     // Catch:{ Throwable -> 0x0093, all -> 0x0090 }
            java.nio.channels.FileLock r2 = r4.tryLock()     // Catch:{ Throwable -> 0x00a8, all -> 0x008b }
            if (r2 == 0) goto L_0x007d
            java.lang.String r1 = "UTF-8"
            byte[] r5 = r5.getBytes(r1)     // Catch:{ Throwable -> 0x007b, all -> 0x0078 }
            java.nio.ByteBuffer r5 = java.nio.ByteBuffer.wrap(r5)     // Catch:{ Throwable -> 0x007b, all -> 0x0078 }
            r4.write(r5)     // Catch:{ Throwable -> 0x007b, all -> 0x0078 }
            goto L_0x007d
        L_0x0078:
            r5 = move-exception
            r1 = r2
            goto L_0x008c
        L_0x007b:
            r1 = r2
            goto L_0x00a8
        L_0x007d:
            if (r2 == 0) goto L_0x0082
            r2.release()     // Catch:{ IOException -> 0x0082 }
        L_0x0082:
            if (r4 == 0) goto L_0x0087
            r4.close()     // Catch:{ IOException -> 0x0087 }
        L_0x0087:
            a(r6)     // Catch:{ all -> 0x00b5 }
            goto L_0x00b3
        L_0x008b:
            r5 = move-exception
        L_0x008c:
            r3 = r5
            r5 = r4
            r4 = r3
            goto L_0x0098
        L_0x0090:
            r4 = move-exception
            r5 = r1
            goto L_0x0098
        L_0x0093:
            r4 = r1
            goto L_0x00a8
        L_0x0095:
            r4 = move-exception
            r5 = r1
            r6 = r5
        L_0x0098:
            if (r1 == 0) goto L_0x009d
            r1.release()     // Catch:{ IOException -> 0x009d }
        L_0x009d:
            if (r5 == 0) goto L_0x00a2
            r5.close()     // Catch:{ IOException -> 0x00a2 }
        L_0x00a2:
            a(r6)     // Catch:{ all -> 0x00b5 }
            throw r4     // Catch:{ all -> 0x00b5 }
        L_0x00a6:
            r4 = r1
            r6 = r4
        L_0x00a8:
            if (r1 == 0) goto L_0x00ad
            r1.release()     // Catch:{ IOException -> 0x00ad }
        L_0x00ad:
            if (r4 == 0) goto L_0x0087
            r4.close()     // Catch:{ IOException -> 0x0087 }
            goto L_0x0087
        L_0x00b3:
            monitor-exit(r0)
            return
        L_0x00b5:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.e.a(android.content.Context, java.lang.String, java.lang.String):void");
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }

    private static String a(Context context, boolean z) {
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        try {
            Class<?> cls = Class.forName("android.os.storage.StorageVolume");
            Method method = storageManager.getClass().getMethod("getVolumeList", new Class[0]);
            Method method2 = cls.getMethod("getPath", new Class[0]);
            Method method3 = cls.getMethod("isRemovable", new Class[0]);
            Object invoke = method.invoke(storageManager, new Object[0]);
            int length = Array.getLength(invoke);
            for (int i = 0; i < length; i++) {
                Object obj = Array.get(invoke, i);
                String str = (String) method2.invoke(obj, new Object[0]);
                if (z == ((Boolean) method3.invoke(obj, new Object[0])).booleanValue()) {
                    return str;
                }
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
