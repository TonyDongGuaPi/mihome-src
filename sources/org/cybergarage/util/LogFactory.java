package org.cybergarage.util;

public class LogFactory {
    private static final String TAG = "all_share";
    private static CommonLog log;

    public static CommonLog createLog() {
        if (log == null) {
            log = new CommonLog();
        }
        log.setTag(TAG);
        return log;
    }

    public static CommonLog createLog(String str) {
        if (log == null) {
            log = new CommonLog();
        }
        if (str == null || str.length() < 1) {
            log.setTag(TAG);
        } else {
            log.setTag(str);
        }
        return log;
    }

    public static CommonLog createNewLog(String str) {
        CommonLog commonLog = new CommonLog();
        commonLog.setTag(str);
        return commonLog;
    }
}
