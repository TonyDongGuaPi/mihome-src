package org.wltea.analyzer.core;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.wltea.analyzer.dic.Dictionary;
import org.wltea.analyzer.dic.Hit;

class CN_QuantifierSegmenter implements ISegmenter {

    /* renamed from: a  reason: collision with root package name */
    static final String f4196a = "QUAN_SEGMENTER";
    private static String b = "一二两三四五六七八九十零壹贰叁肆伍陆柒捌玖拾百千万亿拾佰仟萬億兆卅廿";
    private static Set<Character> c = new HashSet();
    private int d = -1;
    private int e = -1;
    private List<Hit> f = new LinkedList();

    static {
        for (char valueOf : b.toCharArray()) {
            c.add(Character.valueOf(valueOf));
        }
    }

    CN_QuantifierSegmenter() {
    }

    public void a(AnalyzeContext analyzeContext) {
        b(analyzeContext);
        c(analyzeContext);
        if (this.d == -1 && this.e == -1 && this.f.isEmpty()) {
            analyzeContext.b(f4196a);
        } else {
            analyzeContext.a(f4196a);
        }
    }

    public void a() {
        this.d = -1;
        this.e = -1;
        this.f.clear();
    }

    private void b(AnalyzeContext analyzeContext) {
        if (this.d == -1 && this.e == -1) {
            if (4 == analyzeContext.d() && c.contains(Character.valueOf(analyzeContext.c()))) {
                this.d = analyzeContext.a();
                this.e = analyzeContext.a();
            }
        } else if (4 != analyzeContext.d() || !c.contains(Character.valueOf(analyzeContext.c()))) {
            e(analyzeContext);
            this.d = -1;
            this.e = -1;
        } else {
            this.e = analyzeContext.a();
        }
        if (analyzeContext.i() && this.d != -1 && this.e != -1) {
            e(analyzeContext);
            this.d = -1;
            this.e = -1;
        }
    }

    private void c(AnalyzeContext analyzeContext) {
        if (d(analyzeContext)) {
            if (4 == analyzeContext.d()) {
                if (!this.f.isEmpty()) {
                    for (Hit a2 : (Hit[]) this.f.toArray(new Hit[this.f.size()])) {
                        Hit a3 = Dictionary.a().a(analyzeContext.b(), analyzeContext.a(), a2);
                        if (a3.a()) {
                            analyzeContext.a(new Lexeme(analyzeContext.e(), a3.h(), (analyzeContext.a() - a3.h()) + 1, 32));
                            if (!a3.c()) {
                                this.f.remove(a3);
                            }
                        } else if (a3.e()) {
                            this.f.remove(a3);
                        }
                    }
                }
                Hit b2 = Dictionary.a().b(analyzeContext.b(), analyzeContext.a(), 1);
                if (b2.a()) {
                    analyzeContext.a(new Lexeme(analyzeContext.e(), analyzeContext.a(), 1, 32));
                    if (b2.c()) {
                        this.f.add(b2);
                    }
                } else if (b2.c()) {
                    this.f.add(b2);
                }
            } else {
                this.f.clear();
            }
            if (analyzeContext.i()) {
                this.f.clear();
            }
        }
    }

    private boolean d(AnalyzeContext analyzeContext) {
        if ((this.d != -1 && this.e != -1) || !this.f.isEmpty()) {
            return true;
        }
        if (analyzeContext.l().n()) {
            return false;
        }
        Lexeme k = analyzeContext.l().k();
        if ((16 == k.g() || 2 == k.g()) && k.b() + k.e() == analyzeContext.a()) {
            return true;
        }
        return false;
    }

    private void e(AnalyzeContext analyzeContext) {
        if (this.d > -1 && this.e > -1) {
            analyzeContext.a(new Lexeme(analyzeContext.e(), this.d, (this.e - this.d) + 1, 16));
        }
    }
}
