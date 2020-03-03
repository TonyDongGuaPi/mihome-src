package org.mp4parser.muxer.tracks.h264.parsing.write;

import java.io.IOException;
import java.io.OutputStream;
import org.mp4parser.muxer.tracks.h264.parsing.Debug;

public class BitstreamWriter {

    /* renamed from: a  reason: collision with root package name */
    private final OutputStream f4047a;
    private int[] b = new int[8];
    private int c;

    public BitstreamWriter(OutputStream outputStream) {
        this.f4047a = outputStream;
    }

    public void a() throws IOException {
        for (int i = this.c; i < 8; i++) {
            this.b[i] = 0;
        }
        this.c = 0;
        c();
    }

    private void c() throws IOException {
        this.f4047a.write((this.b[0] << 7) | (this.b[1] << 6) | (this.b[2] << 5) | (this.b[3] << 4) | (this.b[4] << 3) | (this.b[5] << 2) | (this.b[6] << 1) | this.b[7]);
    }

    public void a(int i) throws IOException {
        Debug.a(i);
        if (this.c == 8) {
            this.c = 0;
            c();
        }
        int[] iArr = this.b;
        int i2 = this.c;
        this.c = i2 + 1;
        iArr[i2] = i;
    }

    public void a(long j, int i) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            a(((int) (j >> ((i - i2) - 1))) & 1);
        }
    }

    public void b() throws IOException {
        a(0, 8 - this.c);
    }

    public void b(int i) throws IOException {
        this.f4047a.write(i);
    }
}
