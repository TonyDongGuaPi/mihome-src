package org.apache.commons.compress.compressors.pack200;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

class TempFileCachingStreamBridge extends StreamBridge {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final File f3334a = File.createTempFile("commons-compress", "packtemp");

    TempFileCachingStreamBridge() throws IOException {
        this.f3334a.deleteOnExit();
        this.out = new FileOutputStream(this.f3334a);
    }

    /* access modifiers changed from: package-private */
    public InputStream a() throws IOException {
        this.out.close();
        return new FileInputStream(this.f3334a) {
            public void close() throws IOException {
                super.close();
                TempFileCachingStreamBridge.this.f3334a.delete();
            }
        };
    }
}
