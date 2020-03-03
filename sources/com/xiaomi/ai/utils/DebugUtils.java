package com.xiaomi.ai.utils;

import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class DebugUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f9945a = (Environment.getExternalStorageDirectory() + File.separator + "aisdk");
    public static final String b = (f9945a + File.separator + "debug_on");

    public static final boolean a() {
        return new File(b).exists();
    }

    public static void b() {
        try {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            File dataDirectory = Environment.getDataDirectory();
            if (externalStorageDirectory.canWrite()) {
                File file = new File(dataDirectory, "//data//com.xiaomi.facephoto//databases//gallery.db");
                File file2 = new File(externalStorageDirectory, "gallery.db");
                FileChannel channel = new FileInputStream(file).getChannel();
                FileChannel channel2 = new FileOutputStream(file2).getChannel();
                channel2.transferFrom(channel, 0, channel.size());
                channel.close();
                channel2.close();
            }
        } catch (Exception unused) {
        }
    }
}
