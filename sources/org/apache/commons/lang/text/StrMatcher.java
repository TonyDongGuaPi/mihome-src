package org.apache.commons.lang.text;

import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;

public abstract class StrMatcher {

    /* renamed from: a  reason: collision with root package name */
    private static final StrMatcher f3405a = new CharMatcher(',');
    private static final StrMatcher b = new CharMatcher(9);
    private static final StrMatcher c = new CharMatcher(' ');
    private static final StrMatcher d = new CharSetMatcher(" \t\n\r\f".toCharArray());
    private static final StrMatcher e = new TrimMatcher();
    private static final StrMatcher f = new CharMatcher(Operators.SINGLE_QUOTE);
    private static final StrMatcher g = new CharMatcher('\"');
    private static final StrMatcher h = new CharSetMatcher("'\"".toCharArray());
    private static final StrMatcher i = new NoMatcher();

    public abstract int a(char[] cArr, int i2, int i3, int i4);

    public static StrMatcher a() {
        return f3405a;
    }

    public static StrMatcher b() {
        return b;
    }

    public static StrMatcher c() {
        return c;
    }

    public static StrMatcher d() {
        return d;
    }

    public static StrMatcher e() {
        return e;
    }

    public static StrMatcher f() {
        return f;
    }

    public static StrMatcher g() {
        return g;
    }

    public static StrMatcher h() {
        return h;
    }

    public static StrMatcher i() {
        return i;
    }

    public static StrMatcher a(char c2) {
        return new CharMatcher(c2);
    }

    public static StrMatcher a(char[] cArr) {
        if (cArr == null || cArr.length == 0) {
            return i;
        }
        if (cArr.length == 1) {
            return new CharMatcher(cArr[0]);
        }
        return new CharSetMatcher(cArr);
    }

    public static StrMatcher a(String str) {
        if (str == null || str.length() == 0) {
            return i;
        }
        if (str.length() == 1) {
            return new CharMatcher(str.charAt(0));
        }
        return new CharSetMatcher(str.toCharArray());
    }

    public static StrMatcher b(String str) {
        if (str == null || str.length() == 0) {
            return i;
        }
        return new StringMatcher(str);
    }

    protected StrMatcher() {
    }

    public int a(char[] cArr, int i2) {
        return a(cArr, i2, 0, cArr.length);
    }

    static final class CharSetMatcher extends StrMatcher {

        /* renamed from: a  reason: collision with root package name */
        private final char[] f3407a;

        CharSetMatcher(char[] cArr) {
            this.f3407a = (char[]) cArr.clone();
            Arrays.sort(this.f3407a);
        }

        public int a(char[] cArr, int i, int i2, int i3) {
            return Arrays.binarySearch(this.f3407a, cArr[i]) >= 0 ? 1 : 0;
        }
    }

    static final class CharMatcher extends StrMatcher {

        /* renamed from: a  reason: collision with root package name */
        private final char f3406a;

        CharMatcher(char c) {
            this.f3406a = c;
        }

        public int a(char[] cArr, int i, int i2, int i3) {
            return this.f3406a == cArr[i] ? 1 : 0;
        }
    }

    static final class StringMatcher extends StrMatcher {

        /* renamed from: a  reason: collision with root package name */
        private final char[] f3408a;

        StringMatcher(String str) {
            this.f3408a = str.toCharArray();
        }

        public int a(char[] cArr, int i, int i2, int i3) {
            int length = this.f3408a.length;
            if (i + length > i3) {
                return 0;
            }
            int i4 = i;
            int i5 = 0;
            while (i5 < this.f3408a.length) {
                if (this.f3408a[i5] != cArr[i4]) {
                    return 0;
                }
                i5++;
                i4++;
            }
            return length;
        }
    }

    static final class NoMatcher extends StrMatcher {
        public int a(char[] cArr, int i, int i2, int i3) {
            return 0;
        }

        NoMatcher() {
        }
    }

    static final class TrimMatcher extends StrMatcher {
        TrimMatcher() {
        }

        public int a(char[] cArr, int i, int i2, int i3) {
            return cArr[i] <= ' ' ? 1 : 0;
        }
    }
}
