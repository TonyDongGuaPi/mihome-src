package org.wltea.analyzer.core;

import com.facebook.appevents.internal.ViewHierarchyConstants;

public class Lexeme implements Comparable<Lexeme> {

    /* renamed from: a  reason: collision with root package name */
    public static final int f4200a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 64;
    public static final int g = 8;
    public static final int h = 16;
    public static final int i = 32;
    public static final int j = 48;
    private int k;
    private int l;
    private int m;
    private String n;
    private int o;

    public Lexeme(int i2, int i3, int i4, int i5) {
        this.k = i2;
        this.l = i3;
        if (i4 >= 0) {
            this.m = i4;
            this.o = i5;
            return;
        }
        throw new IllegalArgumentException("length < 0");
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Lexeme)) {
            return false;
        }
        Lexeme lexeme = (Lexeme) obj;
        return this.k == lexeme.a() && this.l == lexeme.b() && this.m == lexeme.e();
    }

    public int hashCode() {
        int c2 = c();
        int d2 = d();
        return (c2 * 37) + (d2 * 31) + (((c2 * d2) % e()) * 11);
    }

    /* renamed from: a */
    public int compareTo(Lexeme lexeme) {
        if (this.l < lexeme.b()) {
            return -1;
        }
        if (this.l != lexeme.b()) {
            return 1;
        }
        if (this.m > lexeme.e()) {
            return -1;
        }
        if (this.m == lexeme.e()) {
            return 0;
        }
        return 1;
    }

    public int a() {
        return this.k;
    }

    public void a(int i2) {
        this.k = i2;
    }

    public int b() {
        return this.l;
    }

    public int c() {
        return this.k + this.l;
    }

    public void b(int i2) {
        this.l = i2;
    }

    public int d() {
        return this.k + this.l + this.m;
    }

    public int e() {
        return this.m;
    }

    public void c(int i2) {
        if (this.m >= 0) {
            this.m = i2;
            return;
        }
        throw new IllegalArgumentException("length < 0");
    }

    public String f() {
        if (this.n == null) {
            return "";
        }
        return this.n;
    }

    public void a(String str) {
        if (str == null) {
            this.n = "";
            this.m = 0;
            return;
        }
        this.n = str;
        this.m = str.length();
    }

    public int g() {
        return this.o;
    }

    public void d(int i2) {
        this.o = i2;
    }

    public boolean a(Lexeme lexeme, int i2) {
        if (lexeme == null || d() != lexeme.c()) {
            return false;
        }
        this.m += lexeme.e();
        this.o = i2;
        return true;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(c());
        stringBuffer.append("-");
        stringBuffer.append(d());
        stringBuffer.append(" : ");
        stringBuffer.append(this.n);
        stringBuffer.append(" : \t");
        int i2 = this.o;
        if (i2 == 8) {
            stringBuffer.append("OTHER_CJK");
        } else if (i2 == 16) {
            stringBuffer.append("CN_NUM");
        } else if (i2 == 32) {
            stringBuffer.append("COUNT");
        } else if (i2 == 48) {
            stringBuffer.append("CN_QUAN");
        } else if (i2 != 64) {
            switch (i2) {
                case 0:
                    stringBuffer.append("UNKONW");
                    break;
                case 1:
                    stringBuffer.append(ViewHierarchyConstants.ENGLISH);
                    break;
                case 2:
                    stringBuffer.append("ARABIC");
                    break;
                case 3:
                    stringBuffer.append("LETTER");
                    break;
                case 4:
                    stringBuffer.append("CN_WORD");
                    break;
            }
        } else {
            stringBuffer.append("CN_CHAR");
        }
        return stringBuffer.toString();
    }
}
