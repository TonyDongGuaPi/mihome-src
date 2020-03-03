package org.mp4parser.muxer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import org.mp4parser.tools.CastUtils;

public class MultiFileDataSourceImpl implements DataSource {

    /* renamed from: a  reason: collision with root package name */
    FileChannel[] f3988a;
    int b = 0;

    public MultiFileDataSourceImpl(File... fileArr) throws FileNotFoundException {
        this.f3988a = new FileChannel[fileArr.length];
        for (int i = 0; i < fileArr.length; i++) {
            this.f3988a[i] = new FileInputStream(fileArr[i]).getChannel();
        }
    }

    public int a(ByteBuffer byteBuffer) throws IOException {
        int remaining = byteBuffer.remaining();
        int read = this.f3988a[this.b].read(byteBuffer);
        if (read == remaining) {
            return read;
        }
        this.b++;
        return read + a(byteBuffer);
    }

    public long a() throws IOException {
        long j = 0;
        for (FileChannel size : this.f3988a) {
            j += size.size();
        }
        return j;
    }

    public long b() throws IOException {
        long j = 0;
        for (int i = 0; i < this.b; i++) {
            j += this.f3988a[i].size();
        }
        return j + this.f3988a[this.b].position();
    }

    public void a(long j) throws IOException {
        for (int i = 0; i < this.f3988a.length; i++) {
            if (j - this.f3988a[i].size() < 0) {
                this.f3988a[i].position(j);
                this.b = i;
                return;
            }
            j -= this.f3988a[i].size();
        }
    }

    public long a(long j, long j2, WritableByteChannel writableByteChannel) throws IOException {
        long j3 = j2;
        if (j3 == 0) {
            return 0;
        }
        FileChannel[] fileChannelArr = this.f3988a;
        int length = fileChannelArr.length;
        int i = 0;
        long j4 = 0;
        while (i < length) {
            FileChannel fileChannel = fileChannelArr[i];
            long size = fileChannel.size();
            if (j < j4 || j >= j4 + size || j + j3 <= j4) {
                j4 += size;
                i++;
            } else {
                long j5 = j - j4;
                long min = Math.min(j3, size - j5);
                fileChannel.transferTo(j5, min, writableByteChannel);
                return min + a(j + min, j3 - min, writableByteChannel);
            }
        }
        return 0;
    }

    public ByteBuffer a(long j, long j2) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(CastUtils.a(j2));
        a(j, j2, Channels.newChannel(byteArrayOutputStream));
        return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
    }

    public void close() throws IOException {
        for (FileChannel close : this.f3988a) {
            close.close();
        }
    }
}
