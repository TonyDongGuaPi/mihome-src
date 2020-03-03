package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.nio.ByteBuffer;

class FallbackZipEncoding implements ZipEncoding {

    /* renamed from: a  reason: collision with root package name */
    private final String f3250a;

    public boolean a(String str) {
        return true;
    }

    public FallbackZipEncoding() {
        this.f3250a = null;
    }

    public FallbackZipEncoding(String str) {
        this.f3250a = str;
    }

    public ByteBuffer b(String str) throws IOException {
        if (this.f3250a == null) {
            return ByteBuffer.wrap(str.getBytes());
        }
        return ByteBuffer.wrap(str.getBytes(this.f3250a));
    }

    public String a(byte[] bArr) throws IOException {
        if (this.f3250a == null) {
            return new String(bArr);
        }
        return new String(bArr, this.f3250a);
    }
}
