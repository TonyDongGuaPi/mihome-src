package com.mibi.common.data;

import java.io.File;
import java.util.HashMap;

public class StorageFile extends File {

    /* renamed from: a  reason: collision with root package name */
    private static HashMap<String, Object> f7547a = new HashMap<>();

    public StorageFile(File file, String str) {
        super(file, str);
    }

    public StorageFile(String str, String str2) {
        super(str, str2);
    }

    public StorageFile(String str) {
        super(str);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:26|(2:34|35)|(2:38|39)|(2:42|43)|44|45) */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:49|(2:50|51)|(2:55|56)|(2:59|60)|61|62|63) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:9|10|(2:11|12)|(2:15|16)|17|18|19|20|21) */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x001f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0022 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:44:0x0042 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:61:0x0057 */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0035 A[SYNTHETIC, Splitter:B:34:0x0035] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x003a A[SYNTHETIC, Splitter:B:38:0x003a] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x003f A[SYNTHETIC, Splitter:B:42:0x003f] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0047 A[SYNTHETIC, Splitter:B:50:0x0047] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x004f A[SYNTHETIC, Splitter:B:55:0x004f] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0054 A[SYNTHETIC, Splitter:B:59:0x0054] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:19:0x0022=Splitter:B:19:0x0022, B:44:0x0042=Splitter:B:44:0x0042, B:61:0x0057=Splitter:B:61:0x0057} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeObject(java.io.Serializable r6) {
        /*
            r5 = this;
            java.lang.Object r0 = getFileLock(r5)
            monitor-enter(r0)
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0043, all -> 0x0030 }
            r2.<init>(r5)     // Catch:{ Exception -> 0x0043, all -> 0x0030 }
            java.io.OutputStream r3 = com.mibi.common.data.Coder.a((java.io.OutputStream) r2)     // Catch:{ Exception -> 0x002e, all -> 0x002b }
            java.io.ObjectOutputStream r4 = new java.io.ObjectOutputStream     // Catch:{ Exception -> 0x0045, all -> 0x0029 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x0045, all -> 0x0029 }
            r4.writeObject(r6)     // Catch:{ Exception -> 0x0027, all -> 0x0024 }
            r4.close()     // Catch:{ IOException -> 0x001a }
        L_0x001a:
            if (r3 == 0) goto L_0x001f
            r3.close()     // Catch:{ IOException -> 0x001f }
        L_0x001f:
            r2.close()     // Catch:{ IOException -> 0x0022 }
        L_0x0022:
            monitor-exit(r0)     // Catch:{ all -> 0x004b }
            return
        L_0x0024:
            r6 = move-exception
            r1 = r4
            goto L_0x0033
        L_0x0027:
            r1 = r4
            goto L_0x0045
        L_0x0029:
            r6 = move-exception
            goto L_0x0033
        L_0x002b:
            r6 = move-exception
            r3 = r1
            goto L_0x0033
        L_0x002e:
            r3 = r1
            goto L_0x0045
        L_0x0030:
            r6 = move-exception
            r2 = r1
            r3 = r2
        L_0x0033:
            if (r1 == 0) goto L_0x0038
            r1.close()     // Catch:{ IOException -> 0x0038 }
        L_0x0038:
            if (r3 == 0) goto L_0x003d
            r3.close()     // Catch:{ IOException -> 0x003d }
        L_0x003d:
            if (r2 == 0) goto L_0x0042
            r2.close()     // Catch:{ IOException -> 0x0042 }
        L_0x0042:
            throw r6     // Catch:{ all -> 0x004b }
        L_0x0043:
            r2 = r1
            r3 = r2
        L_0x0045:
            if (r1 == 0) goto L_0x004d
            r1.close()     // Catch:{ IOException -> 0x004d }
            goto L_0x004d
        L_0x004b:
            r6 = move-exception
            goto L_0x0059
        L_0x004d:
            if (r3 == 0) goto L_0x0052
            r3.close()     // Catch:{ IOException -> 0x0052 }
        L_0x0052:
            if (r2 == 0) goto L_0x0057
            r2.close()     // Catch:{ IOException -> 0x0057 }
        L_0x0057:
            monitor-exit(r0)     // Catch:{ all -> 0x004b }
            return
        L_0x0059:
            monitor-exit(r0)     // Catch:{ all -> 0x004b }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.data.StorageFile.writeObject(java.io.Serializable):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(17:2|3|4|5|6|7|8|9|10|11|12|(2:15|16)|17|18|19|20|21) */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:23|24|(0)|(0)|(0)|44|45) */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:26|25|(0)|(0)|(0)|62|63|64) */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0022 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0025 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:44:0x004b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:62:0x0061 */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x003e A[SYNTHETIC, Splitter:B:34:0x003e] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0043 A[SYNTHETIC, Splitter:B:38:0x0043] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0048 A[SYNTHETIC, Splitter:B:42:0x0048] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0051 A[SYNTHETIC, Splitter:B:51:0x0051] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0059 A[SYNTHETIC, Splitter:B:56:0x0059] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x005e A[SYNTHETIC, Splitter:B:60:0x005e] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:62:0x0061=Splitter:B:62:0x0061, B:19:0x0025=Splitter:B:19:0x0025, B:44:0x004b=Splitter:B:44:0x004b} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.Serializable readObject() {
        /*
            r7 = this;
            java.lang.Object r0 = getFileLock(r7)
            monitor-enter(r0)
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x004c, all -> 0x0037 }
            r2.<init>(r7)     // Catch:{ Exception -> 0x004c, all -> 0x0037 }
            java.io.InputStream r3 = com.mibi.common.data.Coder.a((java.io.InputStream) r2)     // Catch:{ Exception -> 0x0035, all -> 0x0030 }
            java.io.ObjectInputStream r4 = new java.io.ObjectInputStream     // Catch:{ Exception -> 0x002e, all -> 0x0029 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x002e, all -> 0x0029 }
            java.lang.Object r5 = r4.readObject()     // Catch:{ Exception -> 0x004f, all -> 0x0027 }
            java.io.Serializable r5 = (java.io.Serializable) r5     // Catch:{ Exception -> 0x004f, all -> 0x0027 }
            r4.close()     // Catch:{ IOException -> 0x001d }
        L_0x001d:
            if (r3 == 0) goto L_0x0022
            r3.close()     // Catch:{ IOException -> 0x0022 }
        L_0x0022:
            r2.close()     // Catch:{ IOException -> 0x0025 }
        L_0x0025:
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            return r5
        L_0x0027:
            r1 = move-exception
            goto L_0x003c
        L_0x0029:
            r4 = move-exception
            r6 = r4
            r4 = r1
            r1 = r6
            goto L_0x003c
        L_0x002e:
            r4 = r1
            goto L_0x004f
        L_0x0030:
            r3 = move-exception
            r4 = r1
            r1 = r3
            r3 = r4
            goto L_0x003c
        L_0x0035:
            r3 = r1
            goto L_0x004e
        L_0x0037:
            r2 = move-exception
            r3 = r1
            r4 = r3
            r1 = r2
            r2 = r4
        L_0x003c:
            if (r4 == 0) goto L_0x0041
            r4.close()     // Catch:{ IOException -> 0x0041 }
        L_0x0041:
            if (r3 == 0) goto L_0x0046
            r3.close()     // Catch:{ IOException -> 0x0046 }
        L_0x0046:
            if (r2 == 0) goto L_0x004b
            r2.close()     // Catch:{ IOException -> 0x004b }
        L_0x004b:
            throw r1     // Catch:{ all -> 0x0055 }
        L_0x004c:
            r2 = r1
            r3 = r2
        L_0x004e:
            r4 = r3
        L_0x004f:
            if (r4 == 0) goto L_0x0057
            r4.close()     // Catch:{ IOException -> 0x0057 }
            goto L_0x0057
        L_0x0055:
            r1 = move-exception
            goto L_0x0063
        L_0x0057:
            if (r3 == 0) goto L_0x005c
            r3.close()     // Catch:{ IOException -> 0x005c }
        L_0x005c:
            if (r2 == 0) goto L_0x0061
            r2.close()     // Catch:{ IOException -> 0x0061 }
        L_0x0061:
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            return r1
        L_0x0063:
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.data.StorageFile.readObject():java.io.Serializable");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0020 A[Catch:{ Exception -> 0x0025, all -> 0x001a, all -> 0x002b }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0027 A[Catch:{ Exception -> 0x0025, all -> 0x001a, all -> 0x002b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(java.lang.String r4) {
        /*
            r3 = this;
            java.lang.Object r0 = getFileLock(r3)
            monitor-enter(r0)
            r1 = 0
            java.io.PrintWriter r2 = new java.io.PrintWriter     // Catch:{ Exception -> 0x0024, all -> 0x001c }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0024, all -> 0x001c }
            java.lang.String r4 = com.mibi.common.data.Coder.e(r4)     // Catch:{ Exception -> 0x0025, all -> 0x001a }
            r2.println(r4)     // Catch:{ Exception -> 0x0025, all -> 0x001a }
            r2.flush()     // Catch:{ Exception -> 0x0025, all -> 0x001a }
            r2.close()     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x001a:
            r4 = move-exception
            goto L_0x001e
        L_0x001c:
            r4 = move-exception
            r2 = r1
        L_0x001e:
            if (r2 == 0) goto L_0x0023
            r2.close()     // Catch:{ all -> 0x002b }
        L_0x0023:
            throw r4     // Catch:{ all -> 0x002b }
        L_0x0024:
            r2 = r1
        L_0x0025:
            if (r2 == 0) goto L_0x002d
            r2.close()     // Catch:{ all -> 0x002b }
            goto L_0x002d
        L_0x002b:
            r4 = move-exception
            goto L_0x002f
        L_0x002d:
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002f:
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.data.StorageFile.write(java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0031 A[SYNTHETIC, Splitter:B:26:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x003c A[SYNTHETIC, Splitter:B:34:0x003c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeBytes(byte[] r5) {
        /*
            r4 = this;
            java.lang.Object r0 = getFileLock(r4)
            monitor-enter(r0)
            if (r5 != 0) goto L_0x000e
            super.delete()     // Catch:{ all -> 0x000c }
            monitor-exit(r0)     // Catch:{ all -> 0x000c }
            return
        L_0x000c:
            r5 = move-exception
            goto L_0x0046
        L_0x000e:
            r1 = 0
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x003a, all -> 0x002e }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x003a, all -> 0x002e }
            r3.<init>(r4)     // Catch:{ Exception -> 0x003a, all -> 0x002e }
            r2.<init>(r3)     // Catch:{ Exception -> 0x003a, all -> 0x002e }
            r2.write(r5)     // Catch:{ Exception -> 0x002c, all -> 0x0029 }
            r2.flush()     // Catch:{ Exception -> 0x002c, all -> 0x0029 }
            r2.close()     // Catch:{ IOException -> 0x0023 }
            goto L_0x0027
        L_0x0023:
            r5 = move-exception
            r5.printStackTrace()     // Catch:{ all -> 0x000c }
        L_0x0027:
            monitor-exit(r0)     // Catch:{ all -> 0x000c }
            return
        L_0x0029:
            r5 = move-exception
            r1 = r2
            goto L_0x002f
        L_0x002c:
            r1 = r2
            goto L_0x003a
        L_0x002e:
            r5 = move-exception
        L_0x002f:
            if (r1 == 0) goto L_0x0039
            r1.close()     // Catch:{ IOException -> 0x0035 }
            goto L_0x0039
        L_0x0035:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x000c }
        L_0x0039:
            throw r5     // Catch:{ all -> 0x000c }
        L_0x003a:
            if (r1 == 0) goto L_0x0044
            r1.close()     // Catch:{ IOException -> 0x0040 }
            goto L_0x0044
        L_0x0040:
            r5 = move-exception
            r5.printStackTrace()     // Catch:{ all -> 0x000c }
        L_0x0044:
            monitor-exit(r0)     // Catch:{ all -> 0x000c }
            return
        L_0x0046:
            monitor-exit(r0)     // Catch:{ all -> 0x000c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.data.StorageFile.writeBytes(byte[]):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x002c A[SYNTHETIC, Splitter:B:18:0x002c] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0038 A[SYNTHETIC, Splitter:B:28:0x0038] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] readBytes() {
        /*
            r6 = this;
            java.lang.Object r0 = getFileLock(r6)
            monitor-enter(r0)
            r1 = 0
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0035, all -> 0x0029 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0035, all -> 0x0029 }
            r3.<init>(r6)     // Catch:{ Exception -> 0x0035, all -> 0x0029 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0035, all -> 0x0029 }
            long r3 = super.length()     // Catch:{ Exception -> 0x0036, all -> 0x0024 }
            int r3 = (int) r3     // Catch:{ Exception -> 0x0036, all -> 0x0024 }
            byte[] r3 = new byte[r3]     // Catch:{ Exception -> 0x0036, all -> 0x0024 }
            r2.read(r3)     // Catch:{ Exception -> 0x0036, all -> 0x0024 }
            r2.close()     // Catch:{ IOException -> 0x001e }
            goto L_0x0022
        L_0x001e:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x003c }
        L_0x0022:
            monitor-exit(r0)     // Catch:{ all -> 0x003c }
            return r3
        L_0x0024:
            r1 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
            goto L_0x002a
        L_0x0029:
            r2 = move-exception
        L_0x002a:
            if (r1 == 0) goto L_0x0034
            r1.close()     // Catch:{ IOException -> 0x0030 }
            goto L_0x0034
        L_0x0030:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x003c }
        L_0x0034:
            throw r2     // Catch:{ all -> 0x003c }
        L_0x0035:
            r2 = r1
        L_0x0036:
            if (r2 == 0) goto L_0x0042
            r2.close()     // Catch:{ IOException -> 0x003e }
            goto L_0x0042
        L_0x003c:
            r1 = move-exception
            goto L_0x0044
        L_0x003e:
            r2 = move-exception
            r2.printStackTrace()     // Catch:{ all -> 0x003c }
        L_0x0042:
            monitor-exit(r0)     // Catch:{ all -> 0x003c }
            return r1
        L_0x0044:
            monitor-exit(r0)     // Catch:{ all -> 0x003c }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.data.StorageFile.readBytes():byte[]");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:9|10|11|12|13|14|15|16|17|18|19) */
    /* JADX WARNING: Can't wrap try/catch for region: R(13:7|8|9|10|11|12|13|14|15|16|17|18|19) */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:26|27|(0)|(0)|(0)|41|42) */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:29|28|(0)|(0)|(0)|56|57|58) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0026 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0029 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x0049 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:56:0x005e */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x003c A[Catch:{ Exception -> 0x002e, all -> 0x002b }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0041 A[SYNTHETIC, Splitter:B:35:0x0041] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0046 A[SYNTHETIC, Splitter:B:39:0x0046] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x004e A[Catch:{ Exception -> 0x002e, all -> 0x002b }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0056 A[SYNTHETIC, Splitter:B:50:0x0056] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x005b A[SYNTHETIC, Splitter:B:54:0x005b] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:17:0x0029=Splitter:B:17:0x0029, B:41:0x0049=Splitter:B:41:0x0049, B:56:0x005e=Splitter:B:56:0x005e} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void append(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.Object r0 = getFileLock(r5)
            monitor-enter(r0)
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x004a, all -> 0x0037 }
            r3 = 1
            r2.<init>(r5, r3)     // Catch:{ Exception -> 0x004a, all -> 0x0037 }
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x0035, all -> 0x0032 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x0035, all -> 0x0032 }
            java.io.PrintWriter r4 = new java.io.PrintWriter     // Catch:{ Exception -> 0x004c, all -> 0x0030 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x004c, all -> 0x0030 }
            java.lang.String r6 = com.mibi.common.data.Coder.e(r6)     // Catch:{ Exception -> 0x002e, all -> 0x002b }
            r4.println(r6)     // Catch:{ Exception -> 0x002e, all -> 0x002b }
            r4.flush()     // Catch:{ Exception -> 0x002e, all -> 0x002b }
            r4.close()     // Catch:{ all -> 0x0052 }
            r3.close()     // Catch:{ IOException -> 0x0026 }
        L_0x0026:
            r2.close()     // Catch:{ IOException -> 0x0029 }
        L_0x0029:
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            return
        L_0x002b:
            r6 = move-exception
            r1 = r4
            goto L_0x003a
        L_0x002e:
            r1 = r4
            goto L_0x004c
        L_0x0030:
            r6 = move-exception
            goto L_0x003a
        L_0x0032:
            r6 = move-exception
            r3 = r1
            goto L_0x003a
        L_0x0035:
            r3 = r1
            goto L_0x004c
        L_0x0037:
            r6 = move-exception
            r2 = r1
            r3 = r2
        L_0x003a:
            if (r1 == 0) goto L_0x003f
            r1.close()     // Catch:{ all -> 0x0052 }
        L_0x003f:
            if (r3 == 0) goto L_0x0044
            r3.close()     // Catch:{ IOException -> 0x0044 }
        L_0x0044:
            if (r2 == 0) goto L_0x0049
            r2.close()     // Catch:{ IOException -> 0x0049 }
        L_0x0049:
            throw r6     // Catch:{ all -> 0x0052 }
        L_0x004a:
            r2 = r1
            r3 = r2
        L_0x004c:
            if (r1 == 0) goto L_0x0054
            r1.close()     // Catch:{ all -> 0x0052 }
            goto L_0x0054
        L_0x0052:
            r6 = move-exception
            goto L_0x0060
        L_0x0054:
            if (r3 == 0) goto L_0x0059
            r3.close()     // Catch:{ IOException -> 0x0059 }
        L_0x0059:
            if (r2 == 0) goto L_0x005e
            r2.close()     // Catch:{ IOException -> 0x005e }
        L_0x005e:
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            return
        L_0x0060:
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.data.StorageFile.append(java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0042 A[Catch:{ Exception -> 0x0047, all -> 0x003c }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0049 A[Catch:{ Exception -> 0x0047, all -> 0x003c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String read() {
        /*
            r6 = this;
            java.lang.Object r0 = getFileLock(r6)
            monitor-enter(r0)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x004e }
            r1.<init>()     // Catch:{ all -> 0x004e }
            r2 = 0
            java.util.Scanner r3 = new java.util.Scanner     // Catch:{ Exception -> 0x0046, all -> 0x003e }
            r3.<init>(r6)     // Catch:{ Exception -> 0x0046, all -> 0x003e }
        L_0x0010:
            boolean r4 = r3.hasNextLine()     // Catch:{ Exception -> 0x0047, all -> 0x003c }
            if (r4 == 0) goto L_0x0033
            java.lang.String r4 = r3.nextLine()     // Catch:{ Exception -> 0x0047, all -> 0x003c }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0047, all -> 0x003c }
            r5.<init>()     // Catch:{ Exception -> 0x0047, all -> 0x003c }
            java.lang.String r4 = com.mibi.common.data.Coder.g(r4)     // Catch:{ Exception -> 0x0047, all -> 0x003c }
            r5.append(r4)     // Catch:{ Exception -> 0x0047, all -> 0x003c }
            java.lang.String r4 = "\n"
            r5.append(r4)     // Catch:{ Exception -> 0x0047, all -> 0x003c }
            java.lang.String r4 = r5.toString()     // Catch:{ Exception -> 0x0047, all -> 0x003c }
            r1.append(r4)     // Catch:{ Exception -> 0x0047, all -> 0x003c }
            goto L_0x0010
        L_0x0033:
            r3.close()     // Catch:{ all -> 0x004e }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x004e }
            monitor-exit(r0)     // Catch:{ all -> 0x004e }
            return r1
        L_0x003c:
            r1 = move-exception
            goto L_0x0040
        L_0x003e:
            r1 = move-exception
            r3 = r2
        L_0x0040:
            if (r3 == 0) goto L_0x0045
            r3.close()     // Catch:{ all -> 0x004e }
        L_0x0045:
            throw r1     // Catch:{ all -> 0x004e }
        L_0x0046:
            r3 = r2
        L_0x0047:
            if (r3 == 0) goto L_0x004c
            r3.close()     // Catch:{ all -> 0x004e }
        L_0x004c:
            monitor-exit(r0)     // Catch:{ all -> 0x004e }
            return r2
        L_0x004e:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004e }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.data.StorageFile.read():java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002d, code lost:
        return r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0034 A[Catch:{ Exception -> 0x0039, all -> 0x002e, all -> 0x003f }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x003b A[Catch:{ Exception -> 0x0039, all -> 0x002e, all -> 0x003f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<java.lang.String> readAndSplit() {
        /*
            r5 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.Object r1 = getFileLock(r5)
            monitor-enter(r1)
            r2 = 0
            java.util.Scanner r3 = new java.util.Scanner     // Catch:{ Exception -> 0x0038, all -> 0x0030 }
            r3.<init>(r5)     // Catch:{ Exception -> 0x0038, all -> 0x0030 }
        L_0x0010:
            boolean r4 = r3.hasNextLine()     // Catch:{ Exception -> 0x0039, all -> 0x002e }
            if (r4 == 0) goto L_0x0022
            java.lang.String r4 = r3.nextLine()     // Catch:{ Exception -> 0x0039, all -> 0x002e }
            java.lang.String r4 = com.mibi.common.data.Coder.g(r4)     // Catch:{ Exception -> 0x0039, all -> 0x002e }
            r0.add(r4)     // Catch:{ Exception -> 0x0039, all -> 0x002e }
            goto L_0x0010
        L_0x0022:
            r3.close()     // Catch:{ all -> 0x003f }
            boolean r3 = r0.isEmpty()     // Catch:{ all -> 0x003f }
            if (r3 == 0) goto L_0x002c
            r0 = r2
        L_0x002c:
            monitor-exit(r1)     // Catch:{ all -> 0x003f }
            return r0
        L_0x002e:
            r0 = move-exception
            goto L_0x0032
        L_0x0030:
            r0 = move-exception
            r3 = r2
        L_0x0032:
            if (r3 == 0) goto L_0x0037
            r3.close()     // Catch:{ all -> 0x003f }
        L_0x0037:
            throw r0     // Catch:{ all -> 0x003f }
        L_0x0038:
            r3 = r2
        L_0x0039:
            if (r3 == 0) goto L_0x0041
            r3.close()     // Catch:{ all -> 0x003f }
            goto L_0x0041
        L_0x003f:
            r0 = move-exception
            goto L_0x0043
        L_0x0041:
            monitor-exit(r1)     // Catch:{ all -> 0x003f }
            return r2
        L_0x0043:
            monitor-exit(r1)     // Catch:{ all -> 0x003f }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.data.StorageFile.readAndSplit():java.util.ArrayList");
    }

    public boolean delete() {
        boolean delete;
        synchronized (getFileLock(this)) {
            delete = super.delete();
        }
        return delete;
    }

    public long lastModified() {
        long lastModified;
        synchronized (getFileLock(this)) {
            lastModified = super.lastModified();
        }
        return lastModified;
    }

    public long length() {
        long length;
        synchronized (getFileLock(this)) {
            length = super.length();
        }
        return length;
    }

    static Object getFileLock(File file) {
        Object obj;
        synchronized (f7547a) {
            String absolutePath = file.getAbsolutePath();
            obj = f7547a.get(absolutePath);
            if (obj == null) {
                obj = new Object();
                f7547a.put(absolutePath, obj);
            }
        }
        return obj;
    }
}
