package com.xiaomi.push;

import com.xiaomi.channel.commonutils.logger.b;
import java.io.File;
import java.util.HashMap;

public class x {

    /* renamed from: a  reason: collision with root package name */
    private static final HashMap<String, String> f12950a = new HashMap<>();

    static {
        f12950a.put("FFD8FF", "jpg");
        f12950a.put("89504E47", "png");
        f12950a.put("47494638", "gif");
        f12950a.put("474946", "gif");
        f12950a.put("424D", "bmp");
    }

    public static long a(File file) {
        long j = 0;
        try {
            File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                j += listFiles[i].isDirectory() ? a(listFiles[i]) : listFiles[i].length();
            }
        } catch (Exception e) {
            b.a((Throwable) e);
        }
        return j;
    }
}
