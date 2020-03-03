package com.xiaomi.mishopsdk.util;

import com.adobe.xmp.XMPConst;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.el.parse.Operators;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Log {
    private static boolean DEBUG = false;
    private static final String TAG = "MishopSdkLog";
    private static final boolean WITH_FILE_INFO = false;
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static boolean isDebug() {
        return DEBUG;
    }

    public static void setDebug(boolean z) {
        DEBUG = z;
    }

    public static void d(String str, String str2) {
        if (DEBUG) {
            android.util.Log.d(getFinalTag(str), getFinalLog(str2));
        }
    }

    public static void d(String str, Throwable th) {
        if (DEBUG) {
            d(str, "", (Object) th);
        }
    }

    public static void d(String str, String str2, Object obj) {
        if (DEBUG) {
            android.util.Log.d(getFinalTag(str), getFinalLog(logFormat(str2, obj)));
        }
    }

    public static void d(String str, String str2, Object obj, Object obj2) {
        if (DEBUG) {
            android.util.Log.d(getFinalTag(str), getFinalLog(logFormat(str2, obj, obj2)));
        }
    }

    public static void d(String str, String str2, Object obj, Object obj2, Object obj3) {
        if (DEBUG) {
            android.util.Log.d(getFinalTag(str), getFinalLog(logFormat(str2, obj, obj2, obj3)));
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        if (DEBUG) {
            android.util.Log.d(getFinalTag(str), getFinalLog(logFormat(str2, objArr)));
        }
    }

    public static void w(String str, String str2) {
        if (DEBUG) {
            android.util.Log.w(getFinalTag(str), getFinalLog(str2));
        }
    }

    public static void w(String str, Throwable th) {
        if (DEBUG) {
            w(str, "", (Object) th);
        }
    }

    public static void w(String str, String str2, Object obj) {
        if (DEBUG) {
            android.util.Log.w(getFinalTag(str), getFinalLog(logFormat(str2, obj)));
        }
    }

    public static void w(String str, String str2, Object obj, Object obj2) {
        if (DEBUG) {
            android.util.Log.w(getFinalTag(str), getFinalLog(logFormat(str2, obj, obj2)));
        }
    }

    public static void w(String str, String str2, Object obj, Object obj2, Object obj3) {
        if (DEBUG) {
            android.util.Log.w(getFinalTag(str), getFinalLog(logFormat(str2, obj, obj2, obj3)));
        }
    }

    public static void w(String str, String str2, Object... objArr) {
        if (DEBUG) {
            android.util.Log.w(getFinalTag(str), getFinalLog(logFormat(str2, objArr)));
        }
    }

    public static void e(String str, String str2) {
        if (DEBUG) {
            android.util.Log.e(getFinalTag(str), getFinalLog(str2));
        }
    }

    public static void e(String str, Throwable th) {
        if (DEBUG) {
            e(str, "", (Object) th);
        }
    }

    public static void e(String str, String str2, Object obj) {
        if (DEBUG) {
            android.util.Log.e(getFinalTag(str), getFinalLog(logFormat(str2, obj)));
        }
    }

    public static void e(String str, String str2, Object obj, Object obj2) {
        if (DEBUG) {
            android.util.Log.e(getFinalTag(str), getFinalLog(logFormat(str2, obj, obj2)));
        }
    }

    public static void e(String str, String str2, Object obj, Object obj2, Object obj3) {
        if (DEBUG) {
            android.util.Log.e(getFinalTag(str), getFinalLog(logFormat(str2, obj, obj2, obj3)));
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        if (DEBUG) {
            android.util.Log.e(getFinalTag(str), getFinalLog(logFormat(str2, objArr)));
        }
    }

    public static void v(String str, String str2) {
        if (DEBUG) {
            android.util.Log.v(getFinalTag(str), getFinalLog(str2));
        }
    }

    public static void v(String str, Throwable th) {
        if (DEBUG) {
            v(str, "", (Object) th);
        }
    }

    public static void v(String str, String str2, Object obj) {
        if (DEBUG) {
            android.util.Log.v(getFinalTag(str), getFinalLog(logFormat(str2, obj)));
        }
    }

    public static void v(String str, String str2, Object obj, Object obj2) {
        if (DEBUG) {
            android.util.Log.v(getFinalTag(str), getFinalLog(logFormat(str2, obj, obj2)));
        }
    }

    public static void v(String str, String str2, Object obj, Object obj2, Object obj3) {
        if (DEBUG) {
            android.util.Log.v(getFinalTag(str), getFinalLog(logFormat(str2, obj, obj2, obj3)));
        }
    }

    public static void v(String str, String str2, Object... objArr) {
        if (DEBUG) {
            android.util.Log.v(getFinalTag(str), getFinalLog(logFormat(str2, objArr)));
        }
    }

    public static void i(String str, String str2) {
        if (DEBUG) {
            android.util.Log.i(getFinalTag(str), getFinalLog(str2));
        }
    }

    public static void i(String str, Throwable th) {
        if (DEBUG) {
            i(str, "", (Object) th);
        }
    }

    public static void i(String str, String str2, Object obj) {
        if (DEBUG) {
            android.util.Log.i(getFinalTag(str), getFinalLog(logFormat(str2, obj)));
        }
    }

    public static void i(String str, String str2, Object obj, Object obj2) {
        if (DEBUG) {
            android.util.Log.i(getFinalTag(str), getFinalLog(logFormat(str2, obj, obj2)));
        }
    }

    public static void i(String str, String str2, Object obj, Object obj2, Object obj3) {
        if (DEBUG) {
            android.util.Log.i(getFinalTag(str), getFinalLog(logFormat(str2, obj, obj2, obj3)));
        }
    }

    public static void i(String str, String str2, Object... objArr) {
        if (DEBUG) {
            android.util.Log.i(getFinalTag(str), getFinalLog(logFormat(str2, objArr)));
        }
    }

    private static String getFinalLog(String str) {
        return Operators.ARRAY_START_STR + sDateFormat.format(new Date()) + Operators.ARRAY_END_STR + " " + str;
    }

    private static String getFileLineInfo() {
        StringBuilder sb = new StringBuilder();
        Thread currentThread = Thread.currentThread();
        StackTraceElement[] stackTrace = currentThread.getStackTrace();
        int i = 0;
        while (i < stackTrace.length && !stackTrace[i].getClassName().equals(Log.class.getName())) {
            i++;
        }
        while (i < stackTrace.length && stackTrace[i].getClassName().equals(Log.class.getName())) {
            i++;
        }
        if (i < stackTrace.length) {
            sb.append(Operators.ARRAY_START_STR);
            sb.append(stackTrace[i].getFileName());
            sb.append(Operators.BRACKET_START_STR);
            sb.append(stackTrace[i].getLineNumber());
            sb.append(")]");
        }
        sb.append(Operators.ARRAY_START_STR);
        sb.append(currentThread.getId());
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }

    private static String logFormat(String str, Object... objArr) {
        for (int i = 0; i < objArr.length; i++) {
            if (objArr[i] instanceof String[]) {
                objArr[i] = prettyArray(objArr[i]);
            }
        }
        String str2 = "";
        try {
            str2 = String.format(str, objArr);
        } catch (Exception e) {
            android.util.Log.d(TAG, "log error: the format is \"" + str + "\", the args is: " + Arrays.toString(objArr));
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder(str2);
        if (objArr.length > 0 && (objArr[objArr.length - 1] instanceof Throwable)) {
            sb.append(android.util.Log.getStackTraceString(objArr[objArr.length - 1]));
        }
        return sb.toString();
    }

    private static String prettyArray(String[] strArr) {
        if (strArr.length == 0) {
            return XMPConst.ai;
        }
        StringBuilder sb = new StringBuilder(Operators.ARRAY_START_STR);
        int length = strArr.length - 1;
        for (int i = 0; i < length; i++) {
            sb.append(strArr[i]);
            sb.append(", ");
        }
        sb.append(strArr[length]);
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }

    private static String getFinalTag(String str) {
        return TAG + JSMethod.NOT_SET + str;
    }
}
