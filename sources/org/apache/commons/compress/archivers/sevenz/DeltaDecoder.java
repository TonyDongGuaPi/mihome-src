package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.tukaani.xz.DeltaOptions;
import org.tukaani.xz.FinishableWrapperOutputStream;
import org.tukaani.xz.UnsupportedOptionsException;

class DeltaDecoder extends CoderBase {
    DeltaDecoder() {
        super(Number.class);
    }

    /* access modifiers changed from: package-private */
    public InputStream a(String str, InputStream inputStream, long j, Coder coder, byte[] bArr) throws IOException {
        return new DeltaOptions(a(coder)).getInputStream(inputStream);
    }

    /* access modifiers changed from: package-private */
    public OutputStream a(OutputStream outputStream, Object obj) throws IOException {
        try {
            return new DeltaOptions(a(obj, 1)).getOutputStream(new FinishableWrapperOutputStream(outputStream));
        } catch (UnsupportedOptionsException e) {
            throw new IOException(e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public byte[] b(Object obj) {
        return new byte[]{(byte) (a(obj, 1) - 1)};
    }

    /* access modifiers changed from: package-private */
    public Object a(Coder coder, InputStream inputStream) {
        return Integer.valueOf(a(coder));
    }

    private int a(Coder coder) {
        if (coder.d == null || coder.d.length == 0) {
            return 1;
        }
        return (coder.d[0] & 255) + 1;
    }
}
