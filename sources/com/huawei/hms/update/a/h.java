package com.huawei.hms.update.a;

import com.huawei.hms.c.c;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class h extends OutputStream {

    /* renamed from: a  reason: collision with root package name */
    private RandomAccessFile f5909a;

    public h(File file, int i) throws FileNotFoundException, IOException {
        try {
            this.f5909a = new RandomAccessFile(file, "rwd");
            this.f5909a.setLength((long) i);
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e2) {
            c.a((Closeable) this.f5909a);
            throw e2;
        }
    }

    public void close() throws IOException {
        this.f5909a.close();
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.f5909a.write(bArr, i, i2);
    }

    public void write(int i) throws IOException {
        write(new byte[]{(byte) i}, 0, 1);
    }

    public void a(long j) throws IOException {
        this.f5909a.seek(j);
    }
}
