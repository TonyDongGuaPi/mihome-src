package com.nostra13.universalimageloader.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class IoUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final int f8495a = 32768;
    public static final int b = 512000;
    public static final int c = 75;

    public interface CopyListener {
        boolean a(int i, int i2);
    }

    private IoUtils() {
    }

    public static boolean a(InputStream inputStream, OutputStream outputStream, CopyListener copyListener) throws IOException {
        return a(inputStream, outputStream, copyListener, 32768);
    }

    public static boolean a(InputStream inputStream, OutputStream outputStream, CopyListener copyListener, int i) throws IOException {
        int available = inputStream.available();
        if (available <= 0) {
            available = b;
        }
        byte[] bArr = new byte[i];
        if (a(copyListener, 0, available)) {
            return false;
        }
        int i2 = 0;
        do {
            int read = inputStream.read(bArr, 0, i);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
                i2 += read;
            } else {
                outputStream.flush();
                return true;
            }
        } while (!a(copyListener, i2, available));
        return false;
    }

    private static boolean a(CopyListener copyListener, int i, int i2) {
        return copyListener != null && !copyListener.a(i, i2) && (i * 100) / i2 < 75;
    }

    public static void a(InputStream inputStream) {
        do {
            try {
            } catch (IOException unused) {
            } catch (Throwable th) {
                a((Closeable) inputStream);
                throw th;
            }
        } while (inputStream.read(new byte[32768], 0, 32768) != -1);
        a((Closeable) inputStream);
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }
}
