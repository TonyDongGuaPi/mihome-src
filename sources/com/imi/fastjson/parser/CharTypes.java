package com.imi.fastjson.parser;

import com.google.code.microlog4android.format.PatternFormatter;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.fastvideo.IOUtils;

public final class CharTypes {

    /* renamed from: a  reason: collision with root package name */
    public static final char[] f6084a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static final boolean[] b = new boolean[256];
    public static final boolean[] c = new boolean[256];
    public static final boolean[] d = new boolean[128];
    public static final boolean[] e = new boolean[128];
    public static final char[] f = new char[128];
    public static final char[] g = {'0', '0', '0', '1', '0', '2', '0', '3', '0', '4', '0', '5', '0', '6', '0', '7', '0', '8', '0', '9', '0', 'A', '0', 'B', '0', 'C', '0', 'D', '0', 'E', '0', 'F', '1', '0', '1', '1', '1', '2', '1', '3', '1', '4', '1', '5', '1', '6', '1', '7', '1', '8', '1', '9', '1', 'A', '1', 'B', '1', 'C', '1', 'D', '1', 'E', '1', 'F', '2', '0', '2', '1', '2', '2', '2', '3', '2', '4', '2', '5', '2', '6', '2', '7', '2', '8', '2', '9', '2', 'A', '2', 'B', '2', 'C', '2', 'D', '2', 'E', '2', 'F'};

    static {
        for (char c2 = 0; c2 < b.length; c2 = (char) (c2 + 1)) {
            if (c2 >= 'A' && c2 <= 'Z') {
                b[c2] = true;
            } else if (c2 >= 'a' && c2 <= 'z') {
                b[c2] = true;
            } else if (c2 == '_') {
                b[c2] = true;
            }
        }
        for (char c3 = 0; c3 < c.length; c3 = (char) (c3 + 1)) {
            if (c3 >= 'A' && c3 <= 'Z') {
                c[c3] = true;
            } else if (c3 >= 'a' && c3 <= 'z') {
                c[c3] = true;
            } else if (c3 == '_') {
                c[c3] = true;
            } else if (c3 >= '0' && c3 <= '9') {
                c[c3] = true;
            }
        }
        d[0] = true;
        d[1] = true;
        d[2] = true;
        d[3] = true;
        d[4] = true;
        d[5] = true;
        d[6] = true;
        d[7] = true;
        d[8] = true;
        d[9] = true;
        d[10] = true;
        d[11] = true;
        d[12] = true;
        d[13] = true;
        d[34] = true;
        d[92] = true;
        e[0] = true;
        e[1] = true;
        e[2] = true;
        e[3] = true;
        e[4] = true;
        e[5] = true;
        e[6] = true;
        e[7] = true;
        e[8] = true;
        e[9] = true;
        e[10] = true;
        e[11] = true;
        e[12] = true;
        e[13] = true;
        e[39] = true;
        e[92] = true;
        f[0] = '0';
        f[1] = '1';
        f[2] = '2';
        f[3] = '3';
        f[4] = '4';
        f[5] = '5';
        f[6] = '6';
        f[7] = '7';
        f[8] = 'b';
        f[9] = PatternFormatter.THREAD_CONVERSION_CHAR;
        f[10] = 'n';
        f[11] = 'v';
        f[12] = 'f';
        f[13] = PatternFormatter.RELATIVE_TIME_CONVERSION_CHAR;
        f[34] = '\"';
        f[39] = Operators.SINGLE_QUOTE;
        f[47] = IOUtils.f15883a;
        f[92] = IOUtils.b;
    }

    public static boolean a(char c2) {
        return c2 < d.length && d[c2];
    }
}
