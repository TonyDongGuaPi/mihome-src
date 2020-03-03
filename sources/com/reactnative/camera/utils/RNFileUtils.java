package com.reactnative.camera.utils;

import android.net.Uri;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class RNFileUtils {
    public static File a(File file) throws IOException {
        if (file.isDirectory() || file.mkdirs()) {
            return file;
        }
        throw new IOException("Couldn't create directory '" + file + "'");
    }

    public static String a(File file, String str) throws IOException {
        a(file);
        String uuid = UUID.randomUUID().toString();
        return file + File.separator + uuid + str;
    }

    public static Uri b(File file) {
        return Uri.fromFile(file);
    }
}
