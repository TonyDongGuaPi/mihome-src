package com.xiaomi.youpin.util;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

public class Installation {

    /* renamed from: a  reason: collision with root package name */
    private static String f23767a = null;
    private static final String b = "INSTALLATION";

    public static synchronized String a(Context context) {
        String str;
        synchronized (Installation.class) {
            if (f23767a == null) {
                File file = new File(context.getFilesDir(), b);
                try {
                    if (!file.exists()) {
                        b(file);
                    }
                    f23767a = a(file);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            str = f23767a;
        }
        return str;
    }

    private static String a(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        byte[] bArr = new byte[((int) randomAccessFile.length())];
        randomAccessFile.readFully(bArr);
        randomAccessFile.close();
        return new String(bArr);
    }

    private static void b(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(UUID.randomUUID().toString().getBytes());
        fileOutputStream.close();
    }
}
