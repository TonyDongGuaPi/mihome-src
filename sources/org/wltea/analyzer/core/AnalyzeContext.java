package org.wltea.analyzer.core;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.dic.Dictionary;

class AnalyzeContext {

    /* renamed from: a  reason: collision with root package name */
    private static final int f4194a = 4096;
    private static final int b = 100;
    private char[] c = new char[4096];
    private int[] d = new int[4096];
    private int e;
    private int f;
    private int g;
    private Set<String> h = new HashSet();
    private QuickSortSet i = new QuickSortSet();
    private Map<Integer, LexemePath> j = new HashMap();
    private LinkedList<Lexeme> k = new LinkedList<>();
    private Configuration l;

    public AnalyzeContext(Configuration configuration) {
        this.l = configuration;
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public char[] b() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public char c() {
        return this.c[this.f];
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return this.d[this.f];
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public int a(Reader reader) throws IOException {
        int i2;
        int i3;
        if (this.e == 0) {
            i2 = reader.read(this.c);
        } else {
            int i4 = this.g - this.f;
            if (i4 > 0) {
                System.arraycopy(this.c, this.f, this.c, 0, i4);
                i3 = i4;
            } else {
                i3 = 0;
            }
            i2 = reader.read(this.c, i4, 4096 - i4) + i3;
        }
        this.g = i2;
        this.f = 0;
        return i2;
    }

    /* access modifiers changed from: package-private */
    public void f() {
        this.f = 0;
        this.c[this.f] = CharacterUtil.b(this.c[this.f]);
        this.d[this.f] = CharacterUtil.a(this.c[this.f]);
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        if (this.f >= this.g - 1) {
            return false;
        }
        this.f++;
        this.c[this.f] = CharacterUtil.b(this.c[this.f]);
        this.d[this.f] = CharacterUtil.a(this.c[this.f]);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        this.h.add(str);
    }

    /* access modifiers changed from: package-private */
    public void b(String str) {
        this.h.remove(str);
    }

    /* access modifiers changed from: package-private */
    public boolean h() {
        return this.h.size() > 0;
    }

    /* access modifiers changed from: package-private */
    public boolean i() {
        return this.f == this.g - 1;
    }

    /* access modifiers changed from: package-private */
    public boolean j() {
        if (this.g != 4096 || this.f >= this.g - 1 || this.f <= this.g - 100 || h()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void k() {
        this.e += this.f;
    }

    /* access modifiers changed from: package-private */
    public void a(Lexeme lexeme) {
        this.i.d(lexeme);
    }

    /* access modifiers changed from: package-private */
    public void a(LexemePath lexemePath) {
        if (lexemePath != null) {
            this.j.put(Integer.valueOf(lexemePath.b()), lexemePath);
        }
    }

    /* access modifiers changed from: package-private */
    public QuickSortSet l() {
        return this.i;
    }

    /* access modifiers changed from: package-private */
    public void m() {
        int i2 = 0;
        while (i2 <= this.f) {
            if (this.d[i2] == 0) {
                i2++;
            } else {
                LexemePath lexemePath = this.j.get(Integer.valueOf(i2));
                if (lexemePath != null) {
                    Lexeme j2 = lexemePath.j();
                    while (j2 != null) {
                        this.k.add(j2);
                        i2 = j2.b() + j2.e();
                        j2 = lexemePath.j();
                        if (j2 != null) {
                            while (i2 < j2.b()) {
                                a(i2);
                                i2++;
                            }
                        }
                    }
                } else {
                    a(i2);
                    i2++;
                }
            }
        }
        this.j.clear();
    }

    private void a(int i2) {
        if (4 == this.d[i2]) {
            this.k.add(new Lexeme(this.e, i2, 1, 64));
        } else if (8 == this.d[i2]) {
            this.k.add(new Lexeme(this.e, i2, 1, 8));
        }
    }

    /* access modifiers changed from: package-private */
    public Lexeme n() {
        Lexeme pollFirst = this.k.pollFirst();
        while (true) {
            if (pollFirst != null) {
                b(pollFirst);
                if (!Dictionary.a().c(this.c, pollFirst.b(), pollFirst.e())) {
                    pollFirst.a(String.valueOf(this.c, pollFirst.b(), pollFirst.e()));
                    break;
                }
                pollFirst = this.k.pollFirst();
            } else {
                break;
            }
        }
        return pollFirst;
    }

    /* access modifiers changed from: package-private */
    public void o() {
        this.h.clear();
        this.i = new QuickSortSet();
        this.g = 0;
        this.e = 0;
        this.d = new int[4096];
        this.f = 0;
        this.k.clear();
        this.c = new char[4096];
        this.j.clear();
    }

    private void b(Lexeme lexeme) {
        boolean z;
        if (this.l.a() && !this.k.isEmpty()) {
            boolean z2 = false;
            if (2 == lexeme.g()) {
                Lexeme peekFirst = this.k.peekFirst();
                if (16 == peekFirst.g()) {
                    z = lexeme.a(peekFirst, 16);
                } else {
                    z = 32 == peekFirst.g() ? lexeme.a(peekFirst, 48) : false;
                }
                if (z) {
                    this.k.pollFirst();
                }
            }
            if (16 == lexeme.g() && !this.k.isEmpty()) {
                Lexeme peekFirst2 = this.k.peekFirst();
                if (32 == peekFirst2.g()) {
                    z2 = lexeme.a(peekFirst2, 48);
                }
                if (z2) {
                    this.k.pollFirst();
                }
            }
        }
    }
}
