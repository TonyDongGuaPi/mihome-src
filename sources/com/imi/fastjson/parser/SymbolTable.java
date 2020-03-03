package com.imi.fastjson.parser;

import com.alibaba.fastjson.JSON;

public class SymbolTable {

    /* renamed from: a  reason: collision with root package name */
    public static final int f6092a = 256;
    public static final int b = 8;
    public static final int c = 1024;
    private final Entry[] d;
    private final String[] e;
    private final char[][] f;
    private final int g;
    private int h;

    public SymbolTable() {
        this(256);
        a("$ref", 0, 4, "$ref".hashCode());
        a(JSON.DEFAULT_TYPE_KEY, 0, 4, "$type".hashCode());
    }

    public SymbolTable(int i) {
        this.h = 0;
        this.g = i - 1;
        this.d = new Entry[i];
        this.e = new String[i];
        this.f = new char[i][];
    }

    public String a(char[] cArr, int i, int i2) {
        return a(cArr, i, i2, b(cArr, i, i2));
    }

    public String a(char[] cArr, int i, int i2, int i3) {
        boolean z;
        boolean z2;
        int i4 = this.g & i3;
        String str = this.e[i4];
        if (str == null) {
            z = true;
        } else if (str.length() == i2) {
            char[] cArr2 = this.f[i4];
            int i5 = 0;
            while (true) {
                if (i5 >= i2) {
                    z = true;
                    break;
                } else if (cArr[i + i5] != cArr2[i5]) {
                    z = false;
                    break;
                } else {
                    i5++;
                }
            }
            if (z) {
                return str;
            }
        } else {
            z = false;
        }
        int i6 = 0;
        for (Entry entry = this.d[i4]; entry != null; entry = entry.e) {
            char[] cArr3 = entry.c;
            if (i2 == cArr3.length && i3 == entry.b) {
                int i7 = 0;
                while (true) {
                    if (i7 >= i2) {
                        z2 = true;
                        break;
                    } else if (cArr[i + i7] != cArr3[i7]) {
                        z2 = false;
                        break;
                    } else {
                        i7++;
                    }
                }
                if (z2) {
                    return entry.f6093a;
                }
                i6++;
            }
        }
        if (i6 >= 8) {
            return new String(cArr, i, i2);
        }
        if (this.h >= 1024) {
            return new String(cArr, i, i2);
        }
        Entry entry2 = new Entry(cArr, i, i2, i3, this.d[i4]);
        this.d[i4] = entry2;
        if (z) {
            this.e[i4] = entry2.f6093a;
            this.f[i4] = entry2.c;
        }
        this.h++;
        return entry2.f6093a;
    }

    public String a(String str, int i, int i2, int i3) {
        boolean z;
        boolean z2;
        int i4 = this.g & i3;
        String str2 = this.e[i4];
        if (str2 == null) {
            z = true;
        } else if (str2.length() == i2) {
            char[] cArr = this.f[i4];
            int i5 = 0;
            while (true) {
                if (i5 >= i2) {
                    z = true;
                    break;
                } else if (str.charAt(i + i5) != cArr[i5]) {
                    z = false;
                    break;
                } else {
                    i5++;
                }
            }
            if (z) {
                return str2;
            }
        } else {
            z = false;
        }
        int i6 = 0;
        for (Entry entry = this.d[i4]; entry != null; entry = entry.e) {
            char[] cArr2 = entry.c;
            if (i2 == cArr2.length && i3 == entry.b) {
                int i7 = 0;
                while (true) {
                    if (i7 >= i2) {
                        z2 = true;
                        break;
                    } else if (str.charAt(i + i7) != cArr2[i7]) {
                        z2 = false;
                        break;
                    } else {
                        i7++;
                    }
                }
                if (z2) {
                    return entry.f6093a;
                }
                i6++;
            }
        }
        if (i6 >= 8) {
            return str.substring(i, i2 + i);
        }
        if (this.h >= 1024) {
            return str.substring(i, i2 + i);
        }
        Entry entry2 = new Entry(str, i, i2, i3, this.d[i4]);
        this.d[i4] = entry2;
        if (z) {
            this.e[i4] = entry2.f6093a;
            this.f[i4] = entry2.c;
        }
        this.h++;
        return entry2.f6093a;
    }

    public int a() {
        return this.h;
    }

    public static final int b(char[] cArr, int i, int i2) {
        int i3 = 0;
        int i4 = i;
        int i5 = 0;
        while (i3 < i2) {
            i5 = (i5 * 31) + cArr[i4];
            i3++;
            i4++;
        }
        return i5;
    }

    protected static final class Entry {

        /* renamed from: a  reason: collision with root package name */
        public final String f6093a;
        public final int b;
        public final char[] c;
        public final byte[] d;
        public Entry e;

        public Entry(char[] cArr, int i, int i2, int i3, Entry entry) {
            this.c = new char[i2];
            System.arraycopy(cArr, i, this.c, 0, i2);
            this.f6093a = new String(this.c).intern();
            this.e = entry;
            this.b = i3;
            this.d = null;
        }

        public Entry(String str, int i, int i2, int i3, Entry entry) {
            this.f6093a = str.substring(i, i2 + i).intern();
            this.c = this.f6093a.toCharArray();
            this.e = entry;
            this.b = i3;
            this.d = null;
        }
    }
}
