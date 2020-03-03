package org.mp4parser.tools;

import com.taobao.weex.el.parse.Operators;

public class CastUtils {
    public static int a(long j) {
        if (j <= 2147483647L && j >= -2147483648L) {
            return (int) j;
        }
        throw new RuntimeException("A cast to int has gone wrong. Please contact the mp4parser discussion group (" + j + Operators.BRACKET_END_STR);
    }
}
