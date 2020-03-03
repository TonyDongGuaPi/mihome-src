package com.imi.fastjson.parser;

import com.alibaba.fastjson.parser.JSONLexer;
import com.imi.fastjson.JSON;
import com.imi.fastjson.util.Base64;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public final class JSONScanner extends JSONLexer {
    public final int u;
    public final int v;
    public final int w;
    private final String x;

    private boolean a(char c, char c2, char c3, char c4, char c5, char c6) {
        if (c == '0') {
            if (c2 < '0' || c2 > '9') {
                return false;
            }
        } else if (c == '1') {
            if (c2 < '0' || c2 > '9') {
                return false;
            }
        } else if (c != '2' || c2 < '0' || c2 > '4') {
            return false;
        }
        if (c3 < '0' || c3 > '5') {
            if (!(c3 == '6' && c4 == '0')) {
                return false;
            }
        } else if (c4 < '0' || c4 > '9') {
            return false;
        }
        return (c5 < '0' || c5 > '5') ? c5 == '6' && c6 == '0' : c6 >= '0' && c6 <= '9';
    }

    static boolean a(char c, char c2, char c3, char c4, char c5, char c6, int i, int i2) {
        if ((c != '1' && c != '2') || c2 < '0' || c2 > '9' || c3 < '0' || c3 > '9' || c4 < '0' || c4 > '9') {
            return false;
        }
        if (c5 == '0') {
            if (c6 < '1' || c6 > '9') {
                return false;
            }
        } else if (c5 != '1') {
            return false;
        } else {
            if (!(c6 == '0' || c6 == '1' || c6 == '2')) {
                return false;
            }
        }
        if (i == 48) {
            return i2 >= 49 && i2 <= 57;
        }
        if (i == 49 || i == 50) {
            return i2 >= 48 && i2 <= 57;
        }
        if (i == 51) {
            return i2 == 48 || i2 == 49;
        }
        return false;
    }

    public JSONScanner(String str) {
        this(str, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(String str, int i) {
        this.u = "0000-00-00".length();
        this.v = "0000-00-00T00:00:00".length();
        this.w = "0000-00-00T00:00:00.000".length();
        this.d = i;
        this.x = str;
        this.f = -1;
        n();
        if (this.e == 65279) {
            n();
        }
    }

    public final char c(int i) {
        if (i >= this.x.length()) {
            return JSONLexer.EOI;
        }
        return this.x.charAt(i);
    }

    public final char n() {
        int i = this.f + 1;
        this.f = i;
        char c = c(i);
        this.e = c;
        return c;
    }

    public JSONScanner(char[] cArr, int i) {
        this(cArr, i, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(char[] cArr, int i, int i2) {
        this(new String(cArr, 0, i), i2);
    }

    /* access modifiers changed from: protected */
    public final void a(int i, int i2, char[] cArr) {
        this.x.getChars(i, i2 + i, cArr, 0);
    }

    public final String a(int i, int i2, int i3, SymbolTable symbolTable) {
        return symbolTable.a(this.x, i, i2, i3);
    }

    public byte[] s() {
        return Base64.a(this.x, this.j + 1, this.i);
    }

    /* access modifiers changed from: protected */
    public void a(int i, char[] cArr, int i2, int i3) {
        this.x.getChars(i, i3 + i, cArr, i2);
    }

    public final String z() {
        if (!this.k) {
            return this.x.substring(this.j + 1, this.j + 1 + this.i);
        }
        return new String(this.h, 0, this.i);
    }

    public final String a(int i, int i2) {
        return this.x.substring(i, i2 + i);
    }

    public final String k() {
        char c = c((this.j + this.i) - 1);
        int i = this.i;
        if (c == 'L' || c == 'S' || c == 'B' || c == 'F' || c == 'D') {
            i--;
        }
        return this.x.substring(this.j, this.j + i);
    }

    public boolean I() {
        return b(true);
    }

    public boolean b(boolean z) {
        int i;
        int i2;
        int i3;
        int i4;
        int length = this.x.length() - this.f;
        if (!z && length > 13) {
            char c = c(this.f);
            char c2 = c(this.f + 1);
            char c3 = c(this.f + 2);
            char c4 = c(this.f + 3);
            char c5 = c(this.f + 4);
            char c6 = c(this.f + 5);
            char c7 = c((this.f + length) - 1);
            char c8 = c((this.f + length) - 2);
            if (c == '/' && c2 == 'D' && c3 == 'a' && c4 == 't' && c5 == 'e' && c6 == '(' && c7 == '/' && c8 == ')') {
                int i5 = -1;
                for (int i6 = 6; i6 < length; i6++) {
                    char c9 = c(this.f + i6);
                    if (c9 != '+') {
                        if (c9 < '0' || c9 > '9') {
                            break;
                        }
                    } else {
                        i5 = i6;
                    }
                }
                if (i5 == -1) {
                    return false;
                }
                int i7 = this.f + 6;
                long parseLong = Long.parseLong(a(i7, i5 - i7));
                this.l = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
                this.l.setTimeInMillis(parseLong);
                this.b = 5;
                return true;
            }
        }
        if (length == 8 || length == 14 || length == 17) {
            if (z) {
                return false;
            }
            char c10 = c(this.f);
            char c11 = c(this.f + 1);
            char c12 = c(this.f + 2);
            char c13 = c(this.f + 3);
            char c14 = c(this.f + 4);
            char c15 = c(this.f + 5);
            char c16 = c(this.f + 6);
            char c17 = c(this.f + 7);
            if (!a(c10, c11, c12, c13, c14, c15, (int) c16, (int) c17)) {
                return false;
            }
            a(c10, c11, c12, c13, c14, c15, c16, c17);
            if (length != 8) {
                char c18 = c(this.f + 8);
                char c19 = c(this.f + 9);
                char c20 = c(this.f + 10);
                char c21 = c(this.f + 11);
                char c22 = c(this.f + 12);
                char c23 = c(this.f + 13);
                if (!a(c18, c19, c20, c21, c22, c23)) {
                    return false;
                }
                if (length == 17) {
                    char c24 = c(this.f + 14);
                    char c25 = c(this.f + 15);
                    char c26 = c(this.f + 16);
                    if (c24 < '0' || c24 > '9' || c25 < '0' || c25 > '9' || c26 < '0' || c26 > '9') {
                        return false;
                    }
                    i4 = (t[c24] * 100) + (t[c25] * 10) + t[c26];
                } else {
                    i4 = 0;
                }
                i = (t[c18] * 10) + t[c19];
                i3 = (t[c20] * 10) + t[c21];
                i2 = (t[c22] * 10) + t[c23];
            } else {
                i4 = 0;
                i3 = 0;
                i2 = 0;
                i = 0;
            }
            this.l.set(11, i);
            this.l.set(12, i3);
            this.l.set(13, i2);
            this.l.set(14, i4);
            this.b = 5;
            return true;
        } else if (length < this.u || c(this.f + 4) != '-' || c(this.f + 7) != '-') {
            return false;
        } else {
            char c27 = c(this.f);
            char c28 = c(this.f + 1);
            char c29 = c(this.f + 2);
            char c30 = c(this.f + 3);
            char c31 = c(this.f + 5);
            char c32 = c(this.f + 6);
            char c33 = c(this.f + 8);
            char c34 = c(this.f + 9);
            if (!a(c27, c28, c29, c30, c31, c32, (int) c33, (int) c34)) {
                return false;
            }
            a(c27, c28, c29, c30, c31, c32, c33, c34);
            char c35 = c(this.f + 10);
            if (c35 == 'T' || (c35 == ' ' && !z)) {
                if (length < this.v || c(this.f + 13) != ':' || c(this.f + 16) != ':') {
                    return false;
                }
                char c36 = c(this.f + 11);
                char c37 = c(this.f + 12);
                char c38 = c(this.f + 14);
                char c39 = c(this.f + 15);
                char c40 = c(this.f + 17);
                char c41 = c(this.f + 18);
                if (!a(c36, c37, c38, c39, c40, c41)) {
                    return false;
                }
                int i8 = (t[c36] * 10) + t[c37];
                int i9 = (t[c38] * 10) + t[c39];
                int i10 = (t[c40] * 10) + t[c41];
                this.l.set(11, i8);
                this.l.set(12, i9);
                this.l.set(13, i10);
                if (c(this.f + 19) != '.') {
                    this.l.set(14, 0);
                    int i11 = this.f + 19;
                    this.f = i11;
                    this.e = c(i11);
                    this.b = 5;
                    return true;
                } else if (length < this.w) {
                    return false;
                } else {
                    char c42 = c(this.f + 20);
                    char c43 = c(this.f + 21);
                    char c44 = c(this.f + 22);
                    if (c42 < '0' || c42 > '9' || c43 < '0' || c43 > '9' || c44 < '0' || c44 > '9') {
                        return false;
                    }
                    this.l.set(14, (t[c42] * 100) + (t[c43] * 10) + t[c44]);
                    int i12 = this.f + 23;
                    this.f = i12;
                    this.e = c(i12);
                    this.b = 5;
                    return true;
                }
            } else if (c35 != '\"' && c35 != 26) {
                return false;
            } else {
                this.l.set(11, 0);
                this.l.set(12, 0);
                this.l.set(13, 0);
                this.l.set(14, 0);
                int i13 = this.f + 10;
                this.f = i13;
                this.e = c(i13);
                this.b = 5;
                return true;
            }
        }
    }

    private void a(char c, char c2, char c3, char c4, char c5, char c6, char c7, char c8) {
        this.l = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        int i = (t[c] * 1000) + (t[c2] * 100) + (t[c3] * 10) + t[c4];
        int i2 = (t[c7] * 10) + t[c8];
        this.l.set(1, i);
        this.l.set(2, ((t[c5] * 10) + t[c6]) - 1);
        this.l.set(5, i2);
    }

    public boolean l() {
        if (this.f != this.x.length()) {
            return this.e == 26 && this.f + 1 == this.x.length();
        }
        return true;
    }
}
