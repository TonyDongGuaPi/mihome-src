package com.mijia.model.local;

import com.Utils.FileUtil;
import com.facebook.cache.disk.DefaultDiskStorage;
import com.xiaomi.CameraDevice;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;

public class LocalFileClear {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f8050a = false;

    public static synchronized void a(final CameraDevice cameraDevice) {
        synchronized (LocalFileClear.class) {
            if (!f8050a) {
                f8050a = true;
                new Thread() {
                    public void run() {
                        LocalFileClear.c(cameraDevice);
                    }
                }.run();
            }
        }
    }

    /* access modifiers changed from: private */
    public static void c(CameraDevice cameraDevice) {
        File[] listFiles = new File(FileUtil.a(cameraDevice.getDid())).listFiles(new FilenameFilter() {
            public boolean accept(File file, String str) {
                return str.endsWith(DefaultDiskStorage.FileType.TEMP) || str.endsWith(".aac") || str.endsWith(".h264") || str.endsWith(".mp4");
            }
        });
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.getAbsolutePath().endsWith(".mp4")) {
                    try {
                        if (a(file) < 10240) {
                            file.delete();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    file.delete();
                }
            }
        }
    }

    private static long a(File file) throws Exception {
        if (!file.exists()) {
            return 0;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        long available = (long) fileInputStream.available();
        fileInputStream.close();
        return available;
    }
}
