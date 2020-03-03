package org.mp4parser;

import java.io.IOException;
import java.nio.channels.WritableByteChannel;

public interface Box {
    String ae_();

    void b(WritableByteChannel writableByteChannel) throws IOException;

    long c();
}
