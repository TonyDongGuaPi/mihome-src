package org.mp4parser.muxer;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public interface DataSource extends Closeable {
    int a(ByteBuffer byteBuffer) throws IOException;

    long a() throws IOException;

    long a(long j, long j2, WritableByteChannel writableByteChannel) throws IOException;

    ByteBuffer a(long j, long j2) throws IOException;

    void a(long j) throws IOException;

    long b() throws IOException;

    void close() throws IOException;
}
