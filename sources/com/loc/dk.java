package com.loc;

import android.util.Log;
import com.taobao.weex.el.parse.Operators;

final class dk {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f6562a = false;
    private static int b = -1;

    private static String a() {
        if (b == -1) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            int length = stackTrace.length;
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (stackTrace[i].getMethodName().equals("getTraceInfo")) {
                    b = i2 + 1;
                    break;
                } else {
                    i2++;
                    i++;
                }
            }
        }
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[b + 1];
        return stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + " - [" + stackTraceElement.getMethodName() + Operators.ARRAY_END_STR;
    }

    static void a(String str) {
        if (f6562a && str != null) {
            Log.d("HttpDnsSDK", Thread.currentThread().getId() + " - " + a() + " - " + str);
        }
    }

    static void a(Throwable th) {
        if (f6562a) {
            th.printStackTrace();
        }
    }

    static void b(String str) {
        if (f6562a && str != null) {
            Log.e("HttpDnsSDK", Thread.currentThread().getId() + " - " + a() + " - " + str);
        }
    }
}
