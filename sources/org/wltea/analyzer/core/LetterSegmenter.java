package org.wltea.analyzer.core;

import com.taobao.weex.ui.component.list.template.TemplateDom;
import java.util.Arrays;
import kotlin.text.Typography;

class LetterSegmenter implements ISegmenter {

    /* renamed from: a  reason: collision with root package name */
    static final String f4199a = "LETTER_SEGMENTER";
    private static final char[] b = {'#', Typography.c, '+', '-', '.', TemplateDom.SEPARATOR, '_'};
    private static final char[] c = {',', '.'};
    private int d = -1;
    private int e = -1;
    private int f = -1;
    private int g = -1;
    private int h = -1;
    private int i = -1;

    LetterSegmenter() {
        Arrays.sort(b);
        Arrays.sort(c);
    }

    public void a(AnalyzeContext analyzeContext) {
        boolean z = false;
        boolean z2 = d(analyzeContext) || (c(analyzeContext));
        if (b(analyzeContext) || z2) {
            z = true;
        }
        if (z) {
            analyzeContext.a(f4199a);
        } else {
            analyzeContext.b(f4199a);
        }
    }

    public void a() {
        this.d = -1;
        this.e = -1;
        this.f = -1;
        this.g = -1;
        this.h = -1;
        this.i = -1;
    }

    private boolean b(AnalyzeContext analyzeContext) {
        if (this.d == -1) {
            if (1 == analyzeContext.d() || 2 == analyzeContext.d()) {
                this.d = analyzeContext.a();
                this.e = this.d;
            }
        } else if (1 == analyzeContext.d() || 2 == analyzeContext.d()) {
            this.e = analyzeContext.a();
        } else if (analyzeContext.d() != 0 || !a(analyzeContext.c())) {
            analyzeContext.a(new Lexeme(analyzeContext.e(), this.d, (this.e - this.d) + 1, 3));
            this.d = -1;
            this.e = -1;
        } else {
            this.e = analyzeContext.a();
        }
        if (!(!analyzeContext.i() || this.d == -1 || this.e == -1)) {
            analyzeContext.a(new Lexeme(analyzeContext.e(), this.d, (this.e - this.d) + 1, 3));
            this.d = -1;
            this.e = -1;
        }
        if (this.d == -1 && this.e == -1) {
            return false;
        }
        return true;
    }

    private boolean c(AnalyzeContext analyzeContext) {
        if (this.f == -1) {
            if (2 == analyzeContext.d()) {
                this.f = analyzeContext.a();
                this.g = this.f;
            }
        } else if (2 == analyzeContext.d()) {
            this.g = analyzeContext.a();
        } else {
            analyzeContext.a(new Lexeme(analyzeContext.e(), this.f, (this.g - this.f) + 1, 1));
            this.f = -1;
            this.g = -1;
        }
        if (!(!analyzeContext.i() || this.f == -1 || this.g == -1)) {
            analyzeContext.a(new Lexeme(analyzeContext.e(), this.f, (this.g - this.f) + 1, 1));
            this.f = -1;
            this.g = -1;
        }
        if (this.f == -1 && this.g == -1) {
            return false;
        }
        return true;
    }

    private boolean d(AnalyzeContext analyzeContext) {
        if (this.h == -1) {
            if (1 == analyzeContext.d()) {
                this.h = analyzeContext.a();
                this.i = this.h;
            }
        } else if (1 == analyzeContext.d()) {
            this.i = analyzeContext.a();
        } else if (analyzeContext.d() != 0 || !b(analyzeContext.c())) {
            analyzeContext.a(new Lexeme(analyzeContext.e(), this.h, (this.i - this.h) + 1, 2));
            this.h = -1;
            this.i = -1;
        }
        if (!(!analyzeContext.i() || this.h == -1 || this.i == -1)) {
            analyzeContext.a(new Lexeme(analyzeContext.e(), this.h, (this.i - this.h) + 1, 2));
            this.h = -1;
            this.i = -1;
        }
        if (this.h == -1 && this.i == -1) {
            return false;
        }
        return true;
    }

    private boolean a(char c2) {
        return Arrays.binarySearch(b, c2) >= 0;
    }

    private boolean b(char c2) {
        return Arrays.binarySearch(c, c2) >= 0;
    }
}
