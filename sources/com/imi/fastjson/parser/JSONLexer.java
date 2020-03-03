package com.imi.fastjson.parser;

import com.google.android.exoplayer2.C;
import com.imi.fastjson.JSON;
import com.imi.fastjson.JSONException;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.io.Closeable;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import org.apache.commons.lang.CharUtils;

public abstract class JSONLexer implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    public static final byte f6087a = 26;
    protected static final char[] n = ("\"" + JSON.DEFAULT_TYPE_KEY + "\":\"").toCharArray();
    protected static boolean[] o = new boolean[256];
    protected static final long p = -922337203685477580L;
    protected static final long q = -922337203685477580L;
    protected static final int r = -214748364;
    protected static final int s = -214748364;
    protected static final int[] t = new int[103];
    private static final ThreadLocal<SoftReference<char[]>> u = new ThreadLocal<>();
    protected int b;
    protected int c;
    protected int d = JSON.DEFAULT_PARSER_FEATURE;
    protected char e;
    protected int f;
    protected int g;
    protected char[] h;
    protected int i;
    protected int j;
    protected boolean k;
    protected Calendar l = null;
    protected Keywords m = Keywords.f6089a;

    public static final boolean b(char c2) {
        return c2 == ' ' || c2 == 10 || c2 == 13 || c2 == 9 || c2 == 12 || c2 == 8;
    }

    public abstract String a(int i2, int i3, int i4, SymbolTable symbolTable);

    /* access modifiers changed from: protected */
    public abstract void a(int i2, int i3, char[] cArr);

    /* access modifiers changed from: protected */
    public abstract void a(int i2, char[] cArr, int i3, int i4);

    public abstract char c(int i2);

    public abstract String k();

    public abstract boolean l();

    public abstract char n();

    public abstract byte[] s();

    public abstract String z();

    static {
        o[32] = true;
        o[10] = true;
        o[13] = true;
        o[9] = true;
        o[12] = true;
        o[8] = true;
        for (int i2 = 48; i2 <= 57; i2++) {
            t[i2] = i2 - 48;
        }
        for (int i3 = 97; i3 <= 102; i3++) {
            t[i3] = (i3 - 97) + 10;
        }
        for (int i4 = 65; i4 <= 70; i4++) {
            t[i4] = (i4 - 65) + 10;
        }
    }

    public JSONLexer() {
        SoftReference softReference = u.get();
        if (softReference != null) {
            this.h = (char[]) softReference.get();
            u.set((Object) null);
        }
        if (this.h == null) {
            this.h = new char[64];
        }
    }

    public final void a() {
        this.i = 0;
        while (true) {
            this.c = this.f;
            if (this.e == '\"') {
                p();
                return;
            } else if (this.e == ',') {
                n();
                this.b = 16;
                return;
            } else if (this.e >= '0' && this.e <= '9') {
                E();
                return;
            } else if (this.e == '-') {
                E();
                return;
            } else {
                switch (this.e) {
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 13:
                    case ' ':
                        n();
                    case '\'':
                        if (a(Feature.AllowSingleQuotes)) {
                            C();
                            return;
                        }
                        throw new JSONException("Feature.AllowSingleQuotes is false");
                    case '(':
                        n();
                        this.b = 10;
                        return;
                    case ')':
                        n();
                        this.b = 11;
                        return;
                    case ':':
                        n();
                        this.b = 17;
                        return;
                    case 'S':
                        D();
                        return;
                    case 'T':
                        v();
                        return;
                    case '[':
                        n();
                        this.b = 14;
                        return;
                    case ']':
                        n();
                        this.b = 15;
                        return;
                    case 'f':
                        x();
                        return;
                    case 'n':
                        w();
                        return;
                    case 't':
                        u();
                        return;
                    case '{':
                        n();
                        this.b = 12;
                        return;
                    case '}':
                        n();
                        this.b = 13;
                        return;
                    default:
                        if (!l()) {
                            this.b = 1;
                            n();
                            return;
                        } else if (this.b != 20) {
                            this.b = 20;
                            int i2 = this.g;
                            this.f = i2;
                            this.c = i2;
                            return;
                        } else {
                            throw new JSONException("EOF error");
                        }
                }
            }
        }
    }

    public final void a(int i2) {
        this.i = 0;
        while (true) {
            if (i2 != 2) {
                if (i2 != 4) {
                    if (i2 != 12) {
                        if (i2 != 18) {
                            if (i2 != 20) {
                                switch (i2) {
                                    case 14:
                                        if (this.e == '[') {
                                            this.b = 14;
                                            n();
                                            return;
                                        } else if (this.e == '{') {
                                            this.b = 12;
                                            n();
                                            return;
                                        }
                                        break;
                                    case 15:
                                        if (this.e == ']') {
                                            this.b = 15;
                                            n();
                                            return;
                                        }
                                        break;
                                    case 16:
                                        if (this.e == ',') {
                                            this.b = 16;
                                            n();
                                            return;
                                        } else if (this.e == '}') {
                                            this.b = 13;
                                            n();
                                            return;
                                        } else if (this.e == ']') {
                                            this.b = 15;
                                            n();
                                            return;
                                        } else if (this.e == 26) {
                                            this.b = 20;
                                            return;
                                        }
                                        break;
                                }
                            }
                            if (this.e == 26) {
                                this.b = 20;
                                return;
                            }
                        } else {
                            b();
                            return;
                        }
                    } else if (this.e == '{') {
                        this.b = 12;
                        n();
                        return;
                    } else if (this.e == '[') {
                        this.b = 14;
                        n();
                        return;
                    }
                } else if (this.e == '\"') {
                    this.c = this.f;
                    p();
                    return;
                } else if (this.e >= '0' && this.e <= '9') {
                    this.c = this.f;
                    E();
                    return;
                } else if (this.e == '[') {
                    this.b = 14;
                    n();
                    return;
                } else if (this.e == '{') {
                    this.b = 12;
                    n();
                    return;
                }
            } else if (this.e >= '0' && this.e <= '9') {
                this.c = this.f;
                E();
                return;
            } else if (this.e == '\"') {
                this.c = this.f;
                p();
                return;
            } else if (this.e == '[') {
                this.b = 14;
                n();
                return;
            } else if (this.e == '{') {
                this.b = 12;
                n();
                return;
            }
            if (this.e == ' ' || this.e == 10 || this.e == 13 || this.e == 9 || this.e == 12 || this.e == 8) {
                n();
            } else {
                a();
                return;
            }
        }
    }

    public final void b() {
        while (b(this.e)) {
            n();
        }
        if (this.e == '_' || Character.isLetter(this.e)) {
            y();
        } else {
            a();
        }
    }

    public final void c() {
        this.i = 0;
        while (this.e != ':') {
            if (this.e == ' ' || this.e == 10 || this.e == 13 || this.e == 9 || this.e == 12 || this.e == 8) {
                n();
            } else {
                throw new JSONException("not match ':' - " + this.e);
            }
        }
        n();
        a();
    }

    public final int d() {
        return this.b;
    }

    public final String e() {
        return JSONToken.a(this.b);
    }

    public final int f() {
        return this.c;
    }

    public final int g() {
        return this.f;
    }

    public final Number h() throws NumberFormatException {
        char c2;
        boolean z;
        long j2;
        long j3;
        int i2 = this.j;
        int i3 = this.j + this.i;
        char c3 = c(i3 - 1);
        if (c3 == 'B') {
            i3--;
            c2 = 'B';
        } else if (c3 == 'L') {
            i3--;
            c2 = 'L';
        } else if (c3 != 'S') {
            c2 = ' ';
        } else {
            i3--;
            c2 = 'S';
        }
        if (c(this.j) == '-') {
            j2 = Long.MIN_VALUE;
            i2++;
            z = true;
        } else {
            j2 = C.TIME_UNSET;
            z = false;
        }
        long j4 = -922337203685477580L;
        if (i2 < i3) {
            j3 = (long) (-t[c(i2)]);
            i2++;
        } else {
            j3 = 0;
        }
        while (i2 < i3) {
            int i4 = i2 + 1;
            int i5 = t[c(i2)];
            if (j3 < j4) {
                return new BigInteger(k());
            }
            long j5 = j3 * 10;
            long j6 = (long) i5;
            if (j5 < j2 + j6) {
                return new BigInteger(k());
            }
            j3 = j5 - j6;
            i2 = i4;
            j4 = -922337203685477580L;
        }
        if (!z) {
            long j7 = -j3;
            if (j7 > 2147483647L || c2 == 'L') {
                return Long.valueOf(j7);
            }
            if (c2 == 'S') {
                return Short.valueOf((short) ((int) j7));
            }
            if (c2 == 'B') {
                return Byte.valueOf((byte) ((int) j7));
            }
            return Integer.valueOf((int) j7);
        } else if (i2 <= this.j + 1) {
            throw new NumberFormatException(k());
        } else if (j3 < -2147483648L || c2 == 'L') {
            return Long.valueOf(j3);
        } else {
            return Integer.valueOf((int) j3);
        }
    }

    public final void b(int i2) {
        this.i = 0;
        while (this.e != ':') {
            if (b(this.e)) {
                n();
            } else {
                throw new JSONException("not match ':', actual " + this.e);
            }
        }
        n();
        while (true) {
            if (i2 == 2) {
                if (this.e >= '0' && this.e <= '9') {
                    this.c = this.f;
                    E();
                    return;
                } else if (this.e == '\"') {
                    this.c = this.f;
                    p();
                    return;
                }
            } else if (i2 == 4) {
                if (this.e == '\"') {
                    this.c = this.f;
                    p();
                    return;
                } else if (this.e >= '0' && this.e <= '9') {
                    this.c = this.f;
                    E();
                    return;
                }
            } else if (i2 == 12) {
                if (this.e == '{') {
                    this.b = 12;
                    n();
                    return;
                } else if (this.e == '[') {
                    this.b = 14;
                    n();
                    return;
                }
            } else if (i2 == 14) {
                if (this.e == '[') {
                    this.b = 14;
                    n();
                    return;
                } else if (this.e == '{') {
                    this.b = 12;
                    n();
                    return;
                }
            }
            if (b(this.e)) {
                n();
            } else {
                a();
                return;
            }
        }
    }

    public float i() {
        return Float.parseFloat(k());
    }

    public double j() {
        return Double.parseDouble(k());
    }

    public void a(Feature feature, boolean z) {
        this.d = Feature.config(this.d, feature, z);
    }

    public final boolean a(Feature feature) {
        return Feature.isEnabled(this.d, feature);
    }

    public final char m() {
        return this.e;
    }

    public final String a(SymbolTable symbolTable) {
        B();
        if (this.e == '\"') {
            return a(symbolTable, '\"');
        }
        if (this.e == '\'') {
            if (a(Feature.AllowSingleQuotes)) {
                return a(symbolTable, (char) Operators.SINGLE_QUOTE);
            }
            throw new JSONException("syntax error");
        } else if (this.e == '}') {
            n();
            this.b = 13;
            return null;
        } else if (this.e == ',') {
            n();
            this.b = 16;
            return null;
        } else if (this.e == 26) {
            this.b = 20;
            return null;
        } else if (a(Feature.AllowUnQuotedFieldNames)) {
            return b(symbolTable);
        } else {
            throw new JSONException("syntax error");
        }
    }

    public final String a(SymbolTable symbolTable, char c2) {
        this.j = this.f;
        this.i = 0;
        boolean z = false;
        int i2 = 0;
        while (true) {
            int i3 = this.f + 1;
            this.f = i3;
            char c3 = c(i3);
            if (c3 == c2) {
                this.b = 4;
                n();
                if (!z) {
                    return a(this.j + 1, this.i, i2, symbolTable);
                }
                return symbolTable.a(this.h, 0, this.i, i2);
            } else if (c3 == 26) {
                throw new JSONException("unclosed.str");
            } else if (c3 == '\\') {
                if (!z) {
                    if (this.i >= this.h.length) {
                        int length = this.h.length * 2;
                        if (this.i > length) {
                            length = this.i;
                        }
                        char[] cArr = new char[length];
                        System.arraycopy(this.h, 0, cArr, 0, this.h.length);
                        this.h = cArr;
                    }
                    a(this.j + 1, this.h, 0, this.i);
                    z = true;
                }
                int i4 = this.f + 1;
                this.f = i4;
                char c4 = c(i4);
                switch (c4) {
                    case '/':
                        i2 = (i2 * 31) + 47;
                        a((char) IOUtils.f15883a);
                        break;
                    case '0':
                        i2 = (i2 * 31) + c4;
                        a(0);
                        break;
                    case '1':
                        i2 = (i2 * 31) + c4;
                        a(1);
                        break;
                    case '2':
                        i2 = (i2 * 31) + c4;
                        a(2);
                        break;
                    case '3':
                        i2 = (i2 * 31) + c4;
                        a(3);
                        break;
                    case '4':
                        i2 = (i2 * 31) + c4;
                        a(4);
                        break;
                    case '5':
                        i2 = (i2 * 31) + c4;
                        a(5);
                        break;
                    case '6':
                        i2 = (i2 * 31) + c4;
                        a(6);
                        break;
                    case '7':
                        i2 = (i2 * 31) + c4;
                        a(7);
                        break;
                    default:
                        switch (c4) {
                            case 't':
                                i2 = (i2 * 31) + 9;
                                a(9);
                                break;
                            case 'u':
                                int i5 = this.f + 1;
                                this.f = i5;
                                char c5 = c(i5);
                                int i6 = this.f + 1;
                                this.f = i6;
                                char c6 = c(i6);
                                int i7 = this.f + 1;
                                this.f = i7;
                                char c7 = c(i7);
                                int i8 = this.f + 1;
                                this.f = i8;
                                int parseInt = Integer.parseInt(new String(new char[]{c5, c6, c7, c(i8)}), 16);
                                i2 = (i2 * 31) + parseInt;
                                a((char) parseInt);
                                break;
                            case 'v':
                                i2 = (i2 * 31) + 11;
                                a(11);
                                break;
                            default:
                                switch (c4) {
                                    case '\"':
                                        i2 = (i2 * 31) + 34;
                                        a('\"');
                                        break;
                                    case '\'':
                                        i2 = (i2 * 31) + 39;
                                        a((char) Operators.SINGLE_QUOTE);
                                        break;
                                    case 'F':
                                    case 'f':
                                        i2 = (i2 * 31) + 12;
                                        a(12);
                                        break;
                                    case '\\':
                                        i2 = (i2 * 31) + 92;
                                        a((char) IOUtils.b);
                                        break;
                                    case 'b':
                                        i2 = (i2 * 31) + 8;
                                        a(8);
                                        break;
                                    case 'n':
                                        i2 = (i2 * 31) + 10;
                                        a(10);
                                        break;
                                    case 'r':
                                        i2 = (i2 * 31) + 13;
                                        a((char) CharUtils.b);
                                        break;
                                    case 'x':
                                        int i9 = this.f + 1;
                                        this.f = i9;
                                        char c8 = c(i9);
                                        this.e = c8;
                                        int i10 = this.f + 1;
                                        this.f = i10;
                                        char c9 = c(i10);
                                        this.e = c9;
                                        char c10 = (char) ((t[c8] * 16) + t[c9]);
                                        i2 = (i2 * 31) + c10;
                                        a(c10);
                                        break;
                                    default:
                                        this.e = c4;
                                        throw new JSONException("unclosed.str.lit");
                                }
                        }
                }
            } else {
                i2 = (i2 * 31) + c3;
                if (!z) {
                    this.i++;
                } else if (this.i == this.h.length) {
                    a(c3);
                } else {
                    char[] cArr2 = this.h;
                    int i11 = this.i;
                    this.i = i11 + 1;
                    cArr2[i11] = c3;
                }
            }
        }
    }

    public final void o() {
        this.i = 0;
    }

    public final String b(SymbolTable symbolTable) {
        boolean[] zArr = CharTypes.b;
        int i2 = this.e;
        if (this.e >= zArr.length || zArr[i2]) {
            boolean[] zArr2 = CharTypes.c;
            this.j = this.f;
            this.i = 1;
            while (true) {
                int i3 = this.f + 1;
                this.f = i3;
                char c2 = c(i3);
                if (c2 < zArr2.length && !zArr2[c2]) {
                    break;
                }
                i2 = (i2 * 31) + c2;
                this.i++;
            }
            this.e = c(this.f);
            this.b = 18;
            if (this.i == 4 && i2 == 3392903 && c(this.j) == 'n' && c(this.j + 1) == 'u' && c(this.j + 2) == 'l' && c(this.j + 3) == 'l') {
                return null;
            }
            return a(this.j, this.i, i2, symbolTable);
        }
        throw new JSONException("illegal identifier : " + this.e);
    }

    public final void p() {
        this.j = this.f;
        this.k = false;
        while (true) {
            int i2 = this.f + 1;
            this.f = i2;
            char c2 = c(i2);
            if (c2 == '\"') {
                this.b = 4;
                int i3 = this.f + 1;
                this.f = i3;
                this.e = c(i3);
                return;
            } else if (c2 == 26) {
                throw new JSONException("unclosed string : " + c2);
            } else if (c2 == '\\') {
                if (!this.k) {
                    this.k = true;
                    if (this.i >= this.h.length) {
                        int length = this.h.length * 2;
                        if (this.i > length) {
                            length = this.i;
                        }
                        char[] cArr = new char[length];
                        System.arraycopy(this.h, 0, cArr, 0, this.h.length);
                        this.h = cArr;
                    }
                    a(this.j + 1, this.i, this.h);
                }
                int i4 = this.f + 1;
                this.f = i4;
                char c3 = c(i4);
                switch (c3) {
                    case '/':
                        a((char) IOUtils.f15883a);
                        break;
                    case '0':
                        a(0);
                        break;
                    case '1':
                        a(1);
                        break;
                    case '2':
                        a(2);
                        break;
                    case '3':
                        a(3);
                        break;
                    case '4':
                        a(4);
                        break;
                    case '5':
                        a(5);
                        break;
                    case '6':
                        a(6);
                        break;
                    case '7':
                        a(7);
                        break;
                    default:
                        switch (c3) {
                            case 't':
                                a(9);
                                break;
                            case 'u':
                                int i5 = this.f + 1;
                                this.f = i5;
                                char c4 = c(i5);
                                int i6 = this.f + 1;
                                this.f = i6;
                                char c5 = c(i6);
                                int i7 = this.f + 1;
                                this.f = i7;
                                char c6 = c(i7);
                                int i8 = this.f + 1;
                                this.f = i8;
                                a((char) Integer.parseInt(new String(new char[]{c4, c5, c6, c(i8)}), 16));
                                break;
                            case 'v':
                                a(11);
                                break;
                            default:
                                switch (c3) {
                                    case '\"':
                                        a('\"');
                                        break;
                                    case '\'':
                                        a((char) Operators.SINGLE_QUOTE);
                                        break;
                                    case 'F':
                                    case 'f':
                                        a(12);
                                        break;
                                    case '\\':
                                        a((char) IOUtils.b);
                                        break;
                                    case 'b':
                                        a(8);
                                        break;
                                    case 'n':
                                        a(10);
                                        break;
                                    case 'r':
                                        a((char) CharUtils.b);
                                        break;
                                    case 'x':
                                        int i9 = this.f + 1;
                                        this.f = i9;
                                        char c7 = c(i9);
                                        int i10 = this.f + 1;
                                        this.f = i10;
                                        a((char) ((t[c7] * 16) + t[c(i10)]));
                                        break;
                                    default:
                                        this.e = c3;
                                        throw new JSONException("unclosed string : " + c3);
                                }
                        }
                }
            } else if (!this.k) {
                this.i++;
            } else if (this.i == this.h.length) {
                a(c2);
            } else {
                char[] cArr2 = this.h;
                int i11 = this.i;
                this.i = i11 + 1;
                cArr2[i11] = c2;
            }
        }
    }

    public Calendar q() {
        return this.l;
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0037  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int r() {
        /*
            r9 = this;
            int r0 = r9.j
            int r1 = r9.j
            int r2 = r9.i
            int r1 = r1 + r2
            int r2 = r9.j
            char r2 = r9.c(r2)
            r3 = 0
            r4 = 1
            r5 = 45
            if (r2 != r5) goto L_0x001c
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r0 = r0 + 1
            r2 = r0
            r0 = 1
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x0024
        L_0x001c:
            r2 = -2147483647(0xffffffff80000001, float:-1.4E-45)
            r2 = r0
            r0 = 0
            r5 = -2147483647(0xffffffff80000001, float:-1.4E-45)
        L_0x0024:
            r6 = -214748364(0xfffffffff3333334, float:-1.4197688E31)
            if (r2 >= r1) goto L_0x0035
            int[] r3 = t
            int r7 = r2 + 1
            char r2 = r9.c(r2)
            r2 = r3[r2]
            int r3 = -r2
        L_0x0034:
            r2 = r7
        L_0x0035:
            if (r2 >= r1) goto L_0x006d
            int r7 = r2 + 1
            char r2 = r9.c(r2)
            r8 = 76
            if (r2 == r8) goto L_0x006c
            r8 = 83
            if (r2 == r8) goto L_0x006c
            r8 = 66
            if (r2 != r8) goto L_0x004a
            goto L_0x006c
        L_0x004a:
            int[] r8 = t
            r2 = r8[r2]
            if (r3 < r6) goto L_0x0062
            int r3 = r3 * 10
            int r8 = r5 + r2
            if (r3 < r8) goto L_0x0058
            int r3 = r3 - r2
            goto L_0x0034
        L_0x0058:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r9.k()
            r0.<init>(r1)
            throw r0
        L_0x0062:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r9.k()
            r0.<init>(r1)
            throw r0
        L_0x006c:
            r2 = r7
        L_0x006d:
            if (r0 == 0) goto L_0x007f
            int r0 = r9.j
            int r0 = r0 + r4
            if (r2 <= r0) goto L_0x0075
            return r3
        L_0x0075:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r9.k()
            r0.<init>(r1)
            throw r0
        L_0x007f:
            int r0 = -r3
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.imi.fastjson.parser.JSONLexer.r():int");
    }

    public void close() {
        if (this.h.length <= 8192) {
            u.set(new SoftReference(this.h));
        }
        this.h = null;
    }

    public final boolean t() {
        if (this.i == 4 && c(this.j + 1) == '$' && c(this.j + 2) == 'r' && c(this.j + 3) == 'e' && c(this.j + 4) == 'f') {
            return true;
        }
        return false;
    }

    public final void u() {
        if (this.e == 't') {
            n();
            if (this.e == 'r') {
                n();
                if (this.e == 'u') {
                    n();
                    if (this.e == 'e') {
                        n();
                        if (this.e == ' ' || this.e == ',' || this.e == '}' || this.e == ']' || this.e == 10 || this.e == 13 || this.e == 9 || this.e == 26 || this.e == 12 || this.e == 8) {
                            this.b = 6;
                            return;
                        }
                        throw new JSONException("scan true error");
                    }
                    throw new JSONException("error parse true");
                }
                throw new JSONException("error parse true");
            }
            throw new JSONException("error parse true");
        }
        throw new JSONException("error parse true");
    }

    public final void v() {
        if (this.e == 'T') {
            n();
            if (this.e == 'r') {
                n();
                if (this.e == 'e') {
                    n();
                    if (this.e == 'e') {
                        n();
                        if (this.e == 'S') {
                            n();
                            if (this.e == 'e') {
                                n();
                                if (this.e == 't') {
                                    n();
                                    if (this.e == ' ' || this.e == 10 || this.e == 13 || this.e == 9 || this.e == 12 || this.e == 8 || this.e == '[' || this.e == '(') {
                                        this.b = 22;
                                        return;
                                    }
                                    throw new JSONException("scan set error");
                                }
                                throw new JSONException("error parse true");
                            }
                            throw new JSONException("error parse true");
                        }
                        throw new JSONException("error parse true");
                    }
                    throw new JSONException("error parse true");
                }
                throw new JSONException("error parse true");
            }
            throw new JSONException("error parse true");
        }
        throw new JSONException("error parse true");
    }

    public final void w() {
        if (this.e == 'n') {
            n();
            if (this.e == 'u') {
                n();
                if (this.e == 'l') {
                    n();
                    if (this.e == 'l') {
                        n();
                        if (this.e == ' ' || this.e == ',' || this.e == '}' || this.e == ']' || this.e == 10 || this.e == 13 || this.e == 9 || this.e == 26 || this.e == 12 || this.e == 8) {
                            this.b = 8;
                            return;
                        }
                        throw new JSONException("scan true error");
                    }
                    throw new JSONException("error parse true");
                }
                throw new JSONException("error parse true");
            } else if (this.e == 'e') {
                n();
                if (this.e == 'w') {
                    n();
                    if (this.e == ' ' || this.e == ',' || this.e == '}' || this.e == ']' || this.e == 10 || this.e == 13 || this.e == 9 || this.e == 26 || this.e == 12 || this.e == 8) {
                        this.b = 9;
                        return;
                    }
                    throw new JSONException("scan true error");
                }
                throw new JSONException("error parse w");
            } else {
                throw new JSONException("error parse e");
            }
        } else {
            throw new JSONException("error parse null or new");
        }
    }

    public final void x() {
        if (this.e == 'f') {
            n();
            if (this.e == 'a') {
                n();
                if (this.e == 'l') {
                    n();
                    if (this.e == 's') {
                        n();
                        if (this.e == 'e') {
                            n();
                            if (this.e == ' ' || this.e == ',' || this.e == '}' || this.e == ']' || this.e == 10 || this.e == 13 || this.e == 9 || this.e == 26 || this.e == 12 || this.e == 8) {
                                this.b = 7;
                                return;
                            }
                            throw new JSONException("scan false error");
                        }
                        throw new JSONException("error parse false");
                    }
                    throw new JSONException("error parse false");
                }
                throw new JSONException("error parse false");
            }
            throw new JSONException("error parse false");
        }
        throw new JSONException("error parse false");
    }

    public final void y() {
        this.j = this.f - 1;
        this.k = false;
        do {
            this.i++;
            n();
        } while (Character.isLetterOrDigit(this.e));
        Integer a2 = this.m.a(z());
        if (a2 != null) {
            this.b = a2.intValue();
        } else {
            this.b = 18;
        }
    }

    public final boolean A() {
        int i2 = 0;
        while (true) {
            char c2 = c(i2);
            if (c2 == 26) {
                return true;
            }
            if (!b(c2)) {
                return false;
            }
            i2++;
        }
    }

    public final void B() {
        while (o[this.e]) {
            n();
        }
    }

    public final void C() {
        this.j = this.f;
        this.k = false;
        while (true) {
            int i2 = this.f + 1;
            this.f = i2;
            char c2 = c(i2);
            if (c2 == '\'') {
                this.b = 4;
                n();
                return;
            } else if (c2 == 26) {
                throw new JSONException("unclosed single-quote string");
            } else if (c2 == '\\') {
                if (!this.k) {
                    this.k = true;
                    if (this.i > this.h.length) {
                        char[] cArr = new char[(this.i * 2)];
                        System.arraycopy(this.h, 0, cArr, 0, this.h.length);
                        this.h = cArr;
                    }
                    a(this.j + 1, this.i, this.h);
                }
                int i3 = this.f + 1;
                this.f = i3;
                char c3 = c(i3);
                switch (c3) {
                    case '/':
                        a((char) IOUtils.f15883a);
                        break;
                    case '0':
                        a(0);
                        break;
                    case '1':
                        a(1);
                        break;
                    case '2':
                        a(2);
                        break;
                    case '3':
                        a(3);
                        break;
                    case '4':
                        a(4);
                        break;
                    case '5':
                        a(5);
                        break;
                    case '6':
                        a(6);
                        break;
                    case '7':
                        a(7);
                        break;
                    default:
                        switch (c3) {
                            case 't':
                                a(9);
                                break;
                            case 'u':
                                int i4 = this.f + 1;
                                this.f = i4;
                                char c4 = c(i4);
                                int i5 = this.f + 1;
                                this.f = i5;
                                char c5 = c(i5);
                                int i6 = this.f + 1;
                                this.f = i6;
                                char c6 = c(i6);
                                int i7 = this.f + 1;
                                this.f = i7;
                                a((char) Integer.parseInt(new String(new char[]{c4, c5, c6, c(i7)}), 16));
                                break;
                            case 'v':
                                a(11);
                                break;
                            default:
                                switch (c3) {
                                    case '\"':
                                        a('\"');
                                        break;
                                    case '\'':
                                        a((char) Operators.SINGLE_QUOTE);
                                        break;
                                    case 'F':
                                    case 'f':
                                        a(12);
                                        break;
                                    case '\\':
                                        a((char) IOUtils.b);
                                        break;
                                    case 'b':
                                        a(8);
                                        break;
                                    case 'n':
                                        a(10);
                                        break;
                                    case 'r':
                                        a((char) CharUtils.b);
                                        break;
                                    case 'x':
                                        int i8 = this.f + 1;
                                        this.f = i8;
                                        char c7 = c(i8);
                                        int i9 = this.f + 1;
                                        this.f = i9;
                                        a((char) ((t[c7] * 16) + t[c(i9)]));
                                        break;
                                    default:
                                        this.e = c3;
                                        throw new JSONException("unclosed single-quote string");
                                }
                        }
                }
            } else if (!this.k) {
                this.i++;
            } else if (this.i == this.h.length) {
                a(c2);
            } else {
                char[] cArr2 = this.h;
                int i10 = this.i;
                this.i = i10 + 1;
                cArr2[i10] = c2;
            }
        }
    }

    public final void D() {
        if (this.e == 'S') {
            n();
            if (this.e == 'e') {
                n();
                if (this.e == 't') {
                    n();
                    if (this.e == ' ' || this.e == 10 || this.e == 13 || this.e == 9 || this.e == 12 || this.e == 8 || this.e == '[' || this.e == '(') {
                        this.b = 21;
                        return;
                    }
                    throw new JSONException("scan set error");
                }
                throw new JSONException("error parse true");
            }
            throw new JSONException("error parse true");
        }
        throw new JSONException("error parse true");
    }

    /* access modifiers changed from: protected */
    public final void a(char c2) {
        if (this.i == this.h.length) {
            char[] cArr = new char[(this.h.length * 2)];
            System.arraycopy(this.h, 0, cArr, 0, this.h.length);
            this.h = cArr;
        }
        char[] cArr2 = this.h;
        int i2 = this.i;
        this.i = i2 + 1;
        cArr2[i2] = c2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00e4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void E() {
        /*
            r9 = this;
            int r0 = r9.f
            r9.j = r0
            char r0 = r9.e
            r1 = 45
            r2 = 1
            if (r0 != r1) goto L_0x0013
            int r0 = r9.i
            int r0 = r0 + r2
            r9.i = r0
            r9.n()
        L_0x0013:
            char r0 = r9.e
            r3 = 57
            r4 = 48
            if (r0 < r4) goto L_0x0028
            char r0 = r9.e
            if (r0 > r3) goto L_0x0028
            int r0 = r9.i
            int r0 = r0 + r2
            r9.i = r0
            r9.n()
            goto L_0x0013
        L_0x0028:
            r0 = 0
            char r5 = r9.e
            r6 = 46
            if (r5 != r6) goto L_0x0049
            int r0 = r9.i
            int r0 = r0 + r2
            r9.i = r0
            r9.n()
        L_0x0037:
            char r0 = r9.e
            if (r0 < r4) goto L_0x0048
            char r0 = r9.e
            if (r0 > r3) goto L_0x0048
            int r0 = r9.i
            int r0 = r0 + r2
            r9.i = r0
            r9.n()
            goto L_0x0037
        L_0x0048:
            r0 = 1
        L_0x0049:
            char r5 = r9.e
            r6 = 76
            if (r5 != r6) goto L_0x0058
            int r1 = r9.i
            int r1 = r1 + r2
            r9.i = r1
            r9.n()
            goto L_0x00a1
        L_0x0058:
            char r5 = r9.e
            r6 = 83
            if (r5 != r6) goto L_0x0067
            int r1 = r9.i
            int r1 = r1 + r2
            r9.i = r1
            r9.n()
            goto L_0x00a1
        L_0x0067:
            char r5 = r9.e
            r6 = 66
            if (r5 != r6) goto L_0x0076
            int r1 = r9.i
            int r1 = r1 + r2
            r9.i = r1
            r9.n()
            goto L_0x00a1
        L_0x0076:
            char r5 = r9.e
            r6 = 70
            if (r5 != r6) goto L_0x0085
            int r0 = r9.i
            int r0 = r0 + r2
            r9.i = r0
            r9.n()
            goto L_0x00de
        L_0x0085:
            char r5 = r9.e
            r7 = 68
            if (r5 != r7) goto L_0x0094
            int r0 = r9.i
            int r0 = r0 + r2
            r9.i = r0
            r9.n()
            goto L_0x00de
        L_0x0094:
            char r5 = r9.e
            r8 = 101(0x65, float:1.42E-43)
            if (r5 == r8) goto L_0x00a3
            char r5 = r9.e
            r8 = 69
            if (r5 != r8) goto L_0x00a1
            goto L_0x00a3
        L_0x00a1:
            r2 = r0
            goto L_0x00de
        L_0x00a3:
            int r0 = r9.i
            int r0 = r0 + r2
            r9.i = r0
            r9.n()
            char r0 = r9.e
            r5 = 43
            if (r0 == r5) goto L_0x00b5
            char r0 = r9.e
            if (r0 != r1) goto L_0x00bd
        L_0x00b5:
            int r0 = r9.i
            int r0 = r0 + r2
            r9.i = r0
            r9.n()
        L_0x00bd:
            char r0 = r9.e
            if (r0 < r4) goto L_0x00ce
            char r0 = r9.e
            if (r0 > r3) goto L_0x00ce
            int r0 = r9.i
            int r0 = r0 + r2
            r9.i = r0
            r9.n()
            goto L_0x00bd
        L_0x00ce:
            char r0 = r9.e
            if (r0 == r7) goto L_0x00d6
            char r0 = r9.e
            if (r0 != r6) goto L_0x00de
        L_0x00d6:
            int r0 = r9.i
            int r0 = r0 + r2
            r9.i = r0
            r9.n()
        L_0x00de:
            if (r2 == 0) goto L_0x00e4
            r0 = 3
            r9.b = r0
            goto L_0x00e7
        L_0x00e4:
            r0 = 2
            r9.b = r0
        L_0x00e7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.imi.fastjson.parser.JSONLexer.E():void");
    }

    public final long F() throws NumberFormatException {
        long j2;
        boolean z;
        long j3;
        int i2;
        int i3 = this.j;
        int i4 = this.j + this.i;
        if (c(this.j) == '-') {
            j2 = Long.MIN_VALUE;
            i3++;
            z = true;
        } else {
            j2 = C.TIME_UNSET;
            z = false;
        }
        if (i3 < i4) {
            j3 = (long) (-t[c(i3)]);
            i3++;
        } else {
            j3 = 0;
        }
        while (true) {
            if (i3 >= i4) {
                break;
            }
            i2 = i3 + 1;
            char c2 = c(i3);
            if (c2 == 'L' || c2 == 'S' || c2 == 'B') {
                i3 = i2;
            } else {
                int i5 = t[c2];
                if (j3 >= -922337203685477580L) {
                    long j4 = j3 * 10;
                    long j5 = (long) i5;
                    if (j4 >= j2 + j5) {
                        j3 = j4 - j5;
                        i3 = i2;
                    } else {
                        throw new NumberFormatException(k());
                    }
                } else {
                    throw new NumberFormatException(k());
                }
            }
        }
        i3 = i2;
        if (!z) {
            return -j3;
        }
        if (i3 > this.j + 1) {
            return j3;
        }
        throw new NumberFormatException(k());
    }

    public final Number a(boolean z) {
        char c2 = c((this.j + this.i) - 1);
        if (c2 == 'F') {
            return Float.valueOf(Float.parseFloat(k()));
        }
        if (c2 == 'D') {
            return Double.valueOf(Double.parseDouble(k()));
        }
        if (z) {
            return G();
        }
        return Double.valueOf(j());
    }

    public final BigDecimal G() {
        return new BigDecimal(k());
    }

    public final Number H() {
        char c2 = c((this.j + this.i) - 1);
        String k2 = k();
        if (c2 == 'D') {
            return Double.valueOf(Double.parseDouble(k2));
        }
        if (c2 != 'F') {
            return new BigDecimal(k2);
        }
        return Float.valueOf(Float.parseFloat(k2));
    }
}
