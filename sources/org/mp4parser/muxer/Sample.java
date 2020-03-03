package org.mp4parser.muxer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public interface Sample {
    long a();

    void a(WritableByteChannel writableByteChannel) throws IOException;

    ByteBuffer b();
}
