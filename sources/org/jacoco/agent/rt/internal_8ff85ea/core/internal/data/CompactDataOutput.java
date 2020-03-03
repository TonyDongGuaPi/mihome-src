package org.jacoco.agent.rt.internal_8ff85ea.core.internal.data;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CompactDataOutput extends DataOutputStream {
    public CompactDataOutput(OutputStream outputStream) {
        super(outputStream);
    }

    public void a(int i) throws IOException {
        if ((i & -128) == 0) {
            writeByte(i);
            return;
        }
        writeByte((i & 127) | 128);
        a(i >>> 7);
    }

    public void a(boolean[] zArr) throws IOException {
        a(zArr.length);
        int i = 0;
        int i2 = 0;
        for (boolean z : zArr) {
            if (z) {
                i2 |= 1 << i;
            }
            i++;
            if (i == 8) {
                writeByte(i2);
                i = 0;
                i2 = 0;
            }
        }
        if (i > 0) {
            writeByte(i2);
        }
    }
}
