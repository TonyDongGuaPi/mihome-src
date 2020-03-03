package com.bumptech.glide.load.data;

import android.support.annotation.NonNull;
import cn.com.fmsh.tsm.business.constants.Constants;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class ExifOrientationStream extends FilterInputStream {

    /* renamed from: a  reason: collision with root package name */
    private static final int f4841a = 2;
    private static final byte[] b = {-1, -31, 0, 28, Constants.TagName.TERMINAL_MODEL_NUMBER, 120, Constants.TagName.MAIN_ORDER_ID, 102, 0, 0, 77, 77, 0, 0, 0, 0, 0, 8, 0, 1, 1, 18, 0, 2, 0, 0, 0, 1, 0};
    private static final int c = b.length;
    private static final int d = (c + 2);
    private final byte e;
    private int f;

    public boolean markSupported() {
        return false;
    }

    public ExifOrientationStream(InputStream inputStream, int i) {
        super(inputStream);
        if (i < -1 || i > 8) {
            throw new IllegalArgumentException("Cannot add invalid orientation: " + i);
        }
        this.e = (byte) i;
    }

    public void mark(int i) {
        throw new UnsupportedOperationException();
    }

    public int read() throws IOException {
        int i;
        if (this.f < 2 || this.f > d) {
            i = super.read();
        } else if (this.f == d) {
            i = this.e;
        } else {
            i = b[this.f - 2] & 255;
        }
        if (i != -1) {
            this.f++;
        }
        return i;
    }

    public int read(@NonNull byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (this.f > d) {
            i3 = super.read(bArr, i, i2);
        } else if (this.f == d) {
            bArr[i] = this.e;
            i3 = 1;
        } else if (this.f < 2) {
            i3 = super.read(bArr, i, 2 - this.f);
        } else {
            int min = Math.min(d - this.f, i2);
            System.arraycopy(b, this.f - 2, bArr, i, min);
            i3 = min;
        }
        if (i3 > 0) {
            this.f += i3;
        }
        return i3;
    }

    public long skip(long j) throws IOException {
        long skip = super.skip(j);
        if (skip > 0) {
            this.f = (int) (((long) this.f) + skip);
        }
        return skip;
    }

    public void reset() throws IOException {
        throw new UnsupportedOperationException();
    }
}
