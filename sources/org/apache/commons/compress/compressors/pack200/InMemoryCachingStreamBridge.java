package org.apache.commons.compress.compressors.pack200;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

class InMemoryCachingStreamBridge extends StreamBridge {
    InMemoryCachingStreamBridge() {
        super(new ByteArrayOutputStream());
    }

    /* access modifiers changed from: package-private */
    public InputStream a() throws IOException {
        return new ByteArrayInputStream(((ByteArrayOutputStream) this.out).toByteArray());
    }
}
