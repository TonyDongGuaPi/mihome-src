package com.xiaomi.account.diagnosis.encrypt;

import android.util.Log;
import com.xiaomi.account.diagnosis.log.LogFileAppender;
import com.xiaomi.account.diagnosis.log.LogHeader;
import com.xiaomi.account.diagnosis.log.LogLevel;
import com.xiaomi.passport.utils.PassportEnvEncryptUtils;

class EncryptLogSender {
    public static EncryptLogSender getInstance() {
        return new EncryptLogSender();
    }

    private EncryptLogSender() {
    }

    /* access modifiers changed from: package-private */
    public int printLog(int i, String str, String str2, Throwable th) {
        int println = println(i, str, generateEncryptMessageLine(str2)) + 0;
        return th != null ? println + println(i, str, generateEncryptMessageLine(Log.getStackTraceString(th))) : println;
    }

    private String generateEncryptMessageLine(String str) {
        try {
            PassportEnvEncryptUtils.EncryptResult encrypt = PassportEnvEncryptUtils.encrypt(str);
            return String.format("#&^%s!!%s^&#", new Object[]{encrypt.encryptedKey, encrypt.content});
        } catch (PassportEnvEncryptUtils.EncryptException e) {
            e.printStackTrace();
            return str;
        }
    }

    private static int println(int i, String str, String str2) {
        String format = LogHeader.format(LogLevel.fromInt(i), str);
        LogFileAppender.appendLine(format + str2);
        return Log.println(i, str, str2);
    }
}
