package com.xiaomi.jr.http.encoding;

import com.coloros.mcssdk.c.a;
import com.taobao.weex.el.parse.Operators;

public class PercentEscaper extends UnicodeEscaper {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10827a = "-_.*";
    public static final String b = "-_.!~*'()@:$&,;=";
    public static final String c = "-_.!~*'()@:$,;/?:";
    private static final char[] d = {'+'};
    private static final char[] e = a.f.toCharArray();
    private final boolean f;
    private final boolean[] g;

    public PercentEscaper(String str, boolean z) {
        if (str.matches(".*[0-9A-Za-z].*")) {
            throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
        } else if (z && str.contains(" ")) {
            throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
        } else if (!str.contains(Operators.MOD)) {
            this.f = z;
            this.g = b(str);
        } else {
            throw new IllegalArgumentException("The '%' character cannot be specified as 'safe'");
        }
    }

    private static boolean[] b(String str) {
        char[] charArray = str.toCharArray();
        int i = 122;
        for (char max : charArray) {
            i = Math.max(max, i);
        }
        boolean[] zArr = new boolean[(i + 1)];
        for (int i2 = 48; i2 <= 57; i2++) {
            zArr[i2] = true;
        }
        for (int i3 = 65; i3 <= 90; i3++) {
            zArr[i3] = true;
        }
        for (int i4 = 97; i4 <= 122; i4++) {
            zArr[i4] = true;
        }
        for (char c2 : charArray) {
            zArr[c2] = true;
        }
        return zArr;
    }

    /* access modifiers changed from: protected */
    public int a(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            char charAt = charSequence.charAt(i);
            if (charAt >= this.g.length || !this.g[charAt]) {
                break;
            }
            i++;
        }
        return i;
    }

    public String a(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt >= this.g.length || !this.g[charAt]) {
                return a(str, i);
            }
        }
        return str;
    }

    /* access modifiers changed from: protected */
    public char[] a(int i) {
        if (i < this.g.length && this.g[i]) {
            return null;
        }
        if (i == 32 && this.f) {
            return d;
        }
        if (i <= 127) {
            char[] cArr = new char[3];
            cArr[0] = '%';
            cArr[2] = e[i & 15];
            cArr[1] = e[i >>> 4];
            return cArr;
        } else if (i <= 2047) {
            char[] cArr2 = new char[6];
            cArr2[0] = '%';
            cArr2[3] = '%';
            cArr2[5] = e[i & 15];
            int i2 = i >>> 4;
            cArr2[4] = e[(i2 & 3) | 8];
            int i3 = i2 >>> 2;
            cArr2[2] = e[i3 & 15];
            cArr2[1] = e[(i3 >>> 4) | 12];
            return cArr2;
        } else if (i <= 65535) {
            char[] cArr3 = new char[9];
            cArr3[0] = '%';
            cArr3[1] = 'E';
            cArr3[3] = '%';
            cArr3[6] = '%';
            cArr3[8] = e[i & 15];
            int i4 = i >>> 4;
            cArr3[7] = e[(i4 & 3) | 8];
            int i5 = i4 >>> 2;
            cArr3[5] = e[i5 & 15];
            int i6 = i5 >>> 4;
            cArr3[4] = e[(i6 & 3) | 8];
            cArr3[2] = e[i6 >>> 2];
            return cArr3;
        } else if (i <= 1114111) {
            char[] cArr4 = new char[12];
            cArr4[0] = '%';
            cArr4[1] = 'F';
            cArr4[3] = '%';
            cArr4[6] = '%';
            cArr4[9] = '%';
            cArr4[11] = e[i & 15];
            int i7 = i >>> 4;
            cArr4[10] = e[(i7 & 3) | 8];
            int i8 = i7 >>> 2;
            cArr4[8] = e[i8 & 15];
            int i9 = i8 >>> 4;
            cArr4[7] = e[(i9 & 3) | 8];
            int i10 = i9 >>> 2;
            cArr4[5] = e[i10 & 15];
            int i11 = i10 >>> 4;
            cArr4[4] = e[(i11 & 3) | 8];
            cArr4[2] = e[(i11 >>> 2) & 7];
            return cArr4;
        } else {
            throw new IllegalArgumentException("Invalid unicode character value " + i);
        }
    }
}
