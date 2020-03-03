package org.apache.commons.compress.archivers.tar;

import java.io.IOException;

public class TarArchiveSparseEntry implements TarConstants {
    private final boolean b;

    public TarArchiveSparseEntry(byte[] bArr) throws IOException {
        this.b = TarUtils.a(bArr, 504);
    }

    public boolean a() {
        return this.b;
    }
}
