package org.mp4parser.tools;

import java.nio.ByteBuffer;

public final class IsoTypeWriterVariable {
    public static void a(long j, ByteBuffer byteBuffer, int i) {
        if (i != 8) {
            switch (i) {
                case 1:
                    IsoTypeWriter.d(byteBuffer, (int) (j & 255));
                    return;
                case 2:
                    IsoTypeWriter.b(byteBuffer, (int) (j & 65535));
                    return;
                case 3:
                    IsoTypeWriter.a(byteBuffer, (int) (j & 16777215));
                    return;
                case 4:
                    IsoTypeWriter.b(byteBuffer, j);
                    return;
                default:
                    throw new RuntimeException("I don't know how to read " + i + " bytes");
            }
        } else {
            IsoTypeWriter.a(byteBuffer, j);
        }
    }
}
