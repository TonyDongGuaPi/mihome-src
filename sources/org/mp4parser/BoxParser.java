package org.mp4parser;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;

public interface BoxParser {
    ParsableBox a(ReadableByteChannel readableByteChannel, String str) throws IOException;
}
