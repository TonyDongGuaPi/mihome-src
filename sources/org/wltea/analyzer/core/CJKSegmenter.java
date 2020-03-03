package org.wltea.analyzer.core;

import java.util.LinkedList;
import java.util.List;
import org.wltea.analyzer.dic.Dictionary;
import org.wltea.analyzer.dic.Hit;

class CJKSegmenter implements ISegmenter {

    /* renamed from: a  reason: collision with root package name */
    static final String f4195a = "CJK_SEGMENTER";
    private List<Hit> b = new LinkedList();

    CJKSegmenter() {
    }

    public void a(AnalyzeContext analyzeContext) {
        if (analyzeContext.d() != 0) {
            if (!this.b.isEmpty()) {
                for (Hit a2 : (Hit[]) this.b.toArray(new Hit[this.b.size()])) {
                    Hit a3 = Dictionary.a().a(analyzeContext.b(), analyzeContext.a(), a2);
                    if (a3.a()) {
                        analyzeContext.a(new Lexeme(analyzeContext.e(), a3.h(), (analyzeContext.a() - a3.h()) + 1, 4));
                        if (!a3.c()) {
                            this.b.remove(a3);
                        }
                    } else if (a3.e()) {
                        this.b.remove(a3);
                    }
                }
            }
            Hit a4 = Dictionary.a().a(analyzeContext.b(), analyzeContext.a(), 1);
            if (a4.a()) {
                analyzeContext.a(new Lexeme(analyzeContext.e(), analyzeContext.a(), 1, 4));
                if (a4.c()) {
                    this.b.add(a4);
                }
            } else if (a4.c()) {
                this.b.add(a4);
            }
        } else {
            this.b.clear();
        }
        if (analyzeContext.i()) {
            this.b.clear();
        }
        if (this.b.size() == 0) {
            analyzeContext.b(f4195a);
        } else {
            analyzeContext.a(f4195a);
        }
    }

    public void a() {
        this.b.clear();
    }
}
