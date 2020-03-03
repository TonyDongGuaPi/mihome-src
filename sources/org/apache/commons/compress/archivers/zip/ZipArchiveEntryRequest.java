package org.apache.commons.compress.archivers.zip;

import java.io.InputStream;
import org.apache.commons.compress.parallel.InputStreamSupplier;

public class ZipArchiveEntryRequest {

    /* renamed from: a  reason: collision with root package name */
    private final ZipArchiveEntry f3282a;
    private final InputStreamSupplier b;
    private final int c;

    private ZipArchiveEntryRequest(ZipArchiveEntry zipArchiveEntry, InputStreamSupplier inputStreamSupplier) {
        this.f3282a = zipArchiveEntry;
        this.b = inputStreamSupplier;
        this.c = zipArchiveEntry.getMethod();
    }

    public static ZipArchiveEntryRequest a(ZipArchiveEntry zipArchiveEntry, InputStreamSupplier inputStreamSupplier) {
        return new ZipArchiveEntryRequest(zipArchiveEntry, inputStreamSupplier);
    }

    public InputStream a() {
        return this.b.a();
    }

    public int b() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public ZipArchiveEntry c() {
        return this.f3282a;
    }
}
