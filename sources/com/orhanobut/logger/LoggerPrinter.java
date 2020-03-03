package com.orhanobut.logger;

import com.mobikwik.sdk.lib.Constants;
import com.taobao.weex.el.parse.Operators;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class LoggerPrinter implements Printer {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8502a = "PRETTYLOGGER";
    private static final int b = 3;
    private static final int c = 6;
    private static final int d = 7;
    private static final int e = 4;
    private static final int f = 2;
    private static final int g = 5;
    private static final int h = 4000;
    private static final int i = 2;
    private static final int j = 3;
    private static final char k = '╔';
    private static final char l = '╚';
    private static final char m = '╟';
    private static final char n = '║';
    private static final String o = "════════════════════════════════════════════";
    private static final String p = "────────────────────────────────────────────";
    private static final String q = "╔════════════════════════════════════════════════════════════════════════════════════════";
    private static final String r = "╚════════════════════════════════════════════════════════════════════════════════════════";
    private static final String s = "╟────────────────────────────────────────────────────────────────────────────────────────";
    private String t;
    private final ThreadLocal<String> u = new ThreadLocal<>();
    private final ThreadLocal<Integer> v = new ThreadLocal<>();
    private final Settings w = new Settings();

    public LoggerPrinter() {
        a(f8502a);
    }

    public Settings a(String str) {
        if (str == null) {
            throw new NullPointerException("tag may not be null");
        } else if (str.trim().length() != 0) {
            this.t = str;
            return this.w;
        } else {
            throw new IllegalStateException("tag may not be empty");
        }
    }

    public Settings a() {
        return this.w;
    }

    public Printer a(String str, int i2) {
        if (str != null) {
            this.u.set(str);
        }
        this.v.set(Integer.valueOf(i2));
        return this;
    }

    public void a(String str, Object... objArr) {
        a(3, (Throwable) null, str, objArr);
    }

    public void a(Object obj) {
        String str;
        if (obj.getClass().isArray()) {
            str = Arrays.deepToString((Object[]) obj);
        } else {
            str = obj.toString();
        }
        a(3, (Throwable) null, str, new Object[0]);
    }

    public void b(String str, Object... objArr) {
        a((Throwable) null, str, objArr);
    }

    public void a(Throwable th, String str, Object... objArr) {
        a(6, th, str, objArr);
    }

    public void c(String str, Object... objArr) {
        a(5, (Throwable) null, str, objArr);
    }

    public void d(String str, Object... objArr) {
        a(4, (Throwable) null, str, objArr);
    }

    public void e(String str, Object... objArr) {
        a(2, (Throwable) null, str, objArr);
    }

    public void f(String str, Object... objArr) {
        a(7, (Throwable) null, str, objArr);
    }

    public void b(String str) {
        if (Helper.a((CharSequence) str)) {
            a((Object) "Empty/Null json content");
            return;
        }
        try {
            String trim = str.trim();
            if (trim.startsWith(Operators.BLOCK_START_STR)) {
                a((Object) new JSONObject(trim).toString(2));
            } else if (trim.startsWith(Operators.ARRAY_START_STR)) {
                a((Object) new JSONArray(trim).toString(2));
            } else {
                b("Invalid Json", new Object[0]);
            }
        } catch (JSONException unused) {
            b("Invalid Json", new Object[0]);
        }
    }

    public void c(String str) {
        if (Helper.a((CharSequence) str)) {
            a((Object) "Empty/Null xml content");
            return;
        }
        try {
            StreamSource streamSource = new StreamSource(new StringReader(str));
            StreamResult streamResult = new StreamResult(new StringWriter());
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty("indent", Constants.YES);
            newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            newTransformer.transform(streamSource, streamResult);
            a((Object) streamResult.getWriter().toString().replaceFirst(">", ">\n"));
        } catch (TransformerException unused) {
            b("Invalid xml", new Object[0]);
        }
    }

    public synchronized void a(int i2, String str, String str2, Throwable th) {
        if (this.w.d() != LogLevel.NONE) {
            if (!(th == null || str2 == null)) {
                str2 = str2 + " : " + Helper.a(th);
            }
            if (th != null && str2 == null) {
                str2 = Helper.a(th);
            }
            if (str2 == null) {
                str2 = "No message/exception is set";
            }
            int d2 = d();
            if (Helper.a((CharSequence) str2)) {
                str2 = "Empty/NULL log message";
            }
            a(i2, str);
            a(i2, str, d2);
            byte[] bytes = str2.getBytes();
            int length = bytes.length;
            if (length <= 4000) {
                if (d2 > 0) {
                    c(i2, str);
                }
                a(i2, str, str2);
                b(i2, str);
                return;
            }
            if (d2 > 0) {
                c(i2, str);
            }
            for (int i3 = 0; i3 < length; i3 += 4000) {
                a(i2, str, new String(bytes, i3, Math.min(length - i3, 4000)));
            }
            b(i2, str);
        }
    }

    public void b() {
        this.w.g();
    }

    private synchronized void a(int i2, Throwable th, String str, Object... objArr) {
        if (this.w.d() != LogLevel.NONE) {
            a(i2, c(), g(str, objArr), th);
        }
    }

    private void a(int i2, String str) {
        b(i2, str, q);
    }

    private void a(int i2, String str, int i3) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (this.w.c()) {
            b(i2, str, "║ Thread: " + Thread.currentThread().getName());
            c(i2, str);
        }
        String str2 = "";
        int a2 = a(stackTrace) + this.w.e();
        if (i3 + a2 > stackTrace.length) {
            i3 = (stackTrace.length - a2) - 1;
        }
        while (i3 > 0) {
            int i4 = i3 + a2;
            if (i4 < stackTrace.length) {
                str2 = str2 + "   ";
                b(i2, str, "║ " + str2 + d(stackTrace[i4].getClassName()) + "." + stackTrace[i4].getMethodName() + " " + " (" + stackTrace[i4].getFileName() + ":" + stackTrace[i4].getLineNumber() + Operators.BRACKET_END_STR);
            }
            i3--;
        }
    }

    private void b(int i2, String str) {
        b(i2, str, r);
    }

    private void c(int i2, String str) {
        b(i2, str, s);
    }

    private void a(int i2, String str, String str2) {
        for (String str3 : str2.split(System.getProperty("line.separator"))) {
            b(i2, str, "║ " + str3);
        }
    }

    private void b(int i2, String str, String str2) {
        String e2 = e(str);
        if (i2 != 2) {
            switch (i2) {
                case 4:
                    this.w.f().d(e2, str2);
                    return;
                case 5:
                    this.w.f().c(e2, str2);
                    return;
                case 6:
                    this.w.f().b(e2, str2);
                    return;
                case 7:
                    this.w.f().f(e2, str2);
                    return;
                default:
                    this.w.f().a(e2, str2);
                    return;
            }
        } else {
            this.w.f().e(e2, str2);
        }
    }

    private String d(String str) {
        return str.substring(str.lastIndexOf(".") + 1);
    }

    private String e(String str) {
        if (Helper.a((CharSequence) str) || Helper.a(this.t, str)) {
            return this.t;
        }
        return this.t + "-" + str;
    }

    private String c() {
        String str = this.u.get();
        if (str == null) {
            return this.t;
        }
        this.u.remove();
        return str;
    }

    private String g(String str, Object... objArr) {
        return (objArr == null || objArr.length == 0) ? str : String.format(str, objArr);
    }

    private int d() {
        Integer num = this.v.get();
        int b2 = this.w.b();
        if (num != null) {
            this.v.remove();
            b2 = num.intValue();
        }
        if (b2 >= 0) {
            return b2;
        }
        throw new IllegalStateException("methodCount cannot be negative");
    }

    private int a(StackTraceElement[] stackTraceElementArr) {
        for (int i2 = 3; i2 < stackTraceElementArr.length; i2++) {
            String className = stackTraceElementArr[i2].getClassName();
            if (!className.equals(LoggerPrinter.class.getName()) && !className.equals(Logger.class.getName())) {
                return i2 - 1;
            }
        }
        return -1;
    }
}
