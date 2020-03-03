package com.alibaba.android.arouter.utils;

import android.text.TextUtils;
import android.util.Log;
import com.alibaba.android.arouter.facade.template.ILogger;
import com.taobao.weex.el.parse.Operators;

public class DefaultLogger implements ILogger {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f734a = false;
    private static boolean b = false;
    private static boolean c = false;
    private String d = Consts.f733a;

    public void showLog(boolean z) {
        f734a = z;
    }

    public void showStackTrace(boolean z) {
        b = z;
    }

    public void a(boolean z) {
        c = z;
    }

    public DefaultLogger() {
    }

    public DefaultLogger(String str) {
        this.d = str;
    }

    public void debug(String str, String str2) {
        if (f734a) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtils.isEmpty(str)) {
                str = getDefaultTag();
            }
            Log.d(str, str2 + a(stackTraceElement));
        }
    }

    public void info(String str, String str2) {
        if (f734a) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtils.isEmpty(str)) {
                str = getDefaultTag();
            }
            Log.i(str, str2 + a(stackTraceElement));
        }
    }

    public void warning(String str, String str2) {
        if (f734a) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtils.isEmpty(str)) {
                str = getDefaultTag();
            }
            Log.w(str, str2 + a(stackTraceElement));
        }
    }

    public void error(String str, String str2) {
        if (f734a) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtils.isEmpty(str)) {
                str = getDefaultTag();
            }
            Log.e(str, str2 + a(stackTraceElement));
        }
    }

    public void monitor(String str) {
        if (f734a && isMonitorMode()) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            Log.d(this.d + "::monitor", str + a(stackTraceElement));
        }
    }

    public boolean isMonitorMode() {
        return c;
    }

    public String getDefaultTag() {
        return this.d;
    }

    public static String a(StackTraceElement stackTraceElement) {
        StringBuilder sb = new StringBuilder(Operators.ARRAY_START_STR);
        if (b) {
            String name = Thread.currentThread().getName();
            String fileName = stackTraceElement.getFileName();
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            long id = Thread.currentThread().getId();
            int lineNumber = stackTraceElement.getLineNumber();
            sb.append("ThreadId=");
            sb.append(id);
            sb.append(" & ");
            sb.append("ThreadName=");
            sb.append(name);
            sb.append(" & ");
            sb.append("FileName=");
            sb.append(fileName);
            sb.append(" & ");
            sb.append("ClassName=");
            sb.append(className);
            sb.append(" & ");
            sb.append("MethodName=");
            sb.append(methodName);
            sb.append(" & ");
            sb.append("LineNumber=");
            sb.append(lineNumber);
        }
        sb.append(" ] ");
        return sb.toString();
    }
}
