package com.xiaomi.ai.utils;

import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class o {

    /* renamed from: a  reason: collision with root package name */
    public static volatile a f9959a = new p();
    private static final String b = "com.xiaomi.ai.utils.o";
    private static volatile a c;
    private static volatile int d = 4;

    public interface a {
        void a(int i, String str, String str2);
    }

    public static a a() {
        return c;
    }

    private static String a(Object obj) {
        return obj == null ? "NULL" : obj instanceof Throwable ? a((Throwable) obj) : obj.toString();
    }

    private static String a(Throwable th) {
        String str;
        try {
            str = b(th);
        } catch (IOException e) {
            Log.e("ContentValues", "IOException: ", e);
            str = null;
        }
        return str;
    }

    public static void a(int i) {
        d = i;
    }

    public static void a(int i, String str, Object... objArr) {
        a aVar = c;
        if (aVar != null) {
            StringBuilder sb = new StringBuilder();
            for (Object obj : objArr) {
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                sb.append(a(obj));
            }
            aVar.a(i, str, sb.toString());
        }
    }

    public static void a(a aVar) {
        c = aVar;
    }

    public static void a(String str, Object... objArr) {
        a(d, str, objArr);
    }

    public static a b() {
        return f9959a;
    }

    public static String b(String str, Object... objArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(Operators.ARRAY_START_STR + str + ": ");
        int length = objArr.length;
        int i = 0;
        boolean z = true;
        while (i < length) {
            Object obj = objArr[i];
            if (!z) {
                sb.append(", ");
            }
            sb.append(a(obj));
            i++;
            z = false;
        }
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }

    /* JADX INFO: finally extract failed */
    private static String b(Throwable th) {
        if (th == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            th.printStackTrace(new PrintStream(byteArrayOutputStream));
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toString();
        } catch (Throwable th2) {
            byteArrayOutputStream.close();
            throw th2;
        }
    }

    private static String c() {
        boolean z = false;
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            if (stackTraceElement.getClassName().equals(b)) {
                z = true;
            } else if (z) {
                return String.format("%s::%s@%s:%s, thread:%s", new Object[]{stackTraceElement.getClassName(), stackTraceElement.getMethodName(), stackTraceElement.getFileName(), Integer.valueOf(stackTraceElement.getLineNumber()), Long.valueOf(Thread.currentThread().getId())});
            }
        }
        return "";
    }
}
