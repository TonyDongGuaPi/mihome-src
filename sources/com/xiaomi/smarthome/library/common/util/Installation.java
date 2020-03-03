package com.xiaomi.smarthome.library.common.util;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

public class Installation {

    /* renamed from: a  reason: collision with root package name */
    private static String f18686a = null;
    private static final String b = "INSTALLATION";

    public static synchronized String a(Context context) {
        String str;
        synchronized (Installation.class) {
            if (f18686a == null) {
                File file = new File(context.getFilesDir(), b);
                try {
                    if (!file.exists()) {
                        b(file);
                    }
                    f18686a = a(file);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            str = f18686a;
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
