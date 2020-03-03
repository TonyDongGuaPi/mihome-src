package org.mp4parser.muxer.tracks.h264.parsing.read;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.io.InputStream;
import org.mp4parser.muxer.tracks.h264.parsing.BTree;
import org.mp4parser.muxer.tracks.h264.parsing.Debug;

public class CAVLCReader extends BitstreamReader {
    public CAVLCReader(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    public long a(int i, String str) throws IOException {
        long a2 = a(i);
        a(str, String.valueOf(a2));
        return a2;
    }

    private int m() throws IOException {
        int i = 0;
        while (b() == 0) {
            i++;
        }
        if (i <= 0) {
            return 0;
        }
        return (int) (((long) ((1 << i) - 1)) + a(i));
    }

    public int a(String str) throws IOException {
        int m = m();
        a(str, String.valueOf(m));
        return m;
    }

    public int b(String str) throws IOException {
        int m = m();
        int i = m & 1;
        int i2 = ((m >> 1) + i) * ((i << 1) - 1);
        a(str, String.valueOf(i2));
        return i2;
    }

    public boolean c(String str) throws IOException {
        boolean z = b() != 0;
        a(str, z ? "1" : "0");
        return z;
    }

    public int b(int i, String str) throws IOException {
        return (int) a(i, str);
    }

    public byte[] c(int i) throws IOException {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) c();
        }
        return bArr;
    }

    public boolean j() {
        throw new UnsupportedOperationException("Stan");
    }

    public int d(int i) throws IOException {
        if (i > 1) {
            return m();
        }
        return (b() ^ -1) & 1;
    }

    public int k() {
        throw new UnsupportedOperationException("Stan");
    }

    public int d(String str) throws IOException {
        return a(str);
    }

    public Object a(BTree bTree, String str) throws IOException {
        Object a2;
        do {
            bTree = bTree.a(b());
            if (bTree != null) {
                a2 = bTree.a();
            } else {
                throw new RuntimeException("Illegal code");
            }
        } while (a2 == null);
        a(str, a2.toString());
        return a2;
    }

    public int e(String str) throws IOException {
        int i = 0;
        while (b() == 0) {
            i++;
        }
        a(str, String.valueOf(i));
        return i;
    }

    public void l() throws IOException {
        b();
        f();
    }

    private void a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        String valueOf = String.valueOf(f4046a - this.b.b());
        int length = 8 - valueOf.length();
        sb.append("@" + valueOf);
        for (int i = 0; i < length; i++) {
            sb.append(' ');
        }
        sb.append(str);
        int length2 = (100 - sb.length()) - this.b.b();
        for (int i2 = 0; i2 < length2; i2++) {
            sb.append(' ');
        }
        sb.append(this.b);
        sb.append(" (" + str2 + Operators.BRACKET_END_STR);
        this.b.a();
        Debug.b(sb.toString());
    }
}
