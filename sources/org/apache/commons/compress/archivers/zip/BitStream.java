package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import org.apache.commons.compress.utils.BitInputStream;

class BitStream extends BitInputStream {
    BitStream(InputStream inputStream) {
        super(inputStream, ByteOrder.LITTLE_ENDIAN);
    }

    /* access modifiers changed from: package-private */
    public int a() throws IOException {
        return (int) b(1);
    }

    /* access modifiers changed from: package-private */
    public long a(int i) throws IOException {
        return b(i);
    }

    /* access modifiers changed from: package-private */
    public int b() throws IOException {
        return (int) b(8);
    }
}
