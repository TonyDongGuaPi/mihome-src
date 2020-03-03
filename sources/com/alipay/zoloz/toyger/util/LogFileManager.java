package com.alipay.zoloz.toyger.util;

import android.os.Environment;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.bio.utils.DateUtil;
import com.alipay.mobile.security.bio.utils.FileUtil;
import com.xiaomi.smarthome.download.Constants;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class LogFileManager {
    private static final String FILE_NAME_FORMAT = "%s_%04d_%s.bin";
    private static int counter;

    private LogFileManager() {
    }

    public static boolean saveFile(String str, byte[] bArr) {
        String format;
        File file;
        boolean z;
        boolean z2 = false;
        try {
            format = String.format(Locale.US, FILE_NAME_FORMAT, new Object[]{DateUtil.getDateFormat(System.currentTimeMillis()), Integer.valueOf(counter / 2), str});
            file = new File(Environment.getExternalStorageDirectory(), "Download");
            if (file.exists()) {
                if (counter == 0) {
                    File[] listFiles = file.listFiles(new a());
                    if (listFiles != null && listFiles.length > 0) {
                        long currentTimeMillis = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(3);
                        for (File file2 : listFiles) {
                            if (file2.lastModified() < currentTimeMillis) {
                                BioLog.i("Delete File : " + file2);
                                if (file2.delete()) {
                                    file2.deleteOnExit();
                                }
                            }
                        }
                    }
                }
                z = true;
            } else {
                z = file.mkdirs();
            }
        } catch (Throwable th) {
            th = th;
            BioLog.w(th);
            return z2;
        }
        if (!z) {
            return false;
        }
        File file3 = new File(file, format);
        boolean save = FileUtil.save(file3, bArr);
        try {
            BioLog.d("Save file : " + file3 + ", bRet=" + save);
            counter = counter + 1;
            return save;
        } catch (Throwable th2) {
            z2 = save;
            th = th2;
        }
    }

    static class a implements FilenameFilter {
        a() {
        }

        public boolean accept(File file, String str) {
            return str.endsWith(Constants.o);
        }
    }
}
