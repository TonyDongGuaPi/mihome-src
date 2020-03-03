package org.wltea.analyzer.core;

import org.wltea.analyzer.core.QuickSortSet;

class LexemePath extends QuickSortSet implements Comparable<LexemePath> {

    /* renamed from: a  reason: collision with root package name */
    private int f4201a = -1;
    private int b = -1;
    private int c = 0;

    LexemePath() {
    }

    /* access modifiers changed from: package-private */
    public boolean a(Lexeme lexeme) {
        if (n()) {
            d(lexeme);
            this.f4201a = lexeme.b();
            this.b = lexeme.b() + lexeme.e();
            this.c += lexeme.e();
            return true;
        } else if (!c(lexeme)) {
            return false;
        } else {
            d(lexeme);
            if (lexeme.b() + lexeme.e() > this.b) {
                this.b = lexeme.b() + lexeme.e();
            }
            this.c = this.b - this.f4201a;
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b(Lexeme lexeme) {
        if (n()) {
            d(lexeme);
            this.f4201a = lexeme.b();
            this.b = lexeme.b() + lexeme.e();
            this.c += lexeme.e();
            return true;
        } else if (c(lexeme)) {
            return false;
        } else {
            d(lexeme);
            this.c += lexeme.e();
            this.f4201a = i().b();
            Lexeme k = k();
            this.b = k.b() + k.e();
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public Lexeme a() {
        Lexeme l = l();
        if (n()) {
            this.f4201a = -1;
            this.b = -1;
            this.c = 0;
        } else {
            this.c -= l.e();
            Lexeme k = k();
            this.b = k.b() + k.e();
        }
        return l;
    }

    /* access modifiers changed from: package-private */
    public boolean c(Lexeme lexeme) {
        if (lexeme.b() < this.f4201a || lexeme.b() >= this.b) {
            return this.f4201a >= lexeme.b() && this.f4201a < lexeme.b() + lexeme.e();
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.f4201a;
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return this.b - this.f4201a;
    }

    /* access modifiers changed from: package-private */
    public int f() {
        QuickSortSet.Cell o = o();
        int i = 1;
        while (o != null && o.c() != null) {
            i *= o.c().e();
            o = o.b();
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public int g() {
        QuickSortSet.Cell o = o();
        int i = 0;
        int i2 = 0;
        while (o != null && o.c() != null) {
            i2++;
            i += o.c().e() * i2;
            o = o.b();
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public LexemePath h() {
        LexemePath lexemePath = new LexemePath();
        lexemePath.f4201a = this.f4201a;
        lexemePath.b = this.b;
        lexemePath.c = this.c;
        QuickSortSet.Cell o = o();
        while (o != null && o.c() != null) {
            lexemePath.d(o.c());
            o = o.b();
        }
        return lexemePath;
    }

    /* renamed from: a */
    public int compareTo(LexemePath lexemePath) {
        if (this.c > lexemePath.c) {
            return -1;
        }
        if (this.c < lexemePath.c) {
            return 1;
        }
        if (m() < lexemePath.m()) {
            return -1;
        }
        if (m() > lexemePath.m()) {
            return 1;
        }
        if (e() > lexemePath.e()) {
            return -1;
        }
        if (e() < lexemePath.e()) {
            return 1;
        }
        if (this.b > lexemePath.b) {
            return -1;
        }
        if (this.b < lexemePath.b) {
            return 1;
        }
        if (f() > lexemePath.f()) {
            return -1;
        }
        if (f() < lexemePath.f()) {
            return 1;
        }
        if (g() > lexemePath.g()) {
            return -1;
        }
        if (g() < lexemePath.g()) {
            return 1;
        }
        return 0;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("pathBegin  : ");
        stringBuffer.append(this.f4201a);
        stringBuffer.append("\r\n");
        stringBuffer.append("pathEnd  : ");
        stringBuffer.append(this.b);
        stringBuffer.append("\r\n");
        stringBuffer.append("payloadLength  : ");
        stringBuffer.append(this.c);
        stringBuffer.append("\r\n");
        for (QuickSortSet.Cell o = o(); o != null; o = o.b()) {
            stringBuffer.append("lexeme : ");
            stringBuffer.append(o.c());
            stringBuffer.append("\r\n");
        }
        return stringBuffer.toString();
    }
}
