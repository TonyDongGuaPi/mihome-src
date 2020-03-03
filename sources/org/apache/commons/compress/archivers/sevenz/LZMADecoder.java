package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.compress.utils.FlushShieldFilterOutputStream;
import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.LZMAInputStream;
import org.tukaani.xz.LZMAOutputStream;

class LZMADecoder extends CoderBase {
    LZMADecoder() {
        super(LZMA2Options.class, Number.class);
    }

    /* access modifiers changed from: package-private */
    public InputStream a(String str, InputStream inputStream, long j, Coder coder, byte[] bArr) throws IOException {
        byte b = coder.d[0];
        int a2 = a(coder);
        if (a2 <= 2147483632) {
            return new LZMAInputStream(inputStream, j, b, a2);
        }
        throw new IOException("Dictionary larger than 4GiB maximum size used in " + str);
    }

    /* access modifiers changed from: package-private */
    public OutputStream a(OutputStream outputStream, Object obj) throws IOException {
        return new FlushShieldFilterOutputStream(new LZMAOutputStream(outputStream, c(obj), false));
    }

    /* access modifiers changed from: package-private */
    public byte[] b(Object obj) throws IOException {
        LZMA2Options c = c(obj);
        int dictSize = c.getDictSize();
        return new byte[]{(byte) ((((c.getPb() * 5) + c.getLp()) * 9) + c.getLc()), (byte) (dictSize & 255), (byte) ((dictSize >> 8) & 255), (byte) ((dictSize >> 16) & 255), (byte) ((dictSize >> 24) & 255)};
    }

    /* access modifiers changed from: package-private */
    public Object a(Coder coder, InputStream inputStream) throws IOException {
        byte b = coder.d[0] & 255;
        int i = b / 45;
        int i2 = b - ((i * 9) * 5);
        int i3 = i2 / 9;
        LZMA2Options lZMA2Options = new LZMA2Options();
        lZMA2Options.setPb(i);
        lZMA2Options.setLcLp(i2 - (i3 * 9), i3);
        lZMA2Options.setDictSize(a(coder));
        return lZMA2Options;
    }

    private int a(Coder coder) throws IllegalArgumentException {
        int i = 1;
        long j = (long) coder.d[1];
        while (i < 4) {
            int i2 = i + 1;
            j |= (((long) coder.d[i2]) & 255) << (i * 8);
            i = i2;
        }
        return (int) j;
    }

    private LZMA2Options c(Object obj) throws IOException {
        if (obj instanceof LZMA2Options) {
            return (LZMA2Options) obj;
        }
        LZMA2Options lZMA2Options = new LZMA2Options();
        lZMA2Options.setDictSize(d(obj));
        return lZMA2Options;
    }

    private int d(Object obj) {
        return a(obj, 8388608);
    }
}
