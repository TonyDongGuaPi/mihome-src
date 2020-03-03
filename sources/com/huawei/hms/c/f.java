package com.huawei.hms.c;

import com.huawei.hms.support.log.a;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class f {
    public static byte[] a(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA-256").digest(bArr);
        } catch (NoSuchAlgorithmException e) {
            a.d("SHA256", "NoSuchAlgorithmException" + e.getMessage());
            return new byte[0];
        }
    }

    public static byte[] a(File file) {
        BufferedInputStream bufferedInputStream = null;
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
            try {
                byte[] bArr = new byte[4096];
                int i = 0;
                while (true) {
                    int read = bufferedInputStream2.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    i += read;
                    instance.update(bArr, 0, read);
                }
                if (i > 0) {
                    byte[] digest = instance.digest();
                    c.a((InputStream) bufferedInputStream2);
                    return digest;
                }
                c.a((InputStream) bufferedInputStream2);
                return new byte[0];
            } catch (IOException | NoSuchAlgorithmException unused) {
                bufferedInputStream = bufferedInputStream2;
                try {
                    a.d("SHA256", "An exception occurred while computing file 'SHA-256'.");
                    c.a((InputStream) bufferedInputStream);
                    return new byte[0];
                } catch (Throwable th) {
                    th = th;
                    c.a((InputStream) bufferedInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = bufferedInputStream2;
                c.a((InputStream) bufferedInputStream);
                throw th;
            }
        } catch (IOException | NoSuchAlgorithmException unused2) {
            a.d("SHA256", "An exception occurred while computing file 'SHA-256'.");
            c.a((InputStream) bufferedInputStream);
            return new byte[0];
        }
    }
}
