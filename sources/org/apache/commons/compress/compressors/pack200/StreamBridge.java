package org.apache.commons.compress.compressors.pack200;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

abstract class StreamBridge extends FilterOutputStream {

    /* renamed from: a  reason: collision with root package name */
    private InputStream f3333a;
    private final Object b;

    /* access modifiers changed from: package-private */
    public abstract InputStream a() throws IOException;

    protected StreamBridge(OutputStream outputStream) {
        super(outputStream);
        this.b = new Object();
    }

    protected StreamBridge() {
        this((OutputStream) null);
    }

    /* access modifiers changed from: package-private */
    public InputStream b() throws IOException {
        synchronized (this.b) {
            if (this.f3333a == null) {
                this.f3333a = a();
            }
        }
        return this.f3333a;
    }

    /* access modifiers changed from: package-private */
    public void c() throws IOException {
        close();
        synchronized (this.b) {
            if (this.f3333a != null) {
                this.f3333a.close();
                this.f3333a = null;
            }
        }
    }
}
