package org.apache.commons.compress.archivers.sevenz;

import cn.com.fmsh.tsm.business.constants.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.tukaani.xz.FinishableWrapperOutputStream;
import org.tukaani.xz.LZMA2InputStream;
import org.tukaani.xz.LZMA2Options;

class LZMA2Decoder extends CoderBase {
    LZMA2Decoder() {
        super(LZMA2Options.class, Number.class);
    }

    /* access modifiers changed from: package-private */
    public InputStream a(String str, InputStream inputStream, long j, Coder coder, byte[] bArr) throws IOException {
        try {
            return new LZMA2InputStream(inputStream, a(coder));
        } catch (IllegalArgumentException e) {
            throw new IOException(e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public OutputStream a(OutputStream outputStream, Object obj) throws IOException {
        return d(obj).getOutputStream(new FinishableWrapperOutputStream(outputStream));
    }

    /* access modifiers changed from: package-private */
    public byte[] b(Object obj) {
        int c = c(obj);
        int numberOfLeadingZeros = Integer.numberOfLeadingZeros(c);
        return new byte[]{(byte) (((19 - numberOfLeadingZeros) * 2) + ((c >>> (30 - numberOfLeadingZeros)) - 2))};
    }

    /* access modifiers changed from: package-private */
    public Object a(Coder coder, InputStream inputStream) {
        return Integer.valueOf(a(coder));
    }

    private int c(Object obj) {
        if (obj instanceof LZMA2Options) {
            return ((LZMA2Options) obj).getDictSize();
        }
        return e(obj);
    }

    private int a(Coder coder) throws IllegalArgumentException {
        byte b = coder.d[0] & 255;
        if ((b & Constants.TagName.STATION_ENAME) != 0) {
            throw new IllegalArgumentException("Unsupported LZMA2 property bits");
        } else if (b > 40) {
            throw new IllegalArgumentException("Dictionary larger than 4GiB maximum size");
        } else if (b == 40) {
            return -1;
        } else {
            return ((b & 1) | 2) << ((b / 2) + 11);
        }
    }

    private LZMA2Options d(Object obj) throws IOException {
        if (obj instanceof LZMA2Options) {
            return (LZMA2Options) obj;
        }
        LZMA2Options lZMA2Options = new LZMA2Options();
        lZMA2Options.setDictSize(e(obj));
        return lZMA2Options;
    }

    private int e(Object obj) {
        return a(obj, 8388608);
    }
}
