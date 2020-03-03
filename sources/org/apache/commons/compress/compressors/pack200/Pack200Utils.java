package org.apache.commons.compress.compressors.pack200;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Pack200Utils {
    private Pack200Utils() {
    }

    public static void a(File file) throws IOException {
        a(file, file, (Map<String, String>) null);
    }

    public static void a(File file, Map<String, String> map) throws IOException {
        a(file, file, map);
    }

    public static void a(File file, File file2) throws IOException {
        a(file, file2, (Map<String, String>) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0061, code lost:
        r6 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0062, code lost:
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0066, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0067, code lost:
        r5 = r7;
        r7 = r6;
        r6 = r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.io.File r6, java.io.File r7, java.util.Map<java.lang.String, java.lang.String> r8) throws java.io.IOException {
        /*
            if (r8 != 0) goto L_0x0007
            java.util.HashMap r8 = new java.util.HashMap
            r8.<init>()
        L_0x0007:
            java.lang.String r0 = "pack.segment.limit"
            java.lang.String r1 = "-1"
            r8.put(r0, r1)
            java.lang.String r0 = "commons-compress"
            java.lang.String r1 = "pack200normalize"
            java.io.File r0 = java.io.File.createTempFile(r0, r1)
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0083 }
            r1.<init>(r0)     // Catch:{ all -> 0x0083 }
            r2 = 0
            java.util.jar.JarFile r3 = new java.util.jar.JarFile     // Catch:{ Throwable -> 0x0076 }
            r3.<init>(r6)     // Catch:{ Throwable -> 0x0076 }
            java.util.jar.Pack200$Packer r6 = java.util.jar.Pack200.newPacker()     // Catch:{ Throwable -> 0x0064, all -> 0x0061 }
            java.util.SortedMap r4 = r6.properties()     // Catch:{ Throwable -> 0x0064, all -> 0x0061 }
            r4.putAll(r8)     // Catch:{ Throwable -> 0x0064, all -> 0x0061 }
            r6.pack(r3, r1)     // Catch:{ Throwable -> 0x0064, all -> 0x0061 }
            r3.close()     // Catch:{ Throwable -> 0x0076 }
            r1.close()     // Catch:{ all -> 0x0083 }
            java.util.jar.Pack200$Unpacker r6 = java.util.jar.Pack200.newUnpacker()     // Catch:{ all -> 0x0083 }
            java.util.jar.JarOutputStream r8 = new java.util.jar.JarOutputStream     // Catch:{ all -> 0x0083 }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0083 }
            r1.<init>(r7)     // Catch:{ all -> 0x0083 }
            r8.<init>(r1)     // Catch:{ all -> 0x0083 }
            r6.unpack(r0, r8)     // Catch:{ Throwable -> 0x0055 }
            r8.close()     // Catch:{ all -> 0x0083 }
            boolean r6 = r0.delete()
            if (r6 != 0) goto L_0x0052
            r0.deleteOnExit()
        L_0x0052:
            return
        L_0x0053:
            r6 = move-exception
            goto L_0x0057
        L_0x0055:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0053 }
        L_0x0057:
            if (r2 == 0) goto L_0x005d
            r8.close()     // Catch:{ Throwable -> 0x0060 }
            goto L_0x0060
        L_0x005d:
            r8.close()     // Catch:{ all -> 0x0083 }
        L_0x0060:
            throw r6     // Catch:{ all -> 0x0083 }
        L_0x0061:
            r6 = move-exception
            r7 = r2
            goto L_0x006a
        L_0x0064:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0066 }
        L_0x0066:
            r7 = move-exception
            r5 = r7
            r7 = r6
            r6 = r5
        L_0x006a:
            if (r7 == 0) goto L_0x0070
            r3.close()     // Catch:{ Throwable -> 0x0073 }
            goto L_0x0073
        L_0x0070:
            r3.close()     // Catch:{ Throwable -> 0x0076 }
        L_0x0073:
            throw r6     // Catch:{ Throwable -> 0x0076 }
        L_0x0074:
            r6 = move-exception
            goto L_0x0079
        L_0x0076:
            r6 = move-exception
            r2 = r6
            throw r2     // Catch:{ all -> 0x0074 }
        L_0x0079:
            if (r2 == 0) goto L_0x007f
            r1.close()     // Catch:{ Throwable -> 0x0082 }
            goto L_0x0082
        L_0x007f:
            r1.close()     // Catch:{ all -> 0x0083 }
        L_0x0082:
            throw r6     // Catch:{ all -> 0x0083 }
        L_0x0083:
            r6 = move-exception
            boolean r7 = r0.delete()
            if (r7 != 0) goto L_0x008d
            r0.deleteOnExit()
        L_0x008d:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.compressors.pack200.Pack200Utils.a(java.io.File, java.io.File, java.util.Map):void");
    }
}
