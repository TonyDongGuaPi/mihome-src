package com.xiaomi.youpin.hawkeye.network;

import com.xiaomi.youpin.hawkeye.utils.StreamUtil;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.zip.GZIPInputStream;

public class GunzippingOutputStream extends FilterOutputStream {
    private static final ExecutorService b = Executors.newCachedThreadPool();

    /* renamed from: a  reason: collision with root package name */
    private final Future<Void> f23365a;

    public static GunzippingOutputStream a(OutputStream outputStream) throws IOException {
        PipedInputStream pipedInputStream = new PipedInputStream();
        return new GunzippingOutputStream(new PipedOutputStream(pipedInputStream), b.submit(new GunzippingCallable(pipedInputStream, outputStream)));
    }

    private GunzippingOutputStream(OutputStream outputStream, Future<Void> future) throws IOException {
        super(outputStream);
        this.f23365a = future;
    }

    public void close() throws IOException {
        try {
            super.close();
            try {
            } catch (IOException e) {
                throw e;
            }
        } finally {
            try {
                a(this.f23365a);
            } catch (IOException unused) {
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:0|1|2) */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0005, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0006, code lost:
        r0 = r0.getCause();
        com.xiaomi.youpin.hawkeye.utils.ExceptionUtil.a(r0, java.io.IOException.class);
        com.xiaomi.youpin.hawkeye.utils.ExceptionUtil.a(r0);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:0:0x0000 */
    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:4:0x0006, LOOP_START, MTH_ENTER_BLOCK, SYNTHETIC, Splitter:B:0:0x0000] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <T> T a(java.util.concurrent.Future<T> r2) throws java.io.IOException {
        /*
        L_0x0000:
            java.lang.Object r0 = r2.get()     // Catch:{ InterruptedException -> 0x0000, ExecutionException -> 0x0005 }
            return r0
        L_0x0005:
            r0 = move-exception
            java.lang.Throwable r0 = r0.getCause()
            java.lang.Class<java.io.IOException> r1 = java.io.IOException.class
            com.xiaomi.youpin.hawkeye.utils.ExceptionUtil.a(r0, r1)
            com.xiaomi.youpin.hawkeye.utils.ExceptionUtil.a(r0)
            goto L_0x0000
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.hawkeye.network.GunzippingOutputStream.a(java.util.concurrent.Future):java.lang.Object");
    }

    private static class GunzippingCallable implements Callable<Void> {

        /* renamed from: a  reason: collision with root package name */
        private final InputStream f23366a;
        private final OutputStream b;

        public GunzippingCallable(InputStream inputStream, OutputStream outputStream) {
            this.f23366a = inputStream;
            this.b = outputStream;
        }

        /* JADX INFO: finally extract failed */
        /* renamed from: a */
        public Void call() throws IOException {
            GZIPInputStream gZIPInputStream = new GZIPInputStream(this.f23366a);
            try {
                StreamUtil.a(gZIPInputStream, this.b, new byte[1024]);
                gZIPInputStream.close();
                this.b.close();
                return null;
            } catch (Throwable th) {
                gZIPInputStream.close();
                this.b.close();
                throw th;
            }
        }
    }
}
