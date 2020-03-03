package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

abstract class CoderBase {
    private static final byte[] b = new byte[0];

    /* renamed from: a  reason: collision with root package name */
    private final Class<?>[] f3225a;

    /* access modifiers changed from: package-private */
    public abstract InputStream a(String str, InputStream inputStream, long j, Coder coder, byte[] bArr) throws IOException;

    /* access modifiers changed from: package-private */
    public Object a(Coder coder, InputStream inputStream) throws IOException {
        return null;
    }

    protected CoderBase(Class<?>... clsArr) {
        this.f3225a = clsArr;
    }

    /* access modifiers changed from: package-private */
    public boolean a(Object obj) {
        for (Class<?> isInstance : this.f3225a) {
            if (isInstance.isInstance(obj)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public byte[] b(Object obj) throws IOException {
        return b;
    }

    /* access modifiers changed from: package-private */
    public OutputStream a(OutputStream outputStream, Object obj) throws IOException {
        throw new UnsupportedOperationException("method doesn't support writing");
    }

    protected static int a(Object obj, int i) {
        return obj instanceof Number ? ((Number) obj).intValue() : i;
    }
}
