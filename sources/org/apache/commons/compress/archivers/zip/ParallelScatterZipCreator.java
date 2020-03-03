package org.apache.commons.compress.archivers.zip;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.compress.parallel.FileBasedScatterGatherBackingStore;
import org.apache.commons.compress.parallel.InputStreamSupplier;
import org.apache.commons.compress.parallel.ScatterGatherBackingStore;
import org.apache.commons.compress.parallel.ScatterGatherBackingStoreSupplier;

public class ParallelScatterZipCreator {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final List<ScatterZipOutputStream> f3255a;
    private final ExecutorService b;
    /* access modifiers changed from: private */
    public final ScatterGatherBackingStoreSupplier c;
    private final List<Future<Object>> d;
    private final long e;
    private long f;
    private long g;
    /* access modifiers changed from: private */
    public final ThreadLocal<ScatterZipOutputStream> h;

    private static class DefaultBackingStoreSupplier implements ScatterGatherBackingStoreSupplier {

        /* renamed from: a  reason: collision with root package name */
        final AtomicInteger f3259a;

        private DefaultBackingStoreSupplier() {
            this.f3259a = new AtomicInteger(0);
        }

        public ScatterGatherBackingStore a() throws IOException {
            return new FileBasedScatterGatherBackingStore(File.createTempFile("parallelscatter", "n" + this.f3259a.incrementAndGet()));
        }
    }

    /* access modifiers changed from: private */
    public ScatterZipOutputStream a(ScatterGatherBackingStoreSupplier scatterGatherBackingStoreSupplier) throws IOException {
        ScatterGatherBackingStore a2 = scatterGatherBackingStoreSupplier.a();
        return new ScatterZipOutputStream(a2, StreamCompressor.a(-1, a2));
    }

    public ParallelScatterZipCreator() {
        this(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
    }

    public ParallelScatterZipCreator(ExecutorService executorService) {
        this(executorService, new DefaultBackingStoreSupplier());
    }

    public ParallelScatterZipCreator(ExecutorService executorService, ScatterGatherBackingStoreSupplier scatterGatherBackingStoreSupplier) {
        this.f3255a = Collections.synchronizedList(new ArrayList());
        this.d = new ArrayList();
        this.e = System.currentTimeMillis();
        this.f = 0;
        this.h = new ThreadLocal<ScatterZipOutputStream>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public ScatterZipOutputStream initialValue() {
                try {
                    ScatterZipOutputStream a2 = ParallelScatterZipCreator.this.a(ParallelScatterZipCreator.this.c);
                    ParallelScatterZipCreator.this.f3255a.add(a2);
                    return a2;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        this.c = scatterGatherBackingStoreSupplier;
        this.b = executorService;
    }

    public void a(ZipArchiveEntry zipArchiveEntry, InputStreamSupplier inputStreamSupplier) {
        a(b(zipArchiveEntry, inputStreamSupplier));
    }

    public void a(ZipArchiveEntryRequestSupplier zipArchiveEntryRequestSupplier) {
        a(b(zipArchiveEntryRequestSupplier));
    }

    public final void a(Callable<Object> callable) {
        this.d.add(this.b.submit(callable));
    }

    public final Callable<Object> b(ZipArchiveEntry zipArchiveEntry, InputStreamSupplier inputStreamSupplier) {
        if (zipArchiveEntry.getMethod() != -1) {
            final ZipArchiveEntryRequest a2 = ZipArchiveEntryRequest.a(zipArchiveEntry, inputStreamSupplier);
            return new Callable<Object>() {
                public Object call() throws Exception {
                    ((ScatterZipOutputStream) ParallelScatterZipCreator.this.h.get()).a(a2);
                    return null;
                }
            };
        }
        throw new IllegalArgumentException("Method must be set on zipArchiveEntry: " + zipArchiveEntry);
    }

    public final Callable<Object> b(final ZipArchiveEntryRequestSupplier zipArchiveEntryRequestSupplier) {
        return new Callable<Object>() {
            public Object call() throws Exception {
                ((ScatterZipOutputStream) ParallelScatterZipCreator.this.h.get()).a(zipArchiveEntryRequestSupplier.a());
                return null;
            }
        };
    }

    public void a(ZipArchiveOutputStream zipArchiveOutputStream) throws IOException, InterruptedException, ExecutionException {
        for (Future<Object> future : this.d) {
            future.get();
        }
        this.b.shutdown();
        this.b.awaitTermination(60000, TimeUnit.SECONDS);
        this.f = System.currentTimeMillis();
        for (ScatterZipOutputStream next : this.f3255a) {
            next.a(zipArchiveOutputStream);
            next.close();
        }
        this.g = System.currentTimeMillis();
    }

    public ScatterStatistics a() {
        return new ScatterStatistics(this.f - this.e, this.g - this.f);
    }
}
