package org.jacoco.agent.rt.internal_8ff85ea.core.internal.data;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CompactDataInput extends DataInputStream {
    public CompactDataInput(InputStream inputStream) {
        super(inputStream);
    }

    public int a() throws IOException {
        byte readByte = readByte() & 255;
        if ((readByte & 128) == 0) {
            return readByte;
        }
        return (readByte & Byte.MAX_VALUE) | (a() << 7);
    }

    public boolean[] b() throws IOException {
        boolean[] zArr = new boolean[a()];
        int i = 0;
        for (int i2 = 0; i2 < zArr.length; i2++) {
            if (i2 % 8 == 0) {
                i = readByte();
            }
            zArr[i2] = (i & 1) != 0;
            i >>>= 1;
        }
        return zArr;
    }
}
