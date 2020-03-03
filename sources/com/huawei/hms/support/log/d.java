package com.huawei.hms.support.log;

import android.os.Process;
import android.support.media.ExifInterface;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.text.SimpleDateFormat;
import java.util.Locale;
import miuipub.reflect.Field;

public class d {

    /* renamed from: a  reason: collision with root package name */
    private String f5900a = null;
    private String b = "HMS";
    private int c = 0;
    private long d = 0;
    private long e = 0;
    private String f;
    private int g;
    private int h;
    private int i = 0;
    private final StringBuilder j = new StringBuilder();

    d(int i2, String str, int i3, String str2) {
        this.i = i2;
        this.f5900a = str;
        this.c = i3;
        if (str2 != null) {
            this.b = str2;
        }
        c();
    }

    private d c() {
        this.d = System.currentTimeMillis();
        Thread currentThread = Thread.currentThread();
        this.e = currentThread.getId();
        this.g = Process.myPid();
        StackTraceElement[] stackTrace = currentThread.getStackTrace();
        if (stackTrace.length > this.i) {
            StackTraceElement stackTraceElement = stackTrace[this.i];
            this.f = stackTraceElement.getFileName();
            this.h = stackTraceElement.getLineNumber();
        }
        return this;
    }

    public <T> d a(T t) {
        this.j.append(t);
        return this;
    }

    public d a(Throwable th) {
        a(10).a(Log.getStackTraceString(th));
        return this;
    }

    public String a() {
        StringBuilder sb = new StringBuilder();
        a(sb);
        return sb.toString();
    }

    private StringBuilder a(StringBuilder sb) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());
        sb.append(Operators.ARRAY_START);
        sb.append(simpleDateFormat.format(Long.valueOf(this.d)));
        String a2 = a(this.c);
        sb.append(' ');
        sb.append(a2);
        sb.append(IOUtils.f15883a);
        sb.append(this.b);
        sb.append(IOUtils.f15883a);
        sb.append(this.f5900a);
        sb.append(' ');
        sb.append(this.g);
        sb.append(Operators.CONDITION_IF_MIDDLE);
        sb.append(this.e);
        sb.append(' ');
        sb.append(this.f);
        sb.append(Operators.CONDITION_IF_MIDDLE);
        sb.append(this.h);
        sb.append(Operators.ARRAY_END);
        return sb;
    }

    public static String a(int i2) {
        switch (i2) {
            case 3:
                return Field.h;
            case 4:
                return Field.e;
            case 5:
                return "W";
            case 6:
                return ExifInterface.LONGITUDE_EAST;
            default:
                return String.valueOf(i2);
        }
    }

    public String b() {
        StringBuilder sb = new StringBuilder();
        b(sb);
        return sb.toString();
    }

    private StringBuilder b(StringBuilder sb) {
        sb.append(' ');
        sb.append(this.j.toString());
        return sb;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        a(sb);
        b(sb);
        return sb.toString();
    }
}
