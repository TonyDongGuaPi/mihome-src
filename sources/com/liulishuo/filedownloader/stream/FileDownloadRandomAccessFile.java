package com.liulishuo.filedownloader.stream;

import com.liulishuo.filedownloader.util.FileDownloadHelper;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileDownloadRandomAccessFile implements FileDownloadOutputStream {

    /* renamed from: a  reason: collision with root package name */
    private final BufferedOutputStream f6460a = new BufferedOutputStream(new FileOutputStream(this.c.getFD()));
    private final FileDescriptor b = this.c.getFD();
    private final RandomAccessFile c;

    FileDownloadRandomAccessFile(File file) throws IOException {
        this.c = new RandomAccessFile(file, "rw");
    }

    public void a(byte[] bArr, int i, int i2) throws IOException {
        this.f6460a.write(bArr, i, i2);
    }

    public void a() throws IOException {
        this.f6460a.flush();
        this.b.sync();
    }

    public void b() throws IOException {
        this.f6460a.close();
    }

    public void a(long j) throws IOException {
        this.c.seek(j);
    }

    public void b(long j) throws IOException {
        this.c.setLength(j);
    }

    public static class Creator implements FileDownloadHelper.OutputStreamCreator {
        public boolean a() {
            return true;
        }

        public FileDownloadOutputStream a(File file) throws IOException {
            return new FileDownloadRandomAccessFile(file);
        }
    }
}
