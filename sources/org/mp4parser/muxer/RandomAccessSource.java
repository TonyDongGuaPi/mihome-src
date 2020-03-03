package org.mp4parser.muxer;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

public interface RandomAccessSource extends Closeable {
    ByteBuffer a(long j, long j2) throws IOException;
}
