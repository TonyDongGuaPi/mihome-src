package org.mp4parser.muxer.tracks;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CleanInputStream extends FilterInputStream {

    /* renamed from: a  reason: collision with root package name */
    int f4017a = -1;
    int b = -1;

    public boolean markSupported() {
        return false;
    }

    public CleanInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public int read() throws IOException {
        int read = super.read();
        if (read == 3 && this.f4017a == 0 && this.b == 0) {
            this.f4017a = -1;
            this.b = -1;
            read = super.read();
        }
        this.f4017a = this.b;
        this.b = read;
        return read;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            throw new NullPointerException();
        } else if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        } else if (i2 == 0) {
            return 0;
        } else {
            int read = read();
            if (read == -1) {
                return -1;
            }
            bArr[i] = (byte) read;
            int i3 = 1;
            while (true) {
                if (i3 >= i2) {
                    break;
                }
                try {
                    int read2 = read();
                    if (read2 == -1) {
                        break;
                    }
                    bArr[i + i3] = (byte) read2;
                    i3++;
                } catch (IOException unused) {
                }
            }
            return i3;
        }
    }
}
