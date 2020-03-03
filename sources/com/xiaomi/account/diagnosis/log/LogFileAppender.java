package com.xiaomi.account.diagnosis.log;

import com.miui.tsmclient.util.StringUtils;
import com.xiaomi.account.diagnosis.DiagnosisConstants;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogFileAppender {
    private static final ThreadLocal<SimpleDateFormat> sFormat = new ThreadLocal<SimpleDateFormat>() {
        /* access modifiers changed from: protected */
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat(StringUtils.SOURCE_DATE_FORMAT, Locale.US);
        }
    };

    private LogFileAppender() {
    }

    private static File getTodayLogFile() {
        String format = sFormat.get().format(new Date());
        File passportCacheDir = DiagnosisConstants.getPassportCacheDir();
        return new File(passportCacheDir, format + ".log");
    }

    public static void appendLine(String str) {
        File todayLogFile = getTodayLogFile();
        File parentFile = todayLogFile.getParentFile();
        if (parentFile.exists() || parentFile.mkdirs()) {
            BufferedWriter bufferedWriter = null;
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(todayLogFile, true));
                try {
                    bufferedWriter2.append(str);
                    bufferedWriter2.newLine();
                    closeQuietly(bufferedWriter2);
                } catch (IOException e) {
                    e = e;
                    bufferedWriter = bufferedWriter2;
                    try {
                        e.printStackTrace();
                        closeQuietly(bufferedWriter);
                    } catch (Throwable th) {
                        th = th;
                        closeQuietly(bufferedWriter);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedWriter = bufferedWriter2;
                    closeQuietly(bufferedWriter);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                closeQuietly(bufferedWriter);
            }
        }
    }

    private static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
