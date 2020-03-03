package org.mp4parser.tools;

import java.nio.ByteBuffer;

public final class IsoTypeReaderVariable {
    public static long a(ByteBuffer byteBuffer, int i) {
        if (i == 8) {
            return IsoTypeReader.h(byteBuffer);
        }
        switch (i) {
            case 1:
                return (long) IsoTypeReader.f(byteBuffer);
            case 2:
                return (long) IsoTypeReader.d(byteBuffer);
            case 3:
                return (long) IsoTypeReader.c(byteBuffer);
            case 4:
                return IsoTypeReader.b(byteBuffer);
            default:
                throw new RuntimeException("I don't know how to read " + i + " bytes");
        }
    }
}
