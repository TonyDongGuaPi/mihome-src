package org.mp4parser.muxer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import org.mp4parser.tools.CastUtils;

public class FileRandomAccessSourceImpl implements RandomAccessSource {

    /* renamed from: a  reason: collision with root package name */
    private RandomAccessFile f3984a;

    public FileRandomAccessSourceImpl(RandomAccessFile randomAccessFile) {
        this.f3984a = randomAccessFile;
    }

    public ByteBuffer a(long j, long j2) throws IOException {
        byte[] bArr = new byte[CastUtils.a(j2)];
        this.f3984a.seek(j);
        this.f3984a.read(bArr);
        return ByteBuffer.wrap(bArr);
    }

    public void close() throws IOException {
        this.f3984a.close();
    }
}
