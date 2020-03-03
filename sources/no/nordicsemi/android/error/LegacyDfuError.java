package no.nordicsemi.android.error;

import com.taobao.weex.el.parse.Operators;

public final class LegacyDfuError {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3164a = 2;
    public static final int b = 3;
    public static final int c = 4;
    public static final int d = 5;
    public static final int e = 6;

    public static String a(int i) {
        switch (i) {
            case 258:
                return "INVALID STATE";
            case 259:
                return "NOT SUPPORTED";
            case 260:
                return "DATA SIZE EXCEEDS LIMIT";
            case 261:
                return "INVALID CRC ERROR";
            case 262:
                return "OPERATION FAILED";
            default:
                return "UNKNOWN (" + i + Operators.BRACKET_END_STR;
        }
    }
}
