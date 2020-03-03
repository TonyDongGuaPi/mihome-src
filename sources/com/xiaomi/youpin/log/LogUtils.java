package com.xiaomi.youpin.log;

import android.util.Log;

public class LogUtils {
    public static boolean ENABLE_LOG = false;
    public static LogInterface sLogInterface;

    public static void setEnableLog(boolean z) {
        ENABLE_LOG = z;
    }

    public static void setLog(LogInterface logInterface) {
        sLogInterface = logInterface;
    }

    public static int v(String str, String str2) {
        if (ENABLE_LOG) {
            return Log.v(str, str2);
        }
        if (sLogInterface == null) {
            return 0;
        }
        sLogInterface.v(str, str2);
        return 0;
    }

    public static int v(String str, String str2, Throwable th) {
        if (ENABLE_LOG) {
            return Log.v(str, str2, th);
        }
        if (sLogInterface == null) {
            return 0;
        }
        sLogInterface.v(str, str2, th);
        return 0;
    }

    public static int d(String str, String str2) {
        if (ENABLE_LOG) {
            return Log.d(str, str2);
        }
        if (sLogInterface == null) {
            return 0;
        }
        sLogInterface.d(str, str2);
        return 0;
    }

    public static int d(String str, String str2, Throwable th) {
        if (ENABLE_LOG) {
            return Log.d(str, str2, th);
        }
        if (sLogInterface == null) {
            return 0;
        }
        sLogInterface.d(str, str2, th);
        return 0;
    }

    public static int i(String str, String str2) {
        if (ENABLE_LOG) {
            return Log.i(str, str2);
        }
        if (sLogInterface == null) {
            return 0;
        }
        sLogInterface.i(str, str2);
        return 0;
    }

    public static int i(String str, String str2, Throwable th) {
        if (ENABLE_LOG) {
            return Log.i(str, str2, th);
        }
        if (sLogInterface == null) {
            return 0;
        }
        sLogInterface.i(str, str2, th);
        return 0;
    }

    public static int w(String str, String str2) {
        if (ENABLE_LOG) {
            return Log.w(str, str2);
        }
        if (sLogInterface == null) {
            return 0;
        }
        sLogInterface.w(str, str2);
        return 0;
    }

    public static int w(String str, String str2, Throwable th) {
        if (ENABLE_LOG) {
            return Log.w(str, str2, th);
        }
        if (sLogInterface == null) {
            return 0;
        }
        sLogInterface.w(str, str2, th);
        return 0;
    }

    public static int w(String str, Throwable th) {
        if (ENABLE_LOG) {
            return Log.w(str, th);
        }
        if (sLogInterface == null) {
            return 0;
        }
        sLogInterface.w(str, th);
        return 0;
    }

    public static int e(String str, String str2) {
        if (ENABLE_LOG) {
            return Log.e(str, str2);
        }
        if (sLogInterface == null) {
            return 0;
        }
        sLogInterface.e(str, str2);
        return 0;
    }

    public static int e(String str, String str2, Throwable th) {
        if (ENABLE_LOG) {
            return Log.e(str, str2, th);
        }
        if (sLogInterface == null) {
            return 0;
        }
        sLogInterface.e(str, str2, th);
        return 0;
    }

    private static String handleArgs(Object... objArr) {
        StringBuilder sb = new StringBuilder();
        for (Object append : objArr) {
            sb.append(append);
            sb.append(", ");
        }
        return sb.toString();
    }

    public static int i(String str, Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return 0;
        }
        return i(str, handleArgs(objArr));
    }

    public static int v(String str, Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return 0;
        }
        return v(str, handleArgs(objArr));
    }

    public static int d(String str, Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return 0;
        }
        return d(str, handleArgs(objArr));
    }

    public static int w(String str, Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return 0;
        }
        return w(str, handleArgs(objArr));
    }

    public static int e(String str, Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return 0;
        }
        return e(str, handleArgs(objArr));
    }

    public static void postCatchedException(Throwable th) {
        if (sLogInterface != null) {
            sLogInterface.postCatchedException(th);
        }
    }

    public static String getStackTraceString(Throwable th) {
        return Log.getStackTraceString(th);
    }
}
