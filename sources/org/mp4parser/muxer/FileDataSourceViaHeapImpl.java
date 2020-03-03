package org.mp4parser.muxer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import org.mp4parser.support.Logger;
import org.mp4parser.tools.CastUtils;

public class FileDataSourceViaHeapImpl implements DataSource {
    private static Logger c = Logger.a(FileDataSourceViaHeapImpl.class);

    /* renamed from: a  reason: collision with root package name */
    FileChannel f3983a;
    String b;

    public FileDataSourceViaHeapImpl(File file) throws FileNotFoundException {
        this.f3983a = new FileInputStream(file).getChannel();
        this.b = file.getName();
    }

    public FileDataSourceViaHeapImpl(String str) throws FileNotFoundException {
        File file = new File(str);
        this.f3983a = new FileInputStream(file).getChannel();
        this.b = file.getName();
    }

    public FileDataSourceViaHeapImpl(FileChannel fileChannel) {
        this.f3983a = fileChannel;
        this.b = "unknown";
    }

    public FileDataSourceViaHeapImpl(FileChannel fileChannel, String str) {
        this.f3983a = fileChannel;
        this.b = str;
    }

    public synchronized int a(ByteBuffer byteBuffer) throws IOException {
        return this.f3983a.read(byteBuffer);
    }

    public synchronized long a() throws IOException {
        return this.f3983a.size();
    }

    public synchronized long b() throws IOException {
        return this.f3983a.position();
    }

    public synchronized void a(long j) throws IOException {
        this.f3983a.position(j);
    }

    public synchronized long a(long j, long j2, WritableByteChannel writableByteChannel) throws IOException {
        return this.f3983a.transferTo(j, j2, writableByteChannel);
    }

    public synchronized ByteBuffer a(long j, long j2) throws IOException {
        ByteBuffer allocate;
        allocate = ByteBuffer.allocate(CastUtils.a(j2));
        this.f3983a.read(allocate, j);
        return (ByteBuffer) allocate.rewind();
    }

    public void close() throws IOException {
        this.f3983a.close();
    }

    public String toString() {
        return this.b;
    }
}
