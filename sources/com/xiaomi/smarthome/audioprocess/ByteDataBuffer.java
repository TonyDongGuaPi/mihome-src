package com.xiaomi.smarthome.audioprocess;

import java.util.ArrayList;

public class ByteDataBuffer {

    /* renamed from: a  reason: collision with root package name */
    final ArrayList<byte[]> f13751a = new ArrayList<>();

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0064, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(byte[] r7) {
        /*
            r6 = this;
            java.util.ArrayList<byte[]> r0 = r6.f13751a
            monitor-enter(r0)
            java.util.ArrayList<byte[]> r1 = r6.f13751a     // Catch:{ all -> 0x0066 }
            int r1 = r1.size()     // Catch:{ all -> 0x0066 }
            r2 = 0
            if (r1 != 0) goto L_0x000e
            monitor-exit(r0)     // Catch:{ all -> 0x0066 }
            return r2
        L_0x000e:
            r1 = 0
            r3 = 0
        L_0x0010:
            java.util.ArrayList<byte[]> r4 = r6.f13751a     // Catch:{ all -> 0x0066 }
            int r4 = r4.size()     // Catch:{ all -> 0x0066 }
            if (r1 >= r4) goto L_0x0029
            java.util.ArrayList<byte[]> r4 = r6.f13751a     // Catch:{ all -> 0x0066 }
            java.lang.Object r4 = r4.get(r1)     // Catch:{ all -> 0x0066 }
            byte[] r4 = (byte[]) r4     // Catch:{ all -> 0x0066 }
            int r4 = r4.length     // Catch:{ all -> 0x0066 }
            int r3 = r3 + r4
            int r4 = r7.length     // Catch:{ all -> 0x0066 }
            if (r3 < r4) goto L_0x0026
            goto L_0x0029
        L_0x0026:
            int r1 = r1 + 1
            goto L_0x0010
        L_0x0029:
            int r4 = r7.length     // Catch:{ all -> 0x0066 }
            if (r3 >= r4) goto L_0x002e
            monitor-exit(r0)     // Catch:{ all -> 0x0066 }
            return r2
        L_0x002e:
            r3 = 0
        L_0x002f:
            if (r1 <= 0) goto L_0x0042
            java.util.ArrayList<byte[]> r4 = r6.f13751a     // Catch:{ all -> 0x0066 }
            java.lang.Object r4 = r4.remove(r2)     // Catch:{ all -> 0x0066 }
            byte[] r4 = (byte[]) r4     // Catch:{ all -> 0x0066 }
            int r5 = r4.length     // Catch:{ all -> 0x0066 }
            java.lang.System.arraycopy(r4, r2, r7, r3, r5)     // Catch:{ all -> 0x0066 }
            int r4 = r4.length     // Catch:{ all -> 0x0066 }
            int r3 = r3 + r4
            int r1 = r1 + -1
            goto L_0x002f
        L_0x0042:
            int r1 = r7.length     // Catch:{ all -> 0x0066 }
            int r1 = r1 - r3
            java.util.ArrayList<byte[]> r4 = r6.f13751a     // Catch:{ all -> 0x0066 }
            java.lang.Object r4 = r4.remove(r2)     // Catch:{ all -> 0x0066 }
            byte[] r4 = (byte[]) r4     // Catch:{ all -> 0x0066 }
            int r5 = r4.length     // Catch:{ all -> 0x0066 }
            if (r5 <= r1) goto L_0x0060
            java.lang.System.arraycopy(r4, r2, r7, r3, r1)     // Catch:{ all -> 0x0066 }
            int r7 = r4.length     // Catch:{ all -> 0x0066 }
            int r7 = r7 - r1
            byte[] r7 = new byte[r7]     // Catch:{ all -> 0x0066 }
            int r3 = r7.length     // Catch:{ all -> 0x0066 }
            java.lang.System.arraycopy(r4, r1, r7, r2, r3)     // Catch:{ all -> 0x0066 }
            java.util.ArrayList<byte[]> r1 = r6.f13751a     // Catch:{ all -> 0x0066 }
            r1.add(r2, r7)     // Catch:{ all -> 0x0066 }
            goto L_0x0063
        L_0x0060:
            java.lang.System.arraycopy(r4, r2, r7, r3, r1)     // Catch:{ all -> 0x0066 }
        L_0x0063:
            monitor-exit(r0)     // Catch:{ all -> 0x0066 }
            r7 = 1
            return r7
        L_0x0066:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0066 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.audioprocess.ByteDataBuffer.a(byte[]):boolean");
    }

    public byte[] a() {
        synchronized (this.f13751a) {
            if (this.f13751a.size() == 0) {
                return null;
            }
            if (this.f13751a.size() == 1) {
                byte[] remove = this.f13751a.remove(0);
                return remove;
            }
            int i = 0;
            for (int i2 = 0; i2 < this.f13751a.size(); i2++) {
                i += this.f13751a.get(i2).length;
            }
            byte[] bArr = new byte[i];
            int i3 = 0;
            for (int i4 = 0; i4 < this.f13751a.size(); i4++) {
                System.arraycopy(this.f13751a.get(i4), 0, bArr, i3, this.f13751a.get(i4).length);
                i3 += this.f13751a.get(i4).length;
            }
            this.f13751a.clear();
            return bArr;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0067, code lost:
        r7.rewind();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006b, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.nio.ByteBuffer r7) {
        /*
            r6 = this;
            r7.clear()
            java.util.ArrayList<byte[]> r0 = r6.f13751a
            monitor-enter(r0)
            java.util.ArrayList<byte[]> r1 = r6.f13751a     // Catch:{ all -> 0x006c }
            int r1 = r1.size()     // Catch:{ all -> 0x006c }
            r2 = 0
            if (r1 != 0) goto L_0x0011
            monitor-exit(r0)     // Catch:{ all -> 0x006c }
            return r2
        L_0x0011:
            r1 = 0
            r3 = 0
        L_0x0013:
            java.util.ArrayList<byte[]> r4 = r6.f13751a     // Catch:{ all -> 0x006c }
            int r4 = r4.size()     // Catch:{ all -> 0x006c }
            if (r1 >= r4) goto L_0x002f
            java.util.ArrayList<byte[]> r4 = r6.f13751a     // Catch:{ all -> 0x006c }
            java.lang.Object r4 = r4.get(r1)     // Catch:{ all -> 0x006c }
            byte[] r4 = (byte[]) r4     // Catch:{ all -> 0x006c }
            int r4 = r4.length     // Catch:{ all -> 0x006c }
            int r3 = r3 + r4
            int r4 = r7.remaining()     // Catch:{ all -> 0x006c }
            if (r3 < r4) goto L_0x002c
            goto L_0x002f
        L_0x002c:
            int r1 = r1 + 1
            goto L_0x0013
        L_0x002f:
            int r4 = r7.remaining()     // Catch:{ all -> 0x006c }
            if (r3 >= r4) goto L_0x0037
            monitor-exit(r0)     // Catch:{ all -> 0x006c }
            return r2
        L_0x0037:
            if (r1 <= 0) goto L_0x0047
            java.util.ArrayList<byte[]> r3 = r6.f13751a     // Catch:{ all -> 0x006c }
            java.lang.Object r3 = r3.remove(r2)     // Catch:{ all -> 0x006c }
            byte[] r3 = (byte[]) r3     // Catch:{ all -> 0x006c }
            r7.put(r3)     // Catch:{ all -> 0x006c }
            int r1 = r1 + -1
            goto L_0x0037
        L_0x0047:
            int r1 = r7.remaining()     // Catch:{ all -> 0x006c }
            java.util.ArrayList<byte[]> r3 = r6.f13751a     // Catch:{ all -> 0x006c }
            java.lang.Object r3 = r3.remove(r2)     // Catch:{ all -> 0x006c }
            byte[] r3 = (byte[]) r3     // Catch:{ all -> 0x006c }
            r7.put(r3, r2, r1)     // Catch:{ all -> 0x006c }
            int r4 = r3.length     // Catch:{ all -> 0x006c }
            if (r4 <= r1) goto L_0x0066
            int r4 = r3.length     // Catch:{ all -> 0x006c }
            int r4 = r4 - r1
            byte[] r4 = new byte[r4]     // Catch:{ all -> 0x006c }
            int r5 = r4.length     // Catch:{ all -> 0x006c }
            java.lang.System.arraycopy(r3, r1, r4, r2, r5)     // Catch:{ all -> 0x006c }
            java.util.ArrayList<byte[]> r1 = r6.f13751a     // Catch:{ all -> 0x006c }
            r1.add(r2, r4)     // Catch:{ all -> 0x006c }
        L_0x0066:
            monitor-exit(r0)     // Catch:{ all -> 0x006c }
            r7.rewind()
            r7 = 1
            return r7
        L_0x006c:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x006c }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.audioprocess.ByteDataBuffer.a(java.nio.ByteBuffer):boolean");
    }

    public void b(byte[] bArr) {
        synchronized (this.f13751a) {
            this.f13751a.add(bArr);
        }
    }

    public void b() {
        synchronized (this.f13751a) {
            this.f13751a.clear();
        }
    }

    public int c() {
        int size;
        synchronized (this.f13751a) {
            size = this.f13751a.size();
        }
        return size;
    }
}
