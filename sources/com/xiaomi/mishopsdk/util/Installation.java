package com.xiaomi.mishopsdk.util;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

public class Installation {
    private static final String INSTALLATION = "INSTALLATION";
    private static String sID;

    public static synchronized String id(Context context) {
        String str;
        synchronized (Installation.class) {
            if (sID == null) {
                File file = new File(context.getFilesDir(), INSTALLATION);
                try {
                    if (!file.exists()) {
                        writeInstallationFile(file);
                    }
                    sID = readInstallationFile(file);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            str = sID;
        }
        return str;
    }

    private static String readInstallationFile(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        byte[] bArr = new byte[((int) randomAccessFile.length())];
        randomAccessFile.readFully(bArr);
        randomAccessFile.close();
        return new String(bArr, "UTF-8");
    }

    private static void writeInstallationFile(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(UUID.randomUUID().toString().getBytes("UTF-8"));
        fileOutputStream.close();
    }
}
