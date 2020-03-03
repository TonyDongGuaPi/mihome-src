package org.mp4parser.muxer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import org.mp4parser.support.Logger;

public class FileDataSourceImpl implements DataSource {
    private static Logger c = Logger.a(FileDataSourceImpl.class);

    /* renamed from: a  reason: collision with root package name */
    FileChannel f3982a;
    String b;

    public FileDataSourceImpl(File file) throws FileNotFoundException {
        this.f3982a = new FileInputStream(file).getChannel();
        this.b = file.getName();
    }

    public FileDataSourceImpl(String str) throws FileNotFoundException {
        File file = new File(str);
        this.f3982a = new FileInputStream(file).getChannel();
        this.b = file.getName();
    }

    public FileDataSourceImpl(FileChannel fileChannel) {
        this.f3982a = fileChannel;
        this.b = "unknown";
    }

    public FileDataSourceImpl(FileChannel fileChannel, String str) {
        this.f3982a = fileChannel;
        this.b = str;
    }

    public synchronized int a(ByteBuffer byteBuffer) throws IOException {
        return this.f3982a.read(byteBuffer);
    }

    public synchronized long a() throws IOException {
        return this.f3982a.size();
    }

    public synchronized long b() throws IOException {
        return this.f3982a.position();
    }

    public synchronized void a(long j) throws IOException {
        this.f3982a.position(j);
    }

    public synchronized long a(long j, long j2, WritableByteChannel writableByteChannel) throws IOException {
        return this.f3982a.transferTo(j, j2, writableByteChannel);
    }

    public synchronized ByteBuffer a(long j, long j2) throws IOException {
        Logger logger = c;
        logger.a(j + " " + j2);
        return this.f3982a.map(FileChannel.MapMode.READ_ONLY, j, j2);
    }

    public void close() throws IOException {
        this.f3982a.close();
    }

    public String toString() {
        return this.b;
    }
}
