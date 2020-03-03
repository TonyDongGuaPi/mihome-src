package org.apache.commons.compress.archivers.zip;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.commons.compress.parallel.FileBasedScatterGatherBackingStore;
import org.apache.commons.compress.parallel.ScatterGatherBackingStore;

public class ScatterZipOutputStream implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    private final Queue<CompressedEntry> f3261a = new ConcurrentLinkedQueue();
    private final ScatterGatherBackingStore b;
    private final StreamCompressor c;

    private static class CompressedEntry {

        /* renamed from: a  reason: collision with root package name */
        final ZipArchiveEntryRequest f3262a;
        final long b;
        final long c;
        final long d;

        public CompressedEntry(ZipArchiveEntryRequest zipArchiveEntryRequest, long j, long j2, long j3) {
            this.f3262a = zipArchiveEntryRequest;
            this.b = j;
            this.c = j2;
            this.d = j3;
        }

        public ZipArchiveEntry a() {
            ZipArchiveEntry c2 = this.f3262a.c();
            c2.setCompressedSize(this.c);
            c2.setSize(this.d);
            c2.setCrc(this.b);
            c2.setMethod(this.f3262a.b());
            return c2;
        }
    }

    public ScatterZipOutputStream(ScatterGatherBackingStore scatterGatherBackingStore, StreamCompressor streamCompressor) {
        this.b = scatterGatherBackingStore;
        this.c = streamCompressor;
    }

    public void a(ZipArchiveEntryRequest zipArchiveEntryRequest) throws IOException {
        Throwable th;
        InputStream a2 = zipArchiveEntryRequest.a();
        try {
            this.c.a(a2, zipArchiveEntryRequest.b());
            if (a2 != null) {
                a2.close();
            }
            this.f3261a.add(new CompressedEntry(zipArchiveEntryRequest, this.c.a(), this.c.c(), this.c.b()));
            return;
        } catch (Throwable unused) {
        }
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0030, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0031, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0036, code lost:
        r7 = r2;
        r2 = r9;
        r9 = r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream r9) throws java.io.IOException {
        /*
            r8 = this;
            org.apache.commons.compress.parallel.ScatterGatherBackingStore r0 = r8.b
            r0.b()
            org.apache.commons.compress.parallel.ScatterGatherBackingStore r0 = r8.b
            java.io.InputStream r0 = r0.a()
            r1 = 0
            java.util.Queue<org.apache.commons.compress.archivers.zip.ScatterZipOutputStream$CompressedEntry> r2 = r8.f3261a     // Catch:{ Throwable -> 0x004b }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ Throwable -> 0x004b }
        L_0x0012:
            boolean r3 = r2.hasNext()     // Catch:{ Throwable -> 0x004b }
            if (r3 == 0) goto L_0x0043
            java.lang.Object r3 = r2.next()     // Catch:{ Throwable -> 0x004b }
            org.apache.commons.compress.archivers.zip.ScatterZipOutputStream$CompressedEntry r3 = (org.apache.commons.compress.archivers.zip.ScatterZipOutputStream.CompressedEntry) r3     // Catch:{ Throwable -> 0x004b }
            org.apache.commons.compress.utils.BoundedInputStream r4 = new org.apache.commons.compress.utils.BoundedInputStream     // Catch:{ Throwable -> 0x004b }
            long r5 = r3.c     // Catch:{ Throwable -> 0x004b }
            r4.<init>(r0, r5)     // Catch:{ Throwable -> 0x004b }
            org.apache.commons.compress.archivers.zip.ZipArchiveEntry r3 = r3.a()     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            r9.a((org.apache.commons.compress.archivers.zip.ZipArchiveEntry) r3, (java.io.InputStream) r4)     // Catch:{ Throwable -> 0x0033, all -> 0x0030 }
            r4.close()     // Catch:{ Throwable -> 0x004b }
            goto L_0x0012
        L_0x0030:
            r9 = move-exception
            r2 = r1
            goto L_0x0039
        L_0x0033:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x0035 }
        L_0x0035:
            r2 = move-exception
            r7 = r2
            r2 = r9
            r9 = r7
        L_0x0039:
            if (r2 == 0) goto L_0x003f
            r4.close()     // Catch:{ Throwable -> 0x0042 }
            goto L_0x0042
        L_0x003f:
            r4.close()     // Catch:{ Throwable -> 0x004b }
        L_0x0042:
            throw r9     // Catch:{ Throwable -> 0x004b }
        L_0x0043:
            if (r0 == 0) goto L_0x0048
            r0.close()
        L_0x0048:
            return
        L_0x0049:
            r9 = move-exception
            goto L_0x004e
        L_0x004b:
            r9 = move-exception
            r1 = r9
            throw r1     // Catch:{ all -> 0x0049 }
        L_0x004e:
            if (r0 == 0) goto L_0x0059
            if (r1 == 0) goto L_0x0056
            r0.close()     // Catch:{ Throwable -> 0x0059 }
            goto L_0x0059
        L_0x0056:
            r0.close()
        L_0x0059:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.zip.ScatterZipOutputStream.a(org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream):void");
    }

    public void close() throws IOException {
        this.b.close();
        this.c.close();
    }

    public static ScatterZipOutputStream a(File file) throws FileNotFoundException {
        return a(file, -1);
    }

    public static ScatterZipOutputStream a(File file, int i) throws FileNotFoundException {
        FileBasedScatterGatherBackingStore fileBasedScatterGatherBackingStore = new FileBasedScatterGatherBackingStore(file);
        return new ScatterZipOutputStream(fileBasedScatterGatherBackingStore, StreamCompressor.a(i, (ScatterGatherBackingStore) fileBasedScatterGatherBackingStore));
    }
}
