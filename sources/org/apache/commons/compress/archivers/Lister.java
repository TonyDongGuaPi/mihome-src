package org.apache.commons.compress.archivers;

import java.io.InputStream;

public final class Lister {

    /* renamed from: a  reason: collision with root package name */
    private static final ArchiveStreamFactory f3200a = new ArchiveStreamFactory();

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0085, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0086, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x008a, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x008b, code lost:
        r5 = r3;
        r3 = r2;
        r2 = r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.lang.String[] r6) throws java.lang.Exception {
        /*
            int r0 = r6.length
            if (r0 != 0) goto L_0x0007
            a()
            return
        L_0x0007:
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Analysing "
            r1.append(r2)
            r2 = 0
            r3 = r6[r2]
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            java.io.File r0 = new java.io.File
            r1 = r6[r2]
            r0.<init>(r1)
            boolean r1 = r0.isFile()
            if (r1 != 0) goto L_0x0043
            java.io.PrintStream r1 = java.lang.System.err
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r3 = " doesn't exist or is a directory"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.println(r2)
        L_0x0043:
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream
            java.io.FileInputStream r2 = new java.io.FileInputStream
            r2.<init>(r0)
            r1.<init>(r2)
            r0 = 0
            org.apache.commons.compress.archivers.ArchiveInputStream r6 = a(r6, r1)     // Catch:{ Throwable -> 0x009c }
            java.io.PrintStream r2 = java.lang.System.out     // Catch:{ Throwable -> 0x0088, all -> 0x0085 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0088, all -> 0x0085 }
            r3.<init>()     // Catch:{ Throwable -> 0x0088, all -> 0x0085 }
            java.lang.String r4 = "Created "
            r3.append(r4)     // Catch:{ Throwable -> 0x0088, all -> 0x0085 }
            java.lang.String r4 = r6.toString()     // Catch:{ Throwable -> 0x0088, all -> 0x0085 }
            r3.append(r4)     // Catch:{ Throwable -> 0x0088, all -> 0x0085 }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x0088, all -> 0x0085 }
            r2.println(r3)     // Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        L_0x006c:
            org.apache.commons.compress.archivers.ArchiveEntry r2 = r6.a()     // Catch:{ Throwable -> 0x0088, all -> 0x0085 }
            if (r2 == 0) goto L_0x007c
            java.io.PrintStream r3 = java.lang.System.out     // Catch:{ Throwable -> 0x0088, all -> 0x0085 }
            java.lang.String r2 = r2.getName()     // Catch:{ Throwable -> 0x0088, all -> 0x0085 }
            r3.println(r2)     // Catch:{ Throwable -> 0x0088, all -> 0x0085 }
            goto L_0x006c
        L_0x007c:
            if (r6 == 0) goto L_0x0081
            r6.close()     // Catch:{ Throwable -> 0x009c }
        L_0x0081:
            r1.close()
            return
        L_0x0085:
            r2 = move-exception
            r3 = r0
            goto L_0x008e
        L_0x0088:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x008a }
        L_0x008a:
            r3 = move-exception
            r5 = r3
            r3 = r2
            r2 = r5
        L_0x008e:
            if (r6 == 0) goto L_0x0099
            if (r3 == 0) goto L_0x0096
            r6.close()     // Catch:{ Throwable -> 0x0099 }
            goto L_0x0099
        L_0x0096:
            r6.close()     // Catch:{ Throwable -> 0x009c }
        L_0x0099:
            throw r2     // Catch:{ Throwable -> 0x009c }
        L_0x009a:
            r6 = move-exception
            goto L_0x009f
        L_0x009c:
            r6 = move-exception
            r0 = r6
            throw r0     // Catch:{ all -> 0x009a }
        L_0x009f:
            if (r0 == 0) goto L_0x00a5
            r1.close()     // Catch:{ Throwable -> 0x00a8 }
            goto L_0x00a8
        L_0x00a5:
            r1.close()
        L_0x00a8:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.Lister.a(java.lang.String[]):void");
    }

    private static ArchiveInputStream a(String[] strArr, InputStream inputStream) throws ArchiveException {
        if (strArr.length > 1) {
            return f3200a.a(strArr[1], inputStream);
        }
        return f3200a.a(inputStream);
    }

    private static void a() {
        System.out.println("Parameters: archive-name [archive-type]");
    }
}
