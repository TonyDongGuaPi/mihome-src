package com.xiaomi.jr.http.encoding;

import java.io.IOException;

public abstract class UnicodeEscaper implements Escaper {

    /* renamed from: a  reason: collision with root package name */
    private static final int f10828a = 32;
    private static final ThreadLocal<char[]> b = new ThreadLocal<char[]>() {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public char[] initialValue() {
            return new char[1024];
        }
    };

    /* access modifiers changed from: protected */
    public abstract char[] a(int i);

    /* access modifiers changed from: protected */
    public int a(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            int b2 = b(charSequence, i, i2);
            if (b2 < 0 || a(b2) != null) {
                break;
            }
            i += Character.isSupplementaryCodePoint(b2) ? 2 : 1;
        }
        return i;
    }

    public String a(String str) {
        int length = str.length();
        int a2 = a((CharSequence) str, 0, length);
        return a2 == length ? str : a(str, a2);
    }

    /* access modifiers changed from: protected */
    public final String a(String str, int i) {
        int i2;
        int length = str.length();
        char[] cArr = b.get();
        int i3 = 0;
        int i4 = 0;
        while (i < length) {
            int b2 = b(str, i, length);
            if (b2 >= 0) {
                char[] a2 = a(b2);
                if (a2 != null) {
                    int i5 = i - i3;
                    int i6 = i4 + i5;
                    int length2 = a2.length + i6;
                    if (cArr.length < length2) {
                        cArr = a(cArr, i4, length2 + (length - i) + 32);
                    }
                    if (i5 > 0) {
                        str.getChars(i3, i, cArr, i4);
                        i4 = i6;
                    }
                    if (a2.length > 0) {
                        System.arraycopy(a2, 0, cArr, i4, a2.length);
                        i4 += a2.length;
                    }
                }
                i3 = (Character.isSupplementaryCodePoint(b2) ? 2 : 1) + i;
                i = a((CharSequence) str, i3, length);
            } else {
                throw new IllegalArgumentException("Trailing high surrogate at end of input");
            }
        }
        int i7 = length - i3;
        if (i7 > 0) {
            i2 = i7 + i4;
            if (cArr.length < i2) {
                cArr = a(cArr, i4, i2);
            }
            str.getChars(i3, length, cArr, i4);
        } else {
            i2 = i4;
        }
        return new String(cArr, 0, i2);
    }

    public Appendable a(final Appendable appendable) {
        Preconditions.a(appendable);
        return new Appendable() {

            /* renamed from: a  reason: collision with root package name */
            int f10829a = -1;
            char[] b = new char[2];

            public Appendable append(CharSequence charSequence) throws IOException {
                return append(charSequence, 0, charSequence.length());
            }

            public Appendable append(CharSequence charSequence, int i, int i2) throws IOException {
                int i3;
                if (i < i2) {
                    if (this.f10829a != -1) {
                        int i4 = i + 1;
                        char charAt = charSequence.charAt(i);
                        if (Character.isLowSurrogate(charAt)) {
                            char[] a2 = UnicodeEscaper.this.a(Character.toCodePoint((char) this.f10829a, charAt));
                            if (a2 != null) {
                                a(a2, a2.length);
                                i = i4;
                            } else {
                                appendable.append((char) this.f10829a);
                            }
                            this.f10829a = -1;
                            int i5 = i4;
                            i3 = i;
                            i = i5;
                        } else {
                            throw new IllegalArgumentException("Expected low surrogate character but got " + charAt);
                        }
                    } else {
                        i3 = i;
                    }
                    while (true) {
                        int a3 = UnicodeEscaper.this.a(charSequence, i, i2);
                        if (a3 > i3) {
                            appendable.append(charSequence, i3, a3);
                        }
                        if (a3 == i2) {
                            break;
                        }
                        int b2 = UnicodeEscaper.b(charSequence, a3, i2);
                        if (b2 < 0) {
                            this.f10829a = -b2;
                            break;
                        }
                        char[] a4 = UnicodeEscaper.this.a(b2);
                        if (a4 != null) {
                            a(a4, a4.length);
                        } else {
                            a(this.b, Character.toChars(b2, this.b, 0));
                        }
                        i3 = (Character.isSupplementaryCodePoint(b2) ? 2 : 1) + a3;
                        i = i3;
                    }
                }
                return this;
            }

            public Appendable append(char c2) throws IOException {
                if (this.f10829a != -1) {
                    if (Character.isLowSurrogate(c2)) {
                        char[] a2 = UnicodeEscaper.this.a(Character.toCodePoint((char) this.f10829a, c2));
                        if (a2 != null) {
                            a(a2, a2.length);
                        } else {
                            appendable.append((char) this.f10829a);
                            appendable.append(c2);
                        }
                        this.f10829a = -1;
                    } else {
                        throw new IllegalArgumentException("Expected low surrogate character but got '" + c2 + "' with value " + c2);
                    }
                } else if (Character.isHighSurrogate(c2)) {
                    this.f10829a = c2;
                } else if (!Character.isLowSurrogate(c2)) {
                    char[] a3 = UnicodeEscaper.this.a((int) c2);
                    if (a3 != null) {
                        a(a3, a3.length);
                    } else {
                        appendable.append(c2);
                    }
                } else {
                    throw new IllegalArgumentException("Unexpected low surrogate character '" + c2 + "' with value " + c2);
                }
                return this;
            }

            private void a(char[] cArr, int i) throws IOException {
                for (int i2 = 0; i2 < i; i2++) {
                    appendable.append(cArr[i2]);
                }
            }
        };
    }

    protected static final int b(CharSequence charSequence, int i, int i2) {
        if (i < i2) {
            int i3 = i + 1;
            char charAt = charSequence.charAt(i);
            if (charAt < 55296 || charAt > 57343) {
                return charAt;
            }
            if (charAt > 56319) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected low surrogate character '");
                sb.append(charAt);
                sb.append("' with value ");
                sb.append(charAt);
                sb.append(" at index ");
                sb.append(i3 - 1);
                throw new IllegalArgumentException(sb.toString());
            } else if (i3 == i2) {
                return -charAt;
            } else {
                char charAt2 = charSequence.charAt(i3);
                if (Character.isLowSurrogate(charAt2)) {
                    return Character.toCodePoint(charAt, charAt2);
                }
                throw new IllegalArgumentException("Expected low surrogate but got char '" + charAt2 + "' with value " + charAt2 + " at index " + i3);
            }
        } else {
            throw new IndexOutOfBoundsException("Index exceeds specified range");
        }
    }

    private static final char[] a(char[] cArr, int i, int i2) {
        char[] cArr2 = new char[i2];
        if (i > 0) {
            System.arraycopy(cArr, 0, cArr2, 0, i);
        }
        return cArr2;
    }
}
