package org.mp4parser.muxer.tracks.h264.parsing.write;

import java.io.IOException;
import java.io.OutputStream;
import org.cybergarage.http.HTTP;
import org.mp4parser.muxer.tracks.h264.parsing.Debug;

public class CAVLCWriter extends BitstreamWriter {
    public CAVLCWriter(OutputStream outputStream) {
        super(outputStream);
    }

    public void a(int i, int i2, String str) throws IOException {
        Debug.a(str + HTTP.TAB);
        a((long) i, i2);
        Debug.b(HTTP.TAB + i);
    }

    public void c(int i) throws IOException {
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i3 >= 15) {
                break;
            }
            int i5 = (1 << i3) + i4;
            if (i < i5) {
                i2 = i3;
                break;
            } else {
                i3++;
                i4 = i5;
            }
        }
        a(0, i2);
        a(1);
        a((long) (i - i4), i2);
    }

    public void a(int i, String str) throws IOException {
        Debug.a(str + HTTP.TAB);
        c(i);
        Debug.b(HTTP.TAB + i);
    }

    public void b(int i, String str) throws IOException {
        Debug.a(str + HTTP.TAB);
        int i2 = 1;
        int i3 = (i << 1) * (i < 0 ? -1 : 1);
        if (i <= 0) {
            i2 = 0;
        }
        c(i3 + i2);
        Debug.b(HTTP.TAB + i);
    }

    public void a(boolean z, String str) throws IOException {
        Debug.a(str + HTTP.TAB);
        a(z ? 1 : 0);
        Debug.b(HTTP.TAB + z);
    }

    public void a(int i, int i2) throws IOException {
        a((long) i, i2);
    }

    public void a(long j, int i, String str) throws IOException {
        Debug.a(str + HTTP.TAB);
        for (int i2 = 0; i2 < i; i2++) {
            a(((int) (j >> ((i - i2) - 1))) & 1);
        }
        Debug.b(HTTP.TAB + j);
    }

    public void c() throws IOException {
        a(1);
        b();
        a();
    }

    public void d() {
        throw new IllegalStateException("todo");
    }
}
