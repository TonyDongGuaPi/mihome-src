package org.xutils.common.util;

import com.google.code.microlog4android.format.PatternFormatter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5 {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f4235a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', PatternFormatter.CATEGORY_CONVERSION_CHAR, PatternFormatter.DATE_CONVERSION_CHAR, 'e', 'f'};

    private MD5() {
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append(f4235a[(b >> 4) & 15]);
            sb.append(f4235a[b & 15]);
        }
        return sb.toString();
    }

    public static String a(File file) throws IOException {
        FileChannel fileChannel;
        FileInputStream fileInputStream = null;
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                fileChannel = fileInputStream2.getChannel();
            } catch (NoSuchAlgorithmException e) {
                e = e;
                fileChannel = null;
                fileInputStream = fileInputStream2;
                try {
                    throw new RuntimeException(e);
                } catch (Throwable th) {
                    th = th;
                    IOUtil.a((Closeable) fileInputStream);
                    IOUtil.a((Closeable) fileChannel);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileChannel = null;
                fileInputStream = fileInputStream2;
                IOUtil.a((Closeable) fileInputStream);
                IOUtil.a((Closeable) fileChannel);
                throw th;
            }
            try {
                instance.update(fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length()));
                byte[] digest = instance.digest();
                IOUtil.a((Closeable) fileInputStream2);
                IOUtil.a((Closeable) fileChannel);
                return a(digest);
            } catch (NoSuchAlgorithmException e2) {
                e = e2;
                fileInputStream = fileInputStream2;
                throw new RuntimeException(e);
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = fileInputStream2;
                IOUtil.a((Closeable) fileInputStream);
                IOUtil.a((Closeable) fileChannel);
                throw th;
            }
        } catch (NoSuchAlgorithmException e3) {
            e = e3;
            fileChannel = null;
            throw new RuntimeException(e);
        } catch (Throwable th4) {
            th = th4;
            fileChannel = null;
            IOUtil.a((Closeable) fileInputStream);
            IOUtil.a((Closeable) fileChannel);
            throw th;
        }
    }

    public static String a(String str) {
        try {
            return a(MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        }
    }
}
