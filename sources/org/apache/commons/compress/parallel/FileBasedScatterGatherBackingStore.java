package org.apache.commons.compress.parallel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileBasedScatterGatherBackingStore implements ScatterGatherBackingStore {

    /* renamed from: a  reason: collision with root package name */
    private final File f3342a;
    private final FileOutputStream b;
    private boolean c;

    public FileBasedScatterGatherBackingStore(File file) throws FileNotFoundException {
        this.f3342a = file;
        this.b = new FileOutputStream(file);
    }

    public InputStream a() throws IOException {
        return new FileInputStream(this.f3342a);
    }

    public void b() throws IOException {
        if (!this.c) {
            this.b.close();
            this.c = true;
        }
    }

    public void a(byte[] bArr, int i, int i2) throws IOException {
        this.b.write(bArr, i, i2);
    }

    public void close() throws IOException {
        b();
        this.f3342a.delete();
    }
}
