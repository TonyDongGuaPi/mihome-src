package com.bumptech.glide.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class ContentLengthInputStream extends FilterInputStream {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5096a = "ContentLengthStream";
    private static final int b = -1;
    private final long c;
    private int d;

    @NonNull
    public static InputStream a(@NonNull InputStream inputStream, @Nullable String str) {
        return a(inputStream, (long) a(str));
    }

    @NonNull
    public static InputStream a(@NonNull InputStream inputStream, long j) {
        return new ContentLengthInputStream(inputStream, j);
    }

    private static int a(@Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                if (Log.isLoggable(f5096a, 3)) {
                    Log.d(f5096a, "failed to parse content length header: " + str, e);
                }
            }
        }
        return -1;
    }

    private ContentLengthInputStream(@NonNull InputStream inputStream, long j) {
        super(inputStream);
        this.c = j;
    }

    public synchronized int available() throws IOException {
        return (int) Math.max(this.c - ((long) this.d), (long) this.in.available());
    }

    public synchronized int read() throws IOException {
        int read;
        read = super.read();
        a(read >= 0 ? 1 : -1);
        return read;
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public synchronized int read(byte[] bArr, int i, int i2) throws IOException {
        return a(super.read(bArr, i, i2));
    }

    private int a(int i) throws IOException {
        if (i >= 0) {
            this.d += i;
        } else if (this.c - ((long) this.d) > 0) {
            throw new IOException("Failed to read all expected data, expected: " + this.c + ", but read: " + this.d);
        }
        return i;
    }
}
