package org.mp4parser.muxer.tracks.h264.parsing.read;

import java.io.IOException;
import java.io.InputStream;
import org.mp4parser.muxer.tracks.h264.parsing.CharCache;

public class BitstreamReader {

    /* renamed from: a  reason: collision with root package name */
    protected static int f4046a;
    protected CharCache b = new CharCache(50);
    int c;
    private InputStream d;
    private int e;
    private int f;

    public void h() throws IOException {
    }

    public BitstreamReader(InputStream inputStream) throws IOException {
        this.d = inputStream;
        this.e = inputStream.read();
        this.f = inputStream.read();
    }

    public boolean a() throws IOException {
        return b() == 1;
    }

    public int b() throws IOException {
        if (this.c == 8) {
            j();
            if (this.e == -1) {
                return -1;
            }
        }
        int i = (this.e >> (7 - this.c)) & 1;
        this.c++;
        this.b.a(i == 0 ? '0' : '1');
        f4046a++;
        return i;
    }

    public long a(int i) throws IOException {
        if (i <= 64) {
            long j = 0;
            for (int i2 = 0; i2 < i; i2++) {
                j = (j << 1) | ((long) b());
            }
            return j;
        }
        throw new IllegalArgumentException("Can not readByte more then 64 bit");
    }

    private void j() throws IOException {
        this.e = this.f;
        this.f = this.d.read();
        this.c = 0;
    }

    public int c() throws IOException {
        if (this.c > 0) {
            j();
        }
        int i = this.e;
        j();
        return i;
    }

    public boolean d() throws IOException {
        if (this.c == 8) {
            j();
        }
        int i = 1 << ((8 - this.c) - 1);
        boolean z = (((i << 1) - 1) & this.e) == i;
        if (this.e == -1 || (this.f == -1 && z)) {
            return false;
        }
        return true;
    }

    public long e() {
        return (long) ((f4046a * 8) + (this.c % 8));
    }

    public long f() throws IOException {
        return a(8 - this.c);
    }

    public int b(int i) throws IOException {
        if (i <= 8) {
            if (this.c == 8) {
                j();
                if (this.e == -1) {
                    return -1;
                }
            }
            int[] iArr = new int[(16 - this.c)];
            int i2 = this.c;
            int i3 = 0;
            while (i2 < 8) {
                iArr[i3] = (this.e >> (7 - i2)) & 1;
                i2++;
                i3++;
            }
            int i4 = 0;
            while (i4 < 8) {
                iArr[i3] = (this.f >> (7 - i4)) & 1;
                i4++;
                i3++;
            }
            int i5 = 0;
            for (int i6 = 0; i6 < i; i6++) {
                i5 = (i5 << 1) | iArr[i6];
            }
            return i5;
        }
        throw new IllegalArgumentException("N should be less then 8");
    }

    public boolean g() {
        return this.c % 8 == 0;
    }

    public int i() {
        return this.c;
    }
}
