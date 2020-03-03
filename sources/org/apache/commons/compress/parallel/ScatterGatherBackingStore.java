package org.apache.commons.compress.parallel;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public interface ScatterGatherBackingStore extends Closeable {
    InputStream a() throws IOException;

    void a(byte[] bArr, int i, int i2) throws IOException;

    void b() throws IOException;
}
